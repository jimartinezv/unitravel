package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
//@Entity nos permite informarle al proyecto que la clase es una entidad y por lo tanto debe crearse su tabla en ls bd
@NoArgsConstructor //Crea constructor vacio
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // Equals and hascode permite hacer comparación entre objetos determinando su clave única o valor comparativo
@ToString
public class Departamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include // Se determina como valor comparativo
    private Integer codigo;

    private String nombre;

    @OneToMany(mappedBy = "departamento")
    private List<Ciudad> ciudades;


    public Departamento(String nombre) {
        this.nombre = nombre;
    }

}
