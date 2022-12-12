package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.ReceiptAlreadyExistsException;
import incometaxcalculator.exceptions.WrongFileEndingException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

class AddAndRemoveReceiptTest {
  
  private TaxpayerManager taxpayerManager;
  
  
  private AddAndRemoveReceiptTest() throws NumberFormatException, IOException, WrongFileFormatException, WrongFileEndingException, WrongTaxpayerStatusException, WrongReceiptKindException, WrongReceiptDateException {
    taxpayerManager = new TaxpayerManager();
    taxpayerManager.loadTaxpayer("130456093_INFO.txt");
    taxpayerManager.loadTaxpayer("123456789_INFO.xml");
  }
  
  
  @Test
  void addAndRemoveReceiptTest() throws NumberFormatException, IOException, WrongFileFormatException, WrongFileEndingException,
  WrongTaxpayerStatusException, WrongReceiptKindException, WrongReceiptDateException,ReceiptAlreadyExistsException {

    taxpayerManager.createReceipt(6, "25/05/2016", 2500, "Basic", "Kappa", "Greece", "Ioannina", "Napoleontos Zerva", 25, 130456093);
    for(int i=1; i<7 ; i++) {
      assertTrue(taxpayerManager.containsReceipt(i));
    }  
    taxpayerManager.removeReceipt(6);
    assertFalse(taxpayerManager.containsReceipt(6));
    taxpayerManager.addReceipt(7, "25/05/2016", 2500, "Basic", "Kappa", "Greece", "Ioannina", "Napoleontos Zerva", 25, 123456789); 
    taxpayerManager.removeReceipt(7);
    assertFalse(taxpayerManager.containsReceipt(7));
  }
  
  
  @Test
  void addAndRemoveReceiptTestReceiptAlreadyExistsException() throws NumberFormatException, IOException, WrongFileFormatException, WrongFileEndingException,
  WrongTaxpayerStatusException, WrongReceiptKindException, WrongReceiptDateException,ReceiptAlreadyExistsException {
    assertThrows(ReceiptAlreadyExistsException.class,
        () -> {
          taxpayerManager.addReceipt(5, "25/05/2016", 2500, "Basic", "Kappa", "Greece", "Ioannina", "Napoleontos Zerva", 25, 130456093);;
    });
  }
  
  @Test
  void addAndRemoveReceiptTestWrongReceiptDateException() throws NumberFormatException, IOException, WrongFileFormatException, WrongFileEndingException,
  WrongTaxpayerStatusException, WrongReceiptKindException, WrongReceiptDateException,ReceiptAlreadyExistsException {
    assertThrows( WrongReceiptDateException.class,
        () -> {
          taxpayerManager.createReceipt(6, "25/2016", 2500, "Basic", "Kappa", "Greece", "Ioannina", "Napoleontos Zerva", 25, 130456093);;
    });
  }
  
  @Test
  void addAndRemoveReceiptTestWrongReceiptKindException() throws NumberFormatException, IOException, WrongFileFormatException, WrongFileEndingException,
  WrongTaxpayerStatusException, WrongReceiptKindException, WrongReceiptDateException,ReceiptAlreadyExistsException {
    assertThrows( WrongReceiptKindException.class,
        () -> {
          taxpayerManager.createReceipt(6, "25/06/2016", 2500, "sss", "Kappa", "Greece", "Ioannina", "Napoleontos Zerva", 25, 130456093);;
    });
  }
}
