package android.smartdeveloper.ru.domain.usecase;

import android.smartdeveloper.ru.domain.executors.PostExecutionThread;
import android.smartdeveloper.ru.domain.repositories.UserRepository;

import io.reactivex.Completable;

public class UserRemoveUseCase extends BaseUseCase {

    private UserRepository userRepository;

    public UserRemoveUseCase(UserRepository userRepository, PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        this.userRepository = userRepository;
    }

    public Completable removeUser(String userId){
        return userRepository
                .delete(userId)
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }
}
