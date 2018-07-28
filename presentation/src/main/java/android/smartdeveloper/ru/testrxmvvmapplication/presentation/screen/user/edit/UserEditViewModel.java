package android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.edit;

import android.databinding.ObservableField;
import android.smartdeveloper.ru.data.network.RestService;
import android.smartdeveloper.ru.data.repositories.UserRepositoryImpl;
import android.smartdeveloper.ru.domain.entity.User;
import android.smartdeveloper.ru.domain.usecase.UpdateUserUseCase;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.base.BaseViewModel;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.executor.UIThread;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.list.UserListRouter;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;


public class UserEditViewModel extends BaseViewModel<UserListRouter> implements CompletableObserver{

    UpdateUserUseCase updateUserUseCase;

    private static final String TAG = "UserEditViewModel";

    public ObservableField<User> user = new ObservableField<>();
    public ObservableField<String> name = new ObservableField<>();

    public UserEditViewModel(){
        updateUserUseCase = new UpdateUserUseCase(
                new UserRepositoryImpl(RestService.getInstance()), UIThread.getInstance());

    }

    public UserEditViewModel(User user){
        updateUserUseCase = new UpdateUserUseCase(
                new UserRepositoryImpl(RestService.getInstance()), UIThread.getInstance());

        this.user.set(user);
    }


    public void updateUser() {
        //TODO add validation, create new user to send to the userCase
        updateUserUseCase
                .updateUser(user.get())
                .subscribe(this);
    }

    @Override
    public void onSubscribe(Disposable d) {
        getDisposables().add(d);
    }

    @Override
    public void onComplete() {
       router.showUpdateSuccess();
    }

    @Override
    public void onError(Throwable e) {
        router.showUpdateFail(e.getMessage());
    }
}
