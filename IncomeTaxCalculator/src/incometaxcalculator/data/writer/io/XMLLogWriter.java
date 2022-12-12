package incometaxcalculator.data.writer.io;

import java.io.IOException;
import java.io.PrintWriter;

import incometaxcalculator.data.management.TaxpayerManager;


public class XMLLogWriter extends LogWriter {


  @Override
  public void printWriter(TaxpayerManager manager,int taxRegistrationNumber) throws IOException {
    PrintWriter outputStream = new PrintWriter(
        new java.io.FileWriter(taxRegistrationNumber + "_LOG.xml"));
    outputStream.println("<Name> " + manager.getTaxpayerName(taxRegistrationNumber) + " </Name>");
    outputStream.println("<AFM> " + taxRegistrationNumber + " </AFM>");
    outputStream.println("<Income> " + manager.getTaxpayerIncome(taxRegistrationNumber) + " </Income>");
    outputStream
        .println("<BasicTax> " + manager.getTaxpayerBasicTax(taxRegistrationNumber) + " </BasicTax>");
    if (manager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber) > 0) {
      outputStream.println("<TaxIncrease> "
          + manager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber) + " </TaxIncrease>");
    } else {
      outputStream.println("<TaxDecrease> "
          + manager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber) + " </TaxDecrease>");
    }
    outputStream
        .println("<TotalTax> " + manager.getTaxpayerTotalTax(taxRegistrationNumber) + " </TotalTax>");
    outputStream.println(
        "<Receipts> " + manager.getTaxpayerTotalReceiptsGathered(taxRegistrationNumber) + " </Receipts>");
    outputStream.println(
        "<Entertainment> " + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, ENTERTAINMENT)
            + " </Entertainment>");
    outputStream.println(
        "<Basic> " + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, BASIC) + " </Basic>");
    outputStream.println(
        "<Travel> " + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, TRAVEL) + " </Travel>");
    outputStream.println(
        "<Health> " + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, HEALTH) + " </Health>");
    outputStream.println(
        "<Other> " + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, OTHER) + " </Other>");
    outputStream.close();
  }

}
