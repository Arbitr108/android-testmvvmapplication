package android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.list.recycler;

import android.smartdeveloper.ru.domain.entity.User;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.base.recycler.BaseRecyclerViewAdapter;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.base.recycler.OnItemClickEventModel;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class UserListAdapter extends BaseRecyclerViewAdapter<User, UserItemViewModel> {

    private PublishSubject<OnItemClickEventModel<User>> editButtonClickSubject = PublishSubject.create();
    private PublishSubject<OnItemClickEventModel<User>> deleteButtonClickSubject = PublishSubject.create();

    @NonNull
    @Override
    public UserItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return UserItemViewHolder.create(parent, new UserItemViewModel(editButtonClickSubject, deleteButtonClickSubject));
    }

    @Override
    public void updateItem(User user) {
        int position = items.indexOf(user);
        if(position > -1){
            items.set(position, new User(user));
            notifyItemChanged(position);
        }
    }

    public Observable<OnItemClickEventModel<User>> observeEditButtonClick(){
        return editButtonClickSubject;
    }

    public Observable<OnItemClickEventModel<User>> observeDeleteButtonClick(){
        return deleteButtonClickSubject;
    }
}
