package net.api;


import net.BaseResultEntity;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * 通用 service 接口
 * Created by wison on 2017/3/9.
 */
public interface ApiService {

    @GET
    Observable<BaseResultEntity<Object>> doGet(@Url String url);

    @GET
    Observable<BaseResultEntity<Object>> doGetWithParams(@Url String url, @QueryMap Map<String, String> map);

    @GET
    Observable<BaseResultEntity<Object>> doGetWithHeader(@Url String url, @HeaderMap Map<String, String> headers);

    @GET
    Observable<Object> doGetOriginal(@Url String url, @QueryMap Map<String, String> map);

    @GET
    Observable<BaseResultEntity<Object>> doGet(@Url String url, @HeaderMap Map<String, String> headers, @QueryMap Map<String, String> params);

    @POST
    Observable<BaseResultEntity<Object>> doPost(@Url String url);

    @POST
    Observable<BaseResultEntity<Object>> doPost(@Url String url, @Body RequestBody body);

    @POST
    Observable<Object> doPostOriginal(@Url String url, @Body RequestBody body);

    @PUT
    Observable<BaseResultEntity<Object>> doPut(@Url String url);

    @PUT
    Observable<BaseResultEntity<Object>> doPut(@Url String url, @Body RequestBody body);

    @DELETE
    Observable<BaseResultEntity<Object>> doDelete(@Url String url);

    @DELETE
    Observable<BaseResultEntity<Object>> doDelete(@Url String url, @Body RequestBody body);

    @Multipart
    @POST
    Observable<ResponseBody> upLoad(@Url String url, @FieldMap Map<String, String> map);

    @Streaming
    @GET
    Observable<Response<ResponseBody>> download(@Url String url);

}
