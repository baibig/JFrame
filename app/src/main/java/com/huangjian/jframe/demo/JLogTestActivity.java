package com.huangjian.jframe.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.huangjian.jframe.utils.Timber;

public class JLogTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jlog_test);
    }

    public void logDefault(View view) {
        Timber.v("Default message");
        Timber.d("Default message");
        Timber.i("Default message");
        Timber.w("Default message");
        Timber.e("Default message");
        Timber.wtf("Default message");
    }

    public void logCustom(View view) {
        String tag = "Custom message %02d";
        Timber.tag("demo").v("第%2d个 %s", 5, "Custom tag");
        Timber.tag("demo").d(tag, 1);
        Timber.tag("demo").i(tag, 2);
        Timber.tag("demo").w(tag, 3);
        Timber.tag("demo").e(tag, 4);
        Timber.tag("demo").wtf(tag, 5);
    }

    public void logWTF(View view) {
        Timber.wtf("demo", "<resources><string name=\"app_name\">JFrame</string></resources>");
    }
}
