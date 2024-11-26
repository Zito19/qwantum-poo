package Modelo.Tablas;

public abstract class Tabla {
    protected int[] tabla;

    // inicializa la tabla en el tamaño elegido
    public Tabla(int tamano) {
        tabla = new int[tamano];
        // Inicializamos la tabla con ceros, no es necesario
        for (int i = 0; i < tamano; i++) {
            tabla[i] = 0;
        }
    }
    public boolean contiene(int valor) {
        for (int i = 0; i < tabla.length; i++) {
            if (tabla[i] == valor) {
                return true;
            }
        }
        return false;
    }
    public int[] getTabla(){
        return this.tabla.clone();
    }
    public boolean tablaLLena(){return tabla[tabla.length-1] != 0;}
    public boolean tablaVacia(){return tabla[0] == 0;}

}
