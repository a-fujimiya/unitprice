package com.example.atyk.unitprice;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.EditText;

/**
 * Created on 2017/02/04.
 */

public class UnitPriceEditText extends EditText {
  public UnitPriceEditText(Context context, AttributeSet attrs) {
    super(context, attrs);
    setCustomSelectionActionModeCallback(new NoSelectionMode());
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    if (event.getActionMasked() == MotionEvent.ACTION_UP) {
      // Hack to prevent keyboard and insertion handle from showing.
      cancelLongPress();
    }
    return super.onTouchEvent(event);
  }

  @Override public boolean performLongClick() {
    // Prevents keyboard showing and selection text.
    return true;
  }

  @Override
  protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
    super.onTextChanged(text, start, lengthBefore, lengthAfter);
    setSelection(lengthAfter);
  }

  static class NoSelectionMode implements ActionMode.Callback {
    @Override public boolean onCreateActionMode(ActionMode mode, Menu menu) {
      // Prevents the selection action mode on double tap.
      return false;
    }

    @Override public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
      return false;
    }

    @Override public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
      return false;
    }

    @Override public void onDestroyActionMode(ActionMode mode) {

    }
  }
}
