package com.br.gerenciadordetreino.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.br.gerenciadordetreino.model.Equipamento;
import com.br.gerenciadordetreino.model.Treino;
import com.br.gerenciadordetreino.model.User;
import com.br.gerenciadordetreino.persistence.converters.Converters;
import com.br.gerenciadordetreino.persistence.interfaces.EquipamentoPersistence;
import com.br.gerenciadordetreino.persistence.interfaces.TreinoPersistence;
import com.br.gerenciadordetreino.persistence.interfaces.UserPersistence;

@Database(entities = {User.class, Equipamento.class, Treino.class}, version = 2)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract UserPersistence userDao();
    public abstract EquipamentoPersistence equipamentoDao();
    public abstract TreinoPersistence treinoDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "GerenciaAcademiaBase")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}