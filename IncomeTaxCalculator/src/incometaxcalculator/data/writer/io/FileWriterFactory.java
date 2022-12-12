package incometaxcalculator.data.writer.io;




import java.io.File;
import java.util.ArrayList;

import incometaxcalculator.exceptions.WrongFileFormatException;

public class FileWriterFactory {
  public FileWriter getInfoWriter(int taxRegistrationNumber,String fileFormat) throws WrongFileFormatException {
   if(fileFormat.equals("txt")) {
     FileWriter txtFileWriter = new TXTInfoWriter();
     return txtFileWriter;
   }
   else if(fileFormat.equals("xml")){
     FileWriter xmlFileWriter = new XMLInfoWriter();
     return xmlFileWriter;
   }
   else {
     throw new WrongFileFormatException();
   }
  }
  public FileWriter getLogWriter (int taxRegistrationNumber,String fileFormat ) throws WrongFileFormatException {
    if(fileFormat.equals("txt")) {
      FileWriter fileWriter = new TXTLogWriter();
      return fileWriter;
    }
    else if(fileFormat.equals("xml")){
      FileWriter fileWriter = new XMLLogWriter();
      return fileWriter;
    }
    else {
      throw new WrongFileFormatException();
    }
  }
  public ArrayList<String> getFileTypes(int taxRegistrationNumber) {
    ArrayList<String> fileTypes = new ArrayList<>();
    if (new File(taxRegistrationNumber + "_INFO.xml").exists()) {
      fileTypes.add("xml");
    }
    if (new File(taxRegistrationNumber + "_INFO.txt").exists()) {
      fileTypes.add("txt");
    }
    return fileTypes;
  }
}