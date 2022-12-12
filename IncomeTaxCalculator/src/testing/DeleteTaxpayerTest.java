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

class DeleteTaxpayerTest {
  
  private TaxpayerManager taxpayer;
  
  private DeleteTaxpayerTest() throws NumberFormatException, IOException, WrongFileFormatException, WrongFileEndingException, WrongTaxpayerStatusException, WrongReceiptKindException, WrongReceiptDateException {
    taxpayer = new TaxpayerManager();
    taxpayer.loadTaxpayer("130456093_INFO.txt");
    taxpayer.loadTaxpayer("123456789_INFO.xml");
  }

  @Test
  void deleteTaxpayerTest() throws NumberFormatException, IOException, WrongFileFormatException, WrongFileEndingException,
  WrongTaxpayerStatusException, WrongReceiptKindException, WrongReceiptDateException {
    TaxpayerManager taxpayer = new TaxpayerManager();
    assertTrue(taxpayer.containsTaxpayer(130456093));
    assertTrue(taxpayer.containsTaxpayer(123456789));
    taxpayer.removeTaxpayer(130456093);
    taxpayer.removeTaxpayer(123456789);
    assertFalse(taxpayer.containsTaxpayer(130456093));
    assertFalse(taxpayer.containsTaxpayer(123456789));
  }
  
  @Test
  void deleteTaxpayerTestFileNotFound() throws NumberFormatException, IOException, WrongFileFormatException, WrongFileEndingException,
  WrongTaxpayerStatusException, WrongReceiptKindException, WrongReceiptDateException {
    assertThrows(FileNotFoundException.class,
        () -> {
          taxpayer.loadTaxpayer("130456023_INFO.txt");
    });
  }
}

