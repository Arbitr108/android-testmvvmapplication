package android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.edit;

import android.databinding.ObservableField;
import android.smartdeveloper.ru.data.database.Db;
import android.smartdeveloper.ru.data.network.RestService;
import android.smartdeveloper.ru.data.repositories.UserRepositoryImpl;
import android.smartdeveloper.ru.domain.entity.Gender;
import android.smartdeveloper.ru.domain.entity.User;
import android.smartdeveloper.ru.domain.usecase.FetchUserUseCase;
import android.smartdeveloper.ru.domain.usecase.UpdateUserUseCase;
import android.smartdeveloper.ru.testrxmvvmapplication.App;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.base.BaseFragmentViewModel;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.executor.UIThread;

import io.reactivex.Single;


public class UserEditViewModel extends BaseFragmentViewModel<UserEditFragment.UserEditRouting> {

    public static final String MUSC = "М";
    public static final String FEM = "Ж";
    private UpdateUserUseCase updateUserUseCase;
    private FetchUserUseCase fetchUserUseCase;

    private static final String TAG = "UserEditViewModel";

    public String userId;
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> surname = new ObservableField<>();
    public ObservableField<String> age = new ObservableField<>();
    public ObservableField<String> avatarUrl = new ObservableField<>();
    public ObservableField<String> gender = new ObservableField<>();

    public UserEditViewModel() {
        updateUserUseCase =
                new UpdateUserUseCase(
                        new UserRepositoryImpl(
                                RestService.getInstance(),
                                Db.getInstance(App.getAppContext())),
                        UIThread.getInstance());

        fetchUserUseCase =
                new FetchUserUseCase(
                        new UserRepositoryImpl(
                                RestService.getInstance(),
                                Db.getInstance(App.getAppContext())),
                        UIThread.getInstance());

    }

    @Override
    public void setItem(User user) {
        name.set(user.getName());
        surname.set(user.getSurname());
        age.set(String.valueOf(user.getAge()));
        avatarUrl.set(user.getAvatarUrl());
        gender.set(user.getGender() == Gender.M ? MUSC : FEM);
        userId = user.getId();
    }

    public Single<User> update() {
        return updateUserUseCase
                .updateUser(
                        new User(
                                name.get(),
                                surname.get(),
                                avatarUrl.get(),
                                (gender.get() != null && gender.get().equals(MUSC)) ? Gender.M : Gender.W,
                                Integer.valueOf(age.get()),
                                userId));
    }
}
