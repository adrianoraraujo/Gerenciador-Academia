package com.br.gerenciadordetreino.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.br.gerenciadordetreino.R;
import com.br.gerenciadordetreino.model.Equipamento;
import com.br.gerenciadordetreino.model.Treino;
import com.br.gerenciadordetreino.view.adapters.ExercicioAdapter;


import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import noman.weekcalendar.WeekCalendar;
import noman.weekcalendar.listener.OnDateClickListener;

@EFragment(R.layout.fragment_treinos)
public class TreinosFragment extends Fragment {
    @ViewById(R.id.recycler_treinos)
    RecyclerView recyclerView;
    @ViewById(R.id.weekCalendar)
    WeekCalendar weekCalendar;


    List<Treino> treinos;
    ExercicioAdapter treinosAdapter;

    public static TreinosFragment newInstance() {
        Bundle args = new Bundle();
        TreinosFragment fragment = new TreinosFragment_();
        fragment.setArguments(args);
        return fragment;
    }

    @AfterViews
    void initView() {
        initValues();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        treinosAdapter = new ExercicioAdapter(treinos, getActivity());
        recyclerView.setAdapter(treinosAdapter);

    }

    private void initValues() {
        Equipamento equipamento = new Equipamento();
        equipamento.setNome("Teste equipamento");
        treinos = new ArrayList<>();
        Treino t1 = new Treino();
        t1.setData(new Date());
        t1.setIdEquipamento(equipamento.getId());
        t1.setId(1);
        t1.setPeso(20);
        t1.setRepeticoes(3);
        t1.setSerie(30);
        treinos.add(t1);

        Treino t2 = new Treino();
        t2.setData(new Date());
        t2.setIdEquipamento(equipamento.getId());
        t2.setId(1);
        t2.setPeso(20);
        t2.setRepeticoes(3);
        t2.setSerie(30);
        treinos.add(t2);

        weekCalendar.setOnDateClickListener(new OnDateClickListener() {

            @Override
            public void onDateClick(DateTime dateTime) {
                //levar a lista ate essa data
            }
        });
    }
}
