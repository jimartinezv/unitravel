package co.edu.uniquindio.unitravel.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Reserva implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    private LocalDateTime fechaReserva;

    @Future
    private LocalDateTime fechaInicio;

    @Future
    private LocalDateTime fechaFin;

    @Positive
    private double precioTotal;

    @Column(nullable = false, length = 20)
    private String estado;

    @Positive
    private byte cantidadPersonas;

    @OneToMany(mappedBy = "reserva")
    private List<ReservaSilla> reservaSillas;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Vuelo vueloIda;

    @ManyToOne
    private Vuelo vueloRegreso;

    @OneToMany(mappedBy = "reserva")
    private List<ReservaHabitacion> reservaHabitaciones;


    public Reserva(LocalDateTime fechaReserva, LocalDateTime fechaInicio, LocalDateTime fechaFin, double precioTotal, String estado, byte cantidadPersonas) {
        this.fechaReserva = fechaReserva;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precioTotal = precioTotal;
        this.estado = estado;
        this.cantidadPersonas = cantidadPersonas;
    }
}
