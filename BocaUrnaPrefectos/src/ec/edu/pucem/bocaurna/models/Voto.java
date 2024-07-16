package ec.edu.pucem.bocaurna.models;

public class Voto {
    private Candidato candidato;
    private Provincia provincia;
    private String ciudad;

    public Voto(Candidato candidato, Provincia provincia, String ciudad) {
        this.candidato = candidato;
        this.provincia = provincia;
        this.ciudad = ciudad;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
}



