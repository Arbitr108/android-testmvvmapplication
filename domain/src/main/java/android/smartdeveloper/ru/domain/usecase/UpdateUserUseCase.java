package android.smartdeveloper.ru.domain.usecase;

import android.smartdeveloper.ru.domain.entity.User;
import android.smartdeveloper.ru.domain.executors.PostExecutionThread;
import android.smartdeveloper.ru.domain.repositories.UserRepository;

import io.reactivex.Observable;

public class UpdateUserUseCase extends BaseUseCase {

    private final UserRepository userRepository;

    public UpdateUserUseCase(UserRepository userRepository, PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        this.userRepository = userRepository;
    }

    public Observable<User> updateUser(User user){

        return userRepository
                .update(user)
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }
}
