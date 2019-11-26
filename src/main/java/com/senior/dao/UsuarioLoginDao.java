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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.senior.model.UsuarioLogin;
import com.senior.utils.Utils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Eduardo
 */
public class UsuarioLoginDao implements Repository {

    public static UsuarioLogin usuarioLogin = new UsuarioLogin();
    public static Map<String, UsuarioLogin> listaUsuario = new HashMap<String, UsuarioLogin>();

    @Override
    public void inserir(Object o) {
        lista();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        UsuarioLogin user = (UsuarioLogin) o;
        user.setDs_senha(Utils.md5(user.getDs_senha()));
        listaUsuario.put(user.getDs_login(), user);
        DatabaseReference myRootRef = database.getReference();
        myRootRef.child("usuarios").setValueAsync(listaUsuario);
    }

    @Override
    public void alterar(Object o) {
        inserir(o);
    }

    public static UsuarioLogin getUsuarioLogin() {
        return usuarioLogin;
    }

    public static void setUsuarioLogin(UsuarioLogin usuarioLogin) {
        UsuarioLoginDao.usuarioLogin = usuarioLogin;
    }

    public static Map<String, UsuarioLogin> getListaUsuario() {
        return listaUsuario;
    }

    public static void setListaUsuario(Map<String, UsuarioLogin> listaUsuario) {
        UsuarioLoginDao.listaUsuario = listaUsuario;
    }

    @Override
    public void excluir(Object o) {
        UsuarioLogin user = (UsuarioLogin) o;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRootRef = database.getReference();
        listaUsuario.remove(user.getDs_login());
        myRootRef.child("usuarios").setValueAsync(listaUsuario);
       
    }

    @Override
    public void lista() {
        FirebaseDatabase database = FirebaseDatabase.getInstance(); //Conectar ao banco.
        DatabaseReference myRootRef = database.getReference(); //Pega a raiz do banco.
        DatabaseReference userRef = myRootRef.child("usuarios"); //Pega a raiz usuário
        final Map listaUsuarios = new HashMap(); // Cria uma lista temporária
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {//Método para pegar os dados do banco e transformar em uma classe de usuário.
                    Iterator<DataSnapshot> dataSnapshots = dataSnapshot.getChildren().iterator();
                    while (dataSnapshots.hasNext()) {
                        DataSnapshot dataSnapshotChild = dataSnapshots.next();
                        UsuarioLogin fcmUser = dataSnapshotChild.getValue(UsuarioLogin.class);
                        listaUsuarios.put(fcmUser.getDs_login(), fcmUser);
                    }
                    //coloca os valores atualizados na lista que é utilizado em consultas
                    listaUsuario = listaUsuarios;

                } catch (Exception e) {
                    //Log the exception and the key 
                    System.out.println(dataSnapshot.getKey());
                    e.printStackTrace();
                    //tratamento de expections
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(("err"));
            }
        });

    }

}
