package com.example.atyk.unitprice.model;

import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 2017/02/04.
 */
public class UnitPriceTest {
  private UnitPrice unitPrice;

  @Before public void setUp() throws Exception {
    unitPrice = new UnitPrice();
  }

  @Test public void calculateUnitPrice_1() {
      unitPrice.calculateUnitPrice();
      assertThat(unitPrice.getUnitPriceString(), is("0"));
  }

  @Test public void calculateUnitPrice_2() {
    unitPrice.setPrice(BigDecimal.valueOf(200));
    unitPrice.setNet(BigDecimal.valueOf(100));
    unitPrice.calculateUnitPrice();
    assertThat(unitPrice.getUnitPriceString(), is("200.0"));
  }

  @Test public void calculateUnitPrice_3() {
    unitPrice.setPrice(BigDecimal.valueOf(480));
    unitPrice.setNet(BigDecimal.valueOf(500));
    unitPrice.calculateUnitPrice();
    assertThat(unitPrice.getUnitPriceString(), is("96.0"));
  }

  @Test public void calculateUnitPrice_4() {
    unitPrice.setPrice(BigDecimal.valueOf(1000));
    unitPrice.setNet(BigDecimal.valueOf(300));
    unitPrice.calculateUnitPrice();
    assertThat(unitPrice.getUnitPriceString(), is("333.3"));
  }

  @Test public void calculateUnitPrice_5() {
    unitPrice.setPrice(BigDecimal.valueOf(2000));
    unitPrice.setNet(BigDecimal.valueOf(300));
    unitPrice.calculateUnitPrice();
    assertThat(unitPrice.getUnitPriceString(), is("666.7"));
  }
}