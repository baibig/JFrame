package com.huangjian.jframe.demo;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.huangjian.jframe.utils.DensityUtils;
import com.huangjian.jframe.utils.ExternalStorageUtils;
import com.huangjian.jframe.utils.image.ImageOptions;
import com.huangjian.jframe.utils.image.JImageView;

import java.io.File;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class GlideTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_test);
        JImageView imageView1 = (JImageView) findViewById(R.id.glide_test_image_1);
        JImageView imageView2 = (JImageView) findViewById(R.id.glide_test_image_2);
        JImageView imageView3 = (JImageView) findViewById(R.id.glide_test_image_3);
        JImageView imageView4 = (JImageView) findViewById(R.id.glide_test_image_4);
        JImageView imageView5 = (JImageView) findViewById(R.id.glide_test_image_5);
        ImageOptions.Builder builder = new ImageOptions.Builder();
        builder.setImageOnEmpty(R.mipmap.glide_blank);
        builder.setImageOnFail(R.mipmap.glide_failed);
        Transformation<Bitmap>[] transformations = new Transformation[1];
        transformations[0] = new CropCircleTransformation(this);
        builder.setTransformation(transformations);
        String url1 = "http://cdn.duitang.com/uploads/item/201511/28/20151128081816_U3kcu.jpeg";
        // 加载url
        imageView1.display(url1, builder.build());
        // 加载资源id
        imageView2.display(R.mipmap.demo_cat, builder.build());
        // 加载文件
        imageView3.display(new File(ExternalStorageUtils.getSdCardPath() + "Download", "glide_dog_and_cat.jpg"), builder.build());
        // 加载空白
        imageView4.display("", builder.build());
        // 加载错误
        imageView5.display("wwww.123.com", builder.build());
    }
}
