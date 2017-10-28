package com.br.gerenciadordetreino.view.fragment.graficos;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.TextView;

import com.br.gerenciadordetreino.R;
import com.br.gerenciadordetreino.model.Equipamento;
import com.br.gerenciadordetreino.model.Treino;
import com.br.gerenciadordetreino.persistence.EquipamentoDAO;
import com.br.gerenciadordetreino.persistence.TreinoDAO;
import com.br.gerenciadordetreino.utils.DateUtils;
import com.br.gerenciadordetreino.utils.SpinnerUtils;
import com.br.gerenciadordetreino.view.SuperActivity;
import com.br.gerenciadordetreino.view.adapters.TreinoCategoriaAdapter;
import com.br.gerenciadordetreino.view.fragment.graficos.GraficosEquipamentoFragment_;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by joaov on 17/07/2017.
 */

@EFragment(R.layout.fragment_graficos_equipamento)
public class GraficosEquipamentoFragment extends Fragment {
    @ViewById(R.id.piechart_equipamentos)
    PieChart pieChartEquipamento;
    @ViewById(R.id.tv_equipamento_empty)
    TextView tvTreinoEmptyEquipamento;
    @ViewById(R.id.spinner_categorias)
    AppCompatSpinner spnCategorias;


    private String[] categorias;
    private List<Treino> treinosMes = new ArrayList<>();
    private LinearLayoutManager mLayoutManager;
    private TreinoCategoriaAdapter treinosCategoriaAdapter;

    public static GraficosEquipamentoFragment newInstance() {
        Bundle args = new Bundle();
        GraficosEquipamentoFragment fragment = new GraficosEquipamentoFragment_();
        //fragment.setArguments(args);
        return fragment;
    }

    @AfterViews
    void init() {
        Resources res = getContext().getResources();
        categorias = res.getStringArray(R.array.categorias);
        setValuesInSpinner();
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


    private void setValuesInSpinner() {
        spnCategorias.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(spnCategorias != null) {
                    ((TextView) spnCategorias.getSelectedView()).setTextColor(ContextCompat.getColor(getActivity(), R.color.colorAccent));
                    ((TextView) spnCategorias.getSelectedView()).setTextSize(24);
                    ((TextView) spnCategorias.getSelectedView()).setGravity(Gravity.CENTER);
                }
            }
        });

        SpinnerUtils.setValuesInSpinner(spnCategorias, getActivity(), categorias);

        spnCategorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String categoria = categorias[position];
                refreshGraphicEquipmentsForCategory(categoria);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void refreshGraphicEquipmentsForCategory(String categoria) {
        List<Treino> treinosEquipamento = getTreinosMes(TreinoDAO.getTreinosForCategory(getActivity(), categoria));
        List<Equipamento> equipamentosCategoria = EquipamentoDAO.getEquipamentsForCategory(getActivity(), categoria);

        if (treinosEquipamento == null || treinosEquipamento.size() == 0) {
            tvTreinoEmptyEquipamento.setVisibility(View.VISIBLE);
            pieChartEquipamento.setVisibility(View.GONE);
            return;
        }

        if (equipamentosCategoria == null || equipamentosCategoria.size() == 0) {
            tvTreinoEmptyEquipamento.setVisibility(View.VISIBLE);
            tvTreinoEmptyEquipamento.setText("Os equipamentos dos treinos deste mês\n foram excluídos");
            pieChartEquipamento.setVisibility(View.GONE);
            return;
        }

        tvTreinoEmptyEquipamento.setVisibility(View.GONE);
        pieChartEquipamento.setVisibility(View.VISIBLE);

        ArrayList<Entry> entrys = new ArrayList<>();
        ArrayList<String> xVals = new ArrayList<>();
        for (Equipamento equipamentoAtual : equipamentosCategoria) {
            xVals.add(equipamentoAtual.getNome());
            int somaValor = 0;
            int position = 0;
            for (Treino treinoAtual : treinosEquipamento) {
                if (treinoAtual.getIdEquipamento() == equipamentoAtual.getId()) {
                    somaValor++;
                }
            }
            if (somaValor != 0) {
                entrys.add(new Entry(somaValor, position, equipamentoAtual));
            }
            position++;
        }



        configGraphic(pieChartEquipamento, xVals, entrys);

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
}


