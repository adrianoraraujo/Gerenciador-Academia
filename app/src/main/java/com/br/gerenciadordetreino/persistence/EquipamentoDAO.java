package com.br.gerenciadordetreino.persistence;

import android.arch.persistence.room.Update;
import android.content.Context;

import com.br.gerenciadordetreino.model.User;

/**
 * Created by joaov on 13/07/2017.
 */

public class UserDAO {
    public static void createUser(Context context, User user) {
        if(getUser(context)!= null){
            updateUser(context, user);
        }else {
            AppDatabase.getAppDatabase(context).userDao().insertAll(user);
        }
    }

    public static User getUser(Context context) {
        User user = AppDatabase.getAppDatabase(context).userDao().findByName("1");
        return user;
    }
    public  static  void updateUser(Context context, User user){
        AppDatabase.getAppDatabase(context).userDao().update(user);
    }
}
