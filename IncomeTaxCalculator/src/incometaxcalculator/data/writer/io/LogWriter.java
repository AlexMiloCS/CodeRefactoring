package incometaxcalculator.data.writer.io;

import java.io.IOException;

import incometaxcalculator.data.management.TaxpayerManager;

public abstract class LogWriter implements FileWriter{
  
  public static final short ENTERTAINMENT = 0;
  public static final short BASIC = 1;
  public static final short TRAVEL = 2;
  public static final short HEALTH = 3;
  public static final short OTHER = 4;
  
  
  public abstract void printWriter(TaxpayerManager manager,int taxRegistrationNumber) throws IOException;

  public void generateFile(int taxRegistrationNumber) throws IOException {
    
    TaxpayerManager manager = new TaxpayerManager();
    printWriter(manager, taxRegistrationNumber);

  }
}
