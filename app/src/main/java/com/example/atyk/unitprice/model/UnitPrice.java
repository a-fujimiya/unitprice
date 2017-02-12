package com.example.atyk.unitprice.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.parceler.Parcel;

/**
 * Created on 2017/02/04.
 */
@Parcel public class UnitPrice {
  private static final BigDecimal HUNDRED = BigDecimal.valueOf(100L);
  BigDecimal net = BigDecimal.ZERO;
  BigDecimal price = BigDecimal.ZERO;
  BigDecimal unitPrice = BigDecimal.ZERO;

  public void setNet(BigDecimal value) {
    net = value;
  }

  public void setPrice(BigDecimal value) {
    price = value;
  }

  public String getNetString() {
    return net.toPlainString();
  }

  public String getPriceString() {
    return price.toPlainString();
  }

  public String getUnitPriceString() {
    return unitPrice.toPlainString();
  }

  public void setValues(UnitPrice values) {
    net = values.net;
    price = values.price;
    unitPrice = values.unitPrice;
  }

  public void calculateUnitPrice() {
    if (net.compareTo(BigDecimal.ZERO) != 0) {
      unitPrice = price.multiply(HUNDRED).divide(net, 1, RoundingMode.HALF_UP);
    } else {
      unitPrice = BigDecimal.ZERO;
    }
  }

  public static BigDecimal addInputDigit(BigDecimal value, int num) {
    return value.multiply(BigDecimal.TEN).add(BigDecimal.valueOf(num));
  }

  public static BigDecimal deleteLastDigit(BigDecimal value) {
    return value.divide(BigDecimal.TEN, 0, RoundingMode.DOWN);
  }
}
