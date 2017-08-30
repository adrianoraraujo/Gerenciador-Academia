package com.br.gerenciadordetreino.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.SupportActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.br.gerenciadordetreino.R;
import com.br.gerenciadordetreino.model.Equipamento;
import com.br.gerenciadordetreino.persistence.EquipamentoDAO;
import com.br.gerenciadordetreino.view.CadastroEquipamentoActivity_;
import com.br.gerenciadordetreino.view.adapters.CategoriaEquipamentosAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joaov on 15/07/2017.
 */

@EFragment(R.layout.fragment_home)
public class CategoriaEquipamentoFragment extends Fragment {
    @ViewById(R.id.recycler_categorias)
    RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    CategoriaEquipamentosAdapter categoriaEquipamentosAdapter;
    private List<Equipamento> equipamentos = new ArrayList();


    public static CategoriaEquipamentoFragment newInstance() {
        Bundle args = new Bundle();
        CategoriaEquipamentoFragment fragment = new CategoriaEquipamentoFragment_();
        fragment.setArguments(args);
        return fragment;
    }

    @AfterViews
    void init() {
        refreshCategorias();
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        categoriaEquipamentosAdapter = new CategoriaEquipamentosAdapter(equipamentos ,getActivity());
        recyclerView.setAdapter(categoriaEquipamentosAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(recyclerView != null && categoriaEquipamentosAdapter != null){
            refreshCategorias();
        }
    }

    private void refreshCategorias() {
        if(equipamentos != null && categoriaEquipamentosAdapter != null) {
            equipamentos.clear();
            equipamentos.addAll(EquipamentoDAO.getEquipamentos(getActivity()));
            categoriaEquipamentosAdapter.notifyDataSetChanged();
        }
    }

    @Click(R.id.float_button_add)
    void cadastrarEquipamento(){
        startActivity(new Intent(getContext(), CadastroEquipamentoActivity_.class));
        getActivity().overridePendingTransition(R.anim.popup_fragment_enter_anim, R.anim.popup_fragment_exit_anim);
    }

}
