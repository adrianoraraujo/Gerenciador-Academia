package com.br.gerenciadordetreino.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.br.gerenciadordetreino.R;
import com.br.gerenciadordetreino.model.Treino;
import com.br.gerenciadordetreino.persistence.TreinoDAO;
import com.br.gerenciadordetreino.utils.DateUtils;
import com.br.gerenciadordetreino.view.adapters.ExercicioAdapter;
import com.br.gerenciadordetreino.view.ordenacao.TreinosSort;


import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import noman.weekcalendar.WeekCalendar;
import noman.weekcalendar.listener.OnDateClickListener;
import noman.weekcalendar.listener.OnWeekChangeListener;

@EFragment(R.layout.fragment_treinos)
public class TreinosFragment extends Fragment {
    @ViewById(R.id.recycler_treinos)
    RecyclerView recyclerView;
    @ViewById(R.id.weekCalendar)
    WeekCalendar weekCalendar;

    List<Treino> treinos =new ArrayList<>();
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
        refreshList(DateTime.now());


    }

    private void refreshList(DateTime dateTime) {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        treinosAdapter = new ExercicioAdapter(getTreinosWeek(getDateView(dateTime)), getActivity());
        recyclerView.setAdapter(treinosAdapter);
    }

    private List<Treino> getTreinosWeek(Date date){
        List<Treino> treinosWeek = new ArrayList<>();
        int semanaAno = DateUtils.getWeekOfYear(date);
        for(Treino t : treinos){
            int semanaAnoTreino = DateUtils.getWeekOfYear(t.getData());
            if(semanaAno == semanaAnoTreino){
                treinosWeek.add(t);
            }
        }
        return  treinosWeek;
    }

    private void initValues() {
        treinos = TreinoDAO.getTreinos(getActivity());
        TreinosSort treinosSort = new TreinosSort();
        Collections.sort(treinos, treinosSort);

        weekCalendar.setOnWeekChangeListener(new OnWeekChangeListener() {
            @Override
            public void onWeekChange(DateTime firstDayOfTheWeek, boolean forward) {
                refreshList(firstDayOfTheWeek);
            }
        });

        weekCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onDateClick(DateTime dateTime) {
                movePositionList(dateTime);
            }
        });
    }

    void movePositionList(DateTime dateTime){
        int dayWeek = dateTime.getDayOfWeek();

        //levar a lista ate essa data
        for(Treino treinoAtual: treinos){
            Date date = treinoAtual.getData();
            int dayWeekTreino = DateUtils.getDayWeek(date);
            if(dayWeek == dayWeekTreino){
                recyclerView.setVerticalScrollbarPosition(treinos.indexOf(treinoAtual));
            }
        }
    }

    private Date getDateView(DateTime dateTime) {
        return dateTime.toDate();
    }

}
