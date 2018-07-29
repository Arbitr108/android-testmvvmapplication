package android.smartdeveloper.ru.domain.repositories;

import android.smartdeveloper.ru.domain.entity.User;

import java.util.List;
import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface UserRepository {

    Observable<List<User>> all();

    Observable<List<User>> all(Map<String, String> options);

    Observable<User> fetch(String userId);

    Observable<List<User>> search(String search);

    Observable<User> update(User user);

    Completable delete(String id);

    Completable add(User user);
}
