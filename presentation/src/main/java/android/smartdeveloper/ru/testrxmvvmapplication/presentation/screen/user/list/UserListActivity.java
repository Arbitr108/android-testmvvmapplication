package android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.list;

import android.arch.lifecycle.ViewModelProviders;
import android.smartdeveloper.ru.testrxmvvmapplication.R;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.base.BaseMVVMActivity;

public class UserListActivity extends BaseMVVMActivity {

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_user_list;
    }

    @Override
    protected UserListViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(UserListViewModel.class);
    }

    @Override
    protected UserListRouter provideRouter() {
        return new UserListRouter(this);
    }
}
