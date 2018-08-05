package android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.edit;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.smartdeveloper.ru.domain.entity.User;
import android.smartdeveloper.ru.testrxmvvmapplication.R;
import android.smartdeveloper.ru.testrxmvvmapplication.databinding.FragmentUserEditBinding;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.base.BaseMVVMFragment;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.list.UserListActivity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;


public class UserEditFragment extends BaseMVVMFragment
        <UserEditViewModel, FragmentUserEditBinding, UserEditFragment.UserEditRouting> {
    private static final String TAG = "UserEditFragment";
    private static final String USER = "USER";
    public static final String EDIT_USER = "EDIT_USER";

    private PublishSubject<OnClickItemModel<User>> submitClickSubject = PublishSubject.create();
    private PublishSubject<Boolean> cancelClickSubject = PublishSubject.create();

    private ImageButton submitButton;
    private ImageButton cancelButton;

    public UserEditFragment() {
        // Required empty public constructor
    }

    public static UserEditFragment newInstance(User user) {
        Log.d(TAG, "newInstance: ");
        UserEditFragment fragment = new UserEditFragment();
        Bundle args = new Bundle();
        args.putSerializable(USER,user);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(getArguments() != null){
            this.viewModel.setItem((User)getArguments().getSerializable(USER));
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        submitButton = view.findViewById(R.id.fr_commit_edit);
        cancelButton = view.findViewById(R.id.fr_cancel_edit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    viewModel
                            .update()
                            .subscribe(new SingleObserver<User>(){
                                @Override
                                public void onSubscribe(Disposable d) {
                                    viewModel.getDisposables().add(d);
                                }

                                @Override
                                public void onSuccess(User user) {
                                    submitClickSubject
                                            .onNext(
                                            new OnClickItemModel<User>(user)
                                    );
                                }

                                @Override
                                public void onError(Throwable e) {
                                    router.showError(e);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        submitButton.setOnClickListener(null);
        cancelButton.setOnClickListener(null);
        submitButton = null;
        cancelButton = null;
    }

    protected int provideLayoutId() {
        return R.layout.fragment_user_edit;
    }

    @Override
    protected UserEditRouting provideRouter() {
        return (UserEditRouting) ((UserListActivity)getActivity()).getRouter();
    }

    @Override
    protected UserEditViewModel provideViewModel() {
        UserEditViewModel viewModel = ViewModelProviders.of(this).get(UserEditViewModel.class);
        return  ViewModelProviders.of(this).get(UserEditViewModel.class);
    }

    public Observable<OnClickItemModel<User>> observeSubmitClick(){
        return submitClickSubject;
    }

    public Observable<Boolean> observeCancelClick(){
        return cancelClickSubject;
    }

    public interface UserEditRouting{
        public void doSomeRoutingWithUserEditing();
        public void showError(Throwable e);
    }
}
