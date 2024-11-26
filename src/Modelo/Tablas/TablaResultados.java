package Modelo.Tablas;

public class TablaResultados extends Tabla {
    private int[] tablaResultados;

    public TablaResultados() {
        super(6);
        this.tablaResultados = new int[6];
    }

    public void calcularResultado(TablaPuntos[] tablaPuntos) {
        // reiniciar tabla para evitar acumular resultados
        for (int i = 0; i < tablaResultados.length; i++) {
            tablaResultados[i] = 0;
        }
        // suma los valores de las tablas de puntos por color
        for (int i = 0; i < tablaPuntos.length; i++) {
            int[] tabla = tablaPuntos[i].getTabla();
            for (int j = 0; j < tabla.length; j++) {
                tablaResultados[j] += tabla[j];
            }
        }
    }
    public int[] getTabla() {
        return tablaResultados;
    }
    public int getSumaTotal() {
        //recorre toda la tabla y suma
        int suma = 0;
        for (int i = 0; i < tablaResultados.length; i++) {
            suma += tablaResultados[i];
        }
        return suma;
    }
    @Override
    public boolean tablaLLena() {
        // recorre toda la tabla y verifica si alguna posición es igual a 0
        for (int i = 0; i < tablaResultados.length; i++) {
            if (tablaResultados[i] == 0) {
                return false;  // si se encuentra un 0, la tabla no está llena
            }
        }
        return true;
    }
}

