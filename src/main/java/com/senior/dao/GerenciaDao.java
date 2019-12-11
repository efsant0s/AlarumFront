/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senior.dao;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.senior.model.Gerencia;
import com.senior.utils.Utils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Eduardo
 */
public class GerenciaDao implements Repository {

    public static Gerencia gerenciaLogin = new Gerencia();
    public static Map<String, Gerencia> listaGerencia = new HashMap<String, Gerencia>();

    @Override
    public void inserir(Object o) {
        lista();
        FirebaseDatabase database = Utils.getIstancia();
        Gerencia geren = (Gerencia) o;
        listaGerencia.put(geren.getDs_gerencia(), geren);
        DatabaseReference myRootRef = database.getReference();
        myRootRef.child("gerencias").setValueAsync(listaGerencia);
    }

    @Override
    public void alterar(Object o) {
        inserir(o);
    }

    public static Gerencia getGerencia() {
        return gerenciaLogin;
    }

    public static void setGerencia(Gerencia gerenciaLogin) {
        GerenciaDao.gerenciaLogin = gerenciaLogin;
    }

    public static Map<String, Gerencia> getListaUsuario() {
        return listaGerencia;
    }

    public static void setListaUsuario(Map<String, Gerencia> listaUsuario) {
        GerenciaDao.listaGerencia = listaUsuario;
    }

    @Override
    public void excluir(Object o) {
        Gerencia geren = (Gerencia) o;
        FirebaseDatabase database = Utils.getIstancia();
        DatabaseReference myRootRef = database.getReference();
        listaGerencia.remove(geren.getDs_gerencia());
        myRootRef.child("gerencias").setValueAsync(listaGerencia);
       
    }

    @Override
    public void lista() {
        FirebaseDatabase database = Utils.getIstancia();
        DatabaseReference myRootRef = database.getReference();
        DatabaseReference gerenRef = myRootRef.child("gerencias");
        final Map listaUsuarios = new HashMap();
        gerenRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    Iterator<DataSnapshot> dataSnapshots = dataSnapshot.getChildren().iterator();
                    while (dataSnapshots.hasNext()) {
                        DataSnapshot dataSnapshotChild = dataSnapshots.next();
                        Gerencia fcmgeren = dataSnapshotChild.getValue(Gerencia.class);
                        listaUsuarios.put(fcmgeren.getDs_gerencia(), fcmgeren);
                    }
                    listaGerencia = listaUsuarios;

                } catch (Exception e) {
                    //Log the exception and the key 
                    System.out.println(dataSnapshot.getKey());
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(("err"));
            }
        });

    }

    public Map<String, Gerencia> getListaGerencia() {
        return listaGerencia;
    }

}
