package Modelo.Tablas;

public class TablaPuntos extends Tabla {

    private static final int TOTAL_ELEMENTOS = 6;
    private static final int MAYORES = 4; // Los primeros 4 son mayores
    private static final int MENORES = 2; // Los últimos 2 son menores

    private int[] tablaMayores = new int[MAYORES];
    private int[] tablaMenores = new int[MENORES];

    public TablaPuntos() {
        super(TOTAL_ELEMENTOS); // la tabla de mayores + menores se encuentra aca
    }

    public boolean puedeInsertarMayores(int valor) {
        for (int i = 0; i < tablaMayores.length; i++) {
            if (valor > tablaMayores[i] && tablaMayores[i] == 0) {
                return true;
            } else if (valor <= tablaMayores[i]) {
                return false;  // menor o igual no agrega
            }
        }
        return false;
    }
    public boolean puedeInsertarMenores(int valor) {
        if (valor > 0) {
            for (int i = 0; i < tablaMenores.length; i++) {
                if (tablaMenores[i] == 0) { // busca la primera posición vacía
                    if (i == 0 || valor < tablaMenores[0]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean agregarPuntos(int valor, boolean booleano) {
        //INSERCION: TRUE = MENORES || FALSE = MAYORES
        if (booleano) {
            if (puedeInsertarMenores(valor)) { // pregunta si puede insertarlo
                insertarEnMenores(valor); // insertar
                return true;
            } else {
                return false; // no se pudo insertar en menores
            }
        } else {
            if (puedeInsertarMayores(valor)) {// pregunta si puede insertarlo
                insertarEnMayores(valor); // insertar
                return true;
            } else {
                return false; // No se pudo insertar en mayores
            }
        }
    }
    private void insertarEnMayores(int valor) {
        for (int i = 0; i < tablaMayores.length; i++) {
            if (tablaMayores[i] == 0) {
                tablaMayores[i] = valor;
                tabla[i] = valor; // actualiza la tabla
                break;
            }
        }
    }
    private void insertarEnMenores(int valor) {
        for (int i = 0; i < tablaMenores.length; i++) {
            if (tablaMenores[i] == 0) {
                tablaMenores[i] = valor;
                tabla[MAYORES + i] = valor; // actualiza la tabla
                break;
            }
        }
    }
    @Override
    public boolean tablaLLena() {
        for (int i = 0; i < tabla.length; i++) {
            if (tabla[i] == 0) {
                return false; // Si hay alguna posición vacía, la tabla no está llena
            }
        }
        return true; // Si no hay posiciones vacías, la tabla está llena
    }
}