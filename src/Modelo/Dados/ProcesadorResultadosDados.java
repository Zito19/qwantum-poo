package Modelo.Dados;

import Modelo.Util.Color;
import Modelo.Util.Elemento;
import Observer.Observable;
import Observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class ProcesadorResultadosDados implements Observable {
    private static ProcesadorResultadosDados instancia; // instancia única de la clase
    private TiradorDeDados tiradorDeDados = TiradorDeDados.getInstancia();
    private Elemento[] resultadosXColor; // guarda los resultados

    private List<Observer> observadores = new ArrayList<>();

    private ProcesadorResultadosDados() {}

    // instancia unica
    public static ProcesadorResultadosDados getInstancia() {
        if (instancia == null) {
            instancia = new ProcesadorResultadosDados();
        }
        return instancia;
    }

    // procesa los resultados y notifica a los observadores
    public void procesarResultadosDados() {
        //arreglo de elementos inicializados en 0
        resultadosXColor = new Elemento[Color.cantidadColores()];
        for (int i = 0; i < resultadosXColor.length; i++) {
            resultadosXColor[i] = new Elemento(0, Color.getColorPorNumero(i));
        }
        for (Elemento elemento : tiradorDeDados.getResultadoDados()) {
            int valor = elemento.getValor();

            if (elemento.getColor() == Color.BLANCO) {
                // Si el color es blanco lo suma en todas las posiciones
                for (Elemento resultado : resultadosXColor) {
                    resultado.setValor(resultado.getValor() + valor);
                }
            } else {
                // suma a los colores correspondientes
                int i = elemento.getColorOrdinal();
                resultadosXColor[i].setValor(resultadosXColor[i].getValor() + valor);
            }
        }
        notificar();
    }
    public void mostrarResultadosPorColorRestantes(){
        notificar();
    }
    // reinicia los resultados de un color a 0 (alguien los utilizo)
    public void reiniciarColor(Color color) {
        for (Elemento elemento : resultadosXColor) {
            if (elemento.getColor().equals(color)) {
                elemento.setValor(0); //0
                break;
            }
        }
        notificar();
    }

    public Elemento getResultadoPorColor(Color color) {
        for (Elemento elemento : resultadosXColor) {
            if (elemento.getColor().equals(color)) {
                return elemento; // si el color coincide devuelve
            }
        }
        return null;
    }
    //observable
    @Override
    public void agregarObservador(Observer observador) {
        observadores.add(observador);
    }

    @Override
    public void notificar() {
        for (Observer observador : observadores) {
            observador.actualizarResultadosXColor(resultadosXColor.clone());
        }
    }

    @Override
    public void eliminarObservador(Observer observador) {
        observadores.remove(observador);
    }
}
