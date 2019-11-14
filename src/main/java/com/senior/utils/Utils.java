/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senior.utils;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.senior.model.Mensagem;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author Eduardo
 */
public class Utils {

    public static void configuraInstancia() throws FileNotFoundException, IOException {
        try {

            InputStream path = Utils.class
                    .getClassLoader().getResourceAsStream("acess/alarum_auth.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(path))
                    .setDatabaseUrl("https://alarum-e97a3.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }

    public static void enviaMensagem(String gerencia, Mensagem msg) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference msgRef = database.getReference().child("banco").child(gerencia).child("mensagem");
        msgRef.setValueAsync(msg);
    }

    private static String stringHexa(byte[] bytes) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
            int parteBaixa = bytes[i] & 0xf;
            if (parteAlta == 0) {
                s.append('0');
            }
            s.append(Integer.toHexString(parteAlta | parteBaixa));
        }
        return s.toString();
    }

    public static String md5(String mensagem) {
        if (mensagem == null) {
            mensagem = "123";
        };
        return stringHexa(gerarHash(mensagem, "MD5"));
    }

    private static byte[] gerarHash(String frase, String algoritmo) {
        try {
            MessageDigest md = MessageDigest.getInstance(algoritmo);
            md.update(frase.getBytes());
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
