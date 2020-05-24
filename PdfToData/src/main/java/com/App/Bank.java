package com.App;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;


// Data extraction from PDF by using 2 different libraries i.e. PDFBox & iText


public class Bank {

	public static void main(String args[]) throws IOException, ParseException {
		ArrayList<Dashboard> list = new ArrayList<Dashboard>();
		
		Dashboard dboard = new Dashboard();
		String oldFile = "E:\\statements\\";
		String newFile = "E:\\statements\\latest\\";
		
//		list.add(absaBank(oldFile+"ABSABank.pdf"));
//		list.add(fnbBank(oldFile+"FNBBank.pdf"));
//		list.add(nedBank(oldFile+"NEDBank.pdf"));
//		list.add(standardBank(oldFile+"StandardBank.pdf"));
		
		list.add(absaBank(newFile+"ABSABank1.pdf"));
		list.add(fnbBank(newFile+"FNBBank1.pdf"));
		list.add(standardBank(newFile+"StandardBank1.pdf"));
		list.add(nedBank(newFile+"NEDBank1.pdf"));
		
		for(Dashboard d : list) {
			System.out.println("----------------------------------\n\n\n");
			System.out.println("Date : "+d.getInputDate()); 
			System.out.println("Entity Name : "+ d.getBankName());
			System.out.println("Current Balance : "+d.getClosingBalance());
			System.out.println("Opening Balance : "+d.getOpeningBalance());
			System.out.println("External Id : "+d.getExternalId());
			System.out.println("----------------------------------\n\n\n");
		}
		
	}
	
	public static Dashboard absaBank(String fileName) throws IOException, ParseException {

		
		File file = new File(fileName);
		PDDocument document = PDDocument.load(file);
		Dashboard dboard = new Dashboard();
		PDFTextStripper pdfStripper = new PDFTextStripper();
		String text = pdfStripper.getText(document);
		//System.out.println(text);

		String date = "(?<=Statement for Period[ ]{1,20}\\d{4}-\\d{2}-\\d{2}( - ))\\d{4}-\\d{2}-\\d{2}";
		String entityName = "[A-Za-z]{1,20}(?= to the client.)";
		String currentBalance = "(?<=Current balance[ ]{0,20}R( ))\\d[0-9,]{1,20}.\\d[0-9]{1,3}";

		
		List<String> mDate = Dashboard.match(text, date); 
		List<String> mEntityName = Dashboard.match(text, entityName); 
		List<String> mCurrentBalance = Dashboard.match(text,currentBalance);
		//List<String> mOpeningBalance = Dashboard.match(text,openingBalance);
		 
//		System.out.println("Date : "+mDate.get(0)); 
//		//System.out.println("Entity Name : "+ mEntityName.get(0) );
//		System.out.println("Current Balance : "+mCurrentBalance.get(mCurrentBalance.size()-1));
//		System.out.println("Opening Balance : "+mOpeningBalance.get(mOpeningBalance.size()-1));
		document.close();
	
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date d = null;
		if(mDate!=null) {
			d = format.parse(mDate.get(0));
		}
		
		//System.out.println(d);
		Double openBal= -1.0;
		Double closeBal = -1.0;
//		if(mOpeningBalance!=null) {
//			openBal = Double.valueOf(mOpeningBalance.get(mOpeningBalance.size()-1).replaceAll(",", ""));
//		}
		if(mCurrentBalance!=null) {
			closeBal = Double.valueOf(mCurrentBalance.get(mCurrentBalance.size()-1).replaceAll(",", ""));
		}		
		final SimpleDateFormat SDFT = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = SDFT.format(new java.util.Date());
		
		dboard.setBankName("ABSA Bank");
		dboard.setInputDate(d);
		dboard.setOpeningBalance(closeBal);
		dboard.setClosingBalance(closeBal);
		dboard.setExternalId(Integer.parseInt(time.substring(5, time.length() - 1)));
		
		System.out.println("Date : "+dboard.getInputDate()); 
		System.out.println("Entity Name : "+ dboard.getBankName());
		System.out.println("Current Balance : "+dboard.getClosingBalance());
		System.out.println("Opening Balance : "+dboard.getOpeningBalance());
		System.out.println("External Id : "+dboard.getExternalId());
		
		return dboard;
		
	}

	public static Dashboard fnbBank(String fileName) throws IOException, ParseException {

		PdfReader reader = new PdfReader(fileName); 
		int n = reader.getNumberOfPages(); 
		String text ="";
		Dashboard dboard = new Dashboard();
		text = PdfTextExtractor.getTextFromPage(reader, 1);
		//System.out.println(text); 
		//System.out.println("numner of pages : "+ n);
		reader.close(); 

		String date = "(?<=Date:[ ]{1,10})\\d{2} [A-Za-z]{3} \\d{4}";
		String entityName = "(?<=© \\d{4} )[A-Za-z ]{1,20} Bank";
		String currentBalance = "(?<=Current Balance:[ ]{1,10} )[0-9,]{1,15}.\\d{2}";
		
		List<String> mDate = Dashboard.match(text, date); 
		List<String> mEntityName = Dashboard.match(text, entityName); 
		List<String> mCurrentBalance = Dashboard.match(text,currentBalance);
		 
	
		DateFormat format = new SimpleDateFormat("dd MMM yyyy");
		java.util.Date d = null;
		if(mDate!=null) {
			d = format.parse(mDate.get(0));
		}
		
		//System.out.println(d);
		Double openBal= -1.0;
		Double  closeBal = -1.0;
//		if(mOpeningBalance!=null) {
//			openBal = Double.valueOf(mOpeningBalance.get(mOpeningBalance.size()-1).replaceAll(",", ""));
//		}
		
		if(mCurrentBalance!=null) {
			closeBal = Double.valueOf(mCurrentBalance.get(mCurrentBalance.size()-1).replaceAll(",", ""));
		}		
		final SimpleDateFormat SDFT = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = SDFT.format(new java.util.Date());
		
		dboard.setBankName("FNB Bank");
		dboard.setInputDate(d);
		dboard.setOpeningBalance(closeBal);
		dboard.setClosingBalance(closeBal);
		dboard.setExternalId(Integer.parseInt(time.substring(5, time.length() - 1)));
		
		System.out.println("Date : "+dboard.getInputDate()); 
		System.out.println("Entity Name : "+ dboard.getBankName());
		System.out.println("Current Balance : "+dboard.getClosingBalance());
		System.out.println("Opening Balance : "+dboard.getOpeningBalance());
		System.out.println("External Id : "+dboard.getExternalId());
		
		return dboard;

	}

	public static Dashboard nedBank(String fileName) throws IOException, ParseException {

		File file = new File(fileName);
		PDDocument document = PDDocument.load(file);
		Dashboard dboard = new Dashboard();
		PDFTextStripper pdfStripper = new PDFTextStripper();
		String text = pdfStripper.getText(document);
		System.out.println(text);

		String date = "(?<=Date:[ ]{0,10})\\d{2}\\/\\d{2}\\/\\d{4}";
		String entityName = "[A-Za-z]{1,20}(?= to the client.)";
		String currentBalance = "(?<=[0-9]* \\d{2}\\/\\d{2}\\/\\d{4} CARRIED FORWARD )([0-9]+,{0,1})+.[0-9]+";
		String openingBalance = "(?<=[0-9]* \\d{2}\\/\\d{2}\\/\\d{4} BROUGHT FORWARD )([0-9]+,{0,1})+.[0-9]+";
		
		List<String> mDate = Dashboard.match(text, date); 
		List<String> mEntityName = Dashboard.match(text, entityName); 
		List<String> mCurrentBalance = Dashboard.match(text,currentBalance);
		List<String> mOpeningBalance = Dashboard.match(text,openingBalance);
		 
//		System.out.println("Date : "+mDate.get(0)); 
//		//System.out.println("Entity Name : "+ mEntityName.get(0) );
//		System.out.println("Current Balance : "+mCurrentBalance.get(mCurrentBalance.size()-1));
//		System.out.println("Opening Balance : "+mOpeningBalance.get(mOpeningBalance.size()-1));
		 
		document.close();
	
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date d = null;
		if(mDate!=null) {
			d = format.parse(mDate.get(0));
		}
		
		//System.out.println(d);
		Double openBal= -1.0;Double  closeBal = -1.0;
		if(mOpeningBalance!=null) {
			openBal = Double.valueOf(mOpeningBalance.get(mOpeningBalance.size()-1).replaceAll(",", ""));
		}
		if(mCurrentBalance!=null) {
			closeBal = Double.valueOf(mCurrentBalance.get(mCurrentBalance.size()-1).replaceAll(",", ""));
		}
		
		final SimpleDateFormat SDFT = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = SDFT.format(new java.util.Date());
		
		dboard.setBankName("NED Bank");
		dboard.setInputDate(d);
		dboard.setOpeningBalance(closeBal);
		dboard.setClosingBalance(closeBal);
		dboard.setExternalId(Integer.parseInt(time.substring(5, time.length() - 1)));
		
		System.out.println("Date : "+dboard.getInputDate()); 
		System.out.println("Entity Name : "+ dboard.getBankName());
		System.out.println("Current Balance : "+dboard.getClosingBalance());
		System.out.println("Opening Balance : "+dboard.getOpeningBalance());
		System.out.println("External Id : "+dboard.getExternalId());
		
		return dboard;

	}

	public static Dashboard standardBank(String fileName) throws IOException, ParseException {

		File file = new File(fileName);
		PDDocument document = PDDocument.load(file);
		Dashboard dboard = new Dashboard();
		PDFTextStripper pdfStripper = new PDFTextStripper();
		String text = pdfStripper.getText(document);
		//System.out.println(text);

		String date = "(?<=Transaction date range:( ){1,10}\\d{2} [A-Za-z]{3,10} \\d{4}( - ))\\d{2} [A-Za-z]{3,10} \\d{4}";
		String entityName = "[A-Za-z]{1,10}( )[A-Za-z]{1,10}(?= of South Africa Limited \\(Reg. No.)";
		String currentBalance = "(?<=Available balance:( )[A-Z1-9]{0,3}( ))[0-9 ]{0,20}.\\d{2}";

		
		List<String> mDate = Dashboard.match(text, date); 
		List<String> mEntityName = Dashboard.match(text, entityName); 
		List<String> mCurrentBalance = Dashboard.match(text,currentBalance);		 

		document.close();
		DateFormat format = new SimpleDateFormat("dd MMMMM yyyy");
		java.util.Date d = null;
		if(mDate!=null) {
			d = format.parse(mDate.get(0));
		}
		
		//System.out.println(d);
		Double openBal= -1.0;Double  closeBal = -1.0;
//		if(mOpeningBalance!=null) {
//			openBal = Double.valueOf(mOpeningBalance.get(mOpeningBalance.size()-1).replaceAll(",", ""));
//		}
		if(mCurrentBalance!=null) {
			closeBal = Double.valueOf(mCurrentBalance.get(mCurrentBalance.size()-1).replaceAll(",", ""));
		}
		
		final SimpleDateFormat SDFT = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = SDFT.format(new java.util.Date());
		
		dboard.setBankName("Standard Bank");
		dboard.setInputDate(d);
		dboard.setOpeningBalance(closeBal);
		dboard.setClosingBalance(closeBal);
		dboard.setExternalId(Integer.parseInt(time.substring(5, time.length() - 1)));
		
		System.out.println("Date : "+dboard.getInputDate()); 
		System.out.println("Entity Name : "+ dboard.getBankName());
		System.out.println("Current Balance : "+dboard.getClosingBalance());
		System.out.println("Opening Balance : "+dboard.getOpeningBalance());
		System.out.println("External Id : "+dboard.getExternalId());
		
		return dboard;
	}
	
}

