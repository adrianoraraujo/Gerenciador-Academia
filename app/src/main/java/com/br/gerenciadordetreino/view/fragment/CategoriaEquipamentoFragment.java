package com.br.gerenciadordetreino.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.br.gerenciadordetreino.R;
import com.br.gerenciadordetreino.model.Equipamento;
import com.br.gerenciadordetreino.view.adapters.CategoriaEquipamentosAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by joaov on 15/07/2017.
 */

@EFragment(R.layout.fragment_home)
public class CategoriaEquipamentoFragment extends Fragment {
    @ViewById(R.id.recycler_categorias)
    RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    CategoriaEquipamentosAdapter categoriaEquipamentosAdapter;
    private ArrayList<Equipamento> equipamentos = new ArrayList();


    public static CategoriaEquipamentoFragment newInstance() {
        Bundle args = new Bundle();
        CategoriaEquipamentoFragment fragment = new CategoriaEquipamentoFragment_();
        fragment.setArguments(args);
        return fragment;
    }

    @AfterViews
    void init() {
        valuesCategorias();
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        categoriaEquipamentosAdapter = new CategoriaEquipamentosAdapter(equipamentos ,getActivity());
        recyclerView.setAdapter(categoriaEquipamentosAdapter);

    }

    private void valuesCategorias() {
        Equipamento e1 = new Equipamento();
        e1.setNome("Supino com a mao");
        e1.setCategoria("Ombro");
        Equipamento e2 = new Equipamento();
        e2.setNome("Ombro");
        e2.setCategoria("Ombro");
        Equipamento e3 = new Equipamento();
        e3.setNome("fjdawsfjçads");
        e3.setCategoria("Costas");
        Equipamento e4 = new Equipamento();
        e4.setNome("Supino com o pé");
        e4.setCategoria("Ombro");
        equipamentos.add(e1);
        equipamentos.add(e2);
        equipamentos.add(e3);
        equipamentos.add(e4);


    }

    @Click(R.id.float_button_add)
    void cadastrarEquipamento(){
        startActivity(new Intent(getContext(), CadastroEquipamentoActivity_.class));
        getActivity().overridePendingTransition(R.anim.popup_fragment_enter_anim, R.anim.popup_fragment_exit_anim);
    }
}
