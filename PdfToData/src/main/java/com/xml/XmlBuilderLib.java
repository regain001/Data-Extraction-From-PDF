package com.xml;

import java.io.File;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import com.jamesmurty.utils.XMLBuilder;


//one of the easiest way of making XML, if the Template is fixed

public class XmlBuilderLib {

	
	public static void main(String args[]) throws ParserConfigurationException, FactoryConfigurationError, TransformerException {
		XMLBuilder builder = XMLBuilder.create("sarbdex:SARBDEXEnvelope")
				.namespace("sarbdex", "x-schema:http://sarbdex.resbank.co.za/schemas/sarbdex_schema.xml") 
				
				.e("sarbdex:SARBDEXHeader").a("Sender", "SOUTHEASTBANK_FINSURV")
				.a("Recipient","SARB").a("Identity","secl").a("IdentityType", "1")
				.a("SentAt", "2019-12-12T14:41:03.508+02:00").a("Type", "FINSURV")
				.a("Version", "1").a("Reference", "10820190020061")
				
				.up()
				.e("sarbdex:SARBDEXBody")
				.e("FINSURV").namespace("finsurv", "www.finsurv.com")
				.namespace("noNamespaceSchemaLocation", "http://www.w3.org/2001/XMLSchema-instance")
				.namespace("xs", "http://www.w3.org/2001/XMLSchema")
				.a("Reference", "10820190020061")
				
					.e("OriginalTransaction").a("LineNumber", "1").a("OriginatingBank", "SOUTHEAST")
					.a("ReceivingBank", "BRAC BANK LTD.").a("ReceivingCountry", "BD")
				
						.e("NonResident")
							.e("Individual").a("Surname", "Employe").a("Name", "Naztech")
								.e("AdditionalNonResidentData").a("AccountIdentifier", "NON RESIDENT OTHER").a("Country", "BD")
								
						.up().up().up()
						
						.e("ResidentCustomerAccountHolder")
							.e("IndividualCustomer").a("Surname", "Naztech").a("Name", "Employee")
								.e("AdditionalCustomerData").a("AccountIdentifier", "CASH").a("ContactSurname", "Naztech")
											
						.up().up().up()
						
						.e("MonetaryDetails").a("SequenceNumber", "1").a("ForeignCurrencyCode", "BDT")
						.a("MoneyTransferAgentIndicator", "ADLA").a("ForeignValue", "416");
		
		
		
		TransformerFactory transformerFactory =  TransformerFactory.newInstance();
		@SuppressWarnings("unused")
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(builder.getDocument());
		StreamResult result =  new StreamResult(new File("E:\\XML\\testing.xml"));
		transformer.transform(source, result);


	}


}
