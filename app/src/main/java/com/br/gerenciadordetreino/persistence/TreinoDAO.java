package com.br.gerenciadordetreino.persistence;

import android.content.Context;

import com.br.gerenciadordetreino.model.Equipamento;
import com.br.gerenciadordetreino.model.Treino;
import com.br.gerenciadordetreino.utils.DateUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by joaov on 13/07/2017.
 */

public class TreinoDAO {
    public static void add(Context context, Treino treino) {
        AppDatabase.getAppDatabase(context).treinoDao().insertAll(treino);
        AppDatabase.destroyInstance();
    }

    public static void deleteTreino(Context con, Treino treino) {
        AppDatabase.getAppDatabase(con).treinoDao().delete(treino);
    }

    public static List<Treino> getTreinos(Context context) {
        List<Treino> treinos = AppDatabase.getAppDatabase(context).treinoDao().getAllTreinos();
        AppDatabase.destroyInstance();
        return treinos;
    }

    public static List<Treino> getTreinos(Context context, Date data) {
        List<Treino> treinos = AppDatabase.getAppDatabase(context).treinoDao().getAllTreinos();
        AppDatabase.destroyInstance();
        return treinos;
    }

    public static Treino getTreino(Context context, int id) {
        Treino treino = AppDatabase.getAppDatabase(context).treinoDao().findById(id);
        AppDatabase.destroyInstance();
        return treino;
    }

    public static void updateTreino(Context context, Treino treino) {
        AppDatabase.getAppDatabase(context).treinoDao().update(treino);
        AppDatabase.destroyInstance();
    }
}
