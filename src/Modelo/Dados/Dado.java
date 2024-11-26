package Modelo.Dados;

import Modelo.Util.Color;
import Modelo.Util.Elemento;

import java.util.Random;

public class Dado {
    protected Color[] cara = new Color[6];

    public Dado() {
        Random random = new Random();
        for (int i=0; i<4;i++) {
            cara[i] = Color.getColorPorNumero(i);
            //ASIGNA LOS PRIMERO CUATRO COLORES
        }
        do {
            cara[4] = Color.getColorPorNumero(random.nextInt(4));
            cara[5] = Color.getColorPorNumero(random.nextInt(4));
            //GENERA 2 COLORES RANDOMS PARA LOS NUMEROS RESTANTES,DENTRO DE LO VALIDO
        } while (cara[4] == cara[5]); //EVITA QUE UN DADO TENGA 3 COLORES

        mezclarColoresDados();
    }

    private void mezclarColoresDados() {
        Random random = new Random();
        for (int i=0;i< cara.length;i++) {
            int j = random.nextInt(6);
            Color temp = cara[i];
            cara[i] = cara[j];
            cara[j] = temp;
        }
    }

    public Elemento tirarDado() {
        Random random = new Random();
        int posicion = random.nextInt(6); // posicion random entre 0 y 5
        return new Elemento(posicion + 1, cara[posicion]); // Devuelve un Modelo.Util.Elemento con el valor y el color
    }

    @Override
    public String toString() {
        String resultado = "CONTENIDO DEL DADO: ";
        for (int i = 0; i < cara.length; i++) {
            resultado += (i + 1) + ":" + cara[i].name() + " ";
        }
        return resultado.trim();
    }

}
