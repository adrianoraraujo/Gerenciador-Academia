package com.br.gerenciadordetreino.view;

import android.content.Intent;
import android.widget.TextView;

import com.br.gerenciadordetreino.R;
import com.br.gerenciadordetreino.model.Equipamento;
import com.br.gerenciadordetreino.model.Treino;
import com.br.gerenciadordetreino.persistence.TreinoDAO;
import com.br.gerenciadordetreino.utils.DateUtils;
import com.shawnlin.numberpicker.NumberPicker;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.popup_fragment_treino)
public class CadastroTreinoActivity extends SuperActivity {
    public static final int CODE_PARAMETROS_EQUIPAMENTO = 10;
    public static final int CODE_EDICAO = 20;
    public static final String SERIE = "serie";
    public static final String REPETICAO = "repeticao";
    public static final String PESO = "peso";
    public static final String TITULO = "titulo";
    public static final String CATEGORIA_ITEM = "equipamento";
    public static final String IS_MALHACAO = "malhacao";


    @ViewById(R.id.series_picker)
    NumberPicker npSeries;
    @ViewById(R.id.peso_picker)
    NumberPicker npPeso;
    @ViewById(R.id.repeticoes_picker)
    NumberPicker npRepeticoes;
    @ViewById(R.id.tv_title)
    TextView tvTitle;

    @Extra(CATEGORIA_ITEM)
    Equipamento equipamento;
    @Extra(TITULO)
    String titulo;
    @Extra(IS_MALHACAO)
    boolean isMalhacao;


    Treino treino;

    @AfterViews
    void init() {
        setConfigNumbersPicker();
        setValuesInViews();
    }

    @Click(R.id.tv_salvar)
    void salvar(){
        if(isMalhacao){
            saveTreino();
            finish();
        }else {
            saveNumbers();
        }
    }

    @Click(R.id.tv_fechar)
    void fechar() {
        closePopup();
    }


    @Override
    public void onBackPressed() {
        closePopup();
    }

    private void saveTreino(){
        int serie = npSeries.getValue();
        int repeticoes = npRepeticoes.getValue();
        int peso = npPeso.getValue();

        treino = new Treino();
        treino.setSerie(serie);
        treino.setRepeticoes(repeticoes);
        treino.setPeso(peso);
        treino.setIdEquipamento(equipamento.getId());
        treino.setData(DateUtils.today());
        TreinoDAO.add(this, treino);
    }

    private void saveNumbers(){
        int serie = npSeries.getValue();
        int repeticoes = npRepeticoes.getValue();
        int peso = npPeso.getValue();

        Intent it = new Intent();
        it.putExtra(SERIE, serie);
        it.putExtra(REPETICAO,repeticoes );
        it.putExtra(PESO, peso);
        setResult(0, it);
        super.onBackPressed();
    }

    private void setConfigNumbersPicker() {
        npPeso.setMinValue(0);
        npPeso.setMaxValue(800);
        npPeso.setWrapSelectorWheel(true);

        npSeries.setMinValue(0);
        npSeries.setMaxValue(10);
        npSeries.setWrapSelectorWheel(true);

        npRepeticoes.setMinValue(0);
        npRepeticoes.setMaxValue(100);
        npRepeticoes.setWrapSelectorWheel(true);
    }

    private void setValuesInViews() {
        tvTitle.setText(titulo);
        npPeso.setValue(equipamento.getPesoDefault());
        npSeries.setValue(equipamento.getSeriesDefault());
        npRepeticoes.setValue(equipamento.getRepeticoesDefault());
    }

}
