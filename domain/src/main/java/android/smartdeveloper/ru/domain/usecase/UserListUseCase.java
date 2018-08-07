package android.smartdeveloper.ru.domain.usecase;

import android.smartdeveloper.ru.domain.entity.User;
import android.smartdeveloper.ru.domain.executors.PostExecutionThread;
import android.smartdeveloper.ru.domain.repositories.UserRepository;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.Single;

public class UserListUseCase extends BaseUseCase{
    private UserRepository userRepository;

    public UserListUseCase(UserRepository repository, PostExecutionThread thread) {
        super(thread);
        this.userRepository = repository;
    }


    //only for test
    public UserListUseCase(UserRepository repository, Scheduler executionThread, Scheduler postExecutionThread){
        super(executionThread, postExecutionThread);
        this.userRepository = repository;
    }

    public Single<List<User>> getUsers(){

        return userRepository
                .all()
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }



}
