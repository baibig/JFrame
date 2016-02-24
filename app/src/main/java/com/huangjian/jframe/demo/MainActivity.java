package com.huangjian.jframe.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.huangjian.jframe.utils.IntentUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnGlideTest = (Button) findViewById(R.id.btn_glide_test);
        btnGlideTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.sendIntent(MainActivity.this, GlideTestActivity.class);
            }
        });
    }
}
