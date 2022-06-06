package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Vuelo implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    @Column(nullable = false, length = 25)
    private String aerolinea;

    @Future
    @Column(nullable = false)
    private LocalDate fecha;

    @ManyToOne
    private Ciudad ciudadOrigen;

    @ManyToOne
    private Ciudad ciudadDestino;

    @OneToMany(mappedBy = "vuelo")
    private List<Silla> silla;

    //@OneToMany(mappedBy = "vueloIda")
    //private List<Reserva> reservasIda;


    //@ManyToOne
    //private CodigoDescuento codigoDescuento;

    public Vuelo(String codigo, String aerolinea, LocalDate fecha) {
        this.codigo = codigo;
        this.aerolinea = aerolinea;
        this.fecha=fecha;
    }
}
