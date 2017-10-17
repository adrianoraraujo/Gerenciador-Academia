package com.br.gerenciadordetreino.utils;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.br.gerenciadordetreino.model.Equipamento;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Created by joaov on 16/08/2017.
 */

public class PhotoUtils {

    public static Bitmap getImage(Context context, String path) throws FileNotFoundException {
        File filePath = context.getFileStreamPath(path);
        FileInputStream fi = new FileInputStream(filePath);
        Bitmap bitmap = BitmapFactory.decodeStream(fi);
        return bitmap;
    }

    public static  boolean  savePhotoInFile(Context context, Bitmap bitmap, String namePath) throws IOException {
        try {
            FileOutputStream fos = context.openFileOutput(namePath, Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
            return true;
        } catch (Exception e) {
            Log.e("saveToInternalStorage()", "Falha ao salvar foto");
            return  false;
        }
    }

    public static void deleteFoto(Context context, String path){
        File filePath = context.getFileStreamPath(path);
        filePath.delete();
    }
}