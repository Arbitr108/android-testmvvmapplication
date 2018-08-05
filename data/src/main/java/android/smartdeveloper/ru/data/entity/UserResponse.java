package android.smartdeveloper.ru.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.smartdeveloper.ru.data.database.UserDAO;
import android.smartdeveloper.ru.domain.entity.Gender;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = UserDAO.TABLE_NAME)
public class UserResponse  implements DataModel {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    @SerializedName("objectId")
    private String objectId;

    @SerializedName("name")
    private String name;

    @SerializedName("surname")
    private String surname;

    @SerializedName("created")
    private long created;


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

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof UserResponse))
            return false;

        UserResponse user = (UserResponse) o;

        if (objectId.equals(user.getObjectId()))
            return true;

        return false;
    }


}
