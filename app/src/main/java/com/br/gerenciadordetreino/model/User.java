package com.br.gerenciadordetreino.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;
import android.arch.persistence.room.TypeConverters;

import com.br.gerenciadordetreino.persistence.converters.FotoConverter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by joaov on 11/07/2017.
 */
@Entity(tableName = "User")
public class User implements Serializable {
    @PrimaryKey
    private String id;
    @ColumnInfo(name = "name")
    private String nome;
    @ColumnInfo(name = "dataNascimento")
    private String dataNascimento;
    @ColumnInfo(name = "sexo")
    private String sexo;
    @ColumnInfo(name = "pathFoto")
    private String pathFoto;
    @ColumnInfo(name = "peso")
    private double peso;
    @Ignore
    private List<Foto> fotosEvolucao;


    public List<Foto> getFotosEvolucao() {
        return fotosEvolucao;
    }

    public void setFotosEvolucao(List<Foto> fotosEvolucao) {
        this.fotosEvolucao = fotosEvolucao;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPathFoto() {
        return pathFoto;
    }

    public void setPathFoto(String pathFoto) {
        this.pathFoto = pathFoto;
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
