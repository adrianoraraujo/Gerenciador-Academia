package com.br.gerenciadordetreino.persistence.converters;

import android.arch.persistence.room.TypeConverter;

import com.br.gerenciadordetreino.model.Foto;
import com.br.gerenciadordetreino.model.User;
import com.google.gson.Gson;

import java.util.Date;
import java.util.List;

public class FotoConverter {

    @TypeConverter
    public static List<Foto> fromList(String user) {
        Gson gson = new Gson();
        return (List<Foto>) gson.fromJson(user, Foto.class);
    }

    @TypeConverter
    public static String toList(List<User> users) {
        Gson gson = new Gson();
        return  gson.toJson(users);
    }
}