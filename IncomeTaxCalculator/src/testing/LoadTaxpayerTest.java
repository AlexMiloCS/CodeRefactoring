package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;



import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.WrongFileEndingException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

class LoadTaxpayerTest {
  
  private TaxpayerManager taxpayer;
  
  private LoadTaxpayerTest() throws NumberFormatException, IOException, WrongFileFormatException, WrongFileEndingException, WrongTaxpayerStatusException, WrongReceiptKindException, WrongReceiptDateException {
    taxpayer = new TaxpayerManager();
    taxpayer.loadTaxpayer("130456093_INFO.txt");
    taxpayer.loadTaxpayer("123456789_INFO.xml");  
  }

  @Test
  void loadTaxpayerTest() throws NumberFormatException, IOException, WrongFileFormatException, WrongFileEndingException,
  WrongTaxpayerStatusException, WrongReceiptKindException, WrongReceiptDateException {
    assertTrue(taxpayer.containsTaxpayer(123456789));
    assertTrue(taxpayer.containsTaxpayer(130456093));
    assertFalse(taxpayer.containsTaxpayer(130456023));
  }
  
  
  @Test
  void loadTaxpayerTestFileNotFound() throws NumberFormatException, IOException, WrongFileFormatException, WrongFileEndingException,
  WrongTaxpayerStatusException, WrongReceiptKindException, WrongReceiptDateException {
    assertThrows(FileNotFoundException.class,
        () -> {
          taxpayer.loadTaxpayer("130456023_INFO.txt");
    });
  }
  
  
  @Test
  void loadTaxpayerTestWrongFileEnding() throws NumberFormatException, IOException, WrongFileFormatException, WrongFileEndingException,
  WrongTaxpayerStatusException, WrongReceiptKindException, WrongReceiptDateException {
    assertThrows(WrongFileFormatException.class,
        () -> {
          taxpayer.loadTaxpayer("130456093_INFO.sad");
    });
  }
  
  @Test
  void loadTaxpayerTestWrongStatusException() throws NumberFormatException, IOException, WrongFileFormatException, WrongFileEndingException,
  WrongTaxpayerStatusException, WrongReceiptKindException, WrongReceiptDateException {
    assertThrows(WrongTaxpayerStatusException.class,
        () -> {
          taxpayer.loadTaxpayer("130456092_INFO.txt");
    });
  }
}