package com.App;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dashboard {


	private String bankName;
	private Date inputDate;
	private Double openingBalance;
	private Double closingBalance;
	private int externalId;
	
	
	Dashboard(){};
	
	public Dashboard(String bankName, Date inputDate, Double openingBalance, Double closingBalance, int externalId) {
		super();
		this.bankName = bankName;
		this.inputDate = inputDate;
		this.openingBalance = openingBalance;
		this.closingBalance = closingBalance;
		this.externalId = externalId;
	}


	public String getBankName() {
		return bankName;
	}


	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	public Date getInputDate() {
		return inputDate;
	}


	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}


	public Double getOpeningBalance() {
		return openingBalance;
	}


	public void setOpeningBalance(Double openingBalance) {
		this.openingBalance = openingBalance;
	}


	public Double getClosingBalance() {
		return closingBalance;
	}


	public void setClosingBalance(Double closingBalance) {
		this.closingBalance = closingBalance;
	}


	public int getExternalId() {
		return externalId;
	}


	public void setExternalId(int externalId) {
		this.externalId = externalId;
	}


	public static List<String> match(String text, String pattern) {
		// Create a Pattern object
				Pattern r = Pattern.compile(pattern, Pattern.MULTILINE);

				// Now create matcher object
				Matcher m = r.matcher(text);
				List<String> allMatches = new ArrayList<>();
				int f = 0;
				while (m.find()) {
					allMatches.add(m.group());
					//System.out.println(m.group());
					f = 1;
				}

				if (f == 1) {

					//System.out.println(allMatches.size());
					return allMatches;
				} else {
					return null;
				}
	}

}
