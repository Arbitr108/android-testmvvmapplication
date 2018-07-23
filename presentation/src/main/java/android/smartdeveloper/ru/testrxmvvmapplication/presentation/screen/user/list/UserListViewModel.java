package android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.list;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.smartdeveloper.ru.data.network.RestService;
import android.smartdeveloper.ru.data.repositories.UserRepositoryImpl;
import android.smartdeveloper.ru.domain.entity.User;
import android.smartdeveloper.ru.domain.usecase.UserListUseCase;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.base.BaseViewModel;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.executor.UIThread;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.helpers.GuiHelper;
import android.util.Log;
import android.widget.ImageView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class UserListViewModel extends BaseViewModel implements Observer<List<User>> {

    private static final String TAG = "UserListViewModel";

    private UserListUseCase userListUseCase;

    public UserListAdapter adapter  = new UserListAdapter();

    public ObservableField<User> user = new ObservableField<>();

    public UserListViewModel() {

        userListUseCase = new UserListUseCase(
                new UserRepositoryImpl(RestService.getInstance()), UIThread.getInstance());

        userListUseCase
                .getUsers()
                .subscribe(this);
    }

    @BindingAdapter({"bind:avatarUrl"})
    public static void loadAvatar(ImageView view, String avatarUrl) {
        GuiHelper.renderImage(view, avatarUrl);
    }

    @Override
    public void onSubscribe(Disposable d) {
        getDisposables().add(d);
    }

    @Override
    public void onNext(List<User> users) {
        user.set(users.get(users.size() - 1));
        adapter.setUsers(users);
    }

    @Override
    public void onError(Throwable e) {
        router.showError(e);
    }

    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete: ");
    }
}
