package com.br.gerenciadordetreino.view.ordenacao;

import android.support.annotation.NonNull;

import com.br.gerenciadordetreino.model.Treino;

import java.util.Comparator;

/**
 * Created by joaov on 27/08/2017.
 */

public class TreinosSort implements Comparator<Treino> {
    String categoria;


    @Override
    public int compare(Treino o1, Treino o2) {
        if (medirPeso(o1) > medirPeso(o2)){
            return 1;
        }else{
            return -1;
        }
    }

    int medirPeso(Treino o1) {
        if (o1.getCategoria().equals("Abdomên")) {
            return 10;
        }
        if (o1.getCategoria().equals("Peito")) {
            return 9;
        }
        if (o1.getCategoria().equals("Ombro")) {
            return 8;
        }
        if (o1.getCategoria().equals("Costas")) {
            return 7;
        }
        if (o1.getCategoria().equals("Biceps")) {
            return 6;
        }
        if (o1.getCategoria().equals("Triceps")) {
            return 5;
        }
        if (o1.getCategoria().equals("Panturrilha")) {
            return 4;
        }
        if (o1.getCategoria().equals("Posterior")) {
            return 3;
        }
        if (o1.getCategoria().equals("Outro")) {
            return 2;
        }
        return 0;
    }
}
