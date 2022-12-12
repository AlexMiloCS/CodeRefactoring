package incometaxcalculator.data.management;

import java.util.HashMap;

import incometaxcalculator.exceptions.WrongReceiptKindException;

public abstract class Taxpayer {

  protected final String fullname;
  protected final int taxRegistrationNumber;
  protected final float income;
  private float amountPerReceiptsKind[] = new float[5];
  private int totalReceiptsGathered = 0;
  private HashMap<Integer, Receipt> receiptHashMap = new HashMap<Integer, Receipt>(0);
  private String status;
  private static final String[] receiptKind = {"Entertainment","Basic","Travel","Health","Other"};
  private static final float[] receiptVariationTax = {(float) 0.08,(float) 0.04,(float) -0.15,(float) -0.3};
  private float[] taxMultiplier;
  private float[] taxUntil;
  private float[] taxThreshold;



  public double calculateBasicTax() {
    if(status == "Single") {
      taxMultiplier = SingleTaxpayer.getTaxmultiplier();
      taxUntil = SingleTaxpayer.getTaxuntil();
      taxThreshold = SingleTaxpayer.getTaxthreshold();
    }
    else if (status == "Married Filing Separately") {
      taxMultiplier = MarriedFilingSeparatelyTaxpayer.getTaxmultiplier();
      taxUntil = MarriedFilingSeparatelyTaxpayer.getTaxuntil();
      taxThreshold = MarriedFilingSeparatelyTaxpayer.getTaxthreshold();
    }
    else if (status == "Head of Household") {
      taxMultiplier = HeadOfHouseholdTaxpayer.getTaxmultiplier();
      taxUntil = HeadOfHouseholdTaxpayer.getTaxuntil();
      taxThreshold = HeadOfHouseholdTaxpayer.getTaxthreshold();
    }
    else {
      taxMultiplier = MarriedFilingJointlyTaxpayer.getTaxmultiplier();
      taxUntil = MarriedFilingJointlyTaxpayer.getTaxuntil();
      taxThreshold = MarriedFilingJointlyTaxpayer.getTaxthreshold();
    }
    for(int i=0; i<4 ; i++) {
      if(income < taxUntil[i+1]) {
        return(taxThreshold[i]+ taxMultiplier[i]*(income-taxUntil[i]));
      }
    }
    return taxThreshold[4]+ taxMultiplier[4]*(income-taxUntil[4]);
  }
  
  

  protected Taxpayer(String fullname, int taxRegistrationNumber, float income , String status) {
    this.fullname = fullname;
    this.taxRegistrationNumber = taxRegistrationNumber;
    this.income = income;
    this.status = status;
  }
  // Vgalame ta if statement pou proipirxan kai ta antikatastisame me ena pinaka o opoios eixes ta eidi ton apodixewn 
  public void addReceipt(Receipt receipt) throws WrongReceiptKindException {
    int in = 0;
    for(int i=0; i<5; i++) {
      if(receipt.getKind().equals(receiptKind[i])) {
        amountPerReceiptsKind[i] += receipt.getAmount();
        in =1;
      }
    }
    if(in==0) {
      throw new WrongReceiptKindException();
    }    
    receiptHashMap.put(receipt.getId(), receipt);
    totalReceiptsGathered++;
  }
  //omoia me panw
  public void removeReceipt(int receiptId) throws WrongReceiptKindException {
    Receipt receipt = receiptHashMap.get(receiptId);
    int in = 0;
    for(int i=0; i<5; i++) {
      if(receipt.getKind().equals(receiptKind[i])) {
        amountPerReceiptsKind[i] -= receipt.getAmount();
        in =1;
      }
    }
    if(in==0) {
      throw new WrongReceiptKindException();
    }    
    totalReceiptsGathered--;
    receiptHashMap.remove(receiptId);
  }

  public String getFullname() {
    return fullname;
  }

  public int getTaxRegistrationNumber() {
    return taxRegistrationNumber;
  }

  public float getIncome() {
    return income;
  }

  public HashMap<Integer, Receipt> getReceiptHashMap() {
    return receiptHashMap;
  }
  //vgalame ta if statements kai ta noumera gia tis allages ta apothikefsame se ena pinaka stin arxi
  public double getVariationTaxOnReceipts() {
    float totalAmountOfReceipts = getTotalAmountOfReceipts();
    for(int i=1; i<4; i++) {
      if(totalAmountOfReceipts<(0.2*i*income)) {
        return calculateBasicTax()*receiptVariationTax[i-1];
      }
    }
    return calculateBasicTax()*receiptVariationTax[3];
  }

  private float getTotalAmountOfReceipts() {
    int sum = 0;
    for (int i = 0; i < 5; i++) {
      sum += amountPerReceiptsKind[i];
    }
    return sum;
  }

  public int getTotalReceiptsGathered() {
    return totalReceiptsGathered;
  }

  public float getAmountOfReceiptKind(short kind) {
    return amountPerReceiptsKind[kind];
  }

  public double getTotalTax() {
    return calculateBasicTax() + getVariationTaxOnReceipts();
  }

  public double getBasicTax() {
    return calculateBasicTax();
  }

}