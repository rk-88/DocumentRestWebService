package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/*
public class DocumentService_UnitTest {
	
	DocumentService service;
	Document document;
	String id;
	static final String NOT_EXISTING = "NOT_EXISTING";
	static final Document DOC_NOT_EXISTING = null;
	static final String DELETED = "DELETED";

	
	@Before
	public void setUp() throws IOException, ClassNotFoundException, SQLException {
		service = new DocumentService();
		document = service.adddocument("doc1");
		id = document.getId();	
	}
	
	
	@Test
	public void test_adddocument() throws ClassNotFoundException, SQLException, IOException {
		//Execute & Validate
		assertEquals(service.documents.get(0), document);
	} 
	
	
	@Test
	public void test_getdocument() throws ClassNotFoundException, SQLException, IOException {
		//Execute 
		Document doc = service.documents.get(0);
		String id = doc.getId();
		
		
		//& Validate
		assertEquals(service.getDocument(id), document);
		assertEquals(service.getDocument("dummy_id"), DOC_NOT_EXISTING);
		
	}
	
	@Test
	public void test_updatedocument() throws ClassNotFoundException, SQLException, IOException {
		//Execute 
		Document doc = service.documents.get(0);
		String id = doc.getId();
		//Document doc1 = service.updatedocument("updated_value", id);
		
		
		//& Validate
		assertEquals(service.updatedocument("updated_value", id), document);
		assertEquals(service.updatedocument("updated_value", "dummy_id"), DOC_NOT_EXISTING);
		
	}
	
	@Test
	public void test_deletedocument() throws ClassNotFoundException, SQLException, IOException {
		//Execute 
		Document doc = service.documents.get(0);
		String id = doc.getId();
		
		
		//& Validate
		assertEquals(service.deletedocument("dummy_id"), NOT_EXISTING);
		assertEquals(service.deletedocument(id), DELETED);
		assertEquals(service.deletedocument("dummy_id"), NOT_EXISTING);
		
		
	}

	@Test
	public void test_isDocIdExisting() {
		//Execute & Validate	
		assertTrue(service.isDocIdExisting(id));
		assertFalse(service.isDocIdExisting("dummy"));	
	}
	
	
	
	
}
*/
