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
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ReservaSilla implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @Column(length = 10)
    private String codigo;

    @Positive
    private double precio;

    @ManyToOne
    private Reserva reserva;

    @ManyToOne
    private Silla silla;


    public ReservaSilla(String codigo, double precio) {
        this.codigo = codigo;
        this.precio = precio;
    }
}
