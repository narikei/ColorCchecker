package com.ozonicsky.colorchecker;

import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    private int red = 255;
    private int green = 64;
    private int blue = 129;

    @InjectView(R.id.seekRed)
    SeekBar seekRed;
    @InjectView(R.id.numberRed)
    EditText numberRed;
    @InjectView(R.id.seekGreen)
    SeekBar seekGreen;
    @InjectView(R.id.numberGreen)
    EditText numberGreen;
    @InjectView(R.id.seekBlue)
    SeekBar seekBlue;
    @InjectView(R.id.numberBlue)
    EditText numberBlue;

    View mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        mainLayout = this.getWindow().getDecorView();
        mainLayout.forceLayout();

        updateColor();

        setSeekEvent();
        setTextEvent();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mainLayout.requestFocus();
        updateColor();
        return true;
    }

    public void tabHex(View view) {
        new AlertDialog.Builder(this).setTitle(ColorUtil.getHex(red, green, blue)).setPositiveButton("OK", null).show();
    }

    public void tabRandom(View view) {
        setRandomColor();
        updateColor();
    }

    private void setSeekEvent() {
        seekRed.setOnSeekBarChangeListener(new ColorSeekListener(Colors.RED, this));
        seekGreen.setOnSeekBarChangeListener(new ColorSeekListener(Colors.GREEN, this));
        seekBlue.setOnSeekBarChangeListener(new ColorSeekListener(Colors.BLUE, this));
    }

    private void setTextEvent() {
        numberRed.addTextChangedListener(new ColorTextWatcher(Colors.RED, this, numberRed));
        numberGreen.addTextChangedListener(new ColorTextWatcher(Colors.GREEN, this, numberGreen));
        numberBlue.addTextChangedListener(new ColorTextWatcher(Colors.BLUE, this, numberBlue));
    }


    private void updateColor() {
        mainLayout.setBackgroundColor(Color.rgb(red, green, blue));

        seekRed.setProgress(red);
        numberRed.setText(String.valueOf(red));
        seekGreen.setProgress(green);
        numberGreen.setText(String.valueOf(green));
        seekBlue.setProgress(blue);
        numberBlue.setText(String.valueOf(blue));
    }

    private void setRandomColor() {
        red   = (int)(Math.random() * 255);
        green = (int)(Math.random() * 255);
        blue  = (int)(Math.random() * 255);
    }

    protected void setValue(Colors color, int v) {
        if (v < 0) {
            v = 0;
        }
        if (v > 255) {
            v = 255;
        }

        if (color == Colors.RED) {
            if (red == v) {
                return;
            }
            red = v;
        }
        if (color == Colors.GREEN) {
            if (green == v) {
                return;
            }
            green = v;
        }
        if (color == Colors.BLUE) {
            if (blue == v) {
                return;
            }
            blue = v;
        }

        updateColor();
    }

}
