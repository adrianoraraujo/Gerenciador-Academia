package com.br.gerenciadordetreino.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.br.gerenciadordetreino.R;
import com.br.gerenciadordetreino.model.User;
import com.br.gerenciadordetreino.utils.PhotoUtils;
import com.br.gerenciadordetreino.view.fragment.CategoriaEquipamentoFragment;
import com.br.gerenciadordetreino.view.fragment.CategoriaEquipamentoFragment_;
import com.br.gerenciadordetreino.view.fragment.TreinosFragment;
import com.br.gerenciadordetreino.view.fragment.TreinosFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.io.FileNotFoundException;

@EActivity(R.layout.activity_home)
public class HomeActivity extends SuperActivity {


    @ViewById
    LinearLayout container;
    @Extra("Usuario")
    User user;
    private DrawerLayout drawer;

    //TODO ler esse tutorial http://www.android4devs.com/2014/12/how-to-make-material-design-navigation-drawer.html


    @AfterViews
    public void initiViews() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
        setTextToolbar("MEUS EXERCÍCIOS");
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, null,R.string.navigation_drawer_open,R.string.navigation_drawer_close) {
            //QUANDO ABRIR O MENU LATERAL
            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                container.setX(drawerView.getWidth() * slideOffset);
                clicksMenu(drawerView);
                setViews(drawerView);
            }
        };


        toggle.syncState();
        drawer.addDrawerListener(toggle);
        drawer.setScrimColor(Color.TRANSPARENT);

        init();
    }

    //SETANDO OS CAMOS ESCRITOS DO MENU LATERAL
    private void setViews(View v) {
        TextView tvNomeUsuario = (TextView) v.findViewById(R.id.tv_nome_usuario);
        ImageView imgUser = (ImageView) v.findViewById(R.id.img_usuario);

        tvNomeUsuario.setText(user.getNome());
        Bitmap bitmap = null;
        try {
            bitmap = PhotoUtils.getImage(HomeActivity.this,CadastroActivity.FOTO_USER);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (bitmap != null){
            imgUser.setImageBitmap(bitmap);
        }
    }


    private void init() {
        CategoriaEquipamentoFragment categoriaEquipamentoFragment = CategoriaEquipamentoFragment_.newInstance();
        startFragment(categoriaEquipamentoFragment);
    }

    //BOTÃO BACK DO DISPOSITIVO
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    private void clicksMenu(View v) {
        TextView tvMeusTreinos = (TextView) v.findViewById(R.id.tv_meus_treinos);
        TextView tvEquipamentos = (TextView) v.findViewById(R.id.tv_equipamentos);
        TextView tvPerfil = (TextView) v.findViewById(R.id.tv_perfil);


        //clicks
        tvEquipamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategoriaEquipamentoFragment categoriaEquipamentoFragment = CategoriaEquipamentoFragment_.newInstance();
                startFragment(categoriaEquipamentoFragment);
            }
        });
        tvMeusTreinos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TreinosFragment treinosFragment = TreinosFragment_.newInstance();
                startFragment(treinosFragment);
            }
        });


        tvPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CadastroActivity_.class);
                intent.putExtra("isEdicao", true);
                startActivity(intent);
            }
        });
    }

    private void startFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.body_home_empty, fragment);
        fragmentTransaction.commit();
        drawer.closeDrawer(Gravity.LEFT);
    }

    private void startFragmentContainer(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    @Click(R.id.btn_menu)
    void menuClick(){
        if (drawer.isDrawerOpen(Gravity.LEFT)) {
            drawer.closeDrawer(Gravity.LEFT);
        } else {
            drawer.openDrawer(Gravity.LEFT);
        }

    }

}
