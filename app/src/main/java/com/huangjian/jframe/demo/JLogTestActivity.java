package com.huangjian.jframe.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.huangjian.jframe.utils.JLogger;

public class JLogTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jlog_test);
    }

    public void logDefault(View view) {
        JLogger.v("Default message");
        JLogger.d("Default message");
        JLogger.i("Default message");
        JLogger.w("Default message");
        JLogger.e("Default message");
        JLogger.wtf("Default message");
    }

    public void logCustom(View view) {
        String tag = "Custom message %02d";
        JLogger.tag("demo").v("第%2d个 %s", 5, "Custom tag");
        JLogger.tag("demo").d(tag, 1);
        JLogger.tag("demo").i(tag, 2);
        JLogger.tag("demo").w(tag, 3);
        JLogger.tag("demo").e(tag, 4);
        JLogger.tag("demo").wtf(tag, 5);
    }

    public void logWTF(View view) {
        JLogger.wtf("<resources><string name=\"app_name\">JFrame</string></resources>");
    }
}
