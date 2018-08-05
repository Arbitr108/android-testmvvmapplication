package android.smartdeveloper.ru.data.network;

import android.smartdeveloper.ru.data.entity.DeleteResponse;
import android.smartdeveloper.ru.data.entity.ErrorResponse;
import android.smartdeveloper.ru.data.entity.UserRequest;
import android.smartdeveloper.ru.data.entity.UserResponse;
import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.SingleTransformer;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestService {
    private static final String TAG = "RestService";
    public static final String BASE_URL =
            "https://api.backendless.com/5D84CB49-4F56-4AD0-FF44-D3FC94518C00/BFBC3E8C-03A1-BECE-FF6F-C81110FB3400/";
    private RestApi restApi;
    private static RestService instance;
    private ResponseTransformers transformers;

    private RestService() {
        OkHttpClient okHttpClient =
                new OkHttpClient
                        .Builder()
                        .writeTimeout(60, TimeUnit.SECONDS)
                        .readTimeout(60, TimeUnit.SECONDS)
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .addInterceptor(new HttpLoggingInterceptor(
                                new HttpLoggingInterceptor.Logger() {
                                    @Override
                                    public void log(String message) {
                                        Log.d(TAG, "HTTP-interceptor: " + message);
                                    }
                                })
                                .setLevel(HttpLoggingInterceptor.Level.BODY))
                        //debugging
                        .addNetworkInterceptor(new StethoInterceptor())
                        .build();

        Gson gson = new GsonBuilder().create();
        restApi = new Retrofit
                .Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build()
                .create(RestApi.class);
        transformers = new ResponseTransformers(gson);
    }


    public Single<List<UserResponse>> all() {

        return restApi
                .all()
                .compose((SingleTransformer<? super List<UserResponse>, ? extends List<UserResponse>>)
                        transformers.<List<UserResponse>, ErrorResponse>handleErrorResponse());
    }

    public Single<UserResponse> fetch(String id) {
        return restApi
                .fetch(id)
                .compose((SingleTransformer<? super UserResponse, ? extends UserResponse>) transformers.<UserResponse, ErrorResponse>handleErrorResponse());
    }

    public Single<UserResponse> update(UserRequest userRequest) {
        return restApi
                .update(userRequest);
     }

    public Single<DeleteResponse> remove(String id) {

        return restApi
                .remove(id)
                .compose((SingleTransformer<? super DeleteResponse, ? extends DeleteResponse>) transformers.<DeleteResponse, ErrorResponse>handleErrorResponse());

    }

    public static RestService getInstance() {
        if (instance == null) {
            instance = new RestService();
        }
        return instance;
    }

    public Single<List<UserResponse>> search(String search) {
        String searchFormatted = search +"%";
        return restApi
                .search(String.format("name LIKE '%s' OR surname LIKE '%s'", searchFormatted, searchFormatted))
                .compose((SingleTransformer<? super List<UserResponse>, ? extends List<UserResponse>>) transformers.<List<UserResponse>, ErrorResponse>handleErrorResponse());
    }
}
