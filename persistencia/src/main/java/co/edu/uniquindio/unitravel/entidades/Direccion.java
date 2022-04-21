package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
//@Entity nos permite informarle al proyecto que la clase es una entidad y por lo tanto debe crearse su tabla en ls bd
@NoArgsConstructor //Crea constructor vacio
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // Equals and hascode permite hacer comparación entre objetos determinando su clave única o valor comparativo
public class Direccion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    private String direccion;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Ciudad ciudad;

    public Direccion(Integer codigo, String direccion) {
        this.codigo = codigo;
        this.direccion = direccion;
    }
}
