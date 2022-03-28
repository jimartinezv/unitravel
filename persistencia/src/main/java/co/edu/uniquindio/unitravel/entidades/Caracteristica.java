package co.edu.uniquindio.unitravel.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Clase donde se almacenan las caracteristicas individuales de cada habitación
 * y de los hoteles
 */
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
public class Caracteristica implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    private String descripcion;

    @ManyToMany
    private List<Habitacion> habitacion;

    @ManyToMany
    private List<Hotel> hotel;

}
