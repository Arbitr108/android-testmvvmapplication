package android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.edit;

import android.os.Bundle;
import android.smartdeveloper.ru.domain.entity.User;
import android.smartdeveloper.ru.testrxmvvmapplication.R;
import android.smartdeveloper.ru.testrxmvvmapplication.databinding.FragmentUserEditBinding;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.base.BaseMVVMFragment;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.list.UserListActivity;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.list.UserListRouter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class UserEditFragment extends BaseMVVMFragment<UserEditViewModel, FragmentUserEditBinding, UserListRouter> {

    public static final String USER = "user";

    public UserEditFragment() {
        // Required empty public constructor
    }

    public static UserEditFragment newInstance(final User user) {
        UserEditFragment fragment = new UserEditFragment();
        Bundle args = new Bundle();
        args.putSerializable(USER, new User(user));
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(getArguments() != null){
            viewModel.user.set((User)getArguments().getSerializable(USER));
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        router = ((UserListActivity)getActivity()).getRouter();
        viewModel = new UserEditViewModel();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_user_edit;
    }

    public void setViewModel(UserEditViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
