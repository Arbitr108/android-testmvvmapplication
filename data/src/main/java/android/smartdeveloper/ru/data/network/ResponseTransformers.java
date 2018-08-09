package android.smartdeveloper.ru.data.network;

import android.smartdeveloper.ru.domain.exception.AppException;
import android.smartdeveloper.ru.domain.exception.ConnectionException;
import android.smartdeveloper.ru.domain.exception.FetchDataException;
import android.smartdeveloper.ru.domain.exception.ServiceException;
import android.smartdeveloper.ru.domain.exception.ServiceNotFoundException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class ResponseTransformers {
    private static final String TAG = "ResponseTransformers";

    private Gson gson;

    public ResponseTransformers(Gson gson) {
        this.gson = gson;
    }

    public <T, E extends Throwable> ObservableTransformer<T, T> handleErrorResponse() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream
                        .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends T>>() {
                            @Override
                            public ObservableSource<? extends T> apply(Throwable throwable) throws Exception {
                                try {
                                    if (throwable instanceof HttpException) {
                                        HttpException httpException = (HttpException) throwable;

                                        switch (httpException.code()) {
                                            case HttpURLConnection.HTTP_NOT_FOUND:
                                                throw new ServiceNotFoundException(httpException.getMessage());
                                            case HttpURLConnection.HTTP_INTERNAL_ERROR:
                                                throw new ServiceException(httpException.getMessage());
                                            default:
                                                ResponseBody responseBody = httpException.response().errorBody();
                                                if (responseBody != null) {
                                                    String errorBody = responseBody.string();
                                                    E httpError = gson.fromJson(errorBody, new TypeToken<E>() {
                                                    }.getType());
                                                    throw new FetchDataException(httpError.getMessage());
                                                }
                                        }

                                    } else if (throwable instanceof SocketTimeoutException) {
                                        throw new ConnectionException(throwable.getMessage());
                                    } else {
                                        throw new AppException(throwable.getMessage());
                                    }

                                } catch (Throwable e) {
                                    return Observable.error(e);
                                }
                                return null;
                            }
                        });
            }
        };
    }


    public <T, E extends Throwable> SingleTransformer<T, T> handleErrorResponseSingle() {
        return new SingleTransformer<T, T>() {

            @Override
            public SingleSource<T> apply(Single<T> upstream) {
                return upstream
                        .doOnSuccess(new Consumer<T>() {
                            @Override
                            public void accept(T t) throws Exception {
                                System.out.println("successed");
                            }
                        })
                                .onErrorResumeNext(new Function<Throwable, SingleSource<? extends T>>() {
                                    @Override
                                    public SingleSource<? extends T> apply(Throwable throwable) throws Exception {
                                        try {
                                            if (throwable instanceof HttpException) {
                                                HttpException httpException = (HttpException) throwable;

                                                switch (httpException.code()) {
                                                    case HttpURLConnection.HTTP_NOT_FOUND:
                                                        throw new ServiceNotFoundException(httpException.getMessage());
                                                    case HttpURLConnection.HTTP_INTERNAL_ERROR:
                                                        throw new ServiceException(httpException.getMessage());
                                                    default:
                                                        ResponseBody responseBody = httpException.response().errorBody();
                                                        if (responseBody != null) {
                                                            String errorBody = responseBody.string();
                                                            E httpError = gson.fromJson(errorBody, new TypeToken<E>() {
                                                            }.getType());
                                                            throw new FetchDataException(httpError.getMessage());
                                                        }
                                                }

                                            } else if (throwable instanceof SocketTimeoutException) {
                                                throw new ConnectionException(throwable.getMessage());
                                            } else {
                                                throw new AppException(throwable.getMessage());
                                            }

                                        } catch (Throwable e) {
                                            return Single.error(e);
                                        }
                                        return null;
                                    }
                                });
            }
        };
    }

}
