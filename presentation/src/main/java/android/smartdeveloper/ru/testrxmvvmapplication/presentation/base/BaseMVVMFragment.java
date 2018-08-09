package android.smartdeveloper.ru.testrxmvvmapplication.presentation.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.smartdeveloper.ru.testrxmvvmapplication.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseMVVMFragment
        <M extends BaseFragmentViewModel, B extends ViewDataBinding,  R>
        extends BaseFragment {
    private static final String TAG = "BaseMVVMFragment";

    protected R router;
    protected M viewModel;
    protected B binding;

    protected abstract int provideLayoutId();
    protected abstract R provideRouter();
    protected abstract M provideViewModel();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        //Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        this.viewModel = provideViewModel();
        this.router = provideRouter();
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //hide menu for this fragment
        setHasOptionsMenu(false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //return menu back
        setHasOptionsMenu(true);
    }
}
