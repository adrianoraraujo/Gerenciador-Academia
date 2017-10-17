package com.br.gerenciadordetreino.utils;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.widget.ArrayAdapter;

import com.br.gerenciadordetreino.R;

/**
 * Created by joaov on 03/10/2017.
 */

public class SpinnerUtils {

    public static void setValuesInSpinner(AppCompatSpinner spinner, Context context, String[] array) {
        ArrayAdapter<String> adapterCategory = new ArrayAdapter<>(context, R.layout.text_spinner, array);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterCategory);
    }
}
