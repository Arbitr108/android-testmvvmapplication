package android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.view;

import android.smartdeveloper.ru.testrxmvvmapplication.databinding.UserViewBinding;
import android.support.v7.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {

    private UserViewBinding binding;
    private UserViewModel userViewModel;

    public UserViewHolder(UserViewBinding userViewBinding) {
        super(userViewBinding.getRoot());
        this.binding = userViewBinding;
    }

    public void bind(){
        if(binding != null){
            binding.setViewModel(userViewModel);
            binding.executePendingBindings();
        }
    }

    public void unbind() {
        binding = null;
    }

    public void setViewModel(UserViewModel viewModel) {
        this.userViewModel = viewModel;
    }
}
