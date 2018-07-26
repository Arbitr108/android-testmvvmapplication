package android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.list.edit;

import android.smartdeveloper.ru.testrxmvvmapplication.R;
import android.smartdeveloper.ru.testrxmvvmapplication.databinding.FragmentUserEditBinding;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.base.BaseMVVMFragment;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.list.UserListRouter;


public class UserEditFragment extends BaseMVVMFragment<UserEditViewModel, FragmentUserEditBinding, UserListRouter> {
    
    public UserEditFragment() {
        // Required empty public constructor
    }

    public static UserEditFragment newInstance(UserListRouter router, UserEditViewModel viewModel) {
        UserEditFragment fragment = new UserEditFragment();
        fragment.addRouter(router);
        fragment.setViewModel(viewModel);
        return fragment;
    }

    public void addRouter(UserListRouter router){
        this.router = router;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_user_edit;
    }

    public void setViewModel(UserEditViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
