package com.ozonicsky.colorchecker;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class ColorSeekListener implements OnSeekBarChangeListener {

    private Colors color;
    private MainActivity activity;

    public ColorSeekListener(Colors color, MainActivity activity) {
        this.color = color;
        this.activity = activity;
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        activity.setValue(color, progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
