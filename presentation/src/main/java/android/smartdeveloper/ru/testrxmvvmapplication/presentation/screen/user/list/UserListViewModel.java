package android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.list;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.smartdeveloper.ru.data.network.RestService;
import android.smartdeveloper.ru.data.repositories.UserRepositoryImpl;
import android.smartdeveloper.ru.domain.entity.User;
import android.smartdeveloper.ru.domain.events.AppEvent;
import android.smartdeveloper.ru.domain.events.UserAddedEvent;
import android.smartdeveloper.ru.domain.events.UserRemovedEvent;
import android.smartdeveloper.ru.domain.events.UserUpdateEvent;
import android.smartdeveloper.ru.domain.repositories.UserRepository;
import android.smartdeveloper.ru.domain.usecase.UserListUseCase;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.base.BaseViewModel;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.executor.UIThread;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.helpers.GuiHelper;
import android.util.Log;
import android.widget.ImageView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UserListViewModel extends BaseViewModel<UserListRouter>
        implements Observer<List<User>>,
        UserListAdapter.OnUserClickListener{

    private static final String TAG = "UserListViewModel";

    private UserListUseCase userListUseCase;

    public UserListAdapter adapter  = new UserListAdapter(this);

    public ObservableField<User> user = new ObservableField<>();

    public UserListViewModel() {

        userListUseCase = new UserListUseCase(
                new UserRepositoryImpl(RestService.getInstance()), UIThread.getInstance());

        userListUseCase
                .getUsers()
                .subscribe(this);

        getDisposables().add(UserRepository
                .events
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AppEvent>() {
                    @Override
                    public void accept(AppEvent appEvent) throws Exception {
                        if(appEvent instanceof UserUpdateEvent){
                            adapter.update(((UserUpdateEvent)appEvent).getUser());
                        }else if(appEvent instanceof UserAddedEvent){
                            //TODO: сделать получение нового user из UserAddedEvent
                            adapter.notifyDataSetChanged();
                        }else if(appEvent instanceof UserRemovedEvent){
                            //TODO: сделать получение нового id user из UserRemovedEvent
                            adapter.notifyDataSetChanged();
                        }
                    }
                }));
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

    @Override
    public void onUserClicked(User user) {
        router.showUserEdit(user);
    }
}
