package ec.edu.pucem.bocaurna.models;

import java.util.Objects;

public class Candidato {
    private String cedula;
    private String nombre;
    private String apellido;
    private String partido;
    private Provincia provincia;
    private String ciudad;

    // Constructor
    public Candidato(String cedula, String nombre, String apellido, String partido, Provincia provincia) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.partido = partido;
        this.provincia = provincia;
    }

    // Getters y Setters
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
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
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Candidato that = (Candidato) obj;
        return nombre.equals(that.nombre) && apellido.equals(that.apellido) && provincia.equals(that.provincia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellido, provincia);
    }
}




