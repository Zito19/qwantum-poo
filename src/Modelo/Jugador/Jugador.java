package Modelo.Jugador;

import Modelo.Tablas.TablaGeneral;


public class Jugador {
        private static int contadorIds = 0;
        private int idJugador; // ID jugador
        private String nombre;
        private TablaGeneral tabla; //tabla general del jugador
        private int turnosSalteados = 0;

        public Jugador(String nombre) {
            this.nombre = nombre;
            this.idJugador = ++contadorIds; // Incrementa el contador y asigna el ID
            this.tabla = new TablaGeneral(); // Inicializa la tabla
        }
        public int getIdJugador() {
            return idJugador;
        }
        public String getNombre() {
            return nombre;
        }
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
        public TablaGeneral getTablaJugador(){
            return this.tabla;
        }
        public int getTurnosSalteados() {
        return turnosSalteados;
        }
        public void setSaltoturno() {
        this.turnosSalteados += 1;
        }
        public void resetTurnosSalteados() {
            this.turnosSalteados = 0;
        }
}
