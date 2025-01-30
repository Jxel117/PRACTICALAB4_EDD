package models;

public class RutaViaje {
    private int idRuta;
    private String nombre;
    private String origen;
    private String destino;
    private String distancia;

    public int getIdRuta() {
        return this.idRuta;
    }

    // Constructor
    public RutaViaje(int idRuta, String nombre, String origen, String destino, String distancia) {
        this.idRuta = idRuta;
        this.nombre = nombre;
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOrigen() {
        return this.origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return this.destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDistancia() {
        return this.distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public RutaViaje() {
    }
    
    @Override
    public String toString() {
        return "RutaViaje [destino=" + destino + ", distancia=" + distancia + ", idRuta=" + idRuta + ", nombre=" + nombre
                + ", origen=" + origen + "]";
    }
}
