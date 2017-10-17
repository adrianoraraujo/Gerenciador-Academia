package com.br.gerenciadordetreino.view.listener;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by joaov on 11/10/2017.
 */

public interface OnCameraEvolucaoCamera extends Serializable {
    void onSuccess(Bitmap bitmap);
    void onError();
    void onCancel();
}
