/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senior.dao;



/**
 *
 * @author Aluno
 */
public interface Repository {

    void inserir(Object o);

    void alterar(Object o);

    void excluir(Object o);

    void lista();

}
