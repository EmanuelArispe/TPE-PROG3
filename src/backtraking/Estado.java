package src.backtraking;

public class Estado {

    private Solucion solucionActual;
    private int cantEstadoFinales;

    private int cantEstado;

    public Estado (){
        solucionActual = new Solucion(0);
        setCantEstadoFinales(0);
        setCantEstado(0);}

    public int getCantEstadoFinales() {
        return cantEstadoFinales;
    }

    public Solucion getSolucionActual() {
        return solucionActual;
    }

    private void setCantEstadoFinales(int cantEstado) {
        this.cantEstadoFinales = cantEstado;
    }
    public void sumarEstadoFinales(){setCantEstadoFinales(getCantEstadoFinales() + 1);}

    public int getCantEstado() {
        return cantEstado;
    }

    private void setCantEstado(int cantEstado) {
        this.cantEstado = cantEstado;
    }
    public void sumarEstado(){setCantEstado(getCantEstado() + 1);}
    @Override
    public String toString() {
        return "Estado{" +
                "solucionActual=" + solucionActual.toString() +
                ", cantEstadoFinales=" + cantEstadoFinales +
                '}';
    }
}
