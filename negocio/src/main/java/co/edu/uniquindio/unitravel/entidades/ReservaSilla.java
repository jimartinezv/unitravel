package co.edu.uniquindio.unitravel.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ReservaSilla implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Positive
    private double precio;

    @ManyToOne
    private Reserva reserva;

    @ManyToOne
    private Silla silla;

    private LocalDate fecha;

    public ReservaSilla(Integer codigo, double precio) {
        this.codigo = codigo;
        this.precio = precio;
    }
}
