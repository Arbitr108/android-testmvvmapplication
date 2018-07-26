package android.smartdeveloper.ru.data.entity;

import android.smartdeveloper.ru.domain.entity.Gender;

import com.google.gson.annotations.SerializedName;

public class UserResponse  implements DataModel {
    @SerializedName("name")
    private String name;

    @SerializedName("surname")
    private String surname;

    @SerializedName("created")
    private long created;

    @SerializedName("objectId")
    private String objectId;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("age")
    private int age;

    @SerializedName("updated")
    private long updated;

    @SerializedName("gender")
    private Gender gender;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public long getCreated() {
        return created;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getAvatar() {
        return avatar;
    }

    public long getUpdated() {
        return updated;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }
}
