package incometaxcalculator.data.writer.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;

import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.TaxpayerManager;

public class TXTInfoWriter extends InfoWriter {

   @Override
   public PrintWriter printWriter(TaxpayerManager manager,int taxRegistrationNumber) throws IOException {
     PrintWriter outputStream = new PrintWriter(new java.io.FileWriter(taxRegistrationNumber + "_INFO.txt"));
     outputStream.println("Name: " + manager.getTaxpayerName(taxRegistrationNumber));
     outputStream.println("AFM: " + taxRegistrationNumber);
     outputStream.println("Status: " + manager.getTaxpayerStatus(taxRegistrationNumber));
     outputStream.println("Income: " + manager.getTaxpayerIncome(taxRegistrationNumber));
     outputStream.println();// den mas emfanize to \n se aplo notepad
     outputStream.println("Receipts:");
     outputStream.println();
     return outputStream;
  }
   
   @Override
   public void tagMaker(Iterator<HashMap.Entry<Integer, Receipt>> iterator,PrintWriter outputStream) { 
     while (iterator.hasNext()) {
       HashMap.Entry<Integer, Receipt> entry = iterator.next();
       Receipt receipt = entry.getValue();
       outputStream.println("Receipt ID: " + getReceiptId(receipt));
       outputStream.println("Date: " + getReceiptIssueDate(receipt));
       outputStream.println("Kind: " + getReceiptKind(receipt));
       outputStream.println("Amount: " + getReceiptAmount(receipt));
       outputStream.println("Company: " + getCompanyName(receipt));
       outputStream.println("Country: " + getCompanyCountry(receipt));
       outputStream.println("City: " + getCompanyCity(receipt));
       outputStream.println("Street: " + getCompanyStreet(receipt));
       outputStream.println("Number: " + getCompanyNumber(receipt));
       outputStream.println();
     }
   }
}