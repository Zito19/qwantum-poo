package Observer;

import Modelo.Util.Elemento;

import java.util.List;

public interface Observer {
    public void actualizarDados(Elemento[] TiradaDados);
    public void actualizarResultadosXColor(Elemento[] ResultadosXColor);
    public void actualizarTablasJugador(List<int[]> Tablas);
    public void resultadosFinales(List<Object[]> resultados);
}
