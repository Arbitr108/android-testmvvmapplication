package android.smartdeveloper.ru.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.smartdeveloper.ru.data.entity.UserResponse;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface UserDAO {

    String TABLE_NAME = "users";

    @Insert
    void insert(UserResponse ... user);

    @Update
    void update(UserResponse ... user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrUpdate(UserResponse ... users);

    @Query("DELETE FROM " + TABLE_NAME + " WHERE id = :id" )
    void delete(String id);

    @Query("SELECT * FROM " + TABLE_NAME + " WHERE id = :id" )
    UserResponse fetch(String id);

    @Query("SELECT * FROM " + TABLE_NAME + " ORDER BY `name` DESC ")
    Single<List<UserResponse>> all();
}
