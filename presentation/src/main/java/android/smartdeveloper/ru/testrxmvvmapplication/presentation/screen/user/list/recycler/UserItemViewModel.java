package android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.list.recycler;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.smartdeveloper.ru.domain.entity.Gender;
import android.smartdeveloper.ru.domain.entity.User;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.base.recycler.BaseItemViewModel;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.helpers.GuiHelper;
import android.widget.ImageView;

public class UserItemViewModel extends BaseItemViewModel<User> {
    private static final String TAG = "UserViewModel";
    private static final String MUSC = "муж";
    private static final String FEM = "жен";

    public String userId;
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> surname = new ObservableField<>();
    public ObservableField<String> age = new ObservableField<>();
    public ObservableField<String> gender = new ObservableField<>();
    public ObservableField<String> avatarUrl = new ObservableField<>();


    @BindingAdapter({"bind:avatarUrl"})
    public static void loadAvatar(ImageView view, String avatarUrl) {
        GuiHelper.renderImage(view, avatarUrl);
    }

    @Override
    public void setItem(User user, int position) {
        this.userId = user.getId();
        this.gender.set(user.getGender() == Gender.M ? MUSC : FEM);
        this.name.set(user.getName());
        this.surname.set(user.getSurname());
        this.avatarUrl.set(user.getAvatarUrl());
        this.age.set(String.valueOf(user.getAge()));
    }
}
