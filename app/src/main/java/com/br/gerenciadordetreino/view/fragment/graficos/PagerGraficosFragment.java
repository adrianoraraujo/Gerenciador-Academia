package com.br.gerenciadordetreino.view.fragment.graficos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.TextView;

import com.br.gerenciadordetreino.R;
import com.br.gerenciadordetreino.utils.DateUtils;
import com.br.gerenciadordetreino.view.SuperActivity;
import com.github.vivchar.viewpagerindicator.ViewPagerIndicator;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EFragment(R.layout.fragment_container_graphic)
public class PagerGraficosFragment extends Fragment {

    @ViewById(R.id.vpPager)
    ViewPager vpPager;
    @ViewById(R.id.tv_data)
    TextView tvData;
    @ViewById(R.id.view_pager_indicator)
    ViewPagerIndicator viewPagerIndicator;

    private List<Fragment> fragments;
    private ViewPager.OnPageChangeListener onPageChangeListener;

    public static PagerGraficosFragment newInstance() {
        Bundle args = new Bundle();
        PagerGraficosFragment fragment = new PagerGraficosFragment_();
        fragment.setArguments(args);
        return fragment;
    }

    @AfterViews
    void init() {
        SuperActivity.setTextToolbar(getActivity(), "Gráficos");
        initDate();
        setEventChangeListener();
        initFragments();

        CostumAdapterPager  costumAdapterPager= new CostumAdapterPager(getActivity().getSupportFragmentManager(), fragments);
        vpPager.setAdapter(costumAdapterPager);
        initPagerIndicator();
    }

    private void setEventChangeListener() {
        onPageChangeListener = new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

            }
        };
        vpPager.addOnPageChangeListener(onPageChangeListener);

    }

    private void initPagerIndicator() {
        viewPagerIndicator.setupWithViewPager(vpPager);
        viewPagerIndicator.addOnPageChangeListener(onPageChangeListener);
    }

    private void initDate() {
        tvData.setText(DateUtils.toString("MM/yyyy", new Date()));
    }

    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(GraficosCategoriaFragment_.newInstance());
        fragments.add(GraficosEquipamentoFragment_.newInstance());
    }

}