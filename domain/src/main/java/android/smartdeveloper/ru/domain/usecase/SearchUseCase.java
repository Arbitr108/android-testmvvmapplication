package android.smartdeveloper.ru.domain.usecase;

import android.smartdeveloper.ru.domain.entity.User;
import android.smartdeveloper.ru.domain.executors.PostExecutionThread;
import android.smartdeveloper.ru.domain.repositories.UserRepository;

import java.util.List;

import io.reactivex.Observable;

public class SearchUseCase extends BaseUseCase {
    private UserRepository userRepository;

    public SearchUseCase(UserRepository userRepository, PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        this.userRepository = userRepository;
    }

    public Observable<List<User>> search(String search){
        return userRepository
                .search(search)
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }
}
