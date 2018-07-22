package android.smartdeveloper.ru.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class DeleteResponse implements DataModel {
    @SerializedName("deletionTime")
    private Date deletionTime;

    public Date getDeletionTime() {
        return deletionTime;
    }
}
