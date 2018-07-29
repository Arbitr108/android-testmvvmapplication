package android.smartdeveloper.ru.testrxmvvmapplication.presentation.base.recycler;

import android.databinding.ViewDataBinding;
import android.smartdeveloper.ru.domain.entity.DomainModel;
import android.support.v7.widget.RecyclerView;

import com.android.databinding.library.baseAdapters.BR;

public class BaseItemViewHolder
                <E extends DomainModel,
                VM extends BaseItemViewModel<E>,
                B extends ViewDataBinding>
        extends RecyclerView.ViewHolder {

    private VM viewModel;
    private B binding;

    public BaseItemViewHolder(VM viewModel, B binding) {
        super(binding.getRoot());
        this.viewModel = viewModel;
        this.binding = binding;
        binding.setVariable(BR.viewModel, viewModel);
    }

    public void hydrate(E item, int position){
        viewModel.setItem(item, position);
        binding.executePendingBindings();
    }

    public VM getViewModel(){
        return viewModel;
    }
}
