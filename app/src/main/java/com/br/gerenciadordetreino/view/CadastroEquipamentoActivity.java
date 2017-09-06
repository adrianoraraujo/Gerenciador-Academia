package com.br.gerenciadordetreino.view;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.widget.AppCompatSpinner;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.br.gerenciadordetreino.R;
import com.br.gerenciadordetreino.model.Equipamento;
import com.br.gerenciadordetreino.persistence.EquipamentoDAO;
import com.br.gerenciadordetreino.utils.DateUtils;
import com.br.gerenciadordetreino.utils.PhotoUtils;
import com.github.siyamed.shapeimageview.CircularImageView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

@EActivity(R.layout.activity_cadastro_equipamento)
public class CadastroEquipamentoActivity extends SuperActivity {

    public static final String EQUIPAMENTO = "Equipamento";
    public static final int CODE_CAMERA = 30;

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
    @ViewById(R.id.toolbar_title)
    TextView tvTitle;
    @ViewById(R.id.btn_cadastrar_equipamento)
    Button btnCadastrar;
    @ViewById(R.id.ll_editar_foto)
    LinearLayout llEditarFoto;
    @ViewById(R.id.img_circular_equipamento)
    CircularImageView imgEquipamento;

    @Extra(EQUIPAMENTO)
    Equipamento equipamento;

    private Bitmap image;

    @AfterViews
    void init() {
        if (equipamento == null) {
            equipamento = new Equipamento();
        }
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build()); //ignora a exposição URI
        setValuesInSpinner();
        if (equipamento == null) {
            //Cadastro
            tvTitle.setText("Novo Equipamento");
            btnCadastrar.setText("Salvar Equipamento");
        } else {
            //Editar
            setEquipamentoInFields();
            btnCadastrar.setText("Salvar Alterações");
        }
    }

    @Click(R.id.toolbar_fechar)
    void fechar() {
        closePopup();
    }

    @Click(R.id.btn_cadastrar_equipamento)
    void cadasstrarEquipamento() {
        setValuesInEquipamento();
        EquipamentoDAO.addOrUpdate(CadastroEquipamentoActivity.this, equipamento);
        closePopup();
    }

    @Override
    public void onBackPressed() {
        closePopup();
    }

    @Click(R.id.body_treino_atual)
    void bodyTreinoAtual() {
        if (equipamento.getFoto() != null) {
            setValuesInEquipamento();
        }
        Intent intent = new Intent(CadastroEquipamentoActivity.this, CadastroTreinoActivity_.class);
        intent.putExtra(CadastroTreinoActivity.TITULO, "Valores Padrões");
        intent.putExtra(CadastroTreinoActivity.CODE_ACAO, CadastroTreinoActivity.CADASTRO_OU_EDITAR);
        intent.putExtra(CadastroTreinoActivity.EQUIPAMENTO_ITEM, equipamento);
        startActivityForResult(intent, CadastroTreinoActivity.CODE_PARAMETROS_EQUIPAMENTO);
    }

    @Click(R.id.ll_editar_foto)
    void editarFoto() {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, CODE_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CadastroTreinoActivity.CODE_PARAMETROS_EQUIPAMENTO:
                if (data != null) {
                    int peso = data.getExtras().getInt(CadastroTreinoActivity.PESO);
                    int repeticoes = data.getExtras().getInt(CadastroTreinoActivity.REPETICAO);
                    int series = data.getExtras().getInt(CadastroTreinoActivity.SERIE);

                    tvPeso.setText(String.valueOf(peso));
                    tvRepeticoes.setText(String.valueOf(repeticoes));
                    tvSeries.setText(String.valueOf(series));
                }
                break;

            case CODE_CAMERA:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        image = (Bitmap) data.getExtras().get("data");
                        String nomePath = "";
                        if (equipamento != null && equipamento.getFoto() != null && !equipamento.getFoto().isEmpty()) {
                            nomePath = equipamento.getFoto();
                        } else {
                            nomePath = getNomePath();
                        }
                        equipamento.setFoto(nomePath);
                        imgEquipamento.setImageBitmap(image);


                    } else {
                        //errro
                    }
                } else {
                    //erro
                }
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private String getNomePath() {
        Date date = DateUtils.today();
        String dataFormatada = DateUtils.toString("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", date);
        return  dataFormatada;
    }


    private void setEquipamentoInFields() {
        edtName.setText(equipamento.getNome());
        edtObservacao.setText(equipamento.getObservacoes());
        tvTitle.setText(equipamento.getNome());
        tvPeso.setText(String.valueOf(equipamento.getPesoDefault()));
        tvSeries.setText(String.valueOf(equipamento.getSeriesDefault()));
        tvRepeticoes.setText(String.valueOf(equipamento.getRepeticoesDefault()));
        setPhotoInView();
        setPositionSpinner(equipamento.getCategoria());
    }

    private void setPhotoInView() {
        Bitmap bitmap = null;
        if (equipamento.getFoto() != null) {
            try {
                bitmap = PhotoUtils.getImage(this, equipamento.getFoto());
                if (bitmap != null) {
                    imgEquipamento.setImageBitmap(bitmap);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    private void setValuesInEquipamento() {
        String nome = edtName.getText().toString();
        String categoria = spnCategorias.getSelectedItem().toString();
        String observacoes = edtObservacao.getText().toString();
        int peso = Integer.parseInt(tvPeso.getText().toString());
        int series = Integer.parseInt(tvSeries.getText().toString());
        int repeticao = Integer.parseInt(tvRepeticoes.getText().toString());

        equipamento.setNome(nome);
        equipamento.setCategoria(categoria);
        equipamento.setPesoDefault(peso);
        equipamento.setRepeticoesDefault(repeticao);
        equipamento.setSeriesDefault(series);
        equipamento.setObservacoes(observacoes);
        equipamento.setUltimaDataMalhada(new Date());

        savePhotoInMemory();
    }

    private void savePhotoInMemory() {
        if (image != null) {
            try {
                PhotoUtils.savePhotoInFile(this, image, equipamento.getFoto());
            } catch (IOException e) {
                Toast.makeText(this, "Devido a um erro, não foi possível salvar a foto", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setValuesInSpinner() {
        Resources res = getResources();
        String categorias[] = res.getStringArray(R.array.categorias);
        ArrayAdapter<String> adapterCategory = new ArrayAdapter<>(this, R.layout.text_spinner, categorias);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCategorias.setAdapter(adapterCategory);
    }

    private void setPositionSpinner(String categoria) {
        Resources res = getResources();
        String categorias[] = res.getStringArray(R.array.categorias);
        int position = Arrays.asList(categorias).indexOf(categoria);
        spnCategorias.setSelection(position);
    }


}
