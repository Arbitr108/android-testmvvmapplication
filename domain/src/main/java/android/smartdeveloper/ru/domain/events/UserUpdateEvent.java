package android.smartdeveloper.ru.domain.events;

import android.smartdeveloper.ru.domain.entity.User;

public class UserUpdateEvent extends AppEvent {
    private User user;

    public UserUpdateEvent(User user){
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
