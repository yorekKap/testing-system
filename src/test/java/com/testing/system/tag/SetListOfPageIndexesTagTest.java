package com.testing.system.tag;

import com.testing.system.web.paginator.PageInfo;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SetListOfPageIndexesTagTest {

	private static final String LIST_ATTRIBUTE_NAME = "records";

	@Mock
	JspContext jspContext;

	@Captor
	ArgumentCaptor<String> attributeNameCaptor;

	@Captor
	ArgumentCaptor<List<Integer>> indexesListCaptor;

	@Rule
	public ExpectedException expected = ExpectedException.none();

	List<String> records;
	PageInfo<String> pageInfo;
	SetListOfPageIndexesTag tag;
	@Before
	public void setUpBeforeMethod(){

		records = Arrays.asList("r1", "r2", "r3", "r4");
		this.tag = new SetListOfPageIndexesTag();
		tag.setJspContext(jspContext);
	}

	@Test
	public void setListOfPageIndexesWithOnePage(){
		int index = 1;
		int numOfPages = 1;
		int numOfPageIndexes = 5;
		pageInfo = new PageInfo<>(new ArrayList<>(), index, numOfPages);

		tag.setNumOfPageIndexes(numOfPageIndexes);
		tag.setPage(pageInfo);
		tag.setList(LIST_ATTRIBUTE_NAME);
		try{
			tag.doTag();
		}catch(Exception e){
			e.printStackTrace();
		}
		verify(jspContext).setAttribute(attributeNameCaptor.capture(), indexesListCaptor.capture());

		assertEquals(LIST_ATTRIBUTE_NAME ,attributeNameCaptor.getValue());
		assertEquals(1, indexesListCaptor.getValue().size());
		assertEquals(1, indexesListCaptor.getValue().get(0).intValue());
	}

	@Test
	public void setListOfPageIndexesWithIndexAtTheStart(){
		int index = 1;
		int numOfPages = 20;
		int numOfPageIndexes = 10;
		pageInfo = new PageInfo<>(new ArrayList<>(), index, numOfPages);

		tag.setNumOfPageIndexes(numOfPageIndexes);
		tag.setPage(pageInfo);
		tag.setList(LIST_ATTRIBUTE_NAME);

		try{
			tag.doTag();
		}catch(Exception e){
			e.printStackTrace();
		}
		verify(jspContext).setAttribute(attributeNameCaptor.capture(), indexesListCaptor.capture());

		assertEquals(LIST_ATTRIBUTE_NAME ,attributeNameCaptor.getValue());
		assertEquals(10, indexesListCaptor.getValue().size());
		assertEquals(1, indexesListCaptor.getValue().get(0).intValue());
		assertEquals(10, indexesListCaptor.getValue().get(9).intValue());
	}

	@Test
	public void setListOfPageIndexesWithIndexInTheMiddle(){
		int index = 10;
		int numOfPages = 20;
		int numOfPageIndexes = 10;
		pageInfo = new PageInfo<>(new ArrayList<>(), index, numOfPages);

		tag.setNumOfPageIndexes(numOfPageIndexes);
		tag.setPage(pageInfo);
		tag.setList(LIST_ATTRIBUTE_NAME);

		try{
			tag.doTag();
		}catch(Exception e){
			e.printStackTrace();
		}
		verify(jspContext).setAttribute(attributeNameCaptor.capture(), indexesListCaptor.capture());

		int expectedStart = index - (numOfPageIndexes / 2) + 1;
		int expectedEnd = index + (numOfPageIndexes / 2);

		assertEquals(LIST_ATTRIBUTE_NAME ,attributeNameCaptor.getValue());
		assertEquals(10, indexesListCaptor.getValue().size());
		assertEquals(expectedStart, indexesListCaptor.getValue().get(0).intValue());
		assertEquals(expectedEnd, indexesListCaptor.getValue().get(9).intValue());
	}

	@Test
	public void setListOfPageIndexesWithIndexAtTheEnd(){
		int index = 18;
		int numOfPages = 20;
		int numOfPageIndexes = 10;
		pageInfo = new PageInfo<>(new ArrayList<>(), index, numOfPages);

		tag.setNumOfPageIndexes(numOfPageIndexes);
		tag.setPage(pageInfo);
		tag.setList(LIST_ATTRIBUTE_NAME);

		try{
			tag.doTag();
		}catch(Exception e){
			e.printStackTrace();
		}
		verify(jspContext).setAttribute(attributeNameCaptor.capture(), indexesListCaptor.capture());

		int expectedStart = numOfPages - numOfPageIndexes + 1;
		int expectedEnd = numOfPages;

		assertEquals(LIST_ATTRIBUTE_NAME ,attributeNameCaptor.getValue());
		assertEquals(10, indexesListCaptor.getValue().size());
		assertEquals(expectedStart, indexesListCaptor.getValue().get(0).intValue());
		assertEquals(expectedEnd, indexesListCaptor.getValue().get(9).intValue());
	}

	@Test
	public void setListOfPageIndexesWithInvalidIndex()throws JspException{
		int index = 21;
		int numOfPages = 20;
		int numOfPageIndexes = 10;
		pageInfo = new PageInfo<>(new ArrayList<>(), index, numOfPages);

		tag.setNumOfPageIndexes(numOfPageIndexes);
		tag.setPage(pageInfo);
		tag.setList(LIST_ATTRIBUTE_NAME);

		expected.expect(JspException.class);
		expected.expectMessage("Bad index: " + index);

		try{
			tag.doTag();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
