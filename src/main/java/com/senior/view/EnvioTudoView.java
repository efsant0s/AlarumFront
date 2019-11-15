/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senior.view;

import com.senior.dao.EnvioTudoDao;
import com.senior.model.Mensagem;
import com.senior.model.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Eduardo
 */
@ManagedBean
@SessionScoped
public class EnvioTudoView {

    private EnvioTudoDao enviaDao = new EnvioTudoDao();
    private String gerenciaSelecionada;
    public void setGrupoEnvio(String gerencia){
        this.gerenciaSelecionada =  gerencia;
    }
    public List<String> getListaGerencia() {
        return new ArrayList<>(enviaDao.getListaGrupos().keySet());
    }
    public void enviaMensagemTodos(Mensagem msg){
        enviaDao.enviaMensagemParaTodos(getListaGerencia(), msg);
    }
    
    public void enviaMensagemGrupo(Mensagem msg){
        enviaDao.enviaMensagemParaGrupo(gerenciaSelecionada, msg);
    }
    
    public List<Usuario> getListaUsuario(String gerencia) {
        return new ArrayList<Usuario>(enviaDao.getListaGrupos().get(gerencia).values());
    }
}
