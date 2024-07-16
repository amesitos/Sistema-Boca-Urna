package ec.edu.pucem.bocaurna.models;

import java.util.ArrayList;
import java.util.List;

public class DatosVotacion {
    private static List<Voto> votos = new ArrayList<>();

    public static List<Voto> getVotos() {
        return votos;
    }

    public static void agregarVoto(Voto voto) {
        votos.add(voto);
    }

    public static void reiniciarVotos() {
        votos.clear();
    }
}