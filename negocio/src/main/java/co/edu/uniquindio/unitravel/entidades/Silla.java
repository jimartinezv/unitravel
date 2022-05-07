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
public class Silla implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false, length = 10)
    private String posicion;

    @Column(nullable = false)
    private Boolean disponible;

    @Positive
    private double precio;


    @ManyToOne
    private Vuelo vuelo;

    @OneToMany(mappedBy = "silla")
    private List<ReservaSilla> reservasSilla;

    public Silla(Integer codigo, String posicion, double precio, Boolean disponible) {
        this.codigo = codigo;
        this.posicion = posicion;
        this.precio = precio;
        this.disponible=true;
    }
}
