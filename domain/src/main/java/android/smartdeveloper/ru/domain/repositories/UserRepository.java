package android.smartdeveloper.ru.domain.repositories;

import android.smartdeveloper.ru.domain.entity.User;

import java.util.List;
import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface UserRepository {

    Single<List<User>> all();

    Observable<List<User>> all(Map<String, String> options);

    Single<User> fetch(String userId);

    Single<List<User>> search(String search);

    Single<User> update(User user);

    Completable delete(String id);

    Completable add(User user);
}
