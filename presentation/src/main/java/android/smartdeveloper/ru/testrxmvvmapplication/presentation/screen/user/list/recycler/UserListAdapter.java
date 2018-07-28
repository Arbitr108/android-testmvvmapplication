package android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.list.recycler;

import android.smartdeveloper.ru.domain.entity.User;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.base.recycler.BaseRecyclerViewAdapter;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

public class UserListAdapter extends BaseRecyclerViewAdapter<User, UserItemViewModel> {

    @NonNull
    @Override
    public UserItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return UserItemViewHolder.create(parent, new UserItemViewModel());
    }

    @Override
    public void updateItem(User user) {
        int position = items.indexOf(user);
        if(position > -1){
            items.set(position, new User(user));
            notifyItemChanged(position);
        }
    }
}
