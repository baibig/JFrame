package com.huangjian.jframe.utils.router.router;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;

import com.huangjian.jframe.BuildConfig;
import com.huangjian.jframe.utils.log.JLog;
import com.huangjian.jframe.utils.router.exception.InvalidRoutePathException;
import com.huangjian.jframe.utils.router.exception.InvalidValueTypeException;
import com.huangjian.jframe.utils.router.exception.RouteNotFoundException;
import com.huangjian.jframe.utils.router.route.ActivityRoute;
import com.huangjian.jframe.utils.router.route.IRoute;
import com.huangjian.jframe.utils.router.tools.ActivityRouteRuleBuilder;
import com.huangjian.jframe.utils.router.utils.UrlUtils;
import static com.huangjian.jframe.utils.router.utils.UrlUtils.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by kris on 16/3/10.
 */
public class ActivityRouter extends BaseRouter {
    private static final String TAG = "Router";
    private static String MATCH_SCHEME = "activity";
    static ActivityRouter mSharedActivityRouter = new ActivityRouter();
    Context mBaseContext;
    Map<String, Class<? extends Activity>> mRouteTable = new HashMap<>();


    static {
        CAN_OPEN_ROUTE = ActivityRoute.class;
    }


    public static ActivityRouter getSharedRouter(){
        return mSharedActivityRouter;
    }


    public void init(Context appContext, IActivityRouteTableInitializer initializer) {
        mBaseContext = appContext;
        initializer.initRouterTable(mRouteTable);
        for(String pathRule : mRouteTable.keySet()){
            boolean isValid = ActivityRouteRuleBuilder.isActivityRuleValid(pathRule);
            if(!isValid){
                JLog.e(new InvalidRoutePathException(pathRule), "");
                mRouteTable.remove(pathRule);
            }
        }
    }


    @Override
    public IRoute getRoute(String url) {
        return new ActivityRoute.Builder(this)
                .setUrl(url)
                .build();
    }

    @Override
    public boolean canOpenTheRoute(IRoute route) {

        return CAN_OPEN_ROUTE.equals(route.getClass());
    }


    @Override
    public boolean canOpenTheUrl(String url) {
        return TextUtils.equals(getScheme(url), MATCH_SCHEME);
    }


    public void setMatchScheme(String scheme){
        MATCH_SCHEME = scheme;
    }

    public String getMatchScheme(){
        return MATCH_SCHEME;
    }

    @Override
    public Class<? extends IRoute> getCanOpenRoute() {
        return CAN_OPEN_ROUTE;
    }

    @Override
    public void open(IRoute route) {
        if(route instanceof ActivityRoute){
            ActivityRoute aRoute = (ActivityRoute) route;
            switch (aRoute.getOpenType()){
                case ActivityRoute.START:
                    open(aRoute, aRoute.getActivity());
                    break;
                case ActivityRoute.FOR_RESULT_ACTIVITY:
                    openForResult(aRoute, aRoute.getActivity(), aRoute.getRequestCode());
                    break;
                case ActivityRoute.FOR_RESULT_SUPPORT_FRAGMENT:
                    openForResult(aRoute, aRoute.getSupportFragment(), aRoute.getRequestCode());
                    break;
                case ActivityRoute.FOR_RESULT_FRAGMENT:
                    openForResult(aRoute, aRoute.getFragment(), aRoute.getRequestCode());
                    break;
                default:
                    JLog.e("Error Open Type");
            }
        }

    }

    @Override
    public void open(String url) {
        open(getRoute(url));
    }


    protected void open(ActivityRoute route, Context context) {
        try {
            Intent intent = match(route);
            if(intent == null){
                JLog.e(new RouteNotFoundException(route.getUrl()), "");
                return;
            }

            if(context == null) {
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mBaseContext.startActivity(intent);
            } else {
                context.startActivity(intent);
            }

            if(route.getInAnimation() != -1 && route.getOutAnimation() != -1 && route.getActivity() != null){
                route.getActivity().overridePendingTransition(route.getInAnimation(), route.getOutAnimation());
            }
        } catch (Exception e){
            JLog.e(e, "");
        }
    }

    protected void openForResult(ActivityRoute route, Activity activity, int requestCode) {

        try {
            Intent intent = match(route);
            if(route.getInAnimation() != -1 && route.getOutAnimation() != -1 && route.getActivity() != null){
                route.getActivity().overridePendingTransition(route.getInAnimation(), route.getOutAnimation());
            }
            activity.startActivityForResult(intent, requestCode);
        } catch (Exception e){
            JLog.e(e, "");
        }
    }

    protected void openForResult(ActivityRoute route, Fragment fragment, int requestCode) {

        try {
            Intent intent = match(route);
            if(route.getInAnimation() != -1 && route.getOutAnimation() != -1 && route.getActivity() != null){
                route.getActivity().overridePendingTransition(route.getInAnimation(), route.getOutAnimation());
            }
            fragment.startActivityForResult(intent, requestCode);
        } catch (Exception e){
            JLog.e(e, "");
        }
    }

    protected void openForResult(ActivityRoute route, android.app.Fragment fragment, int requestCode) {

        try {
            Intent intent = match(route);
            if(route.getInAnimation() != -1 && route.getOutAnimation() != -1 && route.getActivity() != null){
                route.getActivity().overridePendingTransition(route.getInAnimation(), route.getOutAnimation());
            }
            fragment.startActivityForResult(intent, requestCode);
        } catch (Exception e){
            JLog.e(e, "");
        }
    }




    /**
     * host 和path匹配称之为路由匹匹配
     * @param route
     * @return String the match routePath
     */
    @Nullable
    private String findMatchedRoute(ActivityRoute route) {
        List<String> givenPathSegs = route.getPath();
        OutLoop:
        for(String routeUrl : mRouteTable.keySet()){
            List<String> routePathSegs = getPathSegments(routeUrl);
            if(!TextUtils.equals(getHost(routeUrl), route.getHost())){
                continue;
            }
            if(givenPathSegs.size() != routePathSegs.size()){
                continue;
            }
            for(int i=0;i<routePathSegs.size();i++){
                if(!routePathSegs.get(i).startsWith(":")
                        &&!TextUtils.equals(routePathSegs.get(i), givenPathSegs.get(i))) {
                    continue OutLoop;
                }
            }
            //find the match route
            return routeUrl;
        }

        return null;
    }

    /**
     * find the key value in the path and set them in the intent
     * @param routeUrl the matched route path
     * @param givenUrl the given path
     * @param intent the intent
     * @return the intent
     */
    private Intent setKeyValueInThePath(String routeUrl, String givenUrl, Intent intent) {
        List<String> routePathSegs = getPathSegments(routeUrl);
        List<String> givenPathSegs = getPathSegments(givenUrl);
        for(int i = 0;i<routePathSegs.size();i++){
            String seg = routePathSegs.get(i);
            if(seg.startsWith(":")){
                int indexOfLeft = seg.indexOf("{");
                int indexOfRight = seg.indexOf("}");
                String key = seg.substring(indexOfLeft + 1, indexOfRight);
                char typeChar = seg.charAt(1);
                switch (typeChar){
                    //interger type
                    case 'i':
                        try {
                            int value = Integer.parseInt(givenPathSegs.get(i));
                            intent.putExtra(key, value);
                        } catch(Exception e){
                            Log.e(TAG, "解析整形类型失败 "+ givenPathSegs.get(i), e);
                            if(BuildConfig.DEBUG){
                                throw new InvalidValueTypeException(givenUrl, givenPathSegs.get(i));
                            } else{
                                //如果是在release情况下则给一个默认值
                                intent.putExtra(key, 0);
                            }
                        }
                        break;
                    case 'f':
                        //float type
                        try {
                            float value = Float.parseFloat(givenPathSegs.get(i));
                            intent.putExtra(key, value);
                        } catch(Exception e){
                            Log.e(TAG, "解析浮点类型失败 " + givenPathSegs.get(i), e);
                            if(BuildConfig.DEBUG) {
                                throw new InvalidValueTypeException(givenUrl, givenPathSegs.get(i));
                            } else {
                                intent.putExtra(key, 0f);
                            }
                        }
                        break;
                    case 'l':
                        //long type
                        try{
                            long value = Long.parseLong(givenPathSegs.get(i));
                            intent.putExtra(key, value);
                        } catch(Exception e){
                            Log.e(TAG, "解析长整形失败 " + givenPathSegs.get(i), e);
                            if(BuildConfig.DEBUG){
                                throw new InvalidValueTypeException(givenUrl, givenPathSegs.get(i));
                            } else {
                                intent.putExtra(key, 0l);
                            }
                        }
                        break;
                    case 'd':
                        try{
                            double value = Double.parseDouble(givenPathSegs.get(i));
                            intent.putExtra(key, value);
                        } catch (Exception e){
                            Log.e(TAG, "解析double类型失败 " + givenPathSegs.get(i), e);
                            if(BuildConfig.DEBUG){
                                throw new InvalidValueTypeException(givenUrl, givenPathSegs.get(i));
                            } else {
                                intent.putExtra(key, 0d);
                            }
                        }
                        break;
                    case 'c':
                        try {
                            char value = givenPathSegs.get(i).charAt(0);
                        } catch(Exception e){
                            Log.e(TAG, "解析Character类型失败" + givenPathSegs.get(i), e);
                            if(BuildConfig.DEBUG){
                                throw new InvalidValueTypeException(givenUrl, givenPathSegs.get(i));
                            } else {
                                intent.putExtra(key, ' ');
                            }
                        }
                        break;
                    case 's':
                    default:
                        intent.putExtra(key, givenPathSegs.get(i));
                }
            }

        }
        return intent;
    }

    private Intent setOptionParams(String url, Intent intent){
        Map<String, String> queryParams = UrlUtils.getParameters(url);
        for(String key: queryParams.keySet()){
            intent.putExtra(key, queryParams.get(key));
        }

        return intent;
    }

    private Intent setExtras(Bundle bundle, Intent intent){
        intent.putExtras(bundle);
        return intent;
    }

    @Nullable
    private Intent match(ActivityRoute route) {
        String matchedRoute = findMatchedRoute(route);
        if(matchedRoute == null){
            return null;
        }
        Class<? extends Activity> matchedActivity = mRouteTable.get(matchedRoute);
        Intent intent = new Intent(mBaseContext, matchedActivity);
        //find the key value in the path
        intent = setKeyValueInThePath(matchedRoute, route.getUrl(), intent);
        intent = setOptionParams(route.getUrl(), intent);
        intent = setExtras(route.getExtras(), intent);
        return intent;
    }
}
