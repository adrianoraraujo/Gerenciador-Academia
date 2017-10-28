package com.br.gerenciadordetreino.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.br.gerenciadordetreino.R;
import com.br.gerenciadordetreino.model.Treino;
import com.br.gerenciadordetreino.persistence.TreinoDAO;
import com.br.gerenciadordetreino.utils.DateUtils;
import com.br.gerenciadordetreino.view.SuperActivity;
import com.br.gerenciadordetreino.view.adapters.ExercicioAdapter;
import com.br.gerenciadordetreino.view.custom.SelecionadorData;
import com.br.gerenciadordetreino.view.ordenacao.TreinosSort;


import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
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
    @ViewById(R.id.id_selecionador_data)
    SelecionadorData selecionadorData;
    @ViewById(R.id.tv_vazio)
    TextView tvVazio;

    List<Treino> treinos =new ArrayList<>();
    List<Treino> treinosWeek =new ArrayList<>();

    ExercicioAdapter treinosAdapter;
    DateTime dataSelecionada;

    public static TreinosFragment newInstance() {
        Bundle args = new Bundle();
        TreinosFragment fragment = new TreinosFragment_();
        fragment.setArguments(args);
        return fragment;
    }

    @AfterViews
    void initView() {
        SuperActivity.setTextToolbar(getActivity(), "Registro de Treinos");
        initValues();
        if(dataSelecionada == null) {
            dataSelecionada = DateTime.now();
            initList();
        }
    }

    private void initList() {
        treinos = TreinoDAO.getTreinos(getActivity() );
        refreshList();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(recyclerView != null && treinosAdapter != null){
            refreshList();
        }
    }

    private void refreshList() {
        selectTreinosWeek();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        setEmptyText();
        selecionadorData.setDate(dataSelecionada.toDate());

        recyclerView.setLayoutManager(mLayoutManager);
        treinosAdapter = new ExercicioAdapter(treinosWeek, getActivity());
        recyclerView.setAdapter(treinosAdapter);
    }

    private void setEmptyText() {
        if(treinosWeek.isEmpty()){
            tvVazio.setVisibility(View.VISIBLE);
        }else{
            tvVazio.setVisibility(View.GONE);
        }
    }

//    private List<Treino> getTreinosWeek(Date date){
//        List<Treino> treinosWeek = new ArrayList<>();
//        int semanaAno = DateUtils.getWeekOfYear(date);
//        for(Treino t : treinos){
//            int semanaAnoTreino = DateUtils.getWeekOfYear(t.getData());
//            if(semanaAno == semanaAnoTreino){
//                treinosWeek.add(t);
//            }
//        }
//        return  treinosWeek;
//    }

    private void initValues() {
        weekCalendar.setStartDate(DateTime.now());
        selecionadorData.goToday();

        weekCalendar.setOnWeekChangeListener(new OnWeekChangeListener() {
            @Override
            public void onWeekChange(DateTime firstDayOfTheWeek, boolean forward) {
                dataSelecionada = firstDayOfTheWeek;
                refreshList();
            }
        });

        weekCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onDateClick(DateTime dateTime) {
                movePositionList(dateTime);
            }
        });
    }

    private void selectTreinosWeek() {
        treinos = TreinoDAO.getTreinos(getActivity());
        Date primeiroDia = DateUtils.getPrimeiroDiaSemana(dataSelecionada.toDate());
        Date ultimoDia = DateUtils.getUltimoDiaSemana(dataSelecionada.toDate());
        treinosWeek = new ArrayList<>();
        for(Treino t: treinos){
            if(t.getData().getTime() >= primeiroDia.getTime() && t.getData().getTime() <= ultimoDia.getTime()){
                treinosWeek.add(t);
            }
        }

        TreinosSort treinosSort = new TreinosSort();
        Collections.sort(treinosWeek, treinosSort);
    }

    void movePositionList(DateTime dateTime){
        int dayWeek = dateTime.getDayOfWeek();

        //levar a lista ate essa data
        for(Treino treinoAtual: treinos){
            //SEGUNDA É DOIS
            Date date = treinoAtual.getData();
            int dayWeekTreino = DateUtils.getDayWeek(date);
            if(dayWeek == dayWeekTreino){
                recyclerView.setVerticalScrollbarPosition(treinos.indexOf(treinoAtual));
            }
        }
    }

//    @Click(R.id.ic_direita)
//    void goNext(){
//        selecionadorData.goNext();
//
//        dataSelecionada =  selecionadorData.getDateSelected();
//        weekCalendar.setSelectedDate(new DateTime(dataSelecionada));
//        refreshList();
//    }
//
//    @Click(R.id.ic_esquerda)
//    void goLeft(){
//        selecionadorData.goBack();
//        dataSelecionada =  selecionadorData.getDateSelected();
//        weekCalendar.setSelectedDate(new DateTime(dataSelecionada));
//        refreshList();
//    }

}
