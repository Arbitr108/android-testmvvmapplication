package android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.list;

import android.smartdeveloper.ru.domain.entity.User;
import android.smartdeveloper.ru.testrxmvvmapplication.R;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.base.BaseRouter;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.add.UserAddActivity;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.edit.OnClickItemModel;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.edit.UserEditFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;

import io.reactivex.Notification;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class UserListRouter extends BaseRouter<UserListActivity> implements UserEditFragment.UserEditRouting {

    private static final String TAG = "UserListRouter";

    public UserListRouter(UserListActivity activity) {
        super(activity);
    }

    public void showUserInfo(String userId) {
    }

    public void showUserEdit(User user) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        UserEditFragment fragment = (UserEditFragment) fragmentManager.findFragmentByTag(UserEditFragment.EDIT_USER);
        if(fragment == null){
            fragment = UserEditFragment.newInstance(user);
        }
        fragment.observeSubmitClick()
                .doOnEach(new Consumer<Notification<OnClickItemModel<User>>>() {
                    @Override
                    public void accept(Notification<OnClickItemModel<User>> onClickItemModelNotification) throws Exception {
                        Log.d(TAG, "accept: submit clicked");
                    }
                })
                .subscribe(new Observer<OnClickItemModel<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        activity.getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(OnClickItemModel<User> userOnUserUpdateSubmitModel) {
                        UserListViewModel viewModel = activity.getViewModel();
                        if(viewModel != null){
                            viewModel.adapter.updateItem(userOnUserUpdateSubmitModel.getEntity());
                        }
                        activity.onBackPressed();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        fragment.observeCancelClick()
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        activity.getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        activity.onBackPressed();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        replaceFragment(fragment, R.id.fragment_container, true, UserEditFragment.EDIT_USER);
    }

    public void showUpdateFail(String message) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
    }

    public void showUpdateSuccess() {
        activity.onBackPressed();
        Toast.makeText(activity, "Новые данные сохранены", Toast.LENGTH_LONG).show();
    }

    public void showUserDetails(User user) {
        Toast.makeText(activity, "User " + user.getId() + " details is clicked", Toast.LENGTH_SHORT).show();
    }

    public void showActionSuccess() {
        Toast.makeText(activity, "Операция выполнена успешно", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void doSomeRoutingWithUserEditing() {
        Log.d(TAG, "doSomeRoutingWithUserEditing: here we may route to logic which fragment needs");
    }

    public void showAddUser() {
        activity.startActivity(UserAddActivity.getIntent(activity));
    }
}
