package android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.list;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.smartdeveloper.ru.data.network.RestService;
import android.smartdeveloper.ru.data.repositories.UserRepositoryImpl;
import android.smartdeveloper.ru.domain.entity.User;
import android.smartdeveloper.ru.domain.repositories.UserRepository;
import android.smartdeveloper.ru.domain.usecase.SearchUseCase;
import android.smartdeveloper.ru.domain.usecase.UserListUseCase;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.base.BaseViewModel;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.base.recycler.OnItemClickEventModel;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.executor.UIThread;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.helpers.GuiHelper;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.list.recycler.UserListAdapter;
import android.widget.ImageView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

public class UserListViewModel extends BaseViewModel<UserListRouter> {

    private static final String TAG = "UserListViewModel";

    private final SearchUseCase userSearchUseCase;

    private final UserListUseCase userListUseCase;

    public UserListAdapter adapter = new UserListAdapter();

    public ObservableField<User> user = new ObservableField<>();

    public UserListViewModel() {
        UserRepository repository = new UserRepositoryImpl(RestService.getInstance());

        userListUseCase = new UserListUseCase(repository, UIThread.getInstance());

        userSearchUseCase = new SearchUseCase(repository, UIThread.getInstance());

    }

    public void load(){
        userListUseCase.getUsers()
                .subscribe(new Observer<List<User>>() {
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

                    }
                });

    }

    public void initObservers() {
        adapter.observeItemClick()
                .subscribe(new Observer<OnItemClickEventModel<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getDisposables().add(d);
                    }

                    @Override
                    public void onNext(OnItemClickEventModel<User> userOnItemClickEventModel) {
                        router.showUserDetails(userOnItemClickEventModel.getEntity());
                    }

                    @Override
                    public void onError(Throwable e) {
                        router.showError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        adapter.observeEditButtonClick().subscribe(new Observer<OnItemClickEventModel<User>>() {
            @Override
            public void onSubscribe(Disposable d) {
                getDisposables().add(d);
            }

            @Override
            public void onNext(OnItemClickEventModel<User> userOnItemClickEventModel) {
                router.showUserEdit(userOnItemClickEventModel.getEntity());
            }

            @Override
            public void onError(Throwable e) {
                router.showError(e);
            }

            @Override
            public void onComplete() {

            }
        });

        adapter.observeDeleteButtonClick().subscribe(new Observer<OnItemClickEventModel<User>>() {
            @Override
            public void onSubscribe(Disposable d) {
                getDisposables().add(d);
            }

            @Override
            public void onNext(OnItemClickEventModel<User> userOnItemClickEventModel) {
                router.showUserRemove(userOnItemClickEventModel.getEntity());
            }

            @Override
            public void onError(Throwable e) {
                router.showError(e);
            }

            @Override
            public void onComplete() {

            }
        });

    }

    @BindingAdapter({"bind:avatarUrl"})
    public static void loadAvatar(ImageView view, String avatarUrl) {
        GuiHelper.renderImage(view, avatarUrl);
    }

    public void observeSearch(PublishSubject<String> searchSubject) {
        searchSubject.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                getDisposables().add(d);
            }

            @Override
            public void onNext(String search) {
                userSearchUseCase.search(search)
                        .subscribe(new Observer<List<User>>() {
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

                            }
                        });
            }

            @Override
            public void onError(Throwable e) {
                router.showError(e);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
