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
import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;

/**
 *
 * @author Aluno
 */
@ManagedBean
@SessionScoped
public class UsuarioLoginView {

    private UsuarioLoginDao usuarioLoginDao = new UsuarioLoginDao();
    public static boolean isLoggedIn = false;

    private UsuarioLogin usuarioQueLoga = new UsuarioLogin();
    private UsuarioLogin usuario = new UsuarioLogin();

    public UsuarioLoginDao getUsuarioLoginDao() {
        return usuarioLoginDao;
    }

    public void setUsuarioLoginDao(UsuarioLoginDao usuarioLoginDao) {
        this.usuarioLoginDao = usuarioLoginDao;
    }

    public static boolean isIsLoggedIn() {
        return isLoggedIn;
    }

    public static void setIsLoggedIn(boolean isLoggedIn) {
        UsuarioLoginView.isLoggedIn = isLoggedIn;
    }

    public UsuarioLogin getUsuarioQueLoga() {
        return usuarioQueLoga;
    }

    public void setUsuarioQueLoga(UsuarioLogin usuarioQueLoga) {
        this.usuarioQueLoga = usuarioQueLoga;
    }

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

    public String login() throws InterruptedException, IOException {
        if (this.login(usuarioQueLoga.getDs_login(), usuarioQueLoga.getDs_senha())) {
            return "EnvioMensagemGrupo";
        } else {
            return "falho";
        }
    }

    public void refresh() {
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
        context.setViewRoot(viewRoot);
        context.renderResponse();
    }

    private boolean login(final String login, final String senha) throws InterruptedException, IOException {
        if (login == null || senha == null) {
            return false;
        }
        Map<String, UsuarioLogin> listaUsuario = getMapUsuarios();
        if (listaUsuario.containsKey(login)) {
            for (Map.Entry<String, UsuarioLogin> entrySet : listaUsuario.entrySet()) {
                if (entrySet.getKey().equals(login) && entrySet.getValue() != null && (entrySet.getValue().getDs_senha().equals(senha)
                        || entrySet.getValue().getDs_senha().equals(Utils.md5(senha))) || entrySet.getValue().getDs_senha().equals(Utils.md5(Utils.md5(senha)))) {
                    return true;
                };
            }
        }
        return false;
    }
}
