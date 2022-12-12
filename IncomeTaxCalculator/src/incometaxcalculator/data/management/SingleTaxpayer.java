package incometaxcalculator.data.management;

public class SingleTaxpayer extends Taxpayer {

  public SingleTaxpayer(String fullname, int taxRegistrationNumber, float income,String status) {
    super(fullname, taxRegistrationNumber, income, status);
  }
  private static final float[] taxMultiplier = {(float) 0.0535,(float) 0.0705, (float)0.0785, (float) 0.0785, (float) 0.0985};
  private static final float[] taxThreshold = {(float) 0,(float) 1320.38,(float) 5296.58,(float) 5996.80,(float) 10906.19};
  private static final float[] taxUntil = {(float) 0,(float) 24680,(float) 81080,(float) 90000,(float) 152540};

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