package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;
import java.io.Serializable;



@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@ToString
public class Persona implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @Column(length = 11)
    private String cedula;

    @Column(length = 30, nullable = false)
    private String nombre;


    @Column(length = 30, nullable = false)
    private String apellidos;

    @Email
    @Column(length = 50, unique = true, nullable = false)
    private String email;

    @Column(length = 50, nullable = false)
    private String password;






    public Persona(String cedula, String nombre, String apellidos, String email, String password) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password= password;

    }


}
