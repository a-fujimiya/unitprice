package com.example.atyk.unitprice;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.example.atyk.unitprice.MainActivity.ALL_CLEAR;
import static com.example.atyk.unitprice.MainActivity.CALCULATE;
import static com.example.atyk.unitprice.MainActivity.CLEAR;

/**
 * Created on 2017/02/03.
 */

public class TenkeyFragment extends Fragment {
  private static final String TAG = "TenkeyFragment";

  private Unbinder unbinder;
  private OnClickNumPadListener listener;
  @BindView(R.id.button_0) Button button0;
  @BindView(R.id.button_1) Button button1;
  @BindView(R.id.button_2) Button button2;
  @BindView(R.id.button_3) Button button3;
  @BindView(R.id.button_4) Button button4;
  @BindView(R.id.button_5) Button button5;
  @BindView(R.id.button_6) Button button6;
  @BindView(R.id.button_7) Button button7;
  @BindView(R.id.button_8) Button button8;
  @BindView(R.id.button_9) Button button9;
  @BindView(R.id.button_go) Button calculate;
  @BindView(R.id.button_all_clear) Button allClear;
  @BindView(R.id.button_clear) Button clear;

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnClickNumPadListener) {
      listener = (OnClickNumPadListener) context;
    }
  }

  @Override public void onDetach() {
    listener = null;
    super.onDetach();
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.tenkey_fragment, container, false);
    unbinder = ButterKnife.bind(this, view);

    return view;
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @OnClick(R.id.button_0) void onClickButton0() {
    notifyClick(0);
  }

  @OnClick(R.id.button_1) void onClickButton1() {
    notifyClick(1);
  }

  @OnClick(R.id.button_2) void onClickButton2() {
    notifyClick(2);
  }

  @OnClick(R.id.button_3) void onClickButton3() {
    notifyClick(3);
  }

  @OnClick(R.id.button_4) void onClickButton4() {
    notifyClick(4);
  }

  @OnClick(R.id.button_5) void onClickButton5() {
    notifyClick(5);
  }

  @OnClick(R.id.button_6) void onClickButton6() {
    notifyClick(6);
  }

  @OnClick(R.id.button_7) void onClickButton7() {
    notifyClick(7);
  }

  @OnClick(R.id.button_8) void onClickButton8() {
    notifyClick(8);
  }

  @OnClick(R.id.button_9) void onClickButton9() {
    notifyClick(9);
  }

  @OnClick(R.id.button_go) void onClickButtonGo() {
    notifyClick(CALCULATE);
  }

  @OnClick(R.id.button_all_clear) void onClickButtonAllClear() {
    notifyClick(ALL_CLEAR);
  }

  @OnClick(R.id.button_clear) void onClickButtonClear() {
    notifyClick(CLEAR);
  }

  private void notifyClick(int num) {
    if (listener != null) {
      listener.onClickNumPad(num);
    } else {
      Log.w(TAG, "listener is null: " + num);
    }
  }

  public interface OnClickNumPadListener {
    void onClickNumPad(int num);
  }
}
