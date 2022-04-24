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
public class AdministradorHotel extends Persona implements Serializable {


    public AdministradorHotel(String cedula, String nombre, String apellidos, String password, String email,  String rol) {
        super(cedula, nombre, apellidos, password, email);
    }
}
