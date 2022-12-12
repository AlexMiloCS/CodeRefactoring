package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import incometaxcalculator.data.management.Taxpayer;
import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

class CreateInstancesOfTaxpayersTest {
  private TaxpayerManager taxpayerManager;
  private Taxpayer alekos;
  private Taxpayer kostas;
  private Taxpayer boss;
  
  @Test
  void CreateTaxpayers() throws WrongTaxpayerStatusException {
    taxpayerManager= new TaxpayerManager();
    taxpayerManager.createTaxpayer("Alexandros Milonakis", 100003045, "Single", 13000);
    taxpayerManager.createTaxpayer("Kostas Zoulias", 100003155, "Head of Household", 200000);
    taxpayerManager.createTaxpayer("O Kanenas", 823256400, "Married Filing Separately", 500000);
    alekos = taxpayerManager.getTaxpayer(100003045);
    kostas = taxpayerManager.getTaxpayer(100003155);
    boss = taxpayerManager.getTaxpayer(823256400);
    assertEquals(alekos.calculateBasicTax(), 695.5);
    assertEquals(kostas.calculateBasicTax(), 14206.4951171875);
    assertEquals(boss.calculateBasicTax(), 45827.48046875);
    taxpayerManager.removeTaxpayer(100003045);
    taxpayerManager.removeTaxpayer(100003155);
    taxpayerManager.removeTaxpayer(823256400);
  }

}
