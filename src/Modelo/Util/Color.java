package Modelo.Util;

public enum Color {
    VIOLETA, AZUL, ROJO, AMARILLO, BLANCO;
    //0,1,2,3,4

    public static Color getColorPorNumero(int numero) {
        return values()[numero];
    }
    public static int cantidadColores(){
        return 4;
    }
}

