package com.br.gerenciadordetreino.view.fragment.graficos;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.br.gerenciadordetreino.R;
import com.br.gerenciadordetreino.model.Treino;
import com.br.gerenciadordetreino.persistence.TreinoDAO;
import com.br.gerenciadordetreino.utils.DateUtils;
import com.br.gerenciadordetreino.view.SuperActivity;
import com.br.gerenciadordetreino.view.adapters.TreinoCategoriaAdapter;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by joaov on 17/07/2017.
 */

@EFragment(R.layout.fragment_graficos_categoria)
public class GraficosCategoriaFragment extends Fragment {
    @ViewById(R.id.piechart)
    PieChart pieChart;
    @ViewById(R.id.tv_treinos_empty)
    TextView tvTreinosEmpty;


    private String[] categorias;
    private List<Treino> treinosMes = new ArrayList<>();
    private LinearLayoutManager mLayoutManager;
    private TreinoCategoriaAdapter treinosCategoriaAdapter;

    public static GraficosCategoriaFragment newInstance() {
        Bundle args = new Bundle();
        GraficosCategoriaFragment fragment = new GraficosCategoriaFragment_();
        fragment.setArguments(args);
        return fragment;
    }

    @AfterViews
    void init() {
        SuperActivity.setTextToolbar(getActivity(), "GRÁFICOS");
        Resources res = getContext().getResources();
        categorias = res.getStringArray(R.array.categorias);
        List<Treino> treinos = TreinoDAO.getTreinos(getActivity());
        treinosMes = getTreinosMes(treinos);
        carregaGraficoCategorias();
    }

    private void carregaGraficoCategorias() {
        ArrayList<Entry> entrys = new ArrayList<>();
        if (treinosMes != null && treinosMes.size() != 0) {
            for (String categoriaAtual : categorias) {
                int somaValor = 0;
                int position = 0;
                for (Treino treinoAtual : treinosMes) {
                    if (treinoAtual.getCategoria().equals(categoriaAtual)) {
                        somaValor++;
                    }
                }
                if (somaValor != 0) {
                    entrys.add(new Entry(somaValor, position, categoriaAtual));
                }
                position++;
            }

            ArrayList<String> xVals = new ArrayList<>();
            xVals.addAll(Arrays.asList(categorias));

            configGraphic(pieChart, xVals, entrys);

        } else {
            tvTreinosEmpty.setVisibility(View.VISIBLE);
            pieChart.setVisibility(View.GONE);
        }
    }

    private List<Treino> getTreinosMes(List<Treino> treinos) {
        Date primeiroDia = DateUtils.getPrimeiroDiaMes(new Date());
        Date ultimoDia = DateUtils.getUltimoDiaMes(new Date());
        List<Treino> treinosMes = new ArrayList<>();
        for (Treino t : treinos) {
            if (t.getData().getTime() >= primeiroDia.getTime() && t.getData().getTime() <= ultimoDia.getTime()) {
                treinosMes.add(t);
            }
        }
        return treinosMes;
    }


    private void configGraphic(PieChart pieChart, ArrayList<String> xVals, ArrayList<Entry> entrys) {
        PieDataSet dataSet = new PieDataSet(entrys, "");
        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.DKGRAY);

        pieChart.setUsePercentValues(true);
        pieChart.setData(data);
        pieChart.setDescription("");
        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(16f);
        pieChart.getLegend().setEnabled(false);
        pieChart.setHoleRadius(45f);
        pieChart.highlightValues(null);
        pieChart.setTouchEnabled(false);

        dataSet.addColor(Color.rgb(207, 248, 246));
        dataSet.addColor(Color.rgb(148, 212, 212));
        dataSet.addColor(Color.rgb(136, 180, 187));
        dataSet.addColor(Color.rgb(118, 174, 175));
        dataSet.addColor(Color.rgb(42, 109, 130));
        dataSet.addColor(Color.rgb(217, 80, 138));
        dataSet.addColor(Color.rgb(254, 149, 7));
        dataSet.addColor(Color.rgb(155, 287, 140));
        dataSet.addColor(Color.rgb(106, 167, 134));
        dataSet.addColor(Color.rgb(53, 194, 209));
        pieChart.animateXY(1400, 1400);

    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().getSupportFragmentManager().popBackStack();
    }
}


