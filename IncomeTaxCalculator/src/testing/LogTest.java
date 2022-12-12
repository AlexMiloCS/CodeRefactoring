package testing;



import java.io.IOException;

import org.junit.jupiter.api.Test;

import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.WrongFileEndingException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

class LogTest {
  private TaxpayerManager taxpayer;
  public LogTest() throws NumberFormatException, IOException, WrongFileFormatException, WrongFileEndingException, WrongTaxpayerStatusException, WrongReceiptKindException, WrongReceiptDateException{
    taxpayer = new TaxpayerManager();
    taxpayer.loadTaxpayer("130456093_INFO.txt");
    taxpayer.loadTaxpayer("123456789_INFO.xml");
  }
  @Test
  void SaveLogTest() throws IOException, WrongFileFormatException {
    taxpayer.saveLogFile(130456093, "txt");
    taxpayer.saveLogFile(130456093, "xml");
  }

}
