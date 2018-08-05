package android.smartdeveloper.ru.testrxmvvmapplication.presentation.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.smartdeveloper.ru.testrxmvvmapplication.BR;
import android.support.annotation.Nullable;

public abstract class BaseMVVMActivity
        <M extends BaseViewModel, B extends ViewDataBinding, R extends BaseRouter>
        extends BaseActivity {

    protected M viewModel;
    protected B binding;
    protected R router;

    protected abstract int provideLayoutId();
    protected abstract M provideViewModel();
    protected abstract R provideRouter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = provideViewModel();
        router = provideRouter();
        binding = DataBindingUtil.setContentView(this, provideLayoutId());
        binding.setVariable(BR.viewModel, viewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.addRouter(router);
    }

    @Override
    protected void onStop() {
        super.onStop();
        viewModel.removeRouter();
    }

    public R getRouter(){
        return router;
    }
    public M getViewModel(){return viewModel;}
}
