package io.github.marcondesnjr.patterngames.gui.bean;

import java.io.Serializable;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class ObsBean implements Serializable{
    private String loginCli;
    private String nomeJogo;

    public String getLoginCli() {
        return loginCli;
    }

    public void setLoginCli(String loginCli) {
        this.loginCli = loginCli;
    }

    public String getNomeJogo() {
        return nomeJogo;
    }

    public void setNomeJogo(String nomeJogo) {
        this.nomeJogo = nomeJogo;
    }
    
    
}
