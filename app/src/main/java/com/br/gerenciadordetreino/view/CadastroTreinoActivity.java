package com.br.gerenciadordetreino.view;

import android.content.Intent;
import android.widget.TextView;

import com.br.gerenciadordetreino.R;
import com.br.gerenciadordetreino.model.Equipamento;
import com.br.gerenciadordetreino.model.Treino;
import com.br.gerenciadordetreino.persistence.EquipamentoDAO;
import com.br.gerenciadordetreino.persistence.TreinoDAO;
import com.shawnlin.numberpicker.NumberPicker;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.Date;

@EActivity(R.layout.popup_fragment_treino)
public class CadastroTreinoActivity extends SuperActivity {
    public static final int CODE_PARAMETROS_EQUIPAMENTO = 10;
    public static final String CADASTRO_OU_EDITAR = "edicao";
    public static final String MALHAR = "malhar";
    public static final String SERIE = "serie";
    public static final String REPETICAO = "repeticao";
    public static final String PESO = "peso";
    public static final String TITULO = "titulo";

    public static final String EQUIPAMENTO_ITEM = "equipamento";
    public static final String TREINO_ITEM = "treino";

    public static final String CODE_ACAO = "acao";
    public static final String EDITAR_TREINO_PASSADO = "editarTreinoPassado";

    @ViewById(R.id.series_picker)
    NumberPicker npSeries;
    @ViewById(R.id.peso_picker)
    NumberPicker npPeso;
    @ViewById(R.id.repeticoes_picker)
    NumberPicker npRepeticoes;
    @ViewById(R.id.tv_title)
    TextView tvTitle;

    @Extra(TREINO_ITEM)
    Treino treino;
    @Extra(EQUIPAMENTO_ITEM)
    Equipamento equipamento;
    @Extra(TITULO)
    String titulo;
    @Extra(CODE_ACAO)
    String codeAction;

    @AfterViews
    void init() {
        setConfigNumbersPicker();
        setValuesInViews();
    }

    @Click(R.id.tv_salvar)
    void salvar() {
        switch (codeAction) {
            case MALHAR:
                setValuesInTreino();
                saveDateEquipament();
                saveTreino();
                finish();
                break;

            case CADASTRO_OU_EDITAR:
                saveNumbers();
                break;

            case EDITAR_TREINO_PASSADO:
                setValuesInTreino();
                updateTreino();
                break;
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

    private void setValuesInTreino() {
        if(treino == null){
            treino = new Treino();
        }
        int serie = npSeries.getValue();
        int repeticoes = npRepeticoes.getValue();
        int peso = npPeso.getValue();

        treino.setSerie(serie);
        treino.setRepeticoes(repeticoes);
        treino.setPeso(peso);
    }

    private void updateTreino() {
        TreinoDAO.updateTreino(CadastroTreinoActivity.this, treino);
        closePopup();
    }

    private void saveDateEquipament() {
        treino.setIdEquipamento(equipamento.getId());
        treino.setData(new Date());
        treino.setCategoria(equipamento.getCategoria());
        treino.setNome(equipamento.getNome());
        equipamento.setUltimaDataMalhada(new Date());
        EquipamentoDAO.addOrUpdate(this, equipamento);
    }

    private void saveTreino() {
        TreinoDAO.add(this, treino);
    }

    private void saveNumbers() {
        int serie = npSeries.getValue();
        int repeticoes = npRepeticoes.getValue();
        int peso = npPeso.getValue();

        Intent it = new Intent();
        it.putExtra(SERIE, serie);
        it.putExtra(REPETICAO, repeticoes);
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
        if (titulo != null) {
            tvTitle.setText(titulo);
        }
        if (equipamento != null) {
            npPeso.setValue(equipamento.getPesoDefault());
            npSeries.setValue(equipamento.getSeriesDefault());
            npRepeticoes.setValue(equipamento.getRepeticoesDefault());
        } else {
            npPeso.setValue(treino.getPeso());
            npSeries.setValue(treino.getSerie());
            npRepeticoes.setValue(treino.getRepeticoes());
        }
    }

}
