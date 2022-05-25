package co.edu.uniquindio.unitravel.entidades;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString(onlyExplicitlyIncluded = true)
public class Habitacion implements Serializable {
    @Id
    @ToString.Include
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;


    @ManyToOne
    @JoinColumn(nullable = true)
    private Hotel hotel;

    @Positive
    @ToString.Include
    @Column(nullable = false)
    private byte capacidad;

    @ToString.Include
    @Column(nullable = false)
    private String nombre;
    @Positive
    @ToString.Include
    @Column(nullable = false)
    private double precio;

    @ManyToMany(mappedBy = "habitacion")
    private List<Caracteristica> caracteristicasHabitacion;

    @ElementCollection
    private List<String> fotos;

    @OneToMany(mappedBy = "habitacion")
    private List<ReservaHabitacion> reservaHabitaciones;



    public Habitacion(Integer codigo,String nombre, Hotel hotel, byte capacidad, double precio, List<Caracteristica> caracteristicasHabitacion, List<String> fotos) {
        this.codigo = codigo;
        this.nombre= nombre;
        this.hotel = hotel;
        this.capacidad = capacidad;
        this.precio = precio;
        this.caracteristicasHabitacion = caracteristicasHabitacion;
        this.fotos = fotos;
    }
}
