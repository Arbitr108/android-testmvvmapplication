package android.smartdeveloper.ru.data.repositories;

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
import io.reactivex.functions.Function;

public class UserRepositoryImpl implements UserRepository {

    private static final String TAG = "UserRepositoryImpl";
    private final RestService restService;

    public UserRepositoryImpl(RestService restService) {
        this.restService = restService;
    }

    @Override
    public Observable<List<User>> all() {


        return restService
                .getInstance()
                .all()
                .map(new Function<List<UserResponse>, List<User>>() {
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
    public Observable<List<User>> all(Map<String, String> options) {
        return null;
    }

    @Override
    public Observable<User> fetch(String userId) {
        return restService.fetch(userId).map(new Function<UserResponse, User>() {
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
    public Observable<List<User>> search(String search) {
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
    public Observable<User> update(User user) {
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
        return Completable.fromObservable(restService.remove(id));
    }

    @Override
    public Completable add(User user) {
        return null;
    }
}
