package Modelo.Tablas;

public class TablaFaltas extends Tabla{

    public TablaFaltas(){
        super(5);
    }
    @Override

    public int[] getTabla(){
        return this.tabla.clone();
    }

    public boolean setFalta(){
        if(!tablaLLena()){
            for(int i =0; i < tabla.length; i++){
                if(tabla[i]==0){
                    tabla[i] = 1;
                    return true;
                }
            }
        }
        System.out.println("Limite de faltas");
        return false;
    }

    public void sacarFalta(){
        if(!tablaLLena()){
            for(int i = tabla.length - 1; i >= 0; i--){
                if(tabla[i] != 0){
                    tabla[i] = 0;
                    break;
                }
            }
        }
    }

    public int calcularPenalizacion() {
        int penalizacion = 0;
        // funciona como un arreglo de bits
        for (int i = 0; i < tabla.length; i++) {
            if (tabla[i] == 1) {
                penalizacion += (i + 1); // suma la posicion ordinal
            }
        }
        return penalizacion;
    }

    @Override
    public boolean tablaLLena() {
        for (int i = 0; i < tabla.length; i++) {
            if (tabla[i] == 0) {
                return false;
            }
        }
        return true;
    }
    @Override
    public String toString() {
        String resultado = "ESTADO TABLA DE FALTAS: \n";
        for (int i = 0; i < tabla.length; i++) {
            resultado += "["+(i+1)+":"+tabla[i]+"]";
        }
        return resultado.trim();
    }
}
