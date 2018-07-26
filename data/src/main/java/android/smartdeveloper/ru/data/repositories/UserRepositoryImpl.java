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

    public UserRepositoryImpl(RestService restService){
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
                        for (UserResponse response : userResponses){
                            users.add(new User(
                                    response.getName(),
                                    response.getSurname(),
                                    response.getAvatar(),
                                    response.getGender(),
                                    response.getAge()
                            ));
                        }
                        return users;
                    }
                });

//        return Observable.create(new ObservableOnSubscribe<List<User>>() {
//            List<User> users = new ArrayList<>();
//            @Override
//            public void subscribe(final ObservableEmitter<List<User>> emitter) throws Exception {
//                users.add(new User("Name2","Surname2","http://i.imgur.com/DvpvklR.png",
//                                Gender.M,
//                                30 ));
//
//                final Random random = new Random();
//                Observable
//                        .interval(2, TimeUnit.SECONDS)
//                        .subscribeOn(Schedulers.io())
//                        .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                       users.add(new User(
//                               "Name" + random.nextInt(500),
//                               "Surname" +random.nextInt(500),
//                               String.format("https://randomuser.me/api/portraits/women/%d.jpg", 1 + random.nextInt(98)),
//                               Gender.M,
//                               random.nextInt(50) ));
//
//                        Log.d(TAG, "accept: added, now size is " + users.size() + " thread:" + Thread.currentThread().getName());
//                        emitter.onNext(users);
//                    }
//                });
//
////                emitter.onComplete();
//            }
//        });
    }

    @Override
    public Observable<List<User>> all(Map<String, String> options) {
        return null;
    }

    @Override
    public Completable update(User user) {
        UserRequest userRequest = new UserRequest();
        userRequest.setName(user.getName());
        userRequest.setSurname(user.getSurname());
        userRequest.setAvatar(user.getAvatarUrl());
        userRequest.setObjectId(user.getId());
        userRequest.setAge(user.getAge());
        return restService.update(userRequest);
    }

    @Override
    public Completable delete(String id) {
        return null;
    }

    @Override
    public Completable add(User user) {
        return null;
    }
}
