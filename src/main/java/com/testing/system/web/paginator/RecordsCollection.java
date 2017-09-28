package com.testing.system.web.paginator;

import java.util.List;

/**
 * Abstraction for some source of records
 *
 * @author yuri
 *
 * @param <T> - type of record
 */
public interface RecordsCollection<T> {

	List<T> getRecords(int offset, int limit);

	int getNumOfRecords();

}
