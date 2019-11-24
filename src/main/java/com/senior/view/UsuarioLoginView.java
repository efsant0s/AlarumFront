/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senior.view;

import com.senior.dao.UsuarioLoginDao;
import com.senior.model.UsuarioLogin;
import com.senior.utils.Utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.ServletException;

/**
 *
 * @author Aluno
 */
@ManagedBean
@SessionScoped
public class UsuarioLoginView {

    private UsuarioLoginDao usuarioLoginDao = new UsuarioLoginDao(); 
    private UsuarioLogin usuario = new UsuarioLogin();

    public void excluiUsuario(UsuarioLogin usuario) {
        usuarioLoginDao.excluir(usuario);
    }

    public void salvaUsuario(UsuarioLogin usuario) {
        usuarioLoginDao.inserir(usuario); 
    }

    public UsuarioLoginView() {
        try { 
            usuarioLoginDao.lista();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioLoginView.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

  

    public Map<String, UsuarioLogin> getMapUsuarios() {
        return usuarioLoginDao.getListaUsuario();
    }

    public List<UsuarioLogin> getListaUsuario() {
        return new ArrayList<UsuarioLogin>(usuarioLoginDao.getListaUsuario().values());
    }

    public UsuarioLogin getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioLogin usuario) {
        this.usuario = usuario;
    }

    public void salvaUsuario() throws ServletException {
        usuarioLoginDao.inserir(usuario);
        usuario = new UsuarioLogin(); 
    }
}
