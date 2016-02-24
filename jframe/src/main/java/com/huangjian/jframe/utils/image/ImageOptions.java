package com.huangjian.jframe.utils.image;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.DrawableCrossFadeViewAnimation;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;

import java.util.List;

/**
 * Description:
 * Author: huangjian
 * Date: 16/2/23 下午2:58.
 */
public class ImageOptions {
    private int imageResOnLoading = 0;
    private int imageResForEmptyUri = 0;
    private int imageResOnFail = 0;
    private Drawable imageOnLoading = null;
    private Drawable imageForEmptyUri = null;
    private Drawable imageOnFail = null;
    private Drawable imageProgress = null;
    private ImageOptions.ScaleType imageScaleType;
    private ImageOptions.ScaleType loadingScaleType;
    private ImageOptions.ScaleType emptyScaleType;
    private ImageOptions.ScaleType failScaleType;
    private ImageOptions.ImageSize imageSize;
    private boolean asGif;
    private Transformation<Bitmap>[] transformationList;
    private DiskCacheStrategy cacheStrategy;
    private boolean showAnimation;
    private ViewPropertyAnimation.Animator animation;
    private int animResId;

    public ImageOptions(ImageOptions.Builder builder) {
        this.imageScaleType = ImageOptions.ScaleType.CENTER_CROP;
        this.loadingScaleType = ImageOptions.ScaleType.CENTER_CROP;
        this.emptyScaleType = ImageOptions.ScaleType.CENTER_CROP;
        this.failScaleType = ImageOptions.ScaleType.CENTER_CROP;
        this.imageSize = null;
        this.imageResOnLoading = builder.imageResOnLoading;
        this.imageResForEmptyUri = builder.imageResForEmptyUri;
        this.imageResOnFail = builder.imageResOnFail;
        this.imageOnLoading = builder.imageOnLoading;
        this.imageForEmptyUri = builder.imageForEmptyUri;
        this.imageOnFail = builder.imageOnFail;
        this.imageProgress = builder.imageProgress;
        this.imageScaleType = builder.imageScaleType;
        this.loadingScaleType = builder.loadingScaleType;
        this.emptyScaleType = builder.emptyScaleType;
        this.failScaleType = builder.failScaleType;
        this.imageSize = builder.imageSize;
        this.transformationList = builder.transformationList;
        this.asGif = builder.asGif;
        this.cacheStrategy = builder.cacheStrategy;
        this.showAnimation = builder.showAnimation;
        this.animation = builder.animation;
        this.animResId = builder.animResId;
    }

    public int getImageResOnLoading() {
        return this.imageResOnLoading;
    }

    public int getImageResForEmptyUri() {
        return this.imageResForEmptyUri;
    }

    public int getImageResOnFail() {
        return this.imageResOnFail;
    }

    public Drawable getImageOnLoading() {
        return this.imageOnLoading;
    }

    public Drawable getImageForEmptyUri() {
        return this.imageForEmptyUri;
    }

    public Drawable getImageOnFail() {
        return this.imageOnFail;
    }

    public ImageOptions.ScaleType getEmptyScaleType() {
        return this.emptyScaleType;
    }

    public ImageOptions.ScaleType getFailScaleType() {
        return this.failScaleType;
    }

    public ImageOptions.ScaleType getLoadingScaleType() {
        return this.loadingScaleType;
    }

    public ImageOptions.ScaleType getImageScaleType() {
        return this.imageScaleType;
    }

    public ImageOptions.ImageSize getImageSize() {
        return this.imageSize;
    }

    public Transformation<Bitmap>[] getTransformationList() {return this.transformationList;}

    public boolean getAsGif() {return this.asGif;}

    public DiskCacheStrategy getCacheStrategy() {return this.cacheStrategy;}

    public Drawable getImageProgress() {
        return this.imageProgress;
    }

    public boolean getShowAnimation() {return this.showAnimation;}

    public ViewPropertyAnimation.Animator getAnimation() {return this.animation;}

    public int getAnimResId() {return this.animResId;}

    public static class ImageSize {
        private int width;
        private int height;

        public ImageSize() {
        }

        public ImageSize(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getWidth() {
            return this.width;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getHeight() {
            return this.height;
        }
    }

    public static enum ScaleType {
        FIT_XY,
        FIT_START,
        FIT_CENTER,
        FIT_END,
        CENTER,
        CENTER_INSIDE,
        CENTER_CROP,
        FOCUS_CROP;

        private ScaleType() {
        }
    }

    public static class Builder {
        private int imageResOnLoading = 0;
        private int imageResForEmptyUri = 0;
        private int imageResOnFail = 0;
        private Drawable imageOnLoading = null;
        private Drawable imageForEmptyUri = null;
        private Drawable imageOnFail = null;
        private Drawable imageProgress = null;
        private ImageOptions.ScaleType imageScaleType;
        private ImageOptions.ScaleType loadingScaleType;
        private ImageOptions.ScaleType emptyScaleType;
        private ImageOptions.ScaleType failScaleType;
        private ImageOptions.ImageSize imageSize;
        private Transformation<Bitmap>[] transformationList;
        private boolean asGif;
        private DiskCacheStrategy cacheStrategy;
        private boolean showAnimation;
        private ViewPropertyAnimation.Animator animation;
        private int animResId;

        public Builder() {
            this.imageScaleType = ImageOptions.ScaleType.CENTER_CROP;
            this.loadingScaleType = ImageOptions.ScaleType.CENTER_CROP;
            this.emptyScaleType = ImageOptions.ScaleType.CENTER_CROP;
            this.failScaleType = ImageOptions.ScaleType.CENTER_CROP;
            this.imageSize = null;
            this.transformationList = null;
            this.asGif = false;
            this.cacheStrategy = null;
            this.showAnimation = true;
            this.animation = null;
            this.animResId = 0;
        }

        public ImageOptions.Builder setImageOnFail(int res) {
            this.imageResOnFail = res;
            return this;
        }

        public ImageOptions.Builder setImageOnLoading(int res) {
            this.imageResOnLoading = res;
            return this;
        }

        public ImageOptions.Builder setImageOnEmpty(int res) {
            this.imageResForEmptyUri = res;
            return this;
        }

        public ImageOptions.Builder setImageOnFail(Drawable res) {
            this.imageOnFail = res;
            return this;
        }

        public ImageOptions.Builder setImageOnLoading(Drawable res) {
            this.imageOnLoading = res;
            return this;
        }

        public ImageOptions.Builder setImageOnEmpty(Drawable res) {
            this.imageForEmptyUri = res;
            return this;
        }

        public ImageOptions.Builder setScaleTypeOnImage(ImageOptions.ScaleType t) {
            this.imageScaleType = t;
            return this;
        }

        public ImageOptions.Builder setScaleTypeOnLoading(ImageOptions.ScaleType t) {
            this.loadingScaleType = t;
            return this;
        }

        public ImageOptions.Builder setScaleTypeOnFail(ImageOptions.ScaleType t) {
            this.failScaleType = t;
            return this;
        }

        public ImageOptions.Builder setScaleTypeOnEmpty(ImageOptions.ScaleType t) {
            this.emptyScaleType = t;
            return this;
        }

        public ImageOptions.Builder setImageSize(ImageOptions.ImageSize imageSize) {
            this.imageSize = imageSize;
            return this;
        }

        public ImageOptions.Builder setTransformation(Transformation<Bitmap>[] transformationList) {
            this.transformationList = transformationList;
            return this;
        }

        public ImageOptions.Builder setAsGif(boolean asGif) {
            this.asGif = asGif;
            return this;
        }

        public ImageOptions.Builder setCacheStrategy(DiskCacheStrategy cacheStrategy) {
            this.cacheStrategy = cacheStrategy;
            return this;
        }

        public ImageOptions.Builder setShowAnimation(boolean showAnimation) {
            this.showAnimation = showAnimation;
            return this;
        }

        public ImageOptions.Builder setAnimation(ViewPropertyAnimation.Animator animation) {
            this.animation = animation;
            return this;
        }

        public ImageOptions.Builder setAnimResId(int animResId) {
            this.animResId = animResId;
            return this;
        }

        public ImageOptions build() {
            return new ImageOptions(this);
        }
    }
}
