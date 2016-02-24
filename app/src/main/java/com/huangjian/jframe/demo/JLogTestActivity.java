package com.huangjian.jframe.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.huangjian.jframe.utils.jlog.JLog;

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
        JLog.t("demo", 2).v("第%2d个 %s", 5, "Custom tag");
        JLog.t("demo", 2).d(tag, 1);
        JLog.t("demo").i(tag, 2);
        JLog.t("demo").w(tag, 3);
        JLog.t("demo", 2).e(tag, 4);
        JLog.t("demo", 2).wtf(tag, 5);
    }

    public void logJson(View view) {
        JLog.json("{\"location\":{\"id\":\"C23NB62W20TF\",\"name\":\"西雅图\",\"country\":\"US\",\"path\":\"西雅图,华盛顿州,美国\",\"timezone\":\"America Los_Angeles\",\"timezone_offset\":\"-08:00\"}}");
    }

    public void logXml(View view) {
        JLog.xml("<resources><string name=\"app_name\">JFrame</string></resources>");
    }

    public void logWTF(View view) {
        JLog.wtf("demo", "<resources><string name=\"app_name\">JFrame</string></resources>");
    }
}
