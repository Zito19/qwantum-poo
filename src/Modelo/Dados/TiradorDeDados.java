package Modelo.Dados;

import Modelo.Util.Elemento;
import Observer.Observable;
import Observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class TiradorDeDados implements Observable {
    //clase encarga de administrar los dados
    private Dado[] dados;
    private Elemento[] resultadosDados;//resultados de los dados
    private static TiradorDeDados instancia; // instancia unica

    private List<Observer> observadores;

    private TiradorDeDados() {
        this.dados = new Dado[7];         // Inicializa el arreglo de dados
        this.resultadosDados = new Elemento[7]; // Inicializa los resultados de la tirada

        for (int i = 0; i < dados.length - 1; i++) {
            this.dados[i] = new Dado();   // Inicializa los dados normales
        }
        this.dados[dados.length - 1] = new DadoBlanco(); // El último dado es blanco

        this.observadores = new ArrayList<>(); // Inicializa la lista de observadores
    }
    //instancia
    public static TiradorDeDados getInstancia() {
        if (instancia == null) { // Si la instancia no existe, la crea
            instancia = new TiradorDeDados();
        }
        return instancia;       // Devuelve la unica instancia
    }

    public void tirarDados() {
        Elemento[] tirada = new Elemento[dados.length];
        for (int i = 0; i < dados.length; i++) {
            tirada[i] = dados[i].tirarDado();  // Guarda el resultado de cada dado
        }
        this.resultadosDados = tirada;
        notificar();
    }

    public Elemento[] getResultadoDados(){
        return resultadosDados.clone();
    }
    //observable
    @Override
    public void agregarObservador(Observer observador) {
        observadores.add(observador);  // Agrega el observador a la lista
    }

    @Override
    public void notificar() {
        for (Observer observador : observadores) {
            observador.actualizarDados(getResultadoDados());
        }
    }

    @Override
    public void eliminarObservador(Observer observador) {
        observadores.remove(observador);
    }
}

