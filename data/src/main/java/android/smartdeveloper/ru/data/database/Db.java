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
    private static Db instanceInMemory;

    public abstract UserDAO getUserDao();


    public static Db getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, Db.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public static Db getInstanceInMemory(Context context){
        if(instanceInMemory == null){
            instanceInMemory = Room.inMemoryDatabaseBuilder(context, Db.class)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instanceInMemory;
    }

    public static void destroyInstance(){ }
}
