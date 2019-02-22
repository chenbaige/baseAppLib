/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.mylibrary.utils;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.media.ExifInterface;
import android.widget.TextView;

import java.io.IOException;

import static com.example.mylibrary.utils.ColorUtil.getColorStateList;
import static com.example.mylibrary.utils.ColorUtil.getRandomColor;

/**
 * ================================================
 * 处理 {@link Drawable} 和 {@link Bitmap} 的工具类
 * <p>
 * Created by JessYan on 2015/11/24
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public class DrawableProvider {

    private DrawableProvider() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    /**
     * 获得选择器
     *
     * @param normalDrawable
     * @param pressDrawable
     * @return
     */
//    public static Drawable getStateListDrawable(Drawable normalDrawable, Drawable pressDrawable) {
//        StateListDrawable stateListDrawable = new StateListDrawable();
//        stateListDrawable.addState(new int[]{android.R.attr.state_checked}, pressDrawable);
//        stateListDrawable.addState(new int[]{}, normalDrawable);
//        return stateListDrawable;
//    }

    /**
     * 背景选择器
     *
     * @param pressedDrawable 按下状态的Drawable
     * @param normalDrawable  正常状态的Drawable
     * @return 状态选择器
     */
    public static StateListDrawable getStateListDrawable(Drawable pressedDrawable, Drawable normalDrawable) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_enabled, android.R.attr.state_pressed}, pressedDrawable);
        stateListDrawable.addState(new int[]{android.R.attr.state_enabled}, normalDrawable);
        //设置不能用的状态
        //默认其他状态背景
        GradientDrawable gray = getSolidRectDrawable(10, Color.GRAY);
        stateListDrawable.addState(new int[]{}, gray);
        return stateListDrawable;
    }


    /**
     * 将 TextView/RadioButton 中设置的 drawable 动态的缩放
     *
     * @param percent
     * @param tv
     * @return
     */
    public static Drawable getScaleDrawableForRadioButton(float percent, TextView tv) {
        Drawable[] compoundDrawables = tv.getCompoundDrawables();
        Drawable drawable = null;
        for (Drawable d : compoundDrawables) {
            if (d != null) {
                drawable = d;
            }
        }
        return getScaleDrawable(percent, drawable);
    }

    /**
     * 将 TextView/RadioButton 中设置的 drawable 动态的缩放
     *
     * @param tv
     * @return
     */
    public static Drawable getScaleDrawableForRadioButton2(float width, TextView tv) {
        Drawable[] compoundDrawables = tv.getCompoundDrawables();
        Drawable drawable = null;
        for (Drawable d : compoundDrawables) {
            if (d != null) {
                drawable = d;
            }
        }
        return getScaleDrawable2(width, drawable);
    }

    /**
     * 传入图片,将图片按传入比例缩放
     *
     * @param percent
     * @return
     */
    public static Drawable getScaleDrawable(float percent, Drawable drawable) {
        drawable.setBounds(0, 0, (int) (drawable.getIntrinsicWidth() * percent + 0.5f), (int) (drawable.getIntrinsicHeight() * percent + 0.5f));
        return drawable;
    }

    /**
     * 传入图片,将图片按传入宽度和原始宽度的比例缩放
     *
     * @param width
     * @return
     */
    public static Drawable getScaleDrawable2(float width, Drawable drawable) {
        float percent = width * 1.0f / drawable.getIntrinsicWidth();
        return getScaleDrawable(percent, drawable);
    }

    /**
     * 设置左边的drawable
     *
     * @param tv
     * @param drawable
     */
    public static void setLeftDrawable(TextView tv, Drawable drawable) {
        tv.setCompoundDrawables(drawable, null, null, null);
    }

    /**
     * 改变Bitmap的长宽
     *
     * @param bitmap
     * @return
     */
    public static Bitmap getReSizeBitmap(Bitmap bitmap, float targetWidth, float targetheight) {
        Bitmap returnBm = null;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(targetWidth / width, targetheight / height); //长和宽放大缩小的比例
        try {
            returnBm = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (returnBm == null) {
            returnBm = bitmap;
        }
        if (bitmap != returnBm) {
            bitmap.recycle();
        }
        return returnBm;
    }

    /**
     * 将图片按照某个角度进行旋转
     *
     * @param bm     需要旋转的图片
     * @param degree 旋转角度
     * @return 旋转后的图片
     */
    public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
        Bitmap returnBm = null;

        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        if (returnBm == null) {
            returnBm = bm;
        }
        if (bm != returnBm) {
            bm.recycle();
        }
        return returnBm;
    }

    /**
     * 读取图片的旋转的角度
     *
     * @param path 图片绝对路径
     * @return 图片的旋转角度
     */
    public static int getBitmapDegree(String path) {
        int degree = 0;
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);
            // 获取图片的旋转信息
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }


    /**
     * 得到实心的drawable, 一般作为选中，点中的效果
     *
     * @param cornerRadius 圆角半径
     * @param solidColor   实心颜色
     * @return 得到实心效果
     */
    public static GradientDrawable getSolidRectDrawable(int cornerRadius, int solidColor) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        // 设置矩形的圆角半径
        gradientDrawable.setCornerRadius(cornerRadius);
        // 设置绘画图片色值
        gradientDrawable.setColor(solidColor);
        // 绘画的是矩形
        gradientDrawable.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        return gradientDrawable;
    }

    /**
     * 得到空心的效果，一般作为默认的效果
     *
     * @param cornerRadius 圆角半径
     * @param solidColor   实心颜色
     * @param strokeColor  边框颜色
     * @param strokeWidth  边框宽度
     * @return 得到空心效果
     */
    public static GradientDrawable getStrokeRectDrawable(int cornerRadius, int solidColor, int strokeColor, int strokeWidth) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setStroke(strokeWidth, strokeColor);
        gradientDrawable.setColor(solidColor);
        gradientDrawable.setCornerRadius(cornerRadius);
        gradientDrawable.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        return gradientDrawable;

    }


    /**
     * 实体  状态选择器
     *
     * @param cornerRadius 圆角半径
     * @param pressedColor 按下颜色
     * @param normalColor  正常的颜色
     * @return 状态选择器
     */
    public static StateListDrawable getDrawable(int cornerRadius, int pressedColor, int normalColor) {
        return getStateListDrawable(getSolidRectDrawable(cornerRadius, pressedColor), getSolidRectDrawable(cornerRadius, normalColor));
    }

    /**
     * 得到 正常空心， 按下实体的状态选择器
     *
     * @param cornerRadiusPX 圆角半径
     * @param strokeWidthPX  边框宽度
     * @param subColor       表框颜色
     * @param mainColor      实心颜色
     * @return 状态选择器
     */
    public static StateListDrawable getStrokeSolidDrawable(int cornerRadiusPX, int strokeWidthPX, int subColor, int mainColor) {
        //一般solidColor 为透明
        return getStateListDrawable(
                //实心
                getSolidRectDrawable(cornerRadiusPX, subColor),
                //空心
                getStrokeRectDrawable(cornerRadiusPX, mainColor, subColor, strokeWidthPX));
    }

    /**
     * 得到 正常空心， 按下实体的状态选择器
     *
     * @param cornerRadiusPX 圆角半径
     * @param strokeWidthPX  边框宽度
     * @param subColor       表框颜色
     * @param mainColor      实心颜色
     * @return 状态选择器
     */
    public static StateListDrawable getSolidStrokeDrawable(int cornerRadiusPX, int strokeWidthPX, int subColor, int mainColor) {
        //一般solidColor 为透明
        return getStateListDrawable(
                //空心
                getStrokeRectDrawable(cornerRadiusPX, subColor, mainColor, strokeWidthPX),
                //实心
                getSolidRectDrawable(cornerRadiusPX, mainColor));
    }

    /**
     * 实体 按下的颜色加深
     *
     * @param cornerRadius 圆角半径
     * @param normalColor  正常的颜色
     * @return 状态选择器
     */

    public static StateListDrawable getDrawable(int cornerRadius, int normalColor) {
        return getDrawable(cornerRadius, ColorUtil.colorDeep(normalColor), normalColor);
    }

    /**
     * 实体 得到随机色 状态选择器
     *
     * @param cornerRadius 圆角半径
     * @return 状态选择器
     */

    public static StateListDrawable getDrawable(int cornerRadius) {
        return getDrawable(cornerRadius, getRandomColor());
    }

    /**
     * 实体 得到随机色 状态选择器 默认10px
     *
     * @return 状态选择器
     */

    public static StateListDrawable getDrawable() {
        return getDrawable(10);
    }


    /**
     * 实心 得到 随机背景色并且带选择器, 并且可以设置圆角
     *
     * @param cornerRadius 圆角
     * @return 状态选择器
     */
    public static StateListDrawable getRandomColorDrawable(int cornerRadius) {
        return getDrawable(cornerRadius, getRandomColor(), getRandomColor());

    }

    /**
     * 实心 得到随机背景色并且带选择器, 并且可以设置圆角
     *
     * @return 状态选择器
     */
    public static StateListDrawable getRandomColorDrawable() {
        return getRandomColorDrawable(10);

    }

    /**
     * 空心，按下实心 得到随机背景色并且带选择器, 并且可以设置圆角
     *
     * @return 状态选择器
     */
    public static StateListDrawable getStrokeRandomColorDrawable() {
        return getStrokeSolidDrawable(10, 4, getRandomColor(), Color.TRANSPARENT);
    }

    /**
     * 默认空心 设置TextView 主题，
     *
     * @param textView     textView
     * @param strokeWidth  边框宽度 px
     * @param cornerRadius 圆角
     * @param color        颜色
     */
    public static void setTextStrokeTheme(TextView textView, int strokeWidth, int cornerRadius, int color) {
        textView.setBackgroundDrawable(getStrokeSolidDrawable(cornerRadius, strokeWidth, color, Color.WHITE));
        textView.setTextColor(getColorStateList(Color.WHITE, color));
        textView.getPaint().setFlags(Paint.FAKE_BOLD_TEXT_FLAG);
    }

    /**
     * 默认空心 设置TextView 主题，随机颜色
     *
     * @param textView     textView
     * @param strokeWidth  边框宽度 px
     * @param cornerRadius 圆角
     */
    public static void setTextStrokeTheme(TextView textView, int strokeWidth, int cornerRadius) {
        setTextStrokeTheme(textView, strokeWidth, cornerRadius, getRandomColor());
    }

    /**
     * 默认空心 设置TextView 主题，随机颜色
     *
     * @param textView textView
     */
    public static void setTextStrokeTheme(TextView textView) {
        setTextStrokeTheme(textView, 6, 10);
    }

    /**
     * 默认空心 设置TextView 主题，随机颜色
     * @param textView 文本控件
     * @param color 颜色
     */
    public static void setTextStrokeTheme(TextView textView, int color) {
        setTextStrokeTheme(textView, 6, 10, color);
    }

    /**
     * 默认实心 设置TextView 主题，
     *
     * @param textView     textView
     * @param strokeWidth  边框宽度 px
     * @param cornerRadius 圆角
     * @param color        颜色
     */
    public static void setTextSolidTheme(TextView textView, int strokeWidth, int cornerRadius, int color) {
        textView.setBackgroundDrawable(getSolidStrokeDrawable(cornerRadius, strokeWidth, Color.WHITE, color));
        textView.setTextColor(getColorStateList(color, Color.WHITE));
        textView.getPaint().setFlags(Paint.FAKE_BOLD_TEXT_FLAG);
    }

    /**
     * 默认实心 设置TextView 主题，随机颜色
     *
     * @param textView     textView
     * @param strokeWidth  边框宽度 px
     * @param cornerRadius 圆角
     */
    public static void setTextSolidTheme(TextView textView, int strokeWidth, int cornerRadius) {
        setTextSolidTheme(textView, strokeWidth, cornerRadius, getRandomColor());
    }

    /**
     * 默认实心 设置TextView 主题，随机颜色
     *
     * @param textView textView
     */
    public static void setTextSolidTheme(TextView textView) {
        setTextSolidTheme(textView, 6, 10);
    }
}