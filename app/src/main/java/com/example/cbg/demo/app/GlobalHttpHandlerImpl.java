/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.cbg.demo.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.cbg.demo.mvp.mode.api.service.UserService;
import com.example.cbg.demo.mvp.mode.entity.User;
import com.example.mylibrary.net.GlobalHttpHandler;
import com.example.mylibrary.net.log.RequestInterceptor;
import com.example.mylibrary.utils.CommonUtils;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import timber.log.Timber;

/**
 * ================================================
 * 展示 {@link GlobalHttpHandler} 的用法
 * <p>
 * Created by JessYan on 04/09/2017 17:06
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public class GlobalHttpHandlerImpl implements GlobalHttpHandler {
    private Context context;

    private static final Charset UTF8 = Charset.forName("UTF-8");

    public GlobalHttpHandlerImpl(Context context) {
        this.context = context;
    }

    /**
     * 这里可以先客户端一步拿到每一次 Http 请求的结果, 可以先解析成 Json, 再做一些操作, 如检测到 token 过期后
     * 重新请求 token, 并重新执行请求
     *
     * @param httpResult 服务器返回的结果 (已被框架自动转换为字符串)
     * @param chain      {@link Interceptor.Chain}
     * @param response   {@link Response}
     * @return {@link Response}
     */
    @NonNull
    @Override
    public Response onHttpResultResponse(@Nullable String httpResult, @NonNull Interceptor.Chain chain, @NonNull Response response) throws IOException {

        /* 这里如果发现 token 过期, 可以先请求最新的 token, 然后在拿新的 token 放入 Request 里去重新请求
        注意在这个回调之前已经调用过 proceed(), 所以这里必须自己去建立网络请求, 如使用 Okhttp 使用新的 Request 去请求
        create a new request and modify it accordingly using the new token
        Request newRequest = chain.request().newBuilder().header("token", newToken)
                             .build();

        retry the request

        response.body().close();
        如果使用 Okhttp 将新的请求, 请求成功后, 再将 Okhttp 返回的 Response return 出去即可
        如果不需要返回新的结果, 则直接把参数 response 返回出去即可*/

//        注意在这个回调之前已经调用过 proceed(), 所以这里必须自己去建立网络请求, 如使用 Okhttp 使用新的 Request 去请求  httpResult是请求接口返回的结果
        Timber.w("body---------->" + httpResult);

        /***************************************/

        //在这里判断是否token过期 过期后重新获取token 并且 重发请求
        if (false) {//根据和服务端的约定判断token过期

            //请求获取token的api


            //取出本地的refreshToken
            String refreshToken = "sssgr122222222";

            // 通过一个特定的接口获取新的token，此处要用到同步的retrofit请求
//            ApiService service = ServiceManager.getService(ApiService.class);
//            CommonUtils.obtainAppComponentFromContext(context).repositoryManager().obtainRetrofitService(UserService.class).getUsers();
//            Call<String> call = service.refreshToken(refreshToken);
//
//            //要用retrofit的同步方式
            String newToken = "";
//            String newToken = call.execute().body();


            // create a new request and modify it accordingly using the new token
            Request newRequest = chain.request().newBuilder().header("token", newToken)
                    .build();

            // retry the request

            response.body().close();
            return chain.proceed(newRequest);
        }

        // otherwise just pass the original response on
        return response;
    }

    /**
     * 这里可以在请求服务器之前拿到 {@link Request}, 做一些操作比如给 {@link Request} 统一添加 token 或者 header 以及参数加密等操作
     *
     * @param chain   {@link Interceptor.Chain}
     * @param request {@link Request}
     * @return {@link Request}
     */
    @NonNull
    @Override
    public Request onHttpRequestBefore(@NonNull Interceptor.Chain chain, @NonNull Request request) {
        /* 如果需要在请求服务器之前做一些操作, 则重新构建一个做过操作的 Request 并 return, 如增加 Header、Params 等请求信息, 不做操作则直接返回参数 request
        return chain.request().newBuilder().header("token", tokenId)
                              .build(); */
        return request;
    }
}
