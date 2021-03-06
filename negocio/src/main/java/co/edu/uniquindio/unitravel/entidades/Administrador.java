package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Map;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Administrador extends Persona implements Serializable{



    public Administrador(String cedula, String nombre, String apellidos, String password, String email, String rol) {
        super(cedula, nombre, apellidos, password, email);
    }
}
