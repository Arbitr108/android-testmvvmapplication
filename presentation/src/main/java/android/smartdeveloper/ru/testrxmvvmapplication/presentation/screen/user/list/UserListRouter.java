package android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.list;

import android.smartdeveloper.ru.domain.entity.User;
import android.smartdeveloper.ru.testrxmvvmapplication.R;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.base.BaseRouter;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.edit.OnClickItemModel;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.edit.UserEditFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class UserListRouter extends BaseRouter<UserListActivity> {
    private static final String TAG = "UserListRouter";

    public UserListRouter(UserListActivity activity) {
        super(activity);
    }

    public void showUserInfo(String userId) {
    }

    public void showUserEdit(User user) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        UserEditFragment fragment =
                UserEditFragment.newInstance(user);

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.addToBackStack(null);

        transaction.setCustomAnimations(
                R.anim.slide_in_right, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_right);

        transaction.replace(R.id.fragment_container, fragment)
                .commit();

        fragment.observeSubmitClick()
                .subscribe(new Observer<OnClickItemModel<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        UserListViewModel viewModel = activity.getViewModel();
                        if(viewModel != null){
                            viewModel.getDisposables().add(d);
                        }
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
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean value) throws Exception {
                        if(value)
                            activity.onBackPressed();
                    }
                });
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

    public void showUserRemove(User user) {
        Toast.makeText(activity, String.format("Delete user %s", user.getId()), Toast.LENGTH_SHORT).show();
    }
}
