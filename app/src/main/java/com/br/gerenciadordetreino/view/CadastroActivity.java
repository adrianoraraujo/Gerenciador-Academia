package com.br.gerenciadordetreino.view;

import android.content.Intent;
import android.provider.Settings;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.br.gerenciadordetreino.R;
import com.br.gerenciadordetreino.firebase.UserFirebase;
import com.br.gerenciadordetreino.model.User;
import com.br.gerenciadordetreino.model.enums.UsuarioEnum;
import com.br.gerenciadordetreino.persistence.UserDAO;
import com.br.gerenciadordetreino.utils.Mask;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_cadastro)
public class CadastroActivity extends SuperActivity {
    @ViewById(R.id.edt_nome)
    EditText edtNome;
    @ViewById(R.id.edt_data_nascimento)
    EditText edtIdade;
    @ViewById(R.id.radio_sexo)
    RadioGroup radioSexo;
    private User user;

    @AfterViews
    void init() {
        addMasks();
        user = new User();
    }

    @Click(R.id.btn_cadastrar)
    void cadastrar() {
        setValuesInUsuario();
        if(validadeFields()) {
            saveUserInFirebase();
            saveUserInDatabase();
            startActivity(new Intent(this, HomeActivity_.class));
        }else{
            //TODO:"Preencha todos os campos" Dialog
        }

    }

    private void setValuesInUsuario() {
        int idEscolhaSexo = radioSexo.getCheckedRadioButtonId();
        user.setNome(edtNome.getText().toString());
        user.setDataNascimento(edtIdade.getText().toString());
        if(idEscolhaSexo == R.id.rb_macho){
            user.setSexo(UsuarioEnum.MACHO.name());
        }else if(idEscolhaSexo == R.id.rb_femea){
            user.setSexo(UsuarioEnum.FÊMEA.name());
        }
    }

    private void saveUserInFirebase(){
        String android_id = Settings.Secure.getString(getContentResolver(),Settings.Secure.ANDROID_ID);
       user.setId(android_id);
        UserFirebase userFirebase = new UserFirebase();
        userFirebase.save(user);
    }
    private void saveUserInDatabase(){
        user.setId("1");
        UserDAO.createUser(this, user);
    }

    private boolean validadeFields(){
        //TODO:validar campos vazios
        return  true;
    }

    private void addMasks(){
        edtIdade.addTextChangedListener(Mask.insert("##/##/####",edtIdade));
    }


}
