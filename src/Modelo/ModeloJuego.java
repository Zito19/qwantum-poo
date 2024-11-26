package Modelo;
import Modelo.Dados.ProcesadorResultadosDados;
import Modelo.Dados.TiradorDeDados;
import Modelo.Jugador.Jugador;
import Modelo.Jugador.ResultadoJuego;
import Modelo.Tablas.TablaGeneral;
import Modelo.Util.Color;
import Modelo.Util.Elemento;
import java.util.ArrayList;
import java.util.List;

public class ModeloJuego {
    private static ModeloJuego instancia;
    private TiradorDeDados tiradorDeDados;
    private List<Jugador> jugadores;// Lista de jugadores
    private Jugador jugadorActual;
    private ResultadoJuego procesadorResultadosFinales;// resultados finales
    private ProcesadorResultadosDados procesadorResultadosDados = ProcesadorResultadosDados.getInstancia(); //encargado de los resultados x color

    private ModeloJuego() {
        this.tiradorDeDados = TiradorDeDados.getInstancia();
        this.jugadores = new ArrayList<>();
        this.procesadorResultadosFinales = new ResultadoJuego();
    }
    // Instancia única de Juego
    public static ModeloJuego getInstancia() {
        if (instancia == null) {
            instancia = new ModeloJuego();                // Crea la instancia única si no existe
        }
        return instancia;
    }

    //-----------DADOS Y RESULTADOS DADOS----------

    public void tirarDados() {
        tiradorDeDados.tirarDados();  // lo hace el tirador de dados
    }
    public void resultadosTiradaXColor() {
       procesadorResultadosDados.procesarResultadosDados();
    }
    public TiradorDeDados getTiradorDeDados(){
        return this.tiradorDeDados;
    }
    public ProcesadorResultadosDados getProcesadorResultadosDados(){
        return this.procesadorResultadosDados;
    }
    public Elemento getResultadoPorColor(int color){
      return  procesadorResultadosDados.getResultadoPorColor(Color.getColorPorNumero(color-1));
    }
    public boolean eliminarResultado(int color) {
        procesadorResultadosDados.reiniciarColor(Color.getColorPorNumero(color - 1));
        return true; // Devuelve true si necesitas un indicador de éxito
    }
    //----------METODOS JUGADOR-------------

    // agrega un jugador a la lista
    public Jugador agregarJugador(String nombre) {
        Jugador nuevo = new Jugador(nombre);
        jugadores.add(nuevo);
        return nuevo;
    }
    // devuelve la lista de jugadores
    public List<Jugador> getJugadores() {
        return jugadores;
    }
    // cambir turno de jugadores
    public Jugador cambiarTurno() {
        // si el jugadorActual es null, asigno el primer jugador
        if (jugadorActual == null) {
            jugadorActual = jugadores.get(0);
            return this.jugadorActual;
        }

        int index = jugadores.indexOf(jugadorActual); //indice jugador actual
        // si el jugador actual es el ultimo debe volver al primer jugador
        if (index == jugadores.size() - 1) {
            jugadorActual = jugadores.get(0);
        } else {
            jugadorActual = jugadores.get(index + 1);
        }
        return this.jugadorActual;
    }
    public Boolean validarCantidadJugadores(Integer cantidadJugadores) {
        return cantidadJugadores >= 2 && cantidadJugadores <= 4;
    }
    public Boolean validarNombreJugador(String nombre) {
        nombre = nombre.trim();
        if (nombre == null || nombre.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    //-------------METODOS TABLA X JUGADOR--------------
    //metodo tabla.
    public void mostrarTabla(Jugador jugador){
        jugador.getTablaJugador().mostrarTabla();
    }
    public TablaGeneral getTablaJugador(){
        return jugadorActual.getTablaJugador();
    }
    public boolean agregarPuntos(Jugador jugador, Elemento elemento, boolean booleano) {
        // Intentamos insertar el punto en la tabla del jugador actual
        boolean insercionValida = jugador.getTablaJugador().setPunto(elemento, booleano);

        if (insercionValida) {
            jugador.resetTurnosSalteados();// si logro insertar se le resetea la tabla de faltas.
        }
        return insercionValida;
    }

    public void setFaltaJugador(Jugador jugador){
        if(jugador == jugadorActual) {//la falta solo se pone si es su turno
            jugadorActual.getTablaJugador().setFalta();
            jugadorActual.setSaltoturno();
        }
    }
    public Jugador getJugadorActual() {
        return jugadorActual;
    }
    public boolean tablaResultadosLlena(Jugador jugador){
        return jugador.getTablaJugador().tablaResultadosLlena();
    }
    public boolean tablaFaltasLlena(Jugador jugador){
        return jugador.getTablaJugador().tablaFaltasLlena();
    }
    public boolean juegoTerminado() {
        for (Jugador jugador : jugadores) {
            // opcion 1: tabla de faltas llena
            if (tablaFaltasLlena(jugador)) {
                return true; //si un jugador tiene la tabla de faltas llena, termino el juego
            }
            // opcion 2: la tabla de resultados esta llena y todos sus colores
           if (tablaResultadosLlena(jugador)) {
           return true; //si un jugador tiene la tabla de resultados y colores llena, termino el juego
            }

            // opcion 3: el jugador salteo 2 veces seguidas el turno
            if (jugador.getTurnosSalteados() > 1) {
               return true; // si un jugador tiene saltea el turno 2 veces, termino el juego
          }
        }
        return false;
    }
    public void resultadosFinales(){
        this.procesadorResultadosFinales.obtenerResultadosDeJugadores(this.jugadores);
    }
    public ResultadoJuego getResultadoJuego(){
        return  this.procesadorResultadosFinales;
    }
}
