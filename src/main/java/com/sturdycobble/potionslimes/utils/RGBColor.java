package com.sturdycobble.potionslimes.utils;

public class RGBColor {

    private int hexColor;
    private float alpha;

    public RGBColor(int colorIn, float alpha) {
        this.hexColor = colorIn;
        this.alpha = alpha;
    }

    public RGBColor(float alpha) {
        this.hexColor = 0;
        this.alpha = alpha;
    }

    public float getRed() {
        return Math.round(this.hexColor >> 16 & 255) / 255.0F;
    }

    public float getGreen() {
        return Math.round(this.hexColor >> 8 & 255) / 255.0F;
    }

    public float getBlue() {
        return Math.round(this.hexColor & 255) / 255.0F;
    }

    public float getAlpha() {
        return alpha;
    }

}
