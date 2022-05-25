package com.example.projetmobile.BDD.Repository;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.projetmobile.BDD.models.UserBDD;
import com.example.projetmobile.Model.User;

@Database(entities ={UserBDD.class},version =1)
public abstract class AppDataBase extends RoomDatabase{
    public abstract UserDao userDao();
}
