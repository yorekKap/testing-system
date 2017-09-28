package com.testing.system.web.paginator;

import java.util.List;

import com.testing.system.exceptions.web.pagination.PaginationException;
import org.apache.log4j.Logger;

/**
 * Class for splitting large set of oldentities into pages
 *
 * @author yuri
 *
 * @param <T> - type of oldentities managed by corresponding DAO
 */
public class Paginator<T> {
	private static final Logger log = Logger.getLogger(Paginator.class);

	private int numOfRecordsPerPage;
	private RecordsCollection<T> recordsCollection;

	public Paginator(int numOfRecordsPerPage) {
		this.numOfRecordsPerPage = numOfRecordsPerPage;
	}

	public Paginator(int numOfRecordsPerPage, RecordsCollection<T> recordsCollection) {
		this.numOfRecordsPerPage = numOfRecordsPerPage;
		this.recordsCollection = recordsCollection;
	}

	public void setRecordsCollection(RecordsCollection<T> recordsCollection) {
		this.recordsCollection = recordsCollection;
	}

	/**
	 * Find element from page with {@code pageIndex} index
	 *
	 *
	 * @param pageIndex - index of page
	 * @return {@link List} of oldentities.
	 */
	public PageInfo<T> findPage(int pageIndex){
		if(recordsCollection == null){
			log.error("RecordsCollection is not set");
			throw new PaginationException("RecordsCollection must be set by constructor"
					+ " or using setter method");
		}

		int numOfPages = getNumOfPages(recordsCollection.getNumOfRecords());
		if(pageIndex < 1 || pageIndex > numOfPages){
			String message ="Index is  invalid: " + pageIndex + ";numOfPages: " + numOfPages;
			log.error(message);
			throw new PaginationException(message);
		}

		int offset = (pageIndex - 1) * numOfRecordsPerPage;
		List<T> records = recordsCollection.getRecords(offset, numOfRecordsPerPage);
		if(records.isEmpty()){
			log.warn("No elements from offset: " + offset);
		}

		int numOfRecords = recordsCollection.getNumOfRecords();
		return new PageInfo<T>(records, pageIndex, getNumOfPages(numOfRecords));
	}

	private int getNumOfPages(int numOfRecords){
		int numOfPages = (int)Math.ceil((double)numOfRecords / numOfRecordsPerPage);
		if(numOfPages == 0){
			return 1;
		}
		else{
			return numOfPages;
		}
	}

}
