package Controlador;

import Modelo.Jugador.Jugador;
import Modelo.ModeloJuego;
import Vista.Vista;

public class Controlador {
        private ModeloJuego juego;
        private Vista vista;

        public Controlador(ModeloJuego modelo, Vista vista) {
            this.juego = modelo;
            this.vista = vista;
           juego.getTiradorDeDados().agregarObservador(vista);
            juego.getProcesadorResultadosDados().agregarObservador(vista);
            juego.getResultadoJuego().agregarObservador(vista);
        }

    public void jugar() {
            int opcionAccion;
        boolean terminar = false;
        vista.mostrarBienvenida();
        while (!terminar) {
            //muestra el menu principal
            Integer opcionElegida = vista.inputMenuPrincipal();
            Boolean agregado = false;
            while (!agregado) {
                if (opcionElegida == 1 || opcionElegida == 2) {
                    agregado = true;
                } else {
                    vista.mostrarOpcionInvalida();
                    opcionElegida = vista.inputMenuPrincipal();
                }
            }
            switch (opcionElegida) { // opcion 1. jugar
                case 1://solicita los jugadores y los carga(2 a 4)
                    Integer cantidadJugadores = vista.inputCantidadJugadores();
                    agregado = false;
                    while (!agregado) {
                        if (juego.validarCantidadJugadores(cantidadJugadores)) {
                            agregado = true;
                        } else {
                            vista.mostrarCantidadDeJugadoresIncorrecta();
                            cantidadJugadores = vista.inputCantidadJugadores();
                        }
                    }
                    for (Integer i = 0; i < cantidadJugadores; i++) {
                        agregado = false;
                        while (!agregado) {
                            String nombre = vista.inputNombreJugador(i + 1);
                            if (juego.validarNombreJugador(nombre)) {
                                Jugador jugadorAgregado = juego.agregarJugador(nombre);
                                jugadorAgregado.getTablaJugador().agregarObservador(vista);
                                agregado = true;
                            } else {
                                vista.mostrarNombreDeJugadorIncorrecto();
                                nombre = vista.inputNombreJugador(i + 1);
                            }
                        }
                    }
                    //arranca el juego
                    int ronda = 1;
                    while(!juego.juegoTerminado()) {
                        vista.vistaRonda(ronda);
                        Jugador turnoActual = juego.cambiarTurno(); // cambia el turno al siguiente jugador
                        vista.mostrarNombreTurnoJugador(juego.getJugadorActual().getNombre()); // muestra el jugador actual
                        juego.tirarDados(); //tira los dados
                        juego.resultadosTiradaXColor(); // calcula los resultados de la tirada por color
                        juego.mostrarTabla(turnoActual); // muestra la tabla del jugador actual

                        opcionAccion = vista.inputSeleccionarAccionJugada(); // 1 jugar 2 pasa turno

                        while (!verificarInputEnteros(opcionAccion, 1, 2)) {
                            vista.mostrarOpcionInvalida();
                            opcionAccion = vista.inputSeleccionarAccionJugada();
                        }

                        if (opcionAccion == 1) { // Eligio jugar
                            insertarPuntos(turnoActual);
                        } else if (opcionAccion == 2) {
                            juego.setFaltaJugador(turnoActual); //salteo el turno, marca la falta
                        }
                        // comienza el bucle para pedirle a los jugadores restantes si desean llevase alguna puntuacion sin cometer falta
                        Jugador jugadorActual = turnoActual;

                        for (Jugador jugador : juego.getJugadores()) {
                            if (!jugador.equals(jugadorActual)) {  // Solo los jugadores que no sean el actual
                                vista.mostrarNombreTurnoJugador(jugador.getNombre());
                                opcionAccion = vista.inputSeleccionarAccionJugada();

                                while (!verificarInputEnteros(opcionAccion, 1, 2)) {
                                    vista.mostrarOpcionInvalida();
                                    opcionAccion = vista.inputSeleccionarAccionJugada();
                                }
                                if (opcionAccion == 1) {//selecciono 1. jugar
                                    juego.getProcesadorResultadosDados().mostrarResultadosPorColorRestantes(); // muestra los puntos restantes
                                    juego.mostrarTabla(jugador); //muestra la tabla del jugador (no el turno actual)
                                    insertarPuntos(jugador);
                                }
                            }
                        }
                        ronda ++;
                    }
                    juego.resultadosFinales(); //Muestra los resultados finales
                case 2: //Salecciono salir o termino el juego, muestra pantalla final
                    terminar = true;
                    vista.mostrarGameover();
                    break;
                default:
                    vista.mostrarOpcionInvalida();
                    break;
            } break;
        }
    }
    public boolean verificarInputEnteros(int input, int x, int y) {
        //verifica si el input fue valido dentro de un rango.
        if (input >= x && input <= y) {
            return true;
        }
        return false;
    }

    public boolean insertarPuntos(Jugador jugador) {
        boolean resultado; //insercion valida
        int color;
        int insercion;//modo de insercion
        boolean booleanoInsercion;

        do {// Seleccion y verificacion del color
            color = vista.inputSeleccionarColor();
            while (!verificarInputEnteros(color, 1, 4)) {
                vista.mostrarOpcionInvalida();
                color = vista.inputSeleccionarColor();
            }
            // Selecciona el modo de inserción
            insercion = vista.inputSeleccionarModoDeInsercion();
            while (!(insercion == 1 || insercion == 2)) {
                vista.mostrarOpcionInvalida();
                insercion = vista.inputSeleccionarModoDeInsercion();
            }

            booleanoInsercion = insercion != 1; // Determina el modo de inserción

            // Intenta agregar los puntos
            resultado = juego.agregarPuntos(jugador, juego.getResultadoPorColor(color), booleanoInsercion);

            if (resultado) {//insercion exitosa eliminar de la tabla de resultados
                juego.eliminarResultado(color);
            } else {
                // error en la insercion, reintentar,salir y mensaje de error
                vista.mostrarOpcionInvalida();
                int continuar = vista.inputIntentarDeNuevoInsercion();
                if (continuar == 2) {
                    juego.setFaltaJugador(jugador);
                    break;
                }
            }

        } while (!resultado);

        return booleanoInsercion; // exitoso o no
    }

}
