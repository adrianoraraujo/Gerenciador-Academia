package com.br.gerenciadordetreino.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.br.gerenciadordetreino.R;

/**
 * Created by joaov on 13/07/2017.
 */

public class SuperActivity extends AppCompatActivity{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Log.i("________CICLO_DE_VIDA","onCreate________");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("________CICLO_DE_VIDA","onDestroy________");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("________CICLO_DE_VIDA","onResume________");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("________ CICLO_DE_VIDA","onPause _________");
    }

    void setTextToolbar(String text){
        TextView title = (TextView) findViewById(R.id.toolbar_title);
        title.setText(text);
    }

    public static void setTextToolbar(Activity activity, String text){
        TextView title = (TextView) activity.findViewById(R.id.toolbar_title);
        title.setText(text);
    }


    protected  void goPopup(Intent intent){
        startActivity(intent);
        overridePendingTransition(R.anim.popup_fragment_enter_anim, R.anim.popup_fragment_exit_anim);
    }

    protected void closePopup(){
        finish();
        overridePendingTransition(R.anim.popup_fragment_enter_anim, R.anim.popup_fragment_exit_anim);
    }

    public static void showMessageSnack(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(Color.WHITE);
        snackbar.show();
    }
}
