package com.br.gerenciadordetreino.persistence.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.br.gerenciadordetreino.model.Equipamento;
import com.br.gerenciadordetreino.model.Treino;

import java.util.Date;
import java.util.List;

/**
 * Created by joaov on 13/07/2017.
 */

@Dao
public  interface TreinoPersistence {
    @Query("SELECT * FROM Treino")
    List<Treino> getAllTreinos();

    @Query("SELECT * FROM Treino where data > :dataInicial and data < :dataFinal  ")
    List<Treino> getTreinosSemana(Date dataInicial, Date dataFinal);

    @Query("SELECT * FROM Treino where id = :id")
    Treino findById(int id);

    @Query("SELECT COUNT(*) from Treino")
    int countTreinos();

    @Query("SELECT * FROM Treino where categoria = :categoria ")
    List<Treino> getTreinosForCategory(String categoria);

    @Insert
    void insertAll(Treino... treinos);

    @Delete
    void delete(Treino treino);

    @Update
    void update(Treino treino);
}
