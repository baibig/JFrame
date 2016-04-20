package com.huangjian.jframe.ui.view.image;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.GifRequestBuilder;
import com.bumptech.glide.Glide;
import com.huangjian.jframe.utils.StringUtils;

import java.io.File;

/**
 * Description:
 * Author: huangjian
 * Date: 16/2/23 下午2:19.
 */
public class JImageView extends ImageView {

    public JImageView(Context context) {
        super(context);
    }

    public JImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public JImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public JImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void display(File file, ImageOptions imageOptions) {
        display(Uri.fromFile(file), imageOptions);
    }

    public void display(Integer resId, ImageOptions imageOptions) {
        Uri uri = Uri.parse("android.resource://" + getContext().getPackageName() + "/" + String.valueOf(resId));
        display(uri, imageOptions);
    }

    public void display(String url, ImageOptions imageOptions) {
        Uri uri = null;
        if (!StringUtils.isEmpty(url)) {
            uri = Uri.parse(url);
        }
        display(uri, imageOptions);
    }

    public void display(Uri uri, ImageOptions options) {
        if(uri == null) {
            if(options != null) {
                if(options.getImageForEmptyUri() != null) {
                    Glide.with(getContext()).load(options.getImageForEmptyUri()).into(this);
                } else if(options.getImageResForEmptyUri() != 0) {
                    Glide.with(getContext()).load(options.getImageResForEmptyUri()).into(this);
                }
            }
        } else {
            if(options != null) {
                if (!options.getAsGif()) {
                    BitmapRequestBuilder builder = Glide.with(getContext()).load(uri).asBitmap();
                    // 设置loading图片
                    if (options.getImageOnLoading() != null) {
                        builder.placeholder(options.getImageOnLoading());
                    } else if (options.getImageResOnLoading() != 0) {
                        builder.placeholder(options.getImageResOnLoading());
                    }
                    // 设置加载失败图片
                    if (options.getImageOnFail() != null) {
                        builder.error(options.getImageOnFail());
                    } else if (options.getImageResOnFail() != 0) {
                        builder.error(options.getImageResOnFail());

                    }
                    // 设置缓存策略
                    if (options.getCacheStrategy() != null) {
                        builder.diskCacheStrategy(options.getCacheStrategy());
                    }
                    // 设置ScaleType
                    if (options.getImageScaleType() != null) {
                        if (options.getImageScaleType() == ImageOptions.ScaleType.CENTER_CROP) {
                            builder.centerCrop();
                        } else if (options.getImageScaleType() == ImageOptions.ScaleType.FIT_CENTER) {
                            builder.fitCenter();
                        }
                    }
                    // 设置transformation
                    if (options.getTransformationList() != null) {
                        builder.transform(options.getTransformationList());
                    }
                    // 设置动画
                    if (options.getShowAnimation()) {
                        if (options.getAnimation() != null) {
                            builder.animate(options.getAnimation());
                        } else if (options.getAnimResId() > 0) {
                            builder.animate(options.getAnimResId());
                        } else {
                        }
                    } else {
                        builder.dontAnimate();
                    }
                    // 重设图片尺寸
                    if (options.getImageSize() != null && options.getImageSize().getWidth() > 0 && options.getImageSize().getHeight() > 0) {
                        builder.override(options.getImageSize().getWidth(), options.getImageSize().getHeight());
                    }
                    builder.into(this);
                } else {
                    GifRequestBuilder builder = Glide.with(getContext()).load(uri).asGif();
                    builder.into(this);
                }
            } else {
                Glide.with(getContext()).load(uri).into(this);
            }
        }
    }
}
