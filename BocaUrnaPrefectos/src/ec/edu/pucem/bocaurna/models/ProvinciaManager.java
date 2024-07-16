package ec.edu.pucem.bocaurna.models;

import java.util.HashMap;
import java.util.Map;

public class ProvinciaManager {
    private static final Map<String, String[]> provinciasCiudades = new HashMap<>();

    static {
        provinciasCiudades.put("Guayas", new String[]{"Guayaquil", "Daule", "Durán", "Samborondón"});
        provinciasCiudades.put("Manabí", new String[]{"Portoviejo", "Manta", "Chone", "Jipijapa"});
        provinciasCiudades.put("Esmeraldas", new String[]{"Esmeraldas", "Atacames", "Quinindé", "San Lorenzo"});
        provinciasCiudades.put("Santa Elena", new String[]{"La Libertad", "Salinas", "Santa Elena", "Manglaralto"});
        provinciasCiudades.put("Los Ríos", new String[]{"Babahoyo", "Quevedo", "Ventanas", "Vinces"});
        provinciasCiudades.put("El Oro", new String[]{"Machala", "Santa Rosa", "Pasaje", "Huaquillas"});
        provinciasCiudades.put("Santo Domingo de los Tsáchilas", new String[]{"Santo Domingo", "La Concordia"});
        provinciasCiudades.put("Cañar", new String[]{"Azogues", "Biblián", "La Troncal"});
        provinciasCiudades.put("Loja", new String[]{"Loja", "Catamayo", "Macará"});
        provinciasCiudades.put("Chimborazo", new String[]{"Riobamba", "Guano", "Alausí"});
        provinciasCiudades.put("Tungurahua", new String[]{"Ambato", "Baños", "Pelileo"});
        provinciasCiudades.put("Cotopaxi", new String[]{"Latacunga", "Salcedo", "La Maná"});
        provinciasCiudades.put("Bolívar", new String[]{"Guaranda", "San Miguel", "Chillanes"});
    }
 
    public static Provincia[] getProvincias() {
        return provinciasCiudades.keySet().stream()
                .map(Provincia::new)
                .toArray(Provincia[]::new);
    }

    public static String[] getCiudades(String provincia) {
        return provinciasCiudades.getOrDefault(provincia, new String[]{});
    }
}

