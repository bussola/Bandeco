package ru.noties.scrollable.sample;

import java.util.List;

/**
 * Created by Vinicius on 15/09/2015.
 */
public class Cardapio {

    private String dia;
    private String periodo;
    private List<String> opcoes;

    public Cardapio(String dia, String periodo, List<String> opcoes){
        super();
        this.dia = dia;
        this.periodo = periodo;
        this.opcoes = opcoes;
    }
}