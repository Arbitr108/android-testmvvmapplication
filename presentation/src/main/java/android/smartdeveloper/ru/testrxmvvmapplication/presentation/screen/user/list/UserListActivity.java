package android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.list;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.smartdeveloper.ru.testrxmvvmapplication.R;
import android.smartdeveloper.ru.testrxmvvmapplication.databinding.ActivityUserListBinding;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.base.BaseMVVMActivity;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

public class UserListActivity extends BaseMVVMActivity<UserListViewModel,ActivityUserListBinding,UserListRouter> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(viewModel.adapter);
    }

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
