package co.edu.uniquindio.unitravel.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Map;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Administrador extends Persona implements Serializable{

    @Column(nullable = false)
    private String rol;

    public Administrador(String cedula, String nombre, String apellidos, String email, String rol) {
        super(cedula, nombre, apellidos, email);
        this.rol = rol;
    }
}
