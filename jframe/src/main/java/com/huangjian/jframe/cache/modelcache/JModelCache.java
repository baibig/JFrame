package com.huangjian.jframe.cache.modelcache;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * created by huangjian 2016/3/28
 * 轻量级的cache，底层是sharedPreference，用Gson格式化，可保存对象。
 */

public class JModelCache extends SharePreferenceUtil {

	private Gson mGson = null;

	public JModelCache(Context context, String cacheFilename) {
		super(context, cacheFilename);
		mGson = new Gson();
	}

	public <T> boolean putModel(String key, T model) {
		if (model == null || key == null) return false;
		String value = this.mGson.toJson(model);

		return putString(key, value);
	}

	public <T> boolean putModelList(String key, List<T> modelList) {
		if (modelList == null || key == null) return false;
		String value = this.mGson.toJson(modelList);

		return putString(key, value);
	}

	public <T> T getModel(String key, Class<T> clazz) {
		if (key == null) throw new NullPointerException("key must not null.");
		String value = getStringValue(key, null);
		if (value == null) return null;
		return this.mGson.fromJson(value, clazz);
	}

	public <T> List<T> getModelList(String key, TypeToken<List<T>> typeToken) {
		if (key == null) throw new NullPointerException("key must not null.");
		String value = getStringValue(key, null);
		if (value == null) return null;
		
		List<T> list = this.mGson.fromJson(value, typeToken.getType());
		return list;
	}

	public boolean removeModel(String key) {
		if (key == null) return false;
		return super.delete(key);
	}

    public boolean contains(String key) {
        if (TextUtils.isEmpty(key)) {
            return false;
        }
        return super.contains(key);
    }

}
