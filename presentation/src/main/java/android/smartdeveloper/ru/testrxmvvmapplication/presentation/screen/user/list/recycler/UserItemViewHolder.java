package android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.list.recycler;

import android.smartdeveloper.ru.domain.entity.User;
import android.smartdeveloper.ru.testrxmvvmapplication.databinding.ItemUserBinding;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.base.recycler.BaseItemViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class UserItemViewHolder extends BaseItemViewHolder
                <User,UserItemViewModel, ItemUserBinding>{

    public UserItemViewHolder(UserItemViewModel viewModel, ItemUserBinding binding) {
        super(viewModel, binding);
    }

    public static UserItemViewHolder create(ViewGroup parent, UserItemViewModel viewModel){

        ItemUserBinding binding =
                ItemUserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new UserItemViewHolder(viewModel, binding);
    }
}
