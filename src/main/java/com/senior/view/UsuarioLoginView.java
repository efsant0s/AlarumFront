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
    private Map<String, UsuarioLogin> listaUsuarios = usuarioLoginDao.getListaUsuario();
    private UsuarioLogin usuario = new UsuarioLogin();

    public void excluiUsuario(UsuarioLogin usuario) {
        usuarioLoginDao.excluir(usuario);
        atualizaLista();
    }

    public void salvaUsuario(UsuarioLogin usuario) {
        usuarioLoginDao.inserir(usuario);
        atualizaLista();
    }

    public UsuarioLoginView() {
        try {
            atualizaLista();
            usuarioLoginDao.lista();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioLoginView.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    private void atualizaLista() {
        listaUsuarios = usuarioLoginDao.getListaUsuario();
    }

    public Map<String, UsuarioLogin> getMapUsuarios() {
        return listaUsuarios;
    }

    public List<UsuarioLogin> getListaUsuario() {
        return new ArrayList<UsuarioLogin>(listaUsuarios.values());
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
        atualizaLista();
    }
}
