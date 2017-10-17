package com.br.gerenciadordetreino.persistence;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.br.gerenciadordetreino.model.Equipamento;
import com.br.gerenciadordetreino.model.Treino;

import org.androidannotations.annotations.App;

import java.util.List;

/**
 * Created by joaov on 13/07/2017.
 */

public class EquipamentoDAO {
    public static void addOrUpdate(Context context, Equipamento equipamento) {
        if(getEquipamento(context, equipamento.getId())!= null){
            updateUser(context, equipamento);
        }else {
            AppDatabase.getAppDatabase(context).equipamentoDao().insertAll(equipamento);
        }
        AppDatabase.destroyInstance();
    }

    public static void deleteEquipamento(Context con, Equipamento equipamento){
        AppDatabase.getAppDatabase(con).equipamentoDao().delete(equipamento);
    }

    public static List<Equipamento> getEquipamentos(Context context){
        List<Equipamento> equipamentos =  AppDatabase.getAppDatabase(context).equipamentoDao().getAllEquipamentos();
        AppDatabase.destroyInstance();
        return equipamentos;
    }

    public static Equipamento getEquipamento(Context context, int id) {
        Equipamento equipamento = AppDatabase.getAppDatabase(context).equipamentoDao().findById(id);
        AppDatabase.destroyInstance();
        return equipamento;
    }

    public  static  void updateUser(Context context, Equipamento equipamento){
        AppDatabase.getAppDatabase(context).equipamentoDao().update(equipamento);
        AppDatabase.destroyInstance();
    }

    public static List<Equipamento> getEquipamentsForCategory(Context context, String categoria) {
        List<Equipamento> equipamentos =  AppDatabase.getAppDatabase(context).equipamentoDao().getEquipamentsForCategory(categoria);
        AppDatabase.destroyInstance();
        return equipamentos;
    }
}
