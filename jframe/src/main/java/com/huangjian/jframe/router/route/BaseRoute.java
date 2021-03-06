package com.huangjian.jframe.router.route;

import com.huangjian.jframe.router.router.IRouter;
import com.huangjian.jframe.router.utils.UrlUtils;

import java.util.List;
import java.util.Map;


/**
 * Created by kris on 16/3/16.
 */
public abstract class BaseRoute implements IRoute {
    IRouter mRouter;
    String mUrl;
    String mScheme;
    String mHost;
    int mPort;
    List<String> mPath;
    Map<String, String> mQueryParameters;

    public BaseRoute(IRouter router, String url){
        mRouter = router;
        mUrl = url;
        mScheme = UrlUtils.getScheme(url);
        mHost = UrlUtils.getHost(url);
        mPort = UrlUtils.getPort(url);
        mPath = UrlUtils.getPathSegments(url);
        mQueryParameters = UrlUtils.getParameters(url);

    }

    @Override
    public IRouter getRouter() {
        return mRouter;
    }

    @Override
    public String getUrl() {
        return mUrl;
    }

    @Override
    public String getScheme() {
        return mScheme;
    }

    @Override
    public String getHost() {
        return mHost;
    }

    @Override
    public int getPort() {
        return mPort;
    }

    @Override
    public List<String> getPath() {
        return mPath;
    }

    @Override
    public Map<String, String> getParameters(){
        return mQueryParameters;
    }


    @Override
    public void open() {
        mRouter.open(this);
    }
}
