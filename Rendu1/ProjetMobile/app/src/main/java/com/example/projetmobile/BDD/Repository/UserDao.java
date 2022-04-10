package com.example.projetmobile.BDD.Repository;

import androidx.room.*;
import com.example.projetmobile.BDD.models.UserBDD;
import com.example.projetmobile.Model.User;
import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserBDD user);
    @Query("SELECT * FROM UserBDD")
    List<UserBDD> getAll();
    @Delete
    void delete(UserBDD plan);
}
