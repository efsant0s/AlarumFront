/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senior.model;

import com.senior.utils.Utils;

/**
 *
 * @author Eduardo
 */
public class UsuarioLogin {

    private String ds_login;
    private String ds_senha;
    private String ds_email;
    private String ie_admin;

    public UsuarioLogin() {
    }

    public String getIe_admin() {
        return ie_admin;
    }

    public void setIe_admin(String ie_admin) {
        this.ie_admin = ie_admin;
    }

    public UsuarioLogin(String login, String email, String senha) {
        this.ds_email = email;
        this.ds_login = login;
        this.ds_senha = Utils.md5(senha);
        this.ie_admin = "N";
    }

    public UsuarioLogin(String login, String email, String senha, String ie_admin) {
        this.ds_email = email;
        this.ds_login = login;
        this.ds_senha = Utils.md5(senha);
        this.ie_admin = ie_admin;
    }

    public String getDs_login() {
        return ds_login;
    }

    public void setDs_login(String ds_login) {
        this.ds_login = ds_login;
    }

    public String getDs_senha() {
        return ds_senha;
    }

    public void setDs_senha(String ds_senha) {
        this.ds_senha = (ds_senha);
    }

    public String getDs_email() {
        return ds_email;
    }

    public void setDs_email(String ds_email) {
        this.ds_email = ds_email;
    }

}
