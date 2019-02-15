package com.example.mylibrary.net.interceptor;

import android.util.Log;

import com.example.mylibrary.constant.IConstant;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Title: singleActivityDemo
 * <p>
 * Description:
 * <p>
 * Author:baigege (baigegechen@gmail.com)
 * <p>
 * Date:2017-12-26
 */

public class requestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        //获得请求信息，此处如有需要可以添加headers信息
        Request request = chain.request();

        //添加Cookie信息 向request header写入信息
//        request.newBuilder().addHeader("Cookie", "aaaa");

        Log.e(IConstant.LOG_DESC, "-------------------------------request start----------------------------------");

        //打印请求信息
        Log.e(IConstant.LOG_DESC, "request-method:" + request.method());

        URL requestUrl = request.url().url();

        String requestUrlPath = requestUrl.toString();
        //带？的查询参数情况
        if (requestUrl.toString().indexOf('?') != -1)
            requestUrlPath = requestUrl.toString().substring(0, requestUrl.toString().indexOf('?'));

        Log.e(IConstant.LOG_DESC, "request-url:" + requestUrlPath);

        String body = null;
        RequestBody requestBody = request.body();
        if (requestBody != null) {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);

            Charset charset = Charset.forName("utf8");
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(charset);
            }
            body = buffer.readString(charset);
        }
        Log.e(IConstant.LOG_DESC, "request-body---->:" + (null == body ? "" : body));

        Log.e(IConstant.LOG_DESC, "request-Params---------------->:" + requestUrl.getQuery());

        //记录请求耗时
        long startNs = System.nanoTime();
        okhttp3.Response response;
        try {
            //发送请求，获得相应，
            response = chain.proceed(request);
        } catch (Exception e) {
            throw e;
        }
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
        //打印请求耗时
//        Log.e(IConstant.LOG_DESC, "request-timer:" + tookMs + "ms");
        //使用response获得headers(),可以更新本地Cookie。
//        Log.e(IConstant.LOG_DESC, "response-headers:" + response.headers().toString());

        //获得返回的body，注意此处不要使用responseBody.string()获取返回数据，原因在于这个方法会消耗返回结果的数据(buffer)
        ResponseBody responseBody = response.body();

        //为了不消耗buffer，我们这里使用source先获得buffer对象，然后clone()后使用
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        //获得返回的数据
        Buffer buffer = source.buffer();
        //使用前clone()下，避免直接消耗
        Log.e(IConstant.LOG_DESC, "response-body:" + buffer.clone().readString(Charset.forName("UTF-8")));

        Log.e(IConstant.LOG_DESC, "--------------------------------request end-------------------------------------");
        return response;
    }
}
