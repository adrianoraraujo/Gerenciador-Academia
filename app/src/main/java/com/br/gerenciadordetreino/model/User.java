package com.br.gerenciadordetreino.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by joaov on 11/07/2017.
 */
@Entity(tableName = "User")
public class User {
    @PrimaryKey
    private String id;
    @ColumnInfo(name = "name")
    private String nome;
    @ColumnInfo(name = "dataNascimento")
    private String dataNascimento;
    @ColumnInfo(name = "sexo")
    private String sexo;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}