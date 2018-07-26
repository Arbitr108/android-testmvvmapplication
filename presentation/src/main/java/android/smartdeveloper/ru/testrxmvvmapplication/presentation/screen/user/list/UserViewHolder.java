package android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.list;

import android.smartdeveloper.ru.testrxmvvmapplication.databinding.ItemUserBinding;
import android.support.v7.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder{

    private ItemUserBinding binding;
    private UserViewModel userViewModel;

    public UserViewHolder(ItemUserBinding itemUserBinding) {
        super(itemUserBinding.getRoot());
        this.binding = itemUserBinding;
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
