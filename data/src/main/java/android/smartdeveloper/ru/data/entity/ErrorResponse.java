package android.smartdeveloper.ru.data.entity;

import com.google.gson.annotations.SerializedName;

public class ErrorResponse extends Exception{

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
