package t3grupo2;

public class Personaje {
    private int idPersonaje;



    private String tipoPersonaje;
    private int nivelFuerza;
    private int nVidas;

    public Personaje(int idPersonaje, String tipoPersonaje, int nivelFuerza, int nVidas) {
        this.idPersonaje = idPersonaje;
        this.tipoPersonaje = tipoPersonaje;
        this.nivelFuerza = nivelFuerza;
        this.nVidas = nVidas;
    }
    public int getIdPersonaje() {
        return idPersonaje;
    }

    public String getTipoPersonaje() {
        return tipoPersonaje;
    }

    public int getNivelFuerza() {
        return nivelFuerza;
    }

    public int getnVidas() {
        return nVidas;
    }

}
