package android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.list;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.smartdeveloper.ru.data.network.RestService;
import android.smartdeveloper.ru.data.repositories.UserRepositoryImpl;
import android.smartdeveloper.ru.domain.entity.User;
import android.smartdeveloper.ru.domain.usecase.UserListUseCase;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.base.BaseViewModel;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.base.recycler.events.OnItemClickEventModel;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.executor.UIThread;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.helpers.GuiHelper;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.list.observers.UserItemOnClickObserver;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.list.recycler.UserListAdapter;
import android.util.Log;
import android.widget.ImageView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class UserListViewModel extends BaseViewModel<UserListRouter>
        implements Observer<List<User>>{

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

        adapter
                .observeItemClick()
                .subscribe(new UserItemOnClickObserver(router) {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getDisposables().add(d);
                    }

                    @Override
                    public void onNext(OnItemClickEventModel<User> userOnItemClickEventModel) {
                        router.showUserDetails();
                    }
                });

//        getDisposables().add(UserRepository
//                .events
//                .subscribe(new Consumer<AppEvent>() {
//                    @Override
//                    public void accept(AppEvent appEvent) throws Exception {
//                        if(appEvent instanceof UserUpdateEvent){
//                            adapter.update(((UserUpdateEvent)appEvent).getUser());
//                        }else if(appEvent instanceof UserAddedEvent){
//                            //TODO: сделать получение нового user из UserAddedEvent
//                            adapter.notifyDataSetChanged();
//                        }else if(appEvent instanceof UserRemovedEvent){
//                            //TODO: сделать получение нового id user из UserRemovedEvent
//                            adapter.notifyDataSetChanged();
//                        }
//                    }
//                }));
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
        adapter.setItems(users);
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
