package Observer;

public interface Observable {

    void agregarObservador(Observer observador); // agrega un observador

    void eliminarObservador(Observer observador); // elimina un observador
    // Notifica a todos los observadores
    void notificar();
}