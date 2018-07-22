package android.smartdeveloper.ru.data.entity;

import com.google.gson.annotations.SerializedName;

public class UserRequest  implements DataModel {

    @SerializedName("firstname")
    private String firstname;

    @SerializedName("surname")
    private String surname;

    @SerializedName("created")
    private long created;

    @SerializedName("objectId")
    private String objectId;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("updated")
    private long updated;

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }
}
