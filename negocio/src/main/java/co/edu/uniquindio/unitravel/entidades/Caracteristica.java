package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Clase donde se almacenan las caracteristicas individuales de cada habitación
 * y de los hoteles
 */
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class Caracteristica implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @ToString.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @ToString.Include
    private String descripcion;

    @ManyToMany
    private List<Habitacion> habitacion;


    @ManyToMany(mappedBy = "caracteristicas")
    private List<Hotel> hotel;


}
