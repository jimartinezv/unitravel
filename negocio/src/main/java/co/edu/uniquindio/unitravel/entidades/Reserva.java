package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Reserva implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    private LocalDateTime fechaReserva;

    @Future
    private LocalDate fechaInicio;

    @Future
    private LocalDate fechaFin;

    @Positive
    private double precioTotal;

    @Column(nullable = false, length = 20)
    private String estado;

    @Positive
    private byte cantidadPersonas;

    @OneToMany(mappedBy = "reserva")
    private List<ReservaSilla> reservaSillas;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cliente cliente;

    @ManyToOne
    private Vuelo vueloIda;

    @ManyToOne
    private Vuelo vueloRegreso;

    @OneToMany(mappedBy = "reserva")
    private List<ReservaHabitacion> Habitaciones;

    @ManyToOne
    private CodigoDescuento codigoDescuento;

    public Reserva(LocalDateTime fechaReserva, LocalDate fechaInicio, LocalDate fechaFin, double precioTotal, String estado, byte cantidadPersonas) {
        this.fechaReserva = fechaReserva;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precioTotal = precioTotal;
        this.estado = estado;
        this.cantidadPersonas = cantidadPersonas;

    }
}
