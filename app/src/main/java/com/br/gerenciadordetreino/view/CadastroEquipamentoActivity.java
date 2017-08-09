package com.br.gerenciadordetreino.view;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.AppCompatSpinner;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.br.gerenciadordetreino.R;
import com.br.gerenciadordetreino.model.Equipamento;
import com.br.gerenciadordetreino.persistence.EquipamentoDAO;
import com.br.gerenciadordetreino.persistence.interfaces.EquipamentoPersistence;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_cadastro_equipamento)
public class CadastroEquipamentoActivity extends SuperActivity {

    @ViewById(R.id.spinner_categorias)
    AppCompatSpinner spnCategorias;
    @ViewById(R.id.edt_name)
    EditText edtName;
    @ViewById(R.id.tv_series)
    TextView tvSeries;
    @ViewById(R.id.tv_peso)
    TextView tvPeso;
    @ViewById(R.id.tv_repeticoes)
    TextView tvRepeticoes;
    @ViewById(R.id.edt_observacao)
    EditText edtObservacao;
    private Equipamento equipamento;

    @AfterViews
    void init() {
        setValuesInSpinner();
    }

    @Click(R.id.toolbar_fechar)
    void fechar() {
        closePopup();
    }

    @Click(R.id.btn_cadastrar_equipamento)
    void cadasstrarEquipamento() {
        setValuesInUser();
        EquipamentoDAO.addEquipamento(CadastroEquipamentoActivity.this, equipamento);
        closePopup();
    }

    @Override
    public void onBackPressed() {
        closePopup();
    }

    @Click(R.id.body_treino_atual)
    void bodyTreinoAtual() {
        Intent intent = new Intent(CadastroEquipamentoActivity.this, CadastroTreinoActivity_.class);
        startActivity(intent);
    }

    private void setValuesInUser() {
        String nome = edtName.getText().toString();
        String categoria = spnCategorias.getSelectedItem().toString();
        String observacoes = edtObservacao.getText().toString();
        int peso = Integer.parseInt(tvPeso.getText().toString());
        int series = Integer.parseInt(tvSeries.getText().toString());
        int repeticao = Integer.parseInt(tvRepeticoes.getText().toString());

        equipamento = new Equipamento();
        equipamento.setNome(nome);
        equipamento.setCategoria(categoria);
        equipamento.setPesoDefault(peso);
        equipamento.setRepeticoesDefault(repeticao);
        equipamento.setSeriesDefault(series);
        equipamento.setObservacoes(observacoes);

    }

    private void setValuesInSpinner() {
        Resources res = getResources();
        String categorias[] = res.getStringArray(R.array.categorias);
        ArrayAdapter<String> adapter_category = new ArrayAdapter<>(this,R.layout.text_spinner, categorias);
        adapter_category.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCategorias.setAdapter(adapter_category);
    }
}
