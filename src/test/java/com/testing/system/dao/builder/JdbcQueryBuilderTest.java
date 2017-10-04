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
	public void select(){
		String expectedQuery = "SELECT * FROM services;";

		String query =	builder.select("*").from("services").toString();

		assertEquals(expectedQuery, query);
	}

	@Test
	public void selectWithWhere(){
		String expectedQuery = "SELECT * FROM admins WHERE id >2.0;";

	    String query =	builder.select("*").from("admins")
	    		       						.where("id")
	    		       						.greater(2)
	    		       						.toString();

	    assertEquals(expectedQuery, query);
	}


	@Test
	public void update(){
		String expectedQuery = " UPDATE services SET description =  ?, title =  ? WHERE id  = 2.0;";

		String query = builder.update("services").set("title", "Change number")
												 .set("description", "Number should be changed")
												 .where("id").isEquals(new Double(2))
												 .toString();
		assertEquals(expectedQuery, query);
	}

	@Test
	public void delete(){
		String expectedQuery = "DELETE FROM services\n" +
				" WHERE title  = 'Change number' AND description LIKE '%changed';";

		String query = builder.delete().from("services")
									   .where("title")
									   .isEquals("Change number")
									   .and("description")
									   .like("%changed")
									   .toString();

		assertEquals(expectedQuery, query);
	}

	@Test
	public void deleteWithInnerQuery(){
		String expectedQuery = "DELETE FROM service_bids\n"+
		" WHERE service_id IN (SELECT max(id) FROM services);";

		SelectQuery sq = builder.select("max(id)").from("services");
		String query =  builder.delete().from("service_bids").where("service_id").in(sq).toString();

		assertEquals(expectedQuery, query);
	}

}
