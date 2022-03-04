package com.lucasbohorquez.colors;

import android.graphics.Color;

import java.util.Random;

public class ColorsGame {
    private int targetBackColor = 0;
    private int targetTextColor = 0;
    private int proposedBackColor = 0;
    private int proposedTextColor = 0;
    private OnChangeTargetColorListener onChangeTargetColorListener;
    private OnChangeProposedColorListener onChangeProposedColorListener;

    public ColorsGame() {
        restartGame();
    }

    public void restartGame(){
        setTargetBackColor(randomColor());
    }

    // Return the distance between two colors
    public static double distance(int color1, int color2){
        int redVal1 = Color.red(color1);
        int greenVal1 = Color.green(color1);
        int blueVal1 = Color.blue(color1);

        int redVal2 = Color.red(color1);
        int greenVal2 = Color.green(color1);
        int blueVal2 = Color.blue(color1);

        int resRedVal = (int) Math.pow((redVal1 - redVal2), 2);
        int resGreenVal = (int) Math.pow((greenVal1 - greenVal2), 2);
        int resBlueVal = (int) Math.pow((blueVal1 - blueVal2), 2);

        return Math.sqrt(resRedVal + resGreenVal + resBlueVal);
    }

    public static int randomColor(){
        Random random = new Random();

        int redVal = random.nextInt(256);
        int greenVal= random.nextInt(256);
        int blueVal = random.nextInt(256);
        int color = Color.rgb(redVal, greenVal, blueVal);

        return color;
    }

    public static int getSuggestedTextColor(int backColor){
        int redVal = Color.red(backColor);
        int greenVal = Color.green(backColor);
        int blueVal = Color.red(backColor);
        int grayVal = (redVal + greenVal + blueVal) / 3;
        int textColor = Color.BLACK;

        if (255 - grayVal > grayVal){
            textColor = Color.WHITE;
        }

        return textColor;
    }

    public int getScore(){
        double dist = distance(targetBackColor, proposedBackColor);
        return (int) (100 - Math.min(dist, 100));
    }

    public interface OnChangeTargetColorListener{
        void onChange(int backColor, int textColor);
    }

    public interface OnChangeProposedColorListener{
        void onChange(int backColor, int textColor);
    }

    public int getTargetBackColor() {
        return targetBackColor;
    }

    public void setTargetBackColor(int targetBackColor) {
        this.targetBackColor = targetBackColor;
        targetTextColor = getSuggestedTextColor(targetBackColor);

        do {
            proposedBackColor = randomColor();
        }while (getScore() > 0);

        proposedTextColor = getSuggestedTextColor(proposedBackColor);

        if (getOnChangeTargetColorListener() != null){
            getOnChangeTargetColorListener().onChange(targetBackColor, targetTextColor);
        }

        if (getOnChangeProposedColorListener() != null){
            getOnChangeProposedColorListener().onChange(proposedBackColor, proposedTextColor);
        }
    }

    public int getTargetTextColor() {
        return targetTextColor;
    }

    public void setTargetTextColor(int targetTextColor) {
        this.targetTextColor = targetTextColor;
    }

    public int getProposedBackColor() {
        return proposedBackColor;
    }

    public void setProposedBackColor(int newBackColor) {
        this.proposedBackColor = newBackColor;
        proposedTextColor = getSuggestedTextColor(proposedBackColor);

        if (getOnChangeProposedColorListener() != null){
            getOnChangeProposedColorListener().onChange(proposedBackColor, proposedTextColor);
        }
    }

    public int getProposedTextColor() {
        return proposedTextColor;
    }

    public void setProposedTextColor(int proposedTextColor) {
        this.proposedTextColor = proposedTextColor;
    }

    public OnChangeTargetColorListener getOnChangeTargetColorListener() {
        return onChangeTargetColorListener;
    }

    public void setOnChangeTargetColorListener(OnChangeTargetColorListener onChangeTargetColorListener) {
        this.onChangeTargetColorListener = onChangeTargetColorListener;
    }

    public OnChangeProposedColorListener getOnChangeProposedColorListener() {
        return onChangeProposedColorListener;
    }

    public void setOnChangeProposedColorListener(OnChangeProposedColorListener onChangeProposedColorListener) {
        this.onChangeProposedColorListener = onChangeProposedColorListener;
    }
}
