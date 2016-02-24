package com.huangjian.jframe.utils.io;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import android.os.Build;

/**
 * @author MaTianyu
 * @date 2014-12-05
 */
public class StringCodingUtils {

    public static byte[] getBytes(String src, Charset charSet) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
            try {
                return src.getBytes(charSet.name());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            return src.getBytes(charSet);
        }
    }

}
