package incometaxcalculator.data.management;

import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public class TaxpayerFactory {

   public Taxpayer getTaxpayer(String fullname, int taxRegistrationNumber, String status,float income) throws WrongTaxpayerStatusException
   {
     if (status.equals("Married Filing Jointly")) 
     {
       Taxpayer taxpayer = new MarriedFilingJointlyTaxpayer(fullname, taxRegistrationNumber, income,status);
       return taxpayer;
     } 
     else if (status.equals("Married Filing Separately")) 
     {      
       Taxpayer taxpayer = new MarriedFilingSeparatelyTaxpayer(fullname, taxRegistrationNumber, income,status);
       return taxpayer;
     } 
     else if (status.equals("Single"))
     {
       Taxpayer taxpayer = new SingleTaxpayer(fullname, taxRegistrationNumber, income,status);
       return taxpayer;
     } 
     else if (status.equals("Head of Household"))
     {
       Taxpayer taxpayer =new HeadOfHouseholdTaxpayer(fullname, taxRegistrationNumber, income,status);
       return taxpayer;
     } 
     else 
     {
       throw new WrongTaxpayerStatusException();
     }

     
   }
}