package com.br.gerenciadordetreino.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
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

@EFragment(R.layout.fragment_afiliados)
public class AfiliadosFragment extends Fragment {

    public static AfiliadosFragment newInstance() {
        Bundle args = new Bundle();
        AfiliadosFragment fragment = new AfiliadosFragment_();
        fragment.setArguments(args);
        return fragment;
    }

    @AfterViews
    void initView() {

    }
}
