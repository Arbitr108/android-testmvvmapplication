package android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.list.recycler;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.smartdeveloper.ru.domain.entity.Gender;
import android.smartdeveloper.ru.domain.entity.User;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.base.recycler.BaseItemViewModel;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.base.recycler.OnItemClickEventModel;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.helpers.GuiHelper;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import io.reactivex.subjects.PublishSubject;

public class UserItemViewModel extends BaseItemViewModel<User> {
    private static final String TAG = "UserViewModel";
    private static final String MUSC = "муж";
    private static final String FEM = "жен";

    private PublishSubject<OnItemClickEventModel<User>> editClickSubject = PublishSubject.create();
    private PublishSubject<OnItemClickEventModel<User>> deleteClickSubject = PublishSubject.create();


    private String userId;
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> surname = new ObservableField<>();
    public ObservableField<String> age = new ObservableField<>();
    public ObservableField<String> gender = new ObservableField<>();
    public ObservableField<String> avatarUrl = new ObservableField<>();
    private int position;


    public UserItemViewModel(PublishSubject<OnItemClickEventModel<User>> editClickSubject,
                             PublishSubject<OnItemClickEventModel<User>> deleteClickSubject) {
        this.editClickSubject = editClickSubject;
        this.deleteClickSubject = deleteClickSubject;
    }

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
        this.position = position;
    }

    public void onEditButtonClicked() {
        editClickSubject.onNext(new OnItemClickEventModel(getUser(), position));
    }

    public void onDeleteButtonClicked() {
        deleteClickSubject.onNext(new OnItemClickEventModel(getUser(), position));
    }

    @NonNull
    private User getUser() {
        return new User(
                name.get(),
                surname.get(),
                avatarUrl.get(),
                gender.get().equals(MUSC) ? Gender.M : Gender.W,
                Integer.valueOf(age.get()),
                userId);
    }
}
