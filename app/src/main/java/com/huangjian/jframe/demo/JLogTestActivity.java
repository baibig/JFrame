package com.huangjian.jframe.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.huangjian.jframe.utils.log.JLog;

public class JLogTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jlog_test);
    }

    public void logDefault(View view) {
        JLog.v("Default message");
        JLog.d("Default message");
        JLog.i("Default message");
        JLog.w("Default message");
        JLog.e("Default message");
        JLog.wtf("Default message");
    }

    public void logCustom(View view) {
        String tag = "Custom message %02d";
        JLog.tag("demo").v("第%2d个 %s", 5, "Custom tag");
        JLog.tag("demo").d(tag, 1);
        JLog.tag("demo").i(tag, 2);
        JLog.tag("demo").w(tag, 3);
        JLog.tag("demo").e(tag, 4);
        JLog.tag("demo").wtf(tag, 5);
    }

    public void logWTF(View view) {
        JLog.wtf("<resources><string name=\"app_name\">JFrame</string></resources>");
    }
}
