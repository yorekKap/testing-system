package com.testing.system.dao.web.paging;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import com.testing.system.exceptions.web.pagination.PaginationException;
import com.testing.system.web.paginator.Paginator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.testing.system.web.paginator.PageInfo;
import com.testing.system.web.paginator.RecordsCollection;

@RunWith(MockitoJUnitRunner.class)
public class PaginatorTest {

	RecordsCollection<String> recordsCollection;

	final List<String> records = Arrays.asList("r1", "r2", "r3", "r4", "r5", "r6", "r7");

	Paginator<String> paginator;

	@Before
	public void setUp(){
		recordsCollection = new RecordsCollection<String>() {
			@Override
			public int getNumOfRecords() {
				return records.size();
			}

			@Override
			public List<String> getRecords(int offset, int limit) {
				int lastIndex = offset + limit;
				if(lastIndex > records.size()){
					lastIndex = records.size();
				}

				return records.subList(offset, lastIndex);
			}
		};
	}

	@Test
	public void findPageWithOneRecordPerPage(){
		int recordsPerPage = 1;
		setUpPaginator(recordsPerPage);

		PageInfo<String> page = paginator.findPage(1);

		List<String> expectedRecords = records.subList(0, recordsPerPage);
		int expectedIndex = 1;
		int expectedNumOfPages = records.size();

		assertThat(page.getRecords(), is(expectedRecords));
		assertEquals(expectedIndex, page.getPageIndex());
		assertEquals(expectedNumOfPages, page.getNumOfPages());
	}

	@Test
	public void findPageWithMultipleRecordsPerPage(){
		int recordsPerPage = 3;
		int pageIndex = 2;
		setUpPaginator(recordsPerPage);

		PageInfo<String> page = paginator.findPage(pageIndex);

		List<String> expectedRecords = records.subList(3, 6);
		int expectedIndex = pageIndex;
		int expectedNumOfPages = 3;

		assertThat(page.getRecords(), is(expectedRecords));
		assertEquals(expectedIndex, page.getPageIndex());
		assertEquals(expectedNumOfPages, page.getNumOfPages());
	}

	@Test
	public void findPageWithIncompleteLastPage(){
		int recordsPerPage = 3;
		int pageIndex = 3;
		setUpPaginator(recordsPerPage);

		PageInfo<String> page = paginator.findPage(pageIndex);

		List<String> expectedRecords = records.subList(6, 7);
		int expectedNumOfPages = 3;

		assertThat(page.getRecords(), is(expectedRecords));
		assertEquals(pageIndex, page.getPageIndex());
		assertEquals(expectedNumOfPages, page.getNumOfPages());

	}

	@Test(expected = PaginationException.class)
	public void findPageWithBadIndex(){
		setUpPaginator(1);

		paginator.findPage(10);

	}

	public void setUpPaginator(int numOfRecordsPerPage){
		paginator = new Paginator<>(numOfRecordsPerPage, recordsCollection);

	}

}
