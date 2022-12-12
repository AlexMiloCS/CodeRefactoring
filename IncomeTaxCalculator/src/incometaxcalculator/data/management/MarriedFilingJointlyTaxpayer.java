package incometaxcalculator.data.management;

public class MarriedFilingJointlyTaxpayer extends Taxpayer{
  public MarriedFilingJointlyTaxpayer(String fullname, int taxRegistrationNumber, float income,String status) {
    super(fullname, taxRegistrationNumber, income, status);
  }

  private static final float[] taxMultiplier = {(float) 0.0535,(float) 0.0705, (float)0.0705, (float) 0.0785, (float) 0.0985};
  private static final float[] taxThreshold = {(float) 0,(float) 1930.28,(float) 5731.64,(float) 9492.82,(float) 18197.69};
  private static final float[] taxUntil = {(float) 0, (float) 36080,(float) 90000,(float) 143350,(float) 254240};

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