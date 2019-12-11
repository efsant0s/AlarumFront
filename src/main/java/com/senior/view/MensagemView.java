/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senior.view;

import com.senior.dao.MensagemDao;
import com.senior.model.Mensagem;
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
public class MensagemView {

    private MensagemDao mensagemDao = new MensagemDao(); 
    private Mensagem mensagem = new Mensagem();

    public void excluiMensagem(Mensagem mensagem) {
        mensagemDao.excluir(mensagem); 
    }

    public void salvaMensagem(Mensagem mensagem) {
        mensagemDao.inserir(mensagem); 
    }

    public MensagemView() {
        try {
            mensagemDao.lista(); 
        } catch (Exception ex) {
            Logger.getLogger(MensagemView.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

 

    public Map<String, Mensagem> getMapMensagems() {
        return mensagemDao.getListaMensagem();
    }

    public List<Mensagem> getListaMensagem() {
        return new ArrayList<Mensagem>(mensagemDao.getListaMensagem().values());
    }

    public Mensagem getMensagem() {
        return mensagem;
    }

    public void setMensagem(Mensagem mensagem) {
        this.mensagem = mensagem;
    }

    public void salvaMensagem() throws ServletException {
        mensagemDao.inserir(mensagem);
        mensagem = new Mensagem(); 
    }
}
