package com.testing.system.dao.factories;

import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 * Factory pattern realization for proper creation of DataSource objects
 * from {@link InitialContext}
 *
 * @author yuri
 *
 */
public class DataSourceFactory {
	private static final Logger log = Logger.getLogger(DataSourceFactory.class);

	public static final String MY_SQL = "mysql";

	public static DataSource getDataSource(String dbType){
		InitialContext initContext = null;

		try {
			initContext = new InitialContext();
		} catch (NamingException e) {
			log.error("", e);
			e.printStackTrace();
		}

		if(dbType.equals(MY_SQL)){

			DataSource ds = null;
			try {
				ds = (DataSource) initContext.lookup("java:comp/env/jdbc/testing_system");
				log.info("MySQL DataSource is fetched from JNDI");
			} catch (NamingException e) {
				log.error("", e);
				e.printStackTrace();
			}

			return ds;
		}

		else{
			log.error("Trying to get DataSource of database(" + dbType + "),"
					+ " which is not specified in the factory");
			throw new IllegalArgumentException("No such database template in factory");
		}
	}



}


