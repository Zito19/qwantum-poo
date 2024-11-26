package Modelo.Dados;

import Modelo.Util.Color;

public class DadoBlanco extends Dado {
    public DadoBlanco(){
        for (int i=0; i<6;i++) {
            cara[i] = Color.BLANCO;
        }
    }
}
