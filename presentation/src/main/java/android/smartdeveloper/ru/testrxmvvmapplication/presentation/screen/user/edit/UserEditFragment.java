package android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.edit;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.smartdeveloper.ru.domain.entity.User;
import android.smartdeveloper.ru.testrxmvvmapplication.R;
import android.smartdeveloper.ru.testrxmvvmapplication.databinding.FragmentUserEditBinding;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.list.UserListActivity;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.list.UserListRouter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;


public class UserEditFragment extends Fragment {
    private static final String TAG = "UserEditFragment";
    private static final String USER = "USER";

    private PublishSubject<OnClickItemModel<User>> submitClickSubject = PublishSubject.create();
    private PublishSubject<Boolean> cancelClickSubject = PublishSubject.create();

    private UserListRouter router;
    private UserEditViewModel viewModel;
    private ImageButton submitButton;
    private ImageButton cancelButton;

    public UserEditFragment() {
        // Required empty public constructor
    }

    public static UserEditFragment newInstance(User user) {
        UserEditFragment fragment = new UserEditFragment();
        Bundle args = new Bundle();
        args.putSerializable(USER,user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserListActivity activity = (UserListActivity)getActivity();
        if(activity != null){
            router = ((UserListActivity)getActivity()).getRouter();
        }
        viewModel = new UserEditViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(getArguments() != null){
            viewModel.setItem((User)getArguments().getSerializable(USER));
        }

        FragmentUserEditBinding binding = DataBindingUtil.inflate(
                inflater, provideLayoutId(), container, false);
        View view = binding.getRoot();

        binding.setViewModel(viewModel);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        submitButton = view.findViewById(R.id.fr_commit_edit);
        cancelButton = view.findViewById(R.id.fr_cancel_edit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    viewModel.update()
                            .subscribe(new Observer<User>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    viewModel.getDisposables().add(d);
                                }

                                @Override
                                public void onNext(User user) {
                                    submitClickSubject.onNext(
                                            new OnClickItemModel<User>(user)
                                    );
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
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelClickSubject.onNext(true);
            }
        });
    }

    protected int provideLayoutId() {
        return R.layout.fragment_user_edit;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        viewModel.clear();
    }

    public Observable<OnClickItemModel<User>> observeSubmitClick(){
        return submitClickSubject;
    }

    public Observable<Boolean> observeCancelClick(){
        return cancelClickSubject;
    }
}
