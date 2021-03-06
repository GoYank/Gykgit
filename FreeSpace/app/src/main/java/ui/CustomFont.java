package ui;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * 项目名：BombDemo
 * 包名：ui
 * 文件名：CustomFontTextView
 * 创建者：Gyk
 * 创建时间：2018/4/26 12:49
 * 描述：  字体样式
 */

public class CustomFont extends android.support.v7.widget.AppCompatTextView {

    public CustomFont(Context context) {
        super(context);
        init(context);
    }


    public CustomFont(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomFont(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        AssetManager assetManager = context.getAssets();
        Typeface font = Typeface.createFromAsset(assetManager, "fonts/huazhong.ttf");
        setTypeface(font);
    }
}
