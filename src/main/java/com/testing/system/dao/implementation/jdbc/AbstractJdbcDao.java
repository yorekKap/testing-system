package com.testing.system.dao.implementation.jdbc;

import com.testing.system.dao.builder.JdbcQueryBuilder;
import com.testing.system.dao.builder.SelectQuery;
import com.testing.system.dao.interfaces.GenericDao;
import com.testing.system.dao.parsers.ObjectToColumnValueMapParser;
import com.testing.system.entities.Identified;
import com.testing.system.exceptions.dao.MySQLException;
import com.testing.system.utils.DaoUtils;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;


/**
 * Generic JDBC implementation of {@link GenericDao}
 *
 * @author yuri
 * @param <T> entity that DAO operates on, should implement {@link Identified}
 * @param <K> primary key type
 */

/**
 * @param <T>
 * @author yuri
 */
public abstract class AbstractJdbcDao<T extends Identified<Long>> implements GenericDao<T, Long> {

    /**
     * Provide default select query with {@code Join}(if necessary)
     *
     * @param query that should be provided with {@code Join}
     */
    public abstract void provideInnerJoin(SelectQuery query);


    /**
     * Provide default select query with ordering(if necessary)
     *
     * @param query that should be provided with ordering
     */

    public abstract void provideOrdering(SelectQuery query);

    /**
     * Main builder for all queries
     */
    protected JdbcQueryBuilder builder;

    /**
     * Class of the corresponding entity
     */
    protected Class<T> clazz;

    /**
     * Table name for the corresponding entity
     */
    protected String tableName;

    /**
     * Primary key column name for the corresponding entity
     */
    protected String pkColumnName;

    public AbstractJdbcDao(DataSource dataSource, Class<T> clazz) {
        init(dataSource, clazz);
    }

    private void init(DataSource dataSource, Class<T> clazz){
        this.clazz = clazz;
        this.builder = new JdbcQueryBuilder(dataSource);
        this.pkColumnName = DaoUtils.getPKColumnName(clazz);
        this.tableName = DaoUtils.getTableName(clazz);
        this.builder.setTableName(tableName);
    }

    /**
     * Convert entity to values map for it's later
     * insertion in the UPDATE/INSERT queries
     *
     * @param object entity to be converted
     * @return {@link Map} of column name - mappedBy pairs
     */
    public Map<String, Object> getValuesMap(T object){
        return ObjectToColumnValueMapParser.parse(object);
    }

    @Override
    public boolean persist(T object) {
        try {
            builder.beginTransaction();

            builder.insert().addValues(getValuesMap(object)).execute();
            Long id = getLastSavedId();

            builder.commit();

            object.setId(id);
        } catch (MySQLException e) {
            builder.rollback();
            return false;
        }

        return true;
    }

    public Long getLastSavedId(){
        String maxId = "max(" + pkColumnName + ")";
        return builder.select(maxId)
                .executeForSingle(rs -> rs.getLong(1));
    }

    @Override
    public T findByPK(Long pk) {
        SelectQuery query = builder.select("*")
                .where(pkColumnName).isEquals(pk);
        provideInnerJoin(query);
        return query.executeForSingle(clazz);

    }

    @Override
    public List<T> findAll() {
        SelectQuery query = builder.select("*")
                .from(tableName);
        provideInnerJoin(query);
        provideOrdering(query);

        return query.execute(clazz);
    }

    @Override
    public List<T> findAll(int offset, int limit) {
        SelectQuery query = builder.select("*")
                .from(pkColumnName);
        provideInnerJoin(query);
        provideOrdering(query);
        return query.limit(offset, limit).execute(clazz);

    }

    @Override
    public void update(T object) {
        builder.update(tableName)
                .setValuesMap(getValuesMap(object))
                .where(pkColumnName).isEquals(object.getId())
                .execute();
    }

    @Override
    public void delete(T object) {
        delete(object.getId());
    }

    @Override
    public void delete(Long id) {
        builder.delete(clazz, id).execute();
    }

    @Override
    public int getNumOfRecords() {
        return builder.select("count(*)")
                .from(pkColumnName)
                .executeForSingle(rs -> rs.getInt(1));
    }

}
