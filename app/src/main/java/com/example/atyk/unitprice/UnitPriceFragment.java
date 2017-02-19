package com.example.atyk.unitprice;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnFocusChange;
import butterknife.Unbinder;
import com.example.atyk.unitprice.model.UnitPrice;
import org.parceler.Parcels;

/**
 * Created on 2017/02/03.
 */

public class UnitPriceFragment extends Fragment {
  private static final String TAG = "UnitPriceFragment";
  private static final String ARG_UNIT_PRICES = "ARG_UNIT_PRICES";

  private Unbinder unbinder;
  private Context context;
  private FocusedFrameValueListener listener;
  @BindView(R.id.net_text_1) UnitPriceEditText netText1;
  @BindView(R.id.net_text_2) UnitPriceEditText netText2;
  @BindView(R.id.price_text_1) UnitPriceEditText priceText1;
  @BindView(R.id.price_text_2) UnitPriceEditText priceText2;
  @BindView(R.id.unit_price_text_1) TextView unitText1;
  @BindView(R.id.unit_price_text_2) TextView unitText2;

  public static UnitPriceFragment newInstance(UnitPrice[] unitPrices) {
    final Bundle bundle = new Bundle();
    final Parcelable[] parcelables = { Parcels.wrap(unitPrices[0]), Parcels.wrap(unitPrices[1]) };
    bundle.putParcelableArray(ARG_UNIT_PRICES, parcelables);
    final UnitPriceFragment fragment = new UnitPriceFragment();
    fragment.setArguments(bundle);
    return fragment;
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    this.context = context;
    if (context instanceof FocusedFrameValueListener) {
      listener = (FocusedFrameValueListener) context;
    }
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.unitprice_fragment, container, false);
    unbinder = ButterKnife.bind(this, view);
    setValues();

    return view;
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @Override public void onPause() {
    super.onPause();
  }

  @Override public void onResume() {
    super.onResume();
    updateView();
  }

  @OnFocusChange(R.id.net_text_1) void onFocusChangeNet1(UnitPriceEditText view, boolean hasFocus) {
    updateFocusFrame(view, hasFocus);
    Log.v(TAG, "net1: " + hasFocus);
    if (hasFocus) sendValue(0, view);
  }

  @OnFocusChange(R.id.net_text_2) void onFocusChangeNet2(UnitPriceEditText view, boolean hasFocus) {
    updateFocusFrame(view, hasFocus);
    Log.v(TAG, "net2: " + hasFocus);
    if (hasFocus) sendValue(1, view);
  }

  @OnFocusChange(R.id.price_text_1) void onFocusChangePrice1(UnitPriceEditText view,
      boolean hasFocus) {
    updateFocusFrame(view, hasFocus);
    Log.v(TAG, "price1: " + hasFocus);
    if (hasFocus) sendValue(2, view);
  }

  @OnFocusChange(R.id.price_text_2) void onFocusChangePrice2(UnitPriceEditText view,
      boolean hasFocus) {
    updateFocusFrame(view, hasFocus);
    Log.v(TAG, "price2: " + hasFocus);
    if (hasFocus) sendValue(3, view);
  }

  public void changeFocusOnAllClear() {
    netText1.setFocusableInTouchMode(true);
    netText1.requestFocus();
  }

  public void updateView() {
    setValues();
  }

  private void sendValue(int indicator, UnitPriceEditText view) {
    final String value = view.getText().toString();
    listener.getFocusedFrameValue(indicator, value);
  }

  private void updateFocusFrame(UnitPriceEditText view, boolean hasFocus) {
    final Drawable drawable;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      if (hasFocus) {
        drawable = context.getDrawable(R.drawable.focused_round_frame_display);
      } else {
        drawable = context.getDrawable(R.drawable.round_frame_display);
      }
    } else {
      if (hasFocus) {
        drawable = getResources().getDrawable(R.drawable.focused_round_frame_display);
      } else {
        drawable = getResources().getDrawable(R.drawable.round_frame_display);
      }
    }
    view.setBackground(drawable);
  }

  private void setValues() {
    final Parcelable[] parcelables = getArguments().getParcelableArray(ARG_UNIT_PRICES);
    if (parcelables == null) return;
    final UnitPrice unitPrice1 = Parcels.unwrap(parcelables[0]);
    final UnitPrice unitPrice2 = Parcels.unwrap(parcelables[1]);
    netText1.setText(unitPrice1.getNetString());
    priceText1.setText(unitPrice1.getPriceString());
    netText2.setText(unitPrice2.getNetString());
    priceText2.setText(unitPrice2.getPriceString());
    unitText1.setText(unitPrice1.getUnitPriceString());
    unitText2.setText(unitPrice2.getUnitPriceString());
  }

  public interface FocusedFrameValueListener {
    void getFocusedFrameValue(int indicator, String value);
  }
}
