package utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.animation.Transformation;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * 项目名：BombDemo
 * 包名：utils
 * 文件名：PicassoUtils
 * 创建者：Gyk
 * 创建时间：2018/5/11 10:12
 * 描述：  TODO
 */

public class PicassoUtils {
    //默认加载图片
    public static void loadImaheView(Context mContext, String url, ImageView imageView) {
        Picasso.with(mContext).load(url).into(imageView);
    }

    //默认加载图片(指定大小)
    public static void loadImageViewSize(Context mContext, String url, int width, int height, ImageView imageView) {
        Picasso.with(mContext).load(url).config(Bitmap.Config.RGB_565).resize(width, height).centerCrop().into(imageView);
    }

    //加载图片有默认图片
    public static void loadImageViewHolder(Context mContext, String url, int loadImg,
                                           int errorImg, ImageView imageView) {
        Picasso.with(mContext).load(url).placeholder(loadImg).error(errorImg)
                .into(imageView);
    }

    //裁剪图片
    public static void loadImageViewCrop(Context mContext, String url,ImageView imageView){
        Picasso.with(mContext).load(url).transform(new CropSquareTransformation()).into(imageView);
    }

    //按比例裁剪 矩形
    public static class CropSquareTransformation implements com.squareup.picasso.Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;
            Bitmap result = Bitmap.createBitmap(source, x, y, size, size);
            if (result != source) {
                //回收
                source.recycle();
            }
            return result;
        }

        @Override public String key() {
            return "lgl";
        }
    }

}
