package com.example.demo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.exceptions.DocumentNotFoundException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class DocumentController {

	private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(DocumentController.class);

	@Autowired
	private DocumentService documentService;
	
	@GetMapping(value = "/storage/documents/{id}")
	@ResponseBody
	public String getDocument(@PathVariable String id) throws IOException, ClassNotFoundException, SQLException {
		Document doc = documentService.getDocument(id);
		String jsonStr = null;
		List<Document> docs = new ArrayList<>();
		docs.add(doc);
		if (documentService.getDocument(id) != DocumentService.DOC_NOT_EXISTING) {
			
			jsonStr = createJSON(docs, HttpStatus.OK);
		} else {
			jsonStr = createJSON(null, HttpStatus.NOT_FOUND);
		}
		
		return jsonStr;
		
	}

	@PostMapping( value = "/storage/documents")
	@ResponseBody
	public String adddocument(@RequestBody List<Document> docs) throws ClassNotFoundException, SQLException, IOException {

		List<Document> documents = documentService.adddocument(docs);
		String jsonStr = null;

		if (documents.size() > 0) { // Instead of comparing doc != null , id we have doc object that is instance of
										// Document
			jsonStr = createJSON(documents, HttpStatus.OK);
		} else {
			jsonStr = createJSON(null, HttpStatus.BAD_REQUEST);
		}

		return jsonStr;
	}

	/*
	 * Create JSON object out of Document Object & dynamic Status - Success JSON 
	 * if document is null create error JSON
	 */
	private String createJSON(List<Document> docs, HttpStatus status) throws IOException {

		// returns success json
		ObjectMapper mapper = new ObjectMapper();
		if (docs.size() > 0) {
			String jsonStr = mapper.writeValueAsString(docs); 
			return jsonStr;
		}
		// returns the error json
		else {
			String jsonStr = mapper.writeValueAsString(status.toString());
			System.out.println(jsonStr); // { "status" : "BAD_REQUEST"}
			return jsonStr;
		}

	}

	@PutMapping(value = "/storage/documents/{id}")
	@ResponseBody
	public String updatedocument(@RequestBody Document doc, @PathVariable String id) throws IOException, ClassNotFoundException {
		log.info("Currently Executing | " + new Object() {
		}.getClass().getEnclosingMethod().getName() + " Method");
		
		
		Document document = documentService.updatedocument(doc, id);
		String jsonStr = null;
		List<Document> docs = new ArrayList<Document>();
		docs.add(document);
	
		if(document != null) {
			jsonStr = createJSON(docs, HttpStatus.OK);
			
		}else {
			jsonStr = createJSON(null, HttpStatus.NOT_FOUND);
			
		}
		return jsonStr;
		

	}

	@DeleteMapping(value = "/storage/documents/{id}")
	@ResponseBody
	public String deletedocument(@PathVariable String id) throws IOException {
		log.info("Currently Executing | " + new Object() {
		}.getClass().getEnclosingMethod().getName() + " Method");

		String jsonStr = null;
		if (documentService.deletedocument(id).equals(DocumentService.DELETED)) {
			//return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
			jsonStr = createJSON(null, HttpStatus.OK);
			
		} else {
			//log.warn(RequestMethod.DELETE + " Method | WARNING : Please check the requested ID");
			jsonStr = createJSON(null, HttpStatus.NOT_FOUND);
			
		}
		return jsonStr;
	}

}
