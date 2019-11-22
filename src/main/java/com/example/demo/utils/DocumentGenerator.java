package com.example.demo.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component 
public class DocumentGenerator {

	public static String getAlphaNumericString() throws IOException {

		int n = 20;

		// chose a Character random from this String
		//String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
		//Using input stream
		InputStream input = new FileInputStream("src/main/resources/application.properties");
		Properties prop = new Properties();
		prop.load(input);
		String AlphaNumericString = prop.getProperty("ASTR");
		//@Value("${ASTR}")
		

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}
}
