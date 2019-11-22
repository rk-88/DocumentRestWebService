package com.example.demo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DocumentDAO;

@Service
public class DocumentService {

	private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(DocumentService.class);

	List<Document> documents = new ArrayList<Document>();
	Set<String> docIds = new TreeSet<String>();
	static final String UPDATED = "UPDATED";
	static final String NOT_EXISTING = "NOT_EXISTING";
	static final Document DOC_NOT_EXISTING = null;
	static final String DELETED = "DELETED";


	
	
	public Document getDocument(String id) throws ClassNotFoundException, SQLException, IOException {
		log.info("Currently Executing | " + new Object() {
		}.getClass().getEnclosingMethod().getName() + " Method");

		if (documents.size() < 1) {

			return DOC_NOT_EXISTING;
		}

		if (isDocIdExisting(id)) {

			DocumentDAO dao = new DocumentDAO();
			dao.retriveDocument();
			
			

			Document document = documents.stream().filter(doc -> doc.getId().equals(id)).findFirst().get();
			return document;
		}
		else {
			
			DocumentDAO dao = new DocumentDAO();
			dao.retriveDocument();
		}
		
		

		return DOC_NOT_EXISTING;
	}

	

	public List<Document> adddocument(List<Document> docs) throws ClassNotFoundException, SQLException, IOException {
		log.info("Currently Executing | " + new Object() {
		}.getClass().getEnclosingMethod().getName() + " Method");

		//Traversing list of docs received from request as JSON to create actual Documents
		for (Document document : docs) {
			//Here the actual docs are created
			List list = createDocument(document.getValue(), document.getEmp_dept());
			String id = (String) list.get(0);
			Document doc = (Document) list.get(1);
			documents.add(doc);
			DocumentDAO dao = new DocumentDAO();
			dao.insertDocument(id, document.getValue(), document.getEmp_dept());
		}
		
		

		return documents;
	}

	public Document updatedocument(Document document, String id) throws ClassNotFoundException, IOException {    //instead of value it should take doc
		log.info("Currently Executing | " + new Object() {
		}.getClass().getEnclosingMethod().getName() + " Method");

		if (documents.size() < 1) {
			return DOC_NOT_EXISTING;
		}
		if (isDocIdExisting(id)) {
			
			//Setting the value
			if(document.getValue() != null && document.getEmp_dept() != null) {
				documents.stream().filter(doc -> doc.getId().equals(id)).findFirst().get().setValue(document.getValue());
				documents.stream().filter(doc -> doc.getId().equals(id)).findFirst().get().setEmp_dept(document.getEmp_dept());
			}
			else if(document.getValue() == null && document.getEmp_dept() != null) {
				documents.stream().filter(doc -> doc.getId().equals(id)).findFirst().get().setEmp_dept(document.getEmp_dept());
			}
			else if(document.getValue() != null && document.getEmp_dept() == null) {
				documents.stream().filter(doc -> doc.getId().equals(id)).findFirst().get().setValue(document.getValue());
			}
			
			//return the value
			Document d = documents.stream().filter(doc -> doc.getId().equals(id)).findFirst().get();
			
			
			/*
			 * Implement DAO 
			 * 
			 */
			DocumentDAO dao = new DocumentDAO();
			dao.updateDocument(id, document.getValue(), document.getEmp_dept());
			
			return d;
		}
		return DOC_NOT_EXISTING;

	}

	public String deletedocument(String id) {
		log.info("Currently Executing | " + new Object() {
		}.getClass().getEnclosingMethod().getName() + " Method");

		if (documents.size() < 1) {
			return NOT_EXISTING;
		}
		if (isDocIdExisting(id)) {

			Document document = documents.stream().filter(doc -> doc.getId().equals(id)).findFirst().get();
			documents.remove(document);
			return DELETED;
		}
		return NOT_EXISTING;
	}

	public List createDocument(String value, String emp_dept) throws IOException {
		Document document = new Document(value, emp_dept);
		docIds.add(document.getId());

		// Since we need id & value to send to DAO after creation we are using list here
		// to send multiple values
		List list = new ArrayList<>();
		list.add(document.getId());
		list.add(document);
		return list;
	}

	public boolean isDocIdExisting(String id) {
		return docIds.contains(id) ? true : false;
	}

}
