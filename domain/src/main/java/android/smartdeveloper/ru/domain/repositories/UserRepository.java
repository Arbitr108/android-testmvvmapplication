package android.smartdeveloper.ru.domain.repositories;

import android.smartdeveloper.ru.domain.entity.User;
import android.smartdeveloper.ru.domain.events.AppEvent;

import java.util.List;
import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public interface UserRepository {
    static PublishSubject<AppEvent> events = PublishSubject.create();

    Observable<List<User>> all();

    Observable<List<User>> all(Map<String, String> options);

    Completable update(User user);

    Completable delete(String id);

    Completable add(User user);
}
