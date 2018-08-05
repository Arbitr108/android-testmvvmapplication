package android.smartdeveloper.ru.data.database.converters;

import android.arch.persistence.room.TypeConverter;
import android.smartdeveloper.ru.domain.entity.Gender;

public class Converters {

    @TypeConverter
    public static Gender toGender(String value) {
        if(value == null)
            return null;
        if(value.equals("M"))
            return Gender.M;
        else if(value.equals("W"))
            return Gender.W;
        return null;
    }

    @TypeConverter
    public static String toString(Gender value) {
        if(value == null)
            return null;
        if(value.equals(Gender.M))
            return "M";
        if(value.equals(Gender.W))
            return "W";
        return null;
    }
}
