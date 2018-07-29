package android.smartdeveloper.ru.data.network;

import android.smartdeveloper.ru.data.entity.DeleteResponse;
import android.smartdeveloper.ru.data.entity.UserRequest;
import android.smartdeveloper.ru.data.entity.UserResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface RestApi {

    @GET("data/user")
    Observable<List<UserResponse>> all(
            @QueryMap Map<String, String> options);

    @GET("data/user?sortBy=objectId%20desc")
    Observable<List<UserResponse>> all();

    @GET("data/user/{id}")
    Observable<UserResponse> fetch(@Path("id") String id);

    @Headers("CustomHeader: some custom header data")
    @PUT("data/user")
    Observable<UserResponse> update(@Body UserRequest userRequest);

    @DELETE("data/user/{id}")
    Observable<DeleteResponse> remove(@Path("id") String id);

    @GET("data/user")
    Observable<List<UserResponse>> search(@Query("where") String search);
}
