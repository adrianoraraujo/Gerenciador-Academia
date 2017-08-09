package com.br.gerenciadordetreino.view.fragment;

import android.support.v4.app.Fragment;

import com.br.gerenciadordetreino.R;
import com.deepakbaliga.beautifulgraph.Plotter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joaov on 17/07/2017.
 */

@EFragment(R.layout.fragment_detalhe_treino)
public class DetalheExercicioFragment extends Fragment {
    @ViewById(R.id.plotter_view)
    Plotter plotter;

    @AfterViews
    void init(){
        List<Integer> plots =  new ArrayList<>();
        plotter.setRowCol(10,10);
        plotter.setPlots(plots);

    }
}
