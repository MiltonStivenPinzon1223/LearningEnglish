package com.edu.ue.myproject.api.model;

import java.util.ArrayList;

public class ResponseCredentials {
    private ArrayList<Credentials> credencials;
    private String mensaje;

    public ResponseCredentials() {
    }

    public ArrayList<Credentials> getCredencials() {
        return credencials;
    }

    public void setCredencials(ArrayList<Credentials> credencials) {
        this.credencials = credencials;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
