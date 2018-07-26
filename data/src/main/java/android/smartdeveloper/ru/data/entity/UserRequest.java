package android.smartdeveloper.ru.data.entity;

import com.google.gson.annotations.SerializedName;

public class UserRequest  implements DataModel {

    @SerializedName("name")
    private String name;

    @SerializedName("surname")
    private String surname;

    @SerializedName("created")
    private long created;

    @SerializedName("objectId")
    private String id;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("updated")
    private long updated;

    @SerializedName("age")
    private int age;

    @SerializedName("___class")
    private String ___class = "user";

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public void setObjectId(String id) {
        this.id = id;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
