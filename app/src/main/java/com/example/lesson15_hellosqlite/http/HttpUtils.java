package com.example.lesson15_hellosqlite.http;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.lesson15_hellosqlite.MainActivity;
import com.example.lesson15_hellosqlite.utils.Session;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * author : LaAmo
 * e-mail : 15991382841@163.com
 * date   : 2021/6/17 14:18
 * desc   : 网络请求封装类
 * version: 1.0
 */
public class HttpUtils {
    private static Handler handler = new Handler(Looper.getMainLooper());
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static OkHttpClient okHttpClient;

    private static OkHttpClient getInstance() {
        if (okHttpClient == null) {
            synchronized (HttpUtils.class) {
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient.Builder()
                            .connectTimeout(10, TimeUnit.SECONDS)//10秒连接超时
                            .writeTimeout(10, TimeUnit.SECONDS)//10m秒写入超时
                            .readTimeout(10, TimeUnit.SECONDS)//10秒读取超时
//                            .proxy(Proxy.NO_PROXY)//Proxy.NO_PROXY 禁止抓https包
                            .build();
                }
            }
        }
        return okHttpClient;
    }

    /**
     * get 请求
     *
     * @param url      url
     * @param map      params
     * @param callback callback
     */
    public static void getRequest(String url, HashMap<String, Object> map, HttpCallback callback) {
        getCommonRequest(getRequestForParams(url, map, null), callback);
    }

    public static void get_no_token(String url, HashMap<String, Object> map, HttpCallback callBack) {
        getCommonRequest(getRequestForGet(url, map, null), callBack);
    }

    public static <T extends CommonJson> void get(String url, HttpCallback callBack) {
        getCommonRequest(getRequestForParams(url, null, null), callBack);
    }

    public static <T extends CommonJson> void get(String url, HashMap<String, Object> params, HttpCallback callBack, Object tag) {
        getCommonRequest(getRequestForParams(url, params, tag), callBack);
    }

    public static <T extends CommonJson> void get(String url, HashMap<String, Object> params, HttpCallback callBack, Object tag, Map<String, String> headMap) {
        getCommonRequest(getRequestForGetToken(url, params, tag, headMap), callBack);
    }

    /**
     * @param url      url地址
     * @param callBack 请求回调接口
     */
    public static void post(String url, HttpCallback callBack) {
        commonPost(getRequestForPostToken(url, null, null), CommonJson.class, callBack);
    }

    /**
     * @param url      url地址
     * @param cls      泛型返回参数
     * @param callBack 请求回调接口
     */
    public static <T extends CommonJson> void post(String url, Class<T> cls, HttpCallback callBack) {
        commonPost(getRequestForPostToken(url, null, null), cls, callBack);
    }


    /**
     * @param url      url地址
     * @param params   HashMap<String, Object> 参数
     * @param callBack 请求回调接口
     */
    public static void post(String url, HashMap<String, Object> params, HttpCallback callBack) {
        commonPost(getRequestForPostToken(url, params, null), CommonJson.class, callBack);
    }

    /**
     * @param url      url地址
     * @param callBack 请求回调接口
     * @param json
     */
    public static void postByJson(String url, String json, HttpCallback callBack) {
        commonPost(getRequestForPostByJsonToken(url, json, null), CommonJson.class, callBack);
    }

    /**
     * @param url      url地址
     * @param callBack 请求回调接口
     * @param json
     */
    public static void postByJson(String url, String json, Map<String, String> map, HttpCallback callBack) {
        commonPost(getRequestForPostByJsonTokens(url, json, null, map), CommonJson.class, callBack);
    }

    /**
     * @param url      url地址
     * @param params   HashMap<String, Object> 参数
     * @param callBack 请求回调接口
     *                 不带Token
     */
    public static void post_no_token(String url, HashMap<String, Object> params, HttpCallback callBack) {
        commonPost(getRequestForPost(url, params, null), CommonJson.class, callBack);
    }

    /**
     * @param url      url地址
     * @param callBack 请求回调接口
     * @param json
     */
    public static void putByJson(String url, String json, HttpCallback callBack) {
        commonPost(getRequestForPutByJsonToken(url, json, null), CommonJson.class, callBack);
    }

    /**
     * @param url      url地址
     * @param params   HashMap<String, Object> 参数
     * @param callBack 请求回调接口
     * @param tag      网络请求tag
     */
    public static void post(String url, HashMap<String, Object> params, HttpCallback callBack, Object tag) {
        commonPost(getRequestForPostToken(url, params, tag), CommonJson.class, callBack);
    }

    /**
     * @param url      url地址
     * @param params   HashMap<String, Object> 参数
     * @param cls      泛型返回参数
     * @param callBack 请求回调接口
     */
    public static <T extends CommonJson> void post(String url, HashMap<String, Object> params, Class<T> cls, final HttpCallback callBack) {
        commonPost(getRequestForPostToken(url, params, null), cls, callBack);
    }

    /**
     * delete 请求
     * @param url
     * @param params
     * @param callBack
     */
    public static void delete(String url, HashMap<String, Object> params, HttpCallback callBack) {
        commonPost(getRequestForDeleteToken(url, params, null), CommonJson.class, callBack);
    }

    /**
     *  put 请求
     * @param url
     * @param params
     * @param callBack
     */
    public static void put(String url, HashMap<String, Object> params, HttpCallback callBack) {
        commonPost(getRequestForPutToken(url, params, null), CommonJson.class, callBack);
    }




    /**
     *  get 请求
     * @param url url
     * @param map params
     * @param headName 拼接参数名
     * @param headValue 拼接参数 value
     * @param callBack callback
     */

    public static void getNewHeader(String url, HashMap<String, Object> map, String headName, String headValue, HttpCallback callBack) {
        getCommonRequest(getRequestForGetNewHeader(url, map, null, headName, headValue), callBack);
    }

    public static void getMapHeader(String url, HashMap<String, Object> map, HashMap<String, String> mapHesd, HttpCallback callBack) {
        getCommonRequest(getRequestForGetMapHeader(url, map, null, mapHesd), callBack);
    }

    /**
     * GET请求 公共请求部分
     */
    private static void getCommonRequest(Request request, final HttpCallback callback) {
        if (request == null) return;
        Call call = getInstance().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                try {
                    if (callback != null && handler != null) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.onFailure(e.getMessage());
                            }
                        });
                    }
                } catch (Exception e1) {
                    callback.onFailure(e1.getMessage());
                    Log.e("HttpUtils", "getCommonRequest()---onFailure()--->" + e.getMessage());
                }
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                try {
                    String str = Objects.requireNonNull(response.body()).string();
                    String token = response.header("Authorization", "");
                    JSONObject obj = JSONObject.parseObject(str);
                    final CommonJson json = JSONArray.parseObject(obj.toJSONString(), CommonJson.class);
                    json.setToken(token);
                    if (callback != null && handler != null) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.onSuccess(json);
                            }
                        });
                    } else {
                        Log.e("HttpUtils", "getCommonRequest()---onResponse()--->" + json.toString());
                    }
                } catch (Exception e) {
                    Log.e("HttpUtils", "getCommonRequest()---onResponse()--->" + e.getMessage());
                }
            }
        });
    }

    private static Request getRequestForGet(String url, HashMap<String, Object> params, Object tag) {
        if (url == null || "".equals(url)) {
            return null;
        }
        Request request;
        if (tag != null) {
            request = new Request.Builder()
                    .url(paramsToString(url, params))
                    .tag(tag)
                    .build();
        } else {
            request = new Request.Builder()
                    .url(paramsToString(url, params))
                    .build();
        }
        return request;
    }

    private static Request getRequestForGetNewHeader(String url, HashMap<String, Object> params, Object tag, String headName, String headValue) {
        if (url == null || "".equals(url)) {
            Log.e("caoliang", "HttpUtil----getRequestForGet()---->" + "url地址为空 无法执行网络请求!!!");
            return null;
        }
        Request request;

        if (tag != null) {
            request = new Request.Builder()
                    .url(paramsToString(url, params))
                    .addHeader(headName, headValue)
                    .tag(tag)
                    .build();
        } else {
            request = new Request.Builder()
                    .url(paramsToString(url, params))
                    .addHeader(headName, headValue)
                    .build();
        }
        return request;
    }

    private static Request getRequestForGetMapHeader(String url, HashMap<String, Object> params, Object tag, HashMap<String, String> mapHead) {
        if (url == null || "".equals(url)) {
            Log.e("caoliang", "HttpUtil----getRequestForGet()---->" + "url地址为空 无法执行网络请求!!!");
            return null;
        }

        Request request;
        if (tag != null) {
            request = new Request.Builder()
                    .url(paramsToString(url, params))
                    .headers(Headers.of(mapHead))
                    .tag(tag)
                    .build();

        } else {
            request = new Request.Builder()
                    .url(paramsToString(url, params))
                    .headers(Headers.of(mapHead))
                    .build();
        }
        return request;
    }

    private static Request getRequestForGetToken(String url, HashMap<String, Object> params, Object tag, Map<String, String> headMap) {
        if (url == null || "".equals(url)) {
            Log.e("caoliang", "HttpUtil----getRequestForGet()---->" + "url地址为空 无法执行网络请求!!!");
            return null;
        }
        Request request;
        String token = Session.getInstance().getToken();
         /*if (token == null || token.isEmpty()) {//判断是否登录
            Intent intent = new Intent("cn.yaoking.yiyaoshop.login.LoginActivity");
            if (MainActivity.instance != null)
                MainActivity.instance.startActivity(intent);
            return null;
        }*/

        Headers headers = SetHeaders(headMap);

        if (tag != null) {
            request = new Request.Builder()
                    .url(paramsToString(url, params))
                    .headers(headers)
                    .tag(tag)
                    .build();

        } else {
            request = new Request.Builder()
                    .url(paramsToString(url, params))
                    .headers(headers)
                    .build();
        }
        return request;
    }

    /**
     * @return 请求参数封装
     */
    private static Request getRequestForParams(String url, HashMap<String, Object> params, Object tag) {
        if (url == null || "".equals(url)) {
            Log.e("HttpUtils", "getRequestForParams()---->" + "url地址为空 无法执行网络请求!!!");
            return null;
        }
        Request request;
        String token = Session.getInstance().getToken();
        /*if (token == null || token.isEmpty()) {//判断是否登录
            Intent intent = new Intent("cn.yaoking.yiyaoshop.login.LoginActivity");
            if (MainActivity.instance != null)
                MainActivity.instance.startActivity(intent);
            return null;
        }*/

        if (tag != null) {
            request = new Request.Builder()
                    .url(paramsToString(url, params))
                    .addHeader("x-wxapp-session", "bearer " + token)
                    .addHeader("Authorization", "bearer " + token)
                    .tag(tag)
                    .build();
        } else {
            request = new Request.Builder()
                    .url(paramsToString(url, params))
                    .addHeader("x-wxapp-session", "bearer " + token)
                    .addHeader("Authorization", "bearer " + token)
                    .build();
        }
        return request;
    }

    /**
     * url 和参数拼接
     *
     * @return
     */
    private static String paramsToString(String url, HashMap<String, Object> params) {
        StringBuilder builder = new StringBuilder();
        builder.append(url);
        if (params != null && params.size() > 0) {
            builder.append("?");
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                try {
                    builder.append("&").append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
                } catch (Exception e) {
                    e.printStackTrace();
                    builder.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                }
            }
        }
       return builder.toString();
    }

    public static Headers SetHeaders(Map<String, String> headersParams) {
        Headers headers = null;
        Headers.Builder headersbuilder = new Headers.Builder();
        if (headersParams != null) {
            Iterator<String> iterator = headersParams.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                headersbuilder.add(key, headersParams.get(key));
            }
        }
        headers = headersbuilder.build();
        return headers;
    }


    /**
     * POST请求 公共请求部分
     */
    private static <T extends CommonJson> void commonPost(Request request, final Class<T> cls, final HttpCallback callBack) {
        if (request == null) return;
        Call call = getInstance().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull final IOException e) {
                try {
                    if (callBack != null && handler != null) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callBack.onFailure(call.toString());
                            }
                        });
                    }
                } catch (Exception e1) {
                    callBack.onFailure(e.getMessage());
                    Log.e("HttpUtil", "commonPost()---onFailure()--->" + e1.getMessage());
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                try {
                    if (callBack != null && handler != null) {
                        String str = Objects.requireNonNull(response.body()).string();
                        JSONObject obj = JSONObject.parseObject(str);
                        final CommonJson json = JSONArray.parseObject(obj.toJSONString(), CommonJson.class);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callBack.onSuccess(json);
                            }
                        });
                    }
                } catch (Exception e) {
                    Log.e("HttpUtil", "commonPost()---onFailure()--->" + e.getMessage());
                }
            }

        });
    }

    private static Request getRequestForPostToken(String url, Map<String, Object> params, Object tag) {
        if (url == null || "".equals(url)) {
            Log.e("HttpUtil", "getRequestForPost()---->" + "url地址为空 无法执行网络请求!!!");
            return null;
        }
        if (params == null) {
            params = new HashMap<>();
        }
        String token = Session.getInstance().getToken();
         /*if (token == null || token.isEmpty()) {//判断是否登录
            Intent intent = new Intent("cn.yaoking.yiyaoshop.login.LoginActivity");
            if (MainActivity.instance != null)
                MainActivity.instance.startActivity(intent);
            return null;
        }*/
        FormBody.Builder body = new FormBody.Builder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            body.add(entry.getKey(), entry.getValue().toString());
        }
        FormBody formBody = body.build();
        Request request;


        if (tag != null) {
            request = new Request.Builder()
                    .url(url)
                    .addHeader("x-wxapp-session", "bearer " + token)
                    .addHeader("Authorization", "bearer " + token)
                    .post(formBody)
                    .tag(tag)
                    .build();
        } else {
            request = new Request.Builder()
                    .url(url)
                    .addHeader("x-wxapp-session", "bearer " + token)
                    .addHeader("Authorization", "bearer " + token)
                    .post(formBody)
                    .build();
        }
        return request;
    }

    private static Request getRequestForPostByJsonTokens(String url, String json, Object tag, Map<String, String> headMap) {
        if (url == null || "".equals(url)) {
            Log.e("HttpUtil", "getRequestForPost()---->" + "url地址为空 无法执行网络请求!!!");
            return null;
        }
        String token = Session.getInstance().getToken();
         /*if (token == null || token.isEmpty()) {//判断是否登录
            Intent intent = new Intent("cn.yaoking.yiyaoshop.login.LoginActivity");
            if (MainActivity.instance != null)
                MainActivity.instance.startActivity(intent);
            return null;
        }*/
        Headers headers = SetHeaders(headMap);
        RequestBody body = RequestBody.create(JSON, json);
        Request request;
        if (tag != null) {
            request = new Request.Builder()
                    .url(url)
                    .headers(headers)
                    .post(body)
                    .tag(tag)
                    .build();
        } else {
            request = new Request.Builder()
                    .url(url)
                    .headers(headers)
                    .post(body)
                    .build();
        }
        return request;
    }

    private static Request getRequestForPostByJsonToken(String url, String json, Object tag) {
        if (url == null || "".equals(url)) {
            Log.e("HttpUtil", "getRequestForPost()---->" + "url地址为空 无法执行网络请求!!!");
            return null;
        }

        String token = Session.getInstance().getToken();
         /*if (token == null || token.isEmpty()) {//判断是否登录
            Intent intent = new Intent("cn.yaoking.yiyaoshop.login.LoginActivity");
            if (MainActivity.instance != null)
                MainActivity.instance.startActivity(intent);
            return null;
        }*/
        RequestBody body = RequestBody.create(JSON, json);
        Request request;
        if (tag != null) {
            request = new Request.Builder()
                    .url(url)
                    .addHeader("x-wxapp-session", "bearer " + token)
                    .addHeader("Authorization", "bearer " + token)
                    .post(body)
                    .tag(tag)
                    .build();
        } else {
            request = new Request.Builder()
                    .url(url)
                    .addHeader("x-wxapp-session", "bearer " + token)
                    .addHeader("Authorization", "bearer " + token)
                    .post(body)
                    .build();
        }
        return request;
    }

    private static Request getRequestForPost(String url, Map<String, Object> params, Object tag) {
        if (url == null || "".equals(url)) {
            Log.e("HttpUtil", "getRequestForPost()---->" + "url地址为空 无法执行网络请求!!!");
            return null;
        }
        if (params == null) {
            params = new HashMap<>();
        }
        FormBody.Builder body = new FormBody.Builder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            body.add(entry.getKey(), entry.getValue().toString());
        }
        FormBody formBody = body.build();
        Request request;
        if (tag != null) {
            request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .tag(tag)
                    .build();
        } else {
            request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();
        }
        return request;
    }

    private static Request getRequestForPutByJsonToken(String url, String json, Object tag) {
        if (url == null || "".equals(url)) {
            Log.e("HttpUtil", "getRequestForPost()---->" + "url地址为空 无法执行网络请求!!!");
            return null;
        }

        String token = Session.getInstance().getToken();
         /*if (token == null || token.isEmpty()) {//判断是否登录
            Intent intent = new Intent("cn.yaoking.yiyaoshop.login.LoginActivity");
            if (MainActivity.instance != null)
                MainActivity.instance.startActivity(intent);
            return null;
        }*/
        RequestBody body = RequestBody.create(JSON, json);
        Request request;
        if (tag != null) {
            request = new Request.Builder()
                    .url(url)
                    .addHeader("x-wxapp-session", "bearer " + token)
                    .addHeader("Authorization", "bearer " + token)
                    .put(body)
                    .tag(tag)
                    .build();
        } else {
            request = new Request.Builder()
                    .url(url)
                    .addHeader("x-wxapp-session", "bearer " + token)
                    .addHeader("Authorization", "bearer " + token)
                    .put(body)
                    .build();
        }
        return request;
    }

    private static Request getRequestForDeleteToken(String url, Map<String, Object> params, Object tag) {
        if (url == null || "".equals(url)) {
            Log.e("HttpUtil", "getRequestForPost()---->" + "url地址为空 无法执行网络请求!!!");
            return null;
        }
        if (params == null) {
            params = new HashMap<>();
        }

        String token = Session.getInstance().getToken();
         /*if (token == null || token.isEmpty()) {//判断是否登录
            Intent intent = new Intent("cn.yaoking.yiyaoshop.login.LoginActivity");
            if (MainActivity.instance != null)
                MainActivity.instance.startActivity(intent);
            return null;
        }*/
        FormBody.Builder body = new FormBody.Builder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            body.add(entry.getKey(), entry.getValue().toString());
        }
        FormBody formBody = body.build();
        Request request;
        if (tag != null) {
            request = new Request.Builder()
                    .url(url)
                    .addHeader("x-wxapp-session", "bearer " + token)
                    .addHeader("Authorization", "bearer " + token)
                    .delete(formBody)
                    .tag(tag)
                    .build();
        } else {
            request = new Request.Builder()
                    .url(url)
                    .addHeader("x-wxapp-session", "bearer " + token)
                    .addHeader("Authorization", "bearer " + token)
                    .delete(formBody)
                    .build();
        }
        return request;
    }

    private static Request getRequestForPutToken(String url, Map<String, Object> params, Object tag) {
        if (url == null || "".equals(url)) {
            Log.e("HttpUtil", "getRequestForPost()---->" + "url地址为空 无法执行网络请求!!!");
            return null;
        }
        if (params == null) {
            params = new HashMap<>();
        }

        String token = Session.getInstance().getToken();
         /*if (token == null || token.isEmpty()) {//判断是否登录
            Intent intent = new Intent("cn.yaoking.yiyaoshop.login.LoginActivity");
            if (MainActivity.instance != null)
                MainActivity.instance.startActivity(intent);
            return null;
        }*/
        FormBody.Builder body = new FormBody.Builder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            body.add(entry.getKey(), entry.getValue().toString());
        }
        FormBody formBody = body.build();
        Request request;
        if (tag != null) {
            request = new Request.Builder()
                    .url(url)
                    .addHeader("x-wxapp-session", "bearer " + token)
                    .addHeader("Authorization", "bearer " + token)
                    .put(formBody)
                    .tag(tag)
                    .build();
        } else {
            request = new Request.Builder()
                    .url(url)
                    .addHeader("x-wxapp-session", "bearer " + token)
                    .addHeader("Authorization", "bearer " + token)
                    .put(formBody)
                    .build();
        }
        return request;
    }
}
