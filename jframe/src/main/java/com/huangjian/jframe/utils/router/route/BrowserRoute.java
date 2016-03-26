package com.huangjian.jframe.utils.router.route;


import com.huangjian.jframe.utils.router.router.IRouter;

/**
 * Created by kris on 16/3/17.
 */
public class BrowserRoute extends BaseRoute {

    public BrowserRoute(IRouter router, String url) {
        super(router, url);
    }



    public static class Builder {
        String mUrl;
        IRouter mRouter;

        public Builder(IRouter router){
            mRouter = router;
        }

        public Builder setUrl(String url){
            mUrl = url;
            return this;
        }
        
        public BrowserRoute build(){
            return new BrowserRoute(mRouter, mUrl);
        }



    }
}
