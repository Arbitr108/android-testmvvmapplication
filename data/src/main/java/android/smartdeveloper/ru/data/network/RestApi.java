package android.smartdeveloper.ru.data.network;

import android.smartdeveloper.ru.data.entity.DeleteResponse;
import android.smartdeveloper.ru.data.entity.UserRequest;
import android.smartdeveloper.ru.data.entity.UserResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;
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
    Single<List<UserResponse>> all();

    @GET("data/user/{id}")
    Single<UserResponse> fetch(@Path("id") String id);

    @Headers("CustomHeader: some custom header data")
    @PUT("data/user")
    Single<UserResponse> update(@Body UserRequest userRequest);

    @DELETE("data/user/{id}")
    Single<DeleteResponse> remove(@Path("id") String id);

    @GET("data/user")
    Single<List<UserResponse>> search(@Query("where") String search);
}
