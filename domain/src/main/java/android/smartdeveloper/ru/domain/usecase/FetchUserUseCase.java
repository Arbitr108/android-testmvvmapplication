package android.smartdeveloper.ru.domain.usecase;

import android.smartdeveloper.ru.domain.entity.User;
import android.smartdeveloper.ru.domain.executors.PostExecutionThread;
import android.smartdeveloper.ru.domain.repositories.UserRepository;

import io.reactivex.Observable;

public class FetchUserUseCase extends BaseUseCase {

    private final UserRepository userRepository;

    public FetchUserUseCase(UserRepository repository, PostExecutionThread thread) {
        super(thread);
        this.userRepository = repository;
    }

    public Observable<User> getUser(String userId){
        return userRepository
                .fetch(userId)
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }
}
