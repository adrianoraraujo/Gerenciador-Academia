package com.br.gerenciadordetreino.persistence;

import android.content.Context;

import com.br.gerenciadordetreino.model.Foto;
import com.br.gerenciadordetreino.model.Treino;

import java.util.Date;
import java.util.List;

/**
 * Created by joaov on 13/07/2017.
 */

public class FotoDAO {
    public static void add(Context context, Foto foto) {
        AppDatabase.getAppDatabase(context).fotoPersistence().insertAll(foto);
        AppDatabase.destroyInstance();
    }

    public static void deleteFoto(Context con, Foto foto) {
        AppDatabase.getAppDatabase(con).fotoPersistence().delete(foto);
    }

    public static List<Foto> getFotos(Context context) {
        List<Foto> fotos = AppDatabase.getAppDatabase(context).fotoPersistence().getAll();
        AppDatabase.destroyInstance();
        return fotos;
    }

    public static Foto getFoto(Context context, String path) {
        Foto foto = AppDatabase.getAppDatabase(context).fotoPersistence().findByName(path);
        AppDatabase.destroyInstance();
        return foto;
    }

    public static void updateFotos(Context context, Foto foto) {
        AppDatabase.getAppDatabase(context).fotoPersistence().update(foto);
        AppDatabase.destroyInstance();
    }
}
