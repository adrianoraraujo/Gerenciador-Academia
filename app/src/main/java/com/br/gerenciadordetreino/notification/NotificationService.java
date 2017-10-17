package com.br.gerenciadordetreino.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.NotificationCompat;

import com.br.gerenciadordetreino.R;
import com.br.gerenciadordetreino.model.Equipamento;

/**
 * Created by joaov on 29/09/2017.
 */

public class NotificationService extends BroadcastReceiver {
    public static final String NOTIFICATION ="NOTIFICATION" ;
    public static final String EQUIPAMENTO ="EQUIPAMENTO" ;
    private Equipamento equipamento;
    private NotificationModel notificationModel;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getBundleExtra("bundle");
        if (bundle != null) {
            equipamento = (Equipamento) bundle.getSerializable(EQUIPAMENTO);
            notificationModel = (NotificationModel) bundle.getSerializable(NOTIFICATION);
        }
        android.support.v4.app.NotificationCompat.Builder builder = new android.support.v4.app.NotificationCompat.Builder(context);
        Notification notification =
                builder
                .setContentTitle(notificationModel.getTitle())
                .setVibrate(new long[] { 0,100 , 0,100})
                .setPriority(android.support.v4.app.NotificationCompat.PRIORITY_LOW)
                .setContentText(notificationModel.getText())
                .setSmallIcon(R.drawable.alarm).build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);

    }
}


