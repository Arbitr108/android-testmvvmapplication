package android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.list;

import android.smartdeveloper.ru.domain.entity.User;
import android.smartdeveloper.ru.testrxmvvmapplication.databinding.ItemUserBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private final OnUserClickListener onUserClickListener;

    public interface OnUserClickListener {
        void onUserClicked(User user);
    }

    private List<User> users = new ArrayList<>();

    public UserListAdapter(OnUserClickListener onUserClickListener){
        this.onUserClickListener = onUserClickListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ItemUserBinding itemUserBinding =
                ItemUserBinding.inflate(layoutInflater, parent, false);
        return new UserViewHolder(itemUserBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = getUserPosition(position);
        if(user != null){
            holder.setViewModel(new UserViewModel(user, this.onUserClickListener));
        }
    }

    private User getUserPosition(int position) {
        return users.get(position);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        //return getLayoutIdForPosition(position);
        return super.getItemViewType(position);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull UserViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.bind();
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull UserViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }
}
