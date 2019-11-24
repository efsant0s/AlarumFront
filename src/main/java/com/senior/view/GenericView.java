/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senior.view;

import com.senior.dao.EnvioTudoDao;
import com.senior.dao.GerenciaDao;
import com.senior.dao.MensagemDao;
import com.senior.dao.UsuarioLoginDao;
import com.senior.utils.Utils;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Eduardo
 */
@ManagedBean
@SessionScoped
public class GenericView {

    private GerenciaDao gerenciaDao = new GerenciaDao();
    private MensagemDao mensagemDao = new MensagemDao();
    private UsuarioLoginDao usuarioLoginDao = new UsuarioLoginDao();
    private EnvioTudoDao enviaDao = new EnvioTudoDao();
    public String getValor(){
        return "foi";
    }
    public GenericView()   {
        try {
            Utils.configuraInstancia();
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(GenericView.class.getName()).log(Level.SEVERE, null, ex);
        }
        gerenciaDao.lista();
        mensagemDao.lista();
        usuarioLoginDao.lista();
        enviaDao.lista();
    }

}
