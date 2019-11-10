/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senior.view;

import com.senior.dao.GerenciaDao;
import com.senior.model.Gerencia;
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
public class GerenciaView {

    private GerenciaDao gerenciaDao = new GerenciaDao();
    private Map<String, Gerencia> listaGerencias = gerenciaDao.getListaGerencia();
    private Gerencia gerencia = new Gerencia();

    public void excluiGerencia(Gerencia gerencia) {
        gerenciaDao.excluir(gerencia);
        atualizaLista();
    }

    public void salvaGerencia(Gerencia gerencia) {
        gerenciaDao.inserir(gerencia);
        atualizaLista();
    }

    public GerenciaView() {
        try {
            atualizaLista();
            gerenciaDao.lista();
        } catch (Exception ex) {
            Logger.getLogger(GerenciaView.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    private void atualizaLista() {
        listaGerencias = gerenciaDao.getListaGerencia();
    }

    public Map<String, Gerencia> getMapGerencias() {
        return listaGerencias;
    }

    public List<Gerencia> getListaGerencia() {
        return new ArrayList<Gerencia>(listaGerencias.values());
    }

    public Gerencia getGerencia() {
        return gerencia;
    }

    public void setGerencia(Gerencia gerencia) {
        this.gerencia = gerencia;
    }

    public void salvaGerencia() throws ServletException {
        gerenciaDao.inserir(gerencia);
        gerencia = new Gerencia();
        atualizaLista();
    }
}
