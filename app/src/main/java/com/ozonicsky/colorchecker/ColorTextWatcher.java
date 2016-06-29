package com.ozonicsky.colorchecker;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class ColorTextWatcher implements TextWatcher {

    private Colors color;
    private MainActivity activity;
    private EditText editText;

    public ColorTextWatcher(Colors color, MainActivity activity, EditText editText) {
        this.color = color;
        this.activity = activity;
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        try {
            activity.setValue(color, Integer.parseInt(editText.getText().toString()));
        } catch (Exception ignored) {
        }
    }
}
