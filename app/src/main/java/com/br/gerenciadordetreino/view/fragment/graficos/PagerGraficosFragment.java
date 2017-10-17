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
        initDate();
        setEventChangeListener();
        CostumAdapterPager  costumAdapterPager= new CostumAdapterPager(getActivity().getSupportFragmentManager());
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
        fragments.add(GraficosCategoriaFragment_.builder().build());
        fragments.add(GraficosEquipamentoFragment_.builder().build());
    }

    private class CostumAdapterPager extends FragmentPagerAdapter {
        public CostumAdapterPager(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
            initFragments();
        }

        @Override
        public Fragment getItem(int position) {
            return   fragments.get(position);
        }


        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "";
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("","d");
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().getFragmentManager().popBackStack();
    }
}