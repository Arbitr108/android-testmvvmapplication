package android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.list;

import android.smartdeveloper.ru.domain.entity.User;
import android.smartdeveloper.ru.testrxmvvmapplication.R;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.base.BaseRouter;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.list.edit.UserEditFragment;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.list.edit.UserEditViewModel;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

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
                UserEditFragment.newInstance(this, new UserEditViewModel(user));

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.addToBackStack(null);

        transaction.setCustomAnimations(
                R.anim.slide_in_right, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_right);

        transaction.replace(R.id.fragment_container, fragment)
                .commit();
    }

    public void showUpdateFail(String message) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
    }

    public void showUpdateSuccess() {
        Toast.makeText(activity, "Новые данные сохранены", Toast.LENGTH_LONG).show();
    }
}
