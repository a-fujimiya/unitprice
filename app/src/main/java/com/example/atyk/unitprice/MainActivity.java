package com.example.atyk.unitprice;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.example.atyk.unitprice.model.UnitPrice;
import java.math.BigDecimal;
import org.parceler.Parcels;

public class MainActivity extends AppCompatActivity
    implements TenkeyFragment.OnClickNumPadListener, UnitPriceFragment.FocusedFrameValueListener {
  private static final String TAG = "MainActivity";
  private static final String STATE_UNIT_PRICES = "STATE_UNIT_PRICES";
  private static final String FOCUSED_VALUE = "FOCUSED_VALUE";
  static final int CLEAR = 11;
  static final int ALL_CLEAR = 12;
  static final int CALCULATE = 13;
  private final UnitPrice[] unitPrices = { new UnitPrice(), new UnitPrice() };
  private int indicator;
  private BigDecimal value;
  private UnitPriceFragment unitPriceFragment;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    final FragmentManager manager = getSupportFragmentManager();
    final FragmentTransaction transaction = manager.beginTransaction();
    unitPriceFragment = UnitPriceFragment.newInstance(unitPrices);
    transaction.replace(R.id.edit_display, unitPriceFragment);
    transaction.commit();
  }

  @Override public void onAttachFragment(Fragment fragment) {
    super.onAttachFragment(fragment);
    if (fragment != null && fragment instanceof UnitPriceFragment) {
      unitPriceFragment = (UnitPriceFragment) fragment;
    }
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    final Parcelable[] parcelables = { Parcels.wrap(unitPrices[0]), Parcels.wrap(unitPrices[1]) };
    outState.putParcelableArray(STATE_UNIT_PRICES, parcelables);
    outState.putSerializable(FOCUSED_VALUE, value);
  }

  @Override protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    final Parcelable[] parcelables = savedInstanceState.getParcelableArray(STATE_UNIT_PRICES);
    if (parcelables != null) {
      unitPrices[0].setValues((UnitPrice) Parcels.unwrap(parcelables[0]));
      unitPrices[1].setValues((UnitPrice) Parcels.unwrap(parcelables[1]));
      value = (BigDecimal) savedInstanceState.getSerializable(FOCUSED_VALUE);
    }
  }

  @Override public void onClickNumPad(int num) {
    Log.v(TAG, "num: " + num);
    if (num < CLEAR) {
      value = UnitPrice.addInputDigit(value, num);
      setInputValue();
    } else if (num == CLEAR) {
      clearLastOneDigit();
      setInputValue();
    } else if (num == ALL_CLEAR) {
      clearAllData();
      calculateUnitPrice();
    } else if (num == CALCULATE) {
      calculateUnitPrice();
    }
    unitPriceFragment.updateView();
  }

  @Override public void getFocusedFrameValue(int indicator, String value) {
    this.indicator = indicator;
    this.value = new BigDecimal(value);
  }

  private void setInputValue() {
    switch (indicator) {
      case 0:
        unitPrices[0].setNet(value);
        break;
      case 1:
        unitPrices[1].setNet(value);
        break;
      case 2:
        unitPrices[0].setPrice(value);
        break;
      case 3:
        unitPrices[1].setPrice(value);
        break;
      default:
        Log.w(TAG, "default: " + indicator);
    }
  }

  private void clearLastOneDigit() {
    value = UnitPrice.deleteLastDigit(value);
  }

  private void clearAllData() {
    unitPrices[0].setNet(BigDecimal.ZERO);
    unitPrices[0].setPrice(BigDecimal.ZERO);
    unitPrices[1].setNet(BigDecimal.ZERO);
    unitPrices[1].setPrice(BigDecimal.ZERO);
    value = BigDecimal.ZERO;
  }

  private void calculateUnitPrice() {
    unitPrices[0].calculateUnitPrice();
    unitPrices[1].calculateUnitPrice();
  }
}
