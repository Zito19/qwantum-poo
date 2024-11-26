package Modelo.Util;

//VIOLETA, AZUL, ROJO, AMARILLO, BLANCO;
//0,1,2,3,4
public class Elemento {
    private int valor;
    private Color color;

    public Elemento(int valor, Color color) {
        this.valor = valor;
        this.color = color;
    }
    public Elemento(int valor, int color) {
        this.valor = valor;
        this.color = Color.getColorPorNumero(color);
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    // Método para obtener el color como int
    public int getColorOrdinal() {
        return color.ordinal(); // Devuelve el índice del color
    }

    @Override
    public String toString() {
        return valor +":"+color;
    }
}

