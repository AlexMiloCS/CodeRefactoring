package incometaxcalculator.data.writer.io;

import java.io.IOException;



public interface FileWriter {

  public abstract void generateFile(int taxRegistrationNumber) throws IOException;

}