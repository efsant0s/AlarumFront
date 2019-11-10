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
import com.senior.model.Mensagem;
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
public class MensagemDao implements Repository {

    public static Mensagem mensagemLogin = new Mensagem();
    public static Map<String, Mensagem> listaMensagem = new HashMap<String, Mensagem>();

    @Override
    public void inserir(Object o) {
        lista();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Mensagem mensag = (Mensagem) o;
        listaMensagem.put(mensag.getIe_tipo(), mensag);
        DatabaseReference myRootRef = database.getReference();
        myRootRef.child("mensagens").setValueAsync(listaMensagem);
    }

    @Override
    public void alterar(Object o) {
        inserir(o);
    }

    public static Mensagem getMensagem() {
        return mensagemLogin;
    }

    public static void setMensagem(Mensagem mensagemLogin) {
        MensagemDao.mensagemLogin = mensagemLogin;
    }

    public static Map<String, Mensagem> getListaMensagem() {
        return listaMensagem;
    }

    public static void setListaMensagem(Map<String, Mensagem> listaMensagem) {
        MensagemDao.listaMensagem = listaMensagem;
    }

    @Override
    public void excluir(Object o) {
        Mensagem mensag = (Mensagem) o;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRootRef = database.getReference();
        listaMensagem.remove(mensag.getDs_mensagem());
        myRootRef.child("mensagens").setValueAsync(listaMensagem);
       
    }

    @Override
    public void lista() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRootRef = database.getReference();
        DatabaseReference mensagRef = myRootRef.child("mensagens");
        final Map listaMensagems = new HashMap();
        mensagRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    Iterator<DataSnapshot> dataSnapshots = dataSnapshot.getChildren().iterator();
                    while (dataSnapshots.hasNext()) {
                        DataSnapshot dataSnapshotChild = dataSnapshots.next();
                        Mensagem fcmmensag = dataSnapshotChild.getValue(Mensagem.class);
                        listaMensagems.put(fcmmensag.getIe_tipo(), fcmmensag);
                    }
                    listaMensagem = listaMensagems;

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

    

}
