package com.br.gerenciadordetreino.persistence.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.br.gerenciadordetreino.model.User;

import java.util.List;

/**
 * Created by joaov on 13/07/2017.
 */

@Dao
public  interface UserPersistence {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user where id LIKE  :id")
    User findByName(String id);

    @Query("SELECT COUNT(*) from user")
    int countUsers();

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);

    @Update
    void update(User user);
}
