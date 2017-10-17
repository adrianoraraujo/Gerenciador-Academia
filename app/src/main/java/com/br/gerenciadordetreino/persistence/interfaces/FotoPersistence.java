package com.br.gerenciadordetreino.persistence.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.br.gerenciadordetreino.model.Foto;
import com.br.gerenciadordetreino.model.User;

import java.util.List;

/**
 * Created by joaov on 13/07/2017.
 */

@Dao
public  interface FotoPersistence {
    @Query("SELECT * FROM foto")
    List<Foto> getAll();

    @Query("SELECT * FROM foto where id LIKE  :path")
    Foto findByName(String path);

    @Query("SELECT COUNT(*) from foto")
    int countFoto();

    @Insert
    void insertAll(Foto... foto);

    @Delete
    void delete(Foto foto);

    @Update
    void update(Foto foto);
}
