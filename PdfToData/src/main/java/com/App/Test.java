package com.App;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.*;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.XHTMLContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import org.apache.tika.exception.TikaException;

public class Test {

//	 public static void main(String args[]) throws IOException {
//	 
//	// Loading an existing document
//		String fileName = "E:\\NazTech\\OFFICE WORK\\statements\\FNB.pdf";
//		File file = new File(fileName);
//		PDDocument document = PDDocument.load(file);
//
//		// Instantiate PDFTextStripper class
//		PDFTextStripper pdfStripper = new PDFTextStripper();
//
//		// Retrieving text from PDF document
//		String text = pdfStripper.getText(document);
//		// System.out.println(text);
//
//		String Date = "(?<=Transaction date range:( ){1,10}\\d{2} [A-Za-z]{3,10} \\d{4}( - ))\\d{2} [A-Za-z]{3,10} \\d{4}";
//		String EntityName = "[A-Za-z]{1,10}( )[A-Za-z]{1,10}(?= of South Africa Limited \\(Reg. No.)";
//		String CurrentBalance = "(?<=Available balance:( ))[A-Z1-9]{0,3}( )[0-9 ]{0,20}.\\d{2}";
//
//		
//		List<String> mDate = match(text, Date); 
//		List<String> mEntityName = match(text, EntityName); 
//		//mEntityName = match(mEntityName.get(0));
//		List<String> mCurrentBalance = match(text,CurrentBalance);
//		 
////		System.out.println("Date : "+mDate.get(0)); 
////		System.out.println("Entity Name : "+ mEntityName.get(0) );
////		System.out.println("Current Balance : "+mCurrentBalance.get(mCurrentBalance.size()-1));
//		 
//
//		// Closing the document
//		document.close();
//
//	}
//
//	public static List<String> match(String text, String pattern) {
//
//		// Create a Pattern object
//		Pattern r = Pattern.compile(pattern, Pattern.MULTILINE);
//
//		// Now create matcher object.
//		Matcher m = r.matcher(text);
//		List<String> allMatches = new ArrayList<>();
//		int f = 0;
//		while (m.find()) {
//			allMatches.add(m.group());
//			//System.out.println(m.group());
//			f = 1;
//		}
//
//		if (f == 1) {
//
//			//System.out.println(allMatches.size());
//			return allMatches;
//		} else {
//			return null;
//		}
//	}

	/*
	 * public static void main(String args[]) {
	 * 
	 * PDFParser parser = null; PDDocument pdDoc = null; COSDocument cosDoc = null;
	 * PDFTextStripper pdfStripper;
	 * 
	 * String parsedText; String fileName =
	 * "E:\\NazTech\\OFFICE WORK\\statements\\FNB 62646640476.pdf"; File file = new
	 * File(fileName); try { parser = new PDFParser( new FileInputStream(file));
	 * parser.parse(); cosDoc = parser.getDocument(); pdfStripper = new
	 * PDFTextStripper(); pdDoc = new PDDocument(cosDoc); parsedText =
	 * pdfStripper.getText(pdDoc);
	 * System.out.println(parsedText.replaceAll("[^A-Za-z0-9. ]+", "")); } catch
	 * (Exception e) { e.printStackTrace(); try { if (cosDoc != null)
	 * cosDoc.close(); if (pdDoc != null) pdDoc.close(); } catch (Exception e1) {
	 * e1.printStackTrace(); }
	 * 
	 * } }
	 */

	/*
	 * public static Map<String, Object> processRecord(String url) throws
	 * FileNotFoundException { DefaultHttpClient httpclient = new
	 * DefaultHttpClient(); FileInputStream inputstream = new FileInputStream(new
	 * File(url)); Map<String, Object> map = new HashMap<String, Object>(); try { //
	 * HttpGet httpGet = new HttpGet(url); // HttpResponse response =
	 * httpclient.execute(httpGet); // HttpEntity entity = response.getEntity();
	 * InputStream input = null; // if (entity != null) { try { input = inputstream;
	 * BodyContentHandler handler = new BodyContentHandler(); Metadata metadata =
	 * new Metadata(); AutoDetectParser parser = new AutoDetectParser();
	 * ParseContext parseContext = new ParseContext(); parser.parse(input, handler,
	 * metadata, parseContext); map.put("text",
	 * handler.toString().replaceAll("\n|\r|\t", " "));
	 * System.out.println(map.get("text")); map.put("title",
	 * metadata.get(TikaCoreProperties.TITLE)); map.put("pageCount",
	 * metadata.get("xmpTPg:NPages")); // map.put("status_code",
	 * response.getStatusLine().getStatusCode() + ""); } catch (Exception e) {
	 * e.printStackTrace(); } finally { if (input != null) { try { input.close(); }
	 * catch (IOException e) { e.printStackTrace(); } } } // } } catch (Exception
	 * exception) { exception.printStackTrace(); } return map; }
	 * 
	 */

}
