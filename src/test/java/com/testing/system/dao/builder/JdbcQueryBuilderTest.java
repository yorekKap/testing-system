package com.testing.system.dao.builder;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import javax.sql.DataSource;
import java.sql.Connection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class JdbcQueryBuilderTest {

    static JdbcQueryBuilder builder;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        DataSource mockDataSource = Mockito.mock(DataSource.class);
        Connection mockConnection = Mockito.mock(Connection.class);

        when(mockDataSource.getConnection()).thenReturn(mockConnection);

        builder = new JdbcQueryBuilder(mockDataSource);
    }

    @Test
    public void select() {
        String expectedQuery = "SELECT * FROM tests;";

        String query = builder.select("*").from("tests").toString();

        assertEquals(expectedQuery, query);
    }

    @Test
    public void selectWithWhere() {
        String expectedQuery = "SELECT * FROM tests WHERE id  > ? ;";

        String query = builder.select("*").from("tests")
                .where("id")
                .greater(2)
                .toString();

        assertEquals(expectedQuery, query);
    }


    @Test
    public void update() {
        String expectedQuery = " UPDATE users SET last_name =  ?, first_name =  ? WHERE id  = ? ;";

        String query = builder.update("users")
                .set("first_name", "Vakula")
                .set("last_name", "Koval")
                .where("id").isEquals(new Double(2))
                .toString();

        assertEquals(expectedQuery, query);
    }

    @Test
    public void delete() {
        String expectedQuery = "DELETE FROM users\n" +
                " WHERE user_name  = ?  AND last_name LIKE '%Koval';";

        String query = builder.delete().from("users")
                .where("user_name")
                .isEquals("Vakula")
                .and("last_name")
                .like("%Koval")
                .toString();

        assertEquals(expectedQuery, query);
    }

    @Test
    public void deleteWithInnerQuery() {
        String expectedQuery = "DELETE FROM test_records\n" +
                " WHERE test_id IN (SELECT MAX(id) FROM tests);";

        SelectQuery sq = builder.select("MAX(id)").from("tests");
        String query = builder.delete().from("test_records").where("test_id").in(sq).toString();

        assertEquals(expectedQuery, query);
    }

}
