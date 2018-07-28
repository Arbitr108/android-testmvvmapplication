package android.smartdeveloper.ru.testrxmvvmapplication.presentation.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.smartdeveloper.ru.testrxmvvmapplication.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseMVVMFragment
        <M extends BaseViewModel, B extends ViewDataBinding,  R extends BaseRouter>
        extends BaseFragment {

    protected R router;
    protected M viewModel;
    protected B binding;

    protected abstract int provideLayoutId();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(
                inflater, provideLayoutId(), container, false);
        binding.setVariable(BR.viewModel, viewModel);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        viewModel.addRouter(router);
    }

    @Override
    public void onStop() {
        super.onStop();
        viewModel.removeRouter();
    }
}
