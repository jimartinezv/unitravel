package co.edu.uniquindio.unitravel.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ReservaHabitacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    @Positive
    private Double precio;

    @ManyToOne
    private Reserva reserva;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Habitacion habitacion;

    @Future
    private LocalDate fechaInicio;

    @Future
    private LocalDate fechaFin;





    @ManyToOne
    private CodigoDescuento codigoDescuento;
}
