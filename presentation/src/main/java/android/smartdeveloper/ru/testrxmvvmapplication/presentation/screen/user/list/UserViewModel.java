package android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.list;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.smartdeveloper.ru.domain.entity.User;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.base.BaseItemViewModel;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.helpers.GuiHelper;
import android.widget.ImageView;

public class UserViewModel extends BaseItemViewModel {
    private static final String TAG = "UserViewModel";
    private final UserListAdapter.OnUserClickListener clickListener;

    public ObservableField<User> user = new ObservableField<>();

    public UserViewModel(User user, UserListAdapter.OnUserClickListener onUserClickListener) {
        this.user.set(user);
        this.clickListener = onUserClickListener;
    }

    @BindingAdapter({"bind:avatarUrl"})
    public static void loadAvatar(ImageView view, String avatarUrl) {
        GuiHelper.renderImage(view, avatarUrl);
    }

    public void onUserClicked(){
        clickListener.onUserClicked(user.get());
    }
}
