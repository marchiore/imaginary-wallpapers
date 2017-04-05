package com.mat.imgwpp.util;

import java.util.ArrayList;
import java.util.List;


public class LogModel{

    private List<Link> lista = new ArrayList<Link>();
    
    public LogModel(){
        //super();
    }    

    public List<Link> getLista() {
        return lista;
    }
    
    public void setLista(List<Link> lista) {
        this.lista = lista;
    }

}