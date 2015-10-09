package com.sherolero.bandeco.sample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinicius on 15/09/2015.
 */
public class Cardapio {

    private String dia;
    private String periodo;
    private List<String> opcoes;

    public Cardapio(String dia, String periodo, List<String> opcoes){
        this.opcoes = new ArrayList<>();
        this.dia = dia;
        this.periodo = periodo;
        this.opcoes = opcoes;
    }

    public String getDia(){
        return dia;
    }
    public String getPeriodo(){
        return periodo;
    }
    public List<String> getOpcoes(){
        return opcoes;
    }
}