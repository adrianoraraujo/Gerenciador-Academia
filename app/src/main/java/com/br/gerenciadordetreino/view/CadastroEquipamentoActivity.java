package com.br.gerenciadordetreino.view;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.br.gerenciadordetreino.R;
import com.br.gerenciadordetreino.model.Equipamento;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_cadastro_equipamento)
public class CadastroEquipamentoActivity extends AppCompatActivity {

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

    @AfterViews
    void init() {

        setSpinner();
    }

    private void setValuesInUser(){
        String nome = edtName.getText().toString();
        String categoria = spnCategorias.getSelectedItem().toString();
        String observacoes = edtObservacao.getText().toString();
        int peso = Integer.parseInt(tvPeso.getText().toString());
        int series = Integer.parseInt(tvSeries.getText().toString());
        int repeticao = Integer.parseInt(tvRepeticoes.getText().toString());

        Equipamento equipamento = new Equipamento();
        equipamento.setNome(nome);
        equipamento.setCategoria(categoria);
        equipamento.setPesoDefault(peso);
        equipamento.setRepeticoesDefault(repeticao);
        equipamento.setSeriesDefault(series);
        equipamento.setObservacoes(observacoes);

    }
    private void setSpinner() {
        Resources res = getResources();
        String categorias[] = res.getStringArray(R.array.categorias);
        ArrayAdapter<String> adapter_category = new ArrayAdapter<String>(this,
                R.layout.text_spinner, categorias);
        adapter_category.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCategorias.setAdapter(adapter_category);
    }

    @Click(R.id.toolbar_fechar)
    void fechar() {
        finish();
        overridePendingTransition(R.anim.popup_fragment_enter_anim, R.anim.popup_fragment_exit_anim);
    }


    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.popup_fragment_enter_anim, R.anim.popup_fragment_exit_anim);
    }

    @Click(R.id.body_treino_atual)
    void bodyTreinoAtual(){
        Intent intent = new Intent(CadastroEquipamentoActivity.this, CadastroTreinoActivity_.class);
        startActivity(intent);

    }
}
