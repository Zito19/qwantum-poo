package Vista;

import Modelo.Util.Color;
import Modelo.Util.Elemento;
import Observer.Observer;

import java.util.List;
import java.util.Scanner;

public class Vista implements Observer {
    private Scanner sc = new Scanner(System.in);
    //acciones de actualizacion
    @Override
    public void actualizarDados(Elemento[] TiradaDados) {
        System.out.println("Dados: ");
        for (Elemento elemento : TiradaDados) {
            System.out.print("[" + elemento.getValor() +"-"+ elemento.getColor() + "]");
        }
        System.out.println();
    }
    @Override
    public void actualizarResultadosXColor(Elemento[] resultadosXColor) {
        System.out.println();
        System.out.println("Posibles puntos: ");
        int i = 1; // Inicializa el número ordinal en 1
        for (Elemento elemento : resultadosXColor) {
            System.out.println(i+"- " + " [" + elemento.getValor() + "][" + elemento.getColor() + "]");
            i++; // Incrementa el número ordinal
        }
    }
    @Override
    public void actualizarTablasJugador(List<int[]> tablas) {
        // recibe las 4 tablas del jugador por color y las muestra
        System.out.println(" ");
        System.out.println("TABLAS DE PUNTOS POR COLOR:");

        for (int i = 0; i < tablas.size() - 2; i++) { //no agarra la tabla resultados y faltas
            Color color = Color.getColorPorNumero(i);
            int[] tablaPuntos = tablas.get(i);
            System.out.print(color + ": ");
            for (int j = 0; j < tablaPuntos.length; j++) {
                System.out.print("[" + (j + 1) + ":" + tablaPuntos[j] + "] ");
            }
            System.out.println();
        }
        // muestra la tabla resultados
        System.out.println("TABLA DE RESULTADOS:");
        int[] tablaResultados = tablas.get(tablas.size() - 2);
        System.out.print("Resultados: ");
        for (int i = 0; i < tablaResultados.length; i++) {
            System.out.print("[" + (i + 1) + ":" + tablaResultados[i] + "] ");
        }
        System.out.println();

        // Mostrar la tabla de faltas
        System.out.println("TABLA DE FALTAS:");
        int[] tablaFaltas = tablas.get(tablas.size() - 1);
        System.out.print("Faltas: ");
        for (int i = 0; i < tablaFaltas.length; i++) {
            System.out.print("[" + (i + 1) + ":" + tablaFaltas[i] + "] ");
        }
        System.out.println();
    }
    @Override
    public void resultadosFinales(List<Object[]> resultados) {
        System.out.println("\u001B[31m======================= FIN DEL JUEGO =======================");
        System.out.print("\u001B[0m");
        if (resultados == null || resultados.isEmpty()) {
            System.out.println("\u001B[31mNo hay resultados para mostrar.");
            return;
        }

        // mostrara primero al ganador
        Object[] ganador = resultados.get(0);
        System.out.println("\u001B[47m\u001B[30m¡GANADOR : " + ganador[0] + " | TOTAL " + ganador[3] + " PUNTOS !\u001B[0m");
        // demas jugadores
        System.out.println("\u001B[31m======================= TABLA RESULTADOS =======================");
        System.out.print("\u001B[32m");

        for (Object[] resultado : resultados) {
            String nombre = (String) resultado[0];
            int puntos = (int) resultado[1];
            int faltas = (int) resultado[2];
            int puntajeFinal = (int) resultado[3];

            System.out.println(nombre + " - Puntos: " + puntos + ", Faltas: " + faltas + ", Puntaje final: " + puntajeFinal);
        }
        System.out.print("\u001B[0m");
    }

    // INPUTS
    public Integer inputCantidadJugadores() {
        System.out.print("\u001B[33mIngrese la cantidad de jugadores: ");
        System.out.print("\u001B[0m");
        while (!sc.hasNextInt()) {
            System.out.print("\u001B[31mIngrese un número válido (cantidad de jugadores)");
            System.out.println("\u001B[0m");
            System.out.print("\u001B[33mIngrese la cantidad de jugadores: ");
            System.out.print("\u001B[0m");
            sc.next();
        }
        Integer cantidadJugadores = sc.nextInt();
        return cantidadJugadores;
    }

    public void mostrarBienvenida() {
        System.out.print("\u001B[34m");//celeste
        System.out.println("====================");
        System.out.println("¡Bienvenido a Qwantum!");
        System.out.println("====================");
        System.out.print("\u001B[0m");
    }
    public Integer inputMenuPrincipal() {
        sc = new Scanner(System.in);
        System.out.println("\u001B[32m1. JUGAR");
        System.out.println("\u001B[31m2. SALIR");
        System.out.print("\u001B[0m");
        System.out.print("\u001B[33mIngrese una opción: ");
        System.out.print("\u001B[0m");
        while (!sc.hasNextInt()) {
            System.out.println("\u001B[31mIngrese una opción válida");
            System.out.print("\u001B[0m");
            System.out.println("\u001B[32m1. JUGAR");
            System.out.println("\u001B[31m2. SALIR");
            System.out.print("\u001B[33mIngrese una opción: ");
            System.out.print("\u001B[0m");
            sc.next();
        }
        Integer opcionElegida = sc.nextInt();
        return opcionElegida;
    }
    public void mostrarOpcionInvalida() {
        System.out.println("\u001B[31mOpción incorrecta");
        System.out.print("\u001B[0m");
    }
    public void mostrarNombreDeJugadorIncorrecto() {
        System.out.println("\u001B[31m¡El nombre del jugador no puede estar vacío!");
        System.out.print("\u001B[0m");
    }
    public void mostrarCantidadDeJugadoresIncorrecta() {
        System.out.println("\u001B[31m¡La cantidad de jugadores debe ser entre 2 y 4!");
        System.out.print("\u001B[0m");
    }
    public String inputNombreJugador(Integer numeroJugador) {
        System.out.print("\u001B[33mIngrese el nombre del jugador " + numeroJugador + ": ");
        System.out.print("\u001B[0m");

        String nombre = sc.next();
        if (sc.hasNextLine()) {
            nombre += sc.nextLine(); //concatena la cadena
        }
        return nombre.trim(); // quitar espacios
    }
    public int inputSeleccionarColor() {
        System.out.print("\u001B[33mIngrese el color de los puntos a guardar (1-4): ");
        System.out.print("\u001B[0m");

        while (!sc.hasNextInt()) {
            System.out.println("Por favor ingrese un número válido.");
            sc.next();//numeros validos
        }
        return sc.nextInt();
    }
    public int inputSeleccionarModoDeInsercion(){
        System.out.print("\u001B[33mIngrese el modo de inserción 1. MAYOR 2. MENOR: ");
        System.out.print("\u001B[0m");
       return sc.nextInt();
    }
    public int inputSeleccionarAccionJugada(){
        System.out.print("\u001B[33m1. Jugar 2. Pasar turno : ");
        System.out.print("\u001B[0m");
        return sc.nextInt();
    }
    public void mostrarNombreTurnoJugador(String nombre){
        System.out.println("\u001B[32mTURNO DE --> [ "+nombre+" ]");
        System.out.print("\u001B[0m");
    }
    public int inputIntentarDeNuevoInsercion(){
        System.out.print("\u001B[33m1. IntentarDenuevo 2. Pasar turno : ");
        System.out.print("\u001B[0m");
        return sc.nextInt();
    }
    public void mostrarGameover() {
        System.out.print("\u001B[31m");
        System.out.println("=======================");
        System.out.println("JUEGO TERMINADO");
        System.out.println("=======================");
        System.out.print("\u001B[0m");
    }
    public void vistaRonda(int ronda){
        System.out.print("\u001B[31m");
        System.out.println("======================= RONDA "+ronda+" =======================");
        System.out.print("\u001B[0m");
    }
}
