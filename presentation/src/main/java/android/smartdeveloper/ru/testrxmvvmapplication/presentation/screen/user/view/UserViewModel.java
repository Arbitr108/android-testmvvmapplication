package android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.view;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.smartdeveloper.ru.domain.entity.User;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.base.BaseViewModel;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.helpers.GuiHelper;
import android.widget.ImageView;

public class UserViewModel extends BaseViewModel {
    public ObservableField<User> user = new ObservableField<>();

    public UserViewModel(User user) {
        this.user.set(user);
    }

    @BindingAdapter({"bind:avatarUrl"})
    public static void loadAvatar(ImageView view, String avatarUrl) {
        GuiHelper.renderImage(view, avatarUrl);
    }
}
