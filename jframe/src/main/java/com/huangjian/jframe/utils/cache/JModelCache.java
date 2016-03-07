package com.huangjian.jframe.utils.cache;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huangjian.jframe.utils.cache.modelcache.IBaseCacheModel;
import com.huangjian.jframe.utils.cache.modelcache.LightModelCache;

/**
 * Created by huangjian.
 * 
 * model相关存储,底层是sharedpreference
 *
 */
public class JModelCache {

    /**
     * 需要存储的model都必须继承自这个类
     */
    public class TXBaseCacheModel implements IBaseCacheModel {
    }


    private LightModelCache mModelCache;

    public JModelCache(Context context) {
        mModelCache = new LightModelCache(context);
        mModelCache.setGson(new Gson());
    }

    public JModelCache(Context context, String cacheFileName) {
        mModelCache = new LightModelCache(context, cacheFileName);
        mModelCache.setGson(new Gson());
    }

    public <T extends IBaseCacheModel> boolean putModel(String key, T model) {
        return mModelCache.putModel(key, model);
    }

    public <T extends IBaseCacheModel> boolean putModelList(String key, List<T> modelList) {
        return mModelCache.putModelList(key, modelList);
    }

    public <T extends IBaseCacheModel> T getModel(String key, Class<T> clazz) {
        return mModelCache.getModel(key, clazz);
    }

    public <T extends IBaseCacheModel> List<T> getModelList(String key, TypeToken<List<T>> typeToken) {
        return mModelCache.getModelList(key, typeToken);
    }

    public boolean removeModel(String key) {
        return mModelCache.removeModel(key);
    }

    public boolean contains(String key) {
        if (TextUtils.isEmpty(key)) {
            return false;
        }
        return mModelCache.contains(key);
    }

}
