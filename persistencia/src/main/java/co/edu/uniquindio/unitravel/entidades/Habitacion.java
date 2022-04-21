package co.edu.uniquindio.unitravel.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Habitacion implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Hotel hotel;

    @Positive
    @Column(nullable = false)
    private byte capacidad;

    @Positive
    @Column(nullable = false)
    private double precio;

    @ManyToMany(mappedBy = "habitacion")
    private List<Caracteristica> caracteristicasHabitacion;

    @OneToMany(mappedBy = "habitacion")
    private List<Foto> fotos;

    @OneToMany(mappedBy = "habitacion")
    private List<ReservaHabitacion> reservaHabitaciones;

    public Habitacion(String codigo, Hotel hotel, byte capacidad, double precio, List<Caracteristica> caracteristicasHabitacion, List<Foto> fotos) {
        this.codigo = codigo;
        this.hotel = hotel;
        this.capacidad = capacidad;
        this.precio = precio;
        this.caracteristicasHabitacion = caracteristicasHabitacion;
        this.fotos = fotos;
    }
}