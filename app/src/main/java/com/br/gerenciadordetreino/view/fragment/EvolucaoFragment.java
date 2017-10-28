package com.br.gerenciadordetreino.view.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.br.gerenciadordetreino.R;
import com.br.gerenciadordetreino.model.Foto;
import com.br.gerenciadordetreino.persistence.FotoDAO;
import com.br.gerenciadordetreino.utils.DateUtils;
import com.br.gerenciadordetreino.utils.PhotoUtils;
import com.br.gerenciadordetreino.view.SuperActivity;
import com.br.gerenciadordetreino.view.adapters.EvolucaoAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.Date;
import java.util.List;

@EFragment(R.layout.fragment_evolucao)
public class EvolucaoFragment extends Fragment {
    @ViewById(R.id.recycler_evolucao)
    RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    EvolucaoAdapter evolucaoAdapter;
    List<Foto> fotos;

    public static EvolucaoFragment newInstance() {
        EvolucaoFragment fragment = new EvolucaoFragment_();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @AfterViews
    void init() {
        SuperActivity.setTextToolbar(getActivity(), "Estande de Evolução");
        fotos = FotoDAO.getFotos(getActivity());
        fotos.add(new Foto("", new Date()));
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        evolucaoAdapter = new EvolucaoAdapter(fotos, (AppCompatActivity) getActivity());
        recyclerView.setAdapter(evolucaoAdapter);

        animationScroolList();
    }

    private void animationScroolList() {
        final int speedScroll = 1000;
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            int count = 0;
            @Override
            public void run() {
                if(count < fotos.size() && recyclerView != null){
                    recyclerView.scrollToPosition(++count);
                    handler.postDelayed(this,speedScroll);
                }


            }
        };

        handler.postDelayed(runnable,speedScroll);
    }

}
