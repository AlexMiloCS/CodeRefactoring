package incometaxcalculator.data.management;

public class MarriedFilingSeparatelyTaxpayer extends Taxpayer {

  public MarriedFilingSeparatelyTaxpayer(String fullname, int taxRegistrationNumber, float income,String status) {
    super(fullname, taxRegistrationNumber, income,status);
  }
  private static final float[] taxMultiplier = {(float) 0.0535,(float) 0.0705, (float)0.0785, (float) 0.0785, (float) 0.0985};
  private static final float[] taxThreshold = {(float) 0,(float) 965.14,(float) 4746.76,(float) 6184.88,(float) 9098.80};
  private static final float[] taxUntil = {(float) 0,(float) 18040,(float) 71680,(float) 90000,(float) 127120};

  public static float[] getTaxmultiplier() {
    return taxMultiplier;
  }

  public static float[] getTaxthreshold() {
    return taxThreshold;
  }

  public static float[] getTaxuntil() {
    return taxUntil;
  }
  
}