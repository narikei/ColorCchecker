package com.ozonicsky.colorchecker;

public class ColorUtil {
    public static String getHex(int red, int green, int blue) {
        return "#" + zeroFill(Integer.toHexString(red)) + zeroFill(Integer.toHexString(green)) + zeroFill(Integer.toHexString(blue));
    }

    public static String zeroFill(String v) {
        if (v.length() == 1) {
            return "0" + v;
        }
        return v;
    }
}
