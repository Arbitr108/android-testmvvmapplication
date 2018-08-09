package android.smartdeveloper.ru.data.repositories;

import android.smartdeveloper.ru.data.database.Db;
import android.smartdeveloper.ru.data.entity.UserRequest;
import android.smartdeveloper.ru.data.entity.UserResponse;
import android.smartdeveloper.ru.data.network.RestService;
import android.smartdeveloper.ru.domain.entity.User;
import android.smartdeveloper.ru.domain.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class UserRepositoryImpl implements UserRepository {

    private static final String TAG = "UserRepositoryImpl";
    private final RestService restService;
    private final Db database;

    public UserRepositoryImpl(RestService restService, Db database) {
        this.restService = restService;
        this.database = database;
    }

    @Override
    public Single<List<User>> all() {

        return database
                .getUserDao()
                .all()
                .flatMap(new Function<List<UserResponse>, SingleSource<List<UserResponse>>>() {
                    @Override
                    public SingleSource<List<UserResponse>> apply(List<UserResponse> userResponses) throws Exception {
                        if(userResponses.isEmpty()){
                            return restService
                                    .getInstance()
                                    .all();
                        }
                        return Single.just(userResponses);
                    }
                })
                .doOnSuccess(new Consumer<List<UserResponse>>() {
                    @Override
                    public void accept(List<UserResponse> userResponses) throws Exception {
                        //Log.d(TAG, "acceptObservable: " + userResponses.isEmpty());
                    }
                })
                .map(new Function<List<UserResponse>, List<User>>() {
                    @Override
                    public List<User> apply(List<UserResponse> userResponses) throws Exception {
                        List<User> users = new ArrayList<>();
                        for (UserResponse response : userResponses) {
                            database.getUserDao().insertOrUpdate(response);
                            users.add(new User(
                                    response.getName(),
                                    response.getSurname(),
                                    response.getAvatar(),
                                    response.getGender(),
                                    response.getAge(),
                                    response.getObjectId()
                            ));
                            //Log.d(TAG, "apply: " + users.size());
                        }
                        return users;
                    }
                });
    }

    @Override
    public Observable<List<User>> all(Map<String, String> options) {
        return null;
    }

    @Override
    public Single<User> fetch(String userId) {
        return
                restService
                        .fetch(userId)
                        .map(new Function<UserResponse, User>() {
            @Override
            public User apply(UserResponse response) throws Exception {
                database.getUserDao().insertOrUpdate(response);
                return new User(
                        response.getName(),
                        response.getSurname(),
                        response.getAvatar(),
                        response.getGender(),
                        response.getAge(),
                        response.getObjectId()
                );
            }
        });
    }

    @Override
    public Single<List<User>> search(String search) {
        return restService
                .search(search).map(new Function<List<UserResponse>, List<User>>() {
                    @Override
                    public List<User> apply(List<UserResponse> userResponses) throws Exception {
                        List<User> users = new ArrayList<>();
                        for (UserResponse response : userResponses) {
                            users.add(new User(
                                    response.getName(),
                                    response.getSurname(),
                                    response.getAvatar(),
                                    response.getGender(),
                                    response.getAge(),
                                    response.getObjectId()
                            ));
                        }
                        return users;
                    }
                });
    }

    @Override
    public Single<User> update(User user) {
        UserRequest userRequest = new UserRequest();
        userRequest.setName(user.getName());
        userRequest.setSurname(user.getSurname());
        userRequest.setAvatar(user.getAvatarUrl());
        userRequest.setObjectId(user.getId());
        userRequest.setAge(user.getAge());
        return restService
                .update(userRequest)
                .map(new Function<UserResponse, User>() {
                    @Override
                    public User apply(UserResponse response) throws Exception {
                        return new User(
                                response.getName(),
                                response.getSurname(),
                                response.getAvatar(),
                                response.getGender(),
                                response.getAge(),
                                response.getObjectId()
                        );
                    }
                });
    }

    @Override
    public Completable delete(String id) {
        return Completable.fromSingle(restService.remove(id));
    }

    @Override
    public Completable add(User user) {
        return null;
    }
}
