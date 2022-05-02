package com.example.projetmobile.BDD.models.Controllers;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;
import com.example.projetmobile.BDD.Repository.AppDataBase;
import com.example.projetmobile.BDD.Repository.UserDao;
import com.example.projetmobile.BDD.models.UserBDD;
import com.example.projetmobile.Model.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserControlers extends ViewModel {

    private UserDao userdao;

    public void init(Context context){
        AppDataBase db = Room.databaseBuilder(context,AppDataBase.class,"database-name").allowMainThreadQueries().build();
        this.userdao = db.userDao();
    }

    public List<UserBDD> getPlanning() {
        return userdao.getAll();

    }
    public void insert(UserBDD c) {
         userdao.insert(c);

    }

    public int Count() {
       return userdao.getCount();

    }

    public void Delete(UserBDD u) {
         userdao.delete(u);

    }
}
