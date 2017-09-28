package com.testing.system.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.testing.system.web.paginator.PageInfo;

/**
 * Custom JSP tag for convenient page indexes management
 *
 * @author yuri
 *
 */
public class SetListOfPageIndexesTag extends SimpleTagSupport {

	private static final Integer DEFAULT_START_INDEX = 1;
	private static final Integer DEFAULT_NUM_OF_PAGE_INDEXES = 10;

	private PageInfo<?> page;
	private int numOfPageIndexes = DEFAULT_NUM_OF_PAGE_INDEXES;
	private String list;

	public void setPage(PageInfo<?> page){
		this.page = page;
	}

	public void setNumOfPageIndexes(int numOfPageIndexes) {
		this.numOfPageIndexes = numOfPageIndexes;
	}

	public void setList(String list) {
		this.list = list;
	}

	@Override
	public void doTag() throws JspException, IOException {
		validateAttributes();

		List<Integer> indexesList = new ArrayList<>();

		int numOfPages = page.getNumOfPages();
		int index = page.getPageIndex();

		if(numOfPages <= numOfPageIndexes){
			fillIndexesList(DEFAULT_START_INDEX, numOfPages, indexesList);
		}
		else if(index <= (numOfPageIndexes / 2)){
			fillIndexesList(DEFAULT_START_INDEX, numOfPageIndexes, indexesList);
		}
		else if(index >= (numOfPages - (numOfPageIndexes / 2))){
			fillIndexesList((numOfPages + 1 - numOfPageIndexes), numOfPages, indexesList);
		}
		else if ((numOfPageIndexes % 2) == 0) {
			int half = numOfPageIndexes / 2;
			int from = index - (half - 1);
			int to = index + half;
			fillIndexesList(from, to, indexesList);
		} else {
			int half = numOfPageIndexes / 2;
			int from = index - half;
			int to = index + half;
			fillIndexesList(from, to, indexesList);
		}

		getJspContext().setAttribute(list.trim(), indexesList);
	}

	private void validateAttributes()throws JspException {
		if(numOfPageIndexes < 1){
			throw new JspTagException("Number of page indexes can't be lower than 1");
		}
		if(page.getPageIndex() < 1 || page.getPageIndex() > page.getNumOfPages()){
			throw new JspTagException("Bad index: " + page.getPageIndex());
		}

	}

	private void fillIndexesList(int from, int to, List<Integer> indexesList){
		IntStream.rangeClosed(from, to)
				 .forEach(i -> indexesList.add(i));
	}
}

