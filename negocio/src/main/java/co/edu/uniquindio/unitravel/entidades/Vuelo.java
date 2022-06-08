package co.edu.uniquindio.unitravel.entidades;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Vuelo implements Serializable {



    @Id
    @EqualsAndHashCode.Include
    @ToString.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 25)
    @ToString.Include
    private String codigo;

    @ToString.Include
    @Column(nullable = false, length = 25)
    private String aerolinea;

    @Future
    @ToString.Include
    @Column(nullable = false)
    private LocalDate fecha;

    @ManyToOne
    @ToString.Include
    private Ciudad ciudadOrigen;

    @ManyToOne
    @ToString.Include
    private Ciudad ciudadDestino;

    @OneToMany(mappedBy = "vuelo")
    @LazyCollection(LazyCollectionOption.FALSE)
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
