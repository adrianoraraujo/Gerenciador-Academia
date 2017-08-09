package com.br.gerenciadordetreino.persistence;

import android.content.Context;

import com.br.gerenciadordetreino.model.Equipamento;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joaov on 13/07/2017.
 */

public class EquipamentoDAO {
    public static void addEquipamento(Context context, Equipamento equipamento) {
        if(getEquipamento(context, equipamento.getNome())!= null){
            updateUser(context, equipamento);
        }else {
            AppDatabase.getAppDatabase(context).equipamentoDao().insertAll(equipamento);
        }
    }

    public static List<Equipamento> getEquipamentos(Context context){
        return  AppDatabase.getAppDatabase(context).equipamentoDao().getAllEquipamentos();
    }

    public static Equipamento getEquipamento(Context context, String nome) {
        Equipamento equipamento = AppDatabase.getAppDatabase(context).equipamentoDao().findByNameEquipamento(nome);
        return equipamento;
    }

    public  static  void updateUser(Context context, Equipamento equipamento){
        AppDatabase.getAppDatabase(context).equipamentoDao().update(equipamento);
    }
}
