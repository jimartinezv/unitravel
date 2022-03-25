package co.edu.uniquindio.unitravel.entidades;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
public class Persona implements Serializable {
    @Id
    private String cedula;

    private String nombre;

    private String apellidos;

    private String email;

    @ElementCollection
    private Map<String, String> telefono;

    public Persona() {
        super();
    }

    public Persona(String cedula, String nombre, String apellidos, String email, Map telefono) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono= telefono;
    }

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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, String> getTelefono() {
        return telefono;
    }

    public void setTelefono(Map<String, String> telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(cedula, persona.cedula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cedula);
    }
}
