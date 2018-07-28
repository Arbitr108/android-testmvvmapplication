package android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.list.observers;

import android.smartdeveloper.ru.domain.entity.User;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.base.BaseItemClickObserver;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.base.recycler.events.OnItemClickEventModel;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.list.UserListRouter;

import io.reactivex.disposables.Disposable;

public class UserItemOnClickObserver extends BaseItemClickObserver<OnItemClickEventModel<User>, UserListRouter> {

    public UserItemOnClickObserver(UserListRouter router) {
        super(router);
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(OnItemClickEventModel<User> userOnItemClickEventModel) {
        router.showUserDetails();
    }
}
