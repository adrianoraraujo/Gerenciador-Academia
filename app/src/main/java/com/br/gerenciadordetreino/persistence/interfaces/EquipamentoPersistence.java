package com.br.gerenciadordetreino.persistence.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.br.gerenciadordetreino.model.Equipamento;
import com.br.gerenciadordetreino.model.User;

import java.util.List;

/**
 * Created by joaov on 13/07/2017.
 */

@Dao
public  interface EquipamentoPersistence {
    @Query("SELECT * FROM equipamento")
    List<Equipamento> getAllEquipamentos();

    @Query("SELECT * FROM equipamento where id = :id")
    Equipamento findById(int id);

    @Query("SELECT COUNT(*) from equipamento")
    int countEquipamentos();

    @Insert
    void insertAll(Equipamento... equipamentos);

    @Delete
    void delete(Equipamento equipamento);

    @Update
    void update(Equipamento equipamento);
}
