package Modelo.Jugador;

import Observer.Observable;
import Observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class ResultadoJuego implements Observable {
    private List<Observer> observadores = new ArrayList<>();
    private List<Object[]> resultados = new ArrayList<>();

    public void obtenerResultadosDeJugadores(List<Jugador> jugadores) {
        this.resultados = new ArrayList<>();
        for (Jugador jugador : jugadores) {
            // obtener resultados finales por jugador
            int[] resultadosJugador = jugador.getTablaJugador().obtenerResultadosFinales();

            // arreglo de objetts contednra: nombr y los resultados del jugador
            Object[] resultado = new Object[4];
            resultado[0] = jugador.getNombre();  // nombre
            resultado[1] = resultadosJugador[0]; // puntos
            resultado[2] = resultadosJugador[1]; // faltas
            resultado[3] = resultadosJugador[2]; // puntaje final (puntos - faltas)
            this.resultados.add(resultado);
        }
        this.resultados.sort((a, b) -> Integer.compare((int) b[3], (int) a[3]));
     notificar();
    }

    @Override
    public void agregarObservador(Observer observador) {
        observadores.add(observador);
    }

    @Override
    public void notificar() {
        for (Observer observador : observadores) {
            observador.resultadosFinales(this.resultados);
        }
    }

    @Override
    public void eliminarObservador(Observer observador) {
        observadores.remove(observador);
    }
}
