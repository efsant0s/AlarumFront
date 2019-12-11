/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senior.view;

import com.senior.dao.GerenciaDao;
import com.senior.model.Gerencia;
import java.util.ArrayList;
import java.util.List;
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
    private Gerencia gerencia = new Gerencia();

    public void excluiGerencia(Gerencia gerencia) {
        gerenciaDao.excluir(gerencia);
    }

    public void salvaGerencia(Gerencia gerencia) {
        gerenciaDao.inserir(gerencia);
    }

    public GerenciaView() {
        try {
            gerenciaDao.lista();
        } catch (Exception ex) {
            Logger.getLogger(GerenciaView.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    public List<Gerencia> getListaGerencia() {
        return new ArrayList<Gerencia>(gerenciaDao.getListaGerencia().values());
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
    }
}
