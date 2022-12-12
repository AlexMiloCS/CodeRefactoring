package incometaxcalculator.data.reader.io;

import incometaxcalculator.exceptions.WrongFileFormatException;

public class FileReaderFactory {
  public FileReader getFileReader (String fileFormat ) throws WrongFileFormatException {
    if(fileFormat.equals("txt")) {
      FileReader fileReader = new TXTFileReader();
      return fileReader;
    }
    else if(fileFormat.equals("xml")){
      FileReader fileReader = new XMLFileReader();
      return fileReader;
    }
    else {
      throw new WrongFileFormatException();
    }
  }
}