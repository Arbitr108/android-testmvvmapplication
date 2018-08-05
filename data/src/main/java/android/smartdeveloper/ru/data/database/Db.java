package android.smartdeveloper.ru.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.smartdeveloper.ru.data.database.converters.Converters;
import android.smartdeveloper.ru.data.entity.UserResponse;

@Database(entities = {UserResponse.class}, version = 1,  exportSchema = false)
@TypeConverters({Converters.class})
public abstract class Db extends RoomDatabase {

    public static final String DATABASE_NAME = "test.db";
    private static Db instance;


    public abstract UserDAO getUserDao();


    public static Db getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, Db.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public static void destroyInstance(){ }
}
