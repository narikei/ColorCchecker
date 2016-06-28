package com.ozonicsky.colorchecker;

import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

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

        setColor();

        setSeekEvent();
        setTextEvent();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mainLayout.requestFocus();
        setColor();
        return true;
    }

    public void tabHex(View view) {
        new AlertDialog.Builder(this).setTitle(getHex()).setPositiveButton("OK", null).show();
    }

    public void tabRandom(View view) {
        setRandomColor();
        setColor();
    }

    private void setSeekEvent() {
        seekRed.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setValue(Colors.RED, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        seekGreen.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setValue(Colors.GREEN, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        seekBlue.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setValue(Colors.BLUE, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void setTextEvent() {
        numberRed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    setValue(Colors.RED, Integer.parseInt(numberRed.getText().toString()));
                } catch (Exception ignored) {
                }
            }
        });

        numberGreen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    setValue(Colors.GREEN, Integer.parseInt(numberGreen.getText().toString()));
                } catch (Exception ignored) {
                }
            }
        });

        numberBlue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    setValue(Colors.BLUE, Integer.parseInt(numberBlue.getText().toString()));
                } catch (Exception ignored) {
                }
            }
        });
    }


    private void setColor() {
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

    private void setValue(Colors color, int v) {
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

        setColor();
    }

    private String getHex() {
        return "#" + zeroFill(Integer.toHexString(red)) + zeroFill(Integer.toHexString(green)) + zeroFill(Integer.toHexString(blue));
    }

    private String zeroFill(String v) {
        if (v.length() == 1) {
            return "0" + v;
        }
        return v;
    }

    private enum Colors {
        RED,
        GREEN,
        BLUE
    }
}
