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
import com.senior.model.Mensagem;
import com.senior.model.Usuario;
import com.senior.utils.Utils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Eduardo
 */
public class EnvioTudoDao {

    private static Map<String, Map<String, Usuario>> listaGrupos = new HashMap();

    private static Map<String, Map<String, Mensagem>> listaMensagem = new HashMap<>();

    public static Map<String, Map<String, Usuario>> getListaGrupos() {
        return listaGrupos;
    }

    public static void enviaMensagemParaTodos(List<String> gerencias, Mensagem msg) {
        for (String gerencia : gerencias) {
            Utils.enviaMensagem(gerencia, msg);
        }
    }

    public void lista() {
        FirebaseDatabase database = Utils.getIstancia();
        DatabaseReference myRootRef = database.getReference();
        DatabaseReference gerenRef = myRootRef.child("banco");
        final Map<String, Map<String, Usuario>> mapUsuario = new HashMap();
        gerenRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    mapUsuario.clear();
                    Iterator<DataSnapshot> gerenSnaps = dataSnapshot.getChildren().iterator();
                    while (gerenSnaps.hasNext()) {
                        DataSnapshot gerenSnap = gerenSnaps.next();
                        Iterator<DataSnapshot> gruposSnaps = gerenSnap.getChildren().iterator();
                        while (gruposSnaps.hasNext()) {
                            DataSnapshot grupoSnap = gruposSnaps.next();
                            if ("usuarios".equals(grupoSnap.getKey())) {
                                Iterator<DataSnapshot> usuariosSnaps = grupoSnap.getChildren().iterator();
                                Map<String, Usuario> listaUsuario = new HashMap<>();
                                while (usuariosSnaps.hasNext()) {
                                    DataSnapshot userSnaps = usuariosSnaps.next();
                                    Usuario fcmUser = userSnaps.getValue(Usuario.class);
                                    listaUsuario.put(fcmUser.getNm_apelido(), fcmUser);
                                }
                                mapUsuario.put(gerenSnap.getKey(), listaUsuario);
                            }
                            if ("mensagem".equals(grupoSnap.getKey())) {
                                Iterator<DataSnapshot> mensagemSnap = grupoSnap.getChildren().iterator();
                                Map<String, Mensagem> listaUsuario = new HashMap<>();
                                while (mensagemSnap.hasNext()) {
                                    DataSnapshot userSnaps = mensagemSnap.next(); 
                                 
                                }
                                 
                            }
                        }

                    }
                    listaGrupos = mapUsuario;

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

    public void enviaMensagemParaGrupo(String gerenciaSelecionada, Mensagem msg) {
        Utils.enviaMensagem(gerenciaSelecionada, msg);
    }
}
