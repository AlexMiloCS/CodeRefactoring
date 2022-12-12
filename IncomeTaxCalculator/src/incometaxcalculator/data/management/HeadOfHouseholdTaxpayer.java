package incometaxcalculator.data.management;

public class HeadOfHouseholdTaxpayer extends Taxpayer {

  public HeadOfHouseholdTaxpayer(String fullname, int taxRegistrationNumber, float income, String status) {
    super(fullname, taxRegistrationNumber, income, status);
  }
  private static final float[] taxMultiplier = {(float) 0.0535,(float) 0.0705, (float)0.0705, (float) 0.0785, (float) 0.0985};
  private static final float[] taxThreshold = {(float) 0,(float) 1625.87,(float) 5828.38,(float) 8092.13,(float) 14472.61};
  private static final float[] taxUntil = {(float) 0,(float) 30390,(float) 90000,(float) 122110,(float) 203390};

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
