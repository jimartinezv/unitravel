package co.edu.uniquindio.unitravel.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Vuelo implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    @Column(nullable = false, length = 25)
    private String aerolinea;

    @ManyToOne
    private Ciudad ciudadOrigen;

    @ManyToOne
    private Ciudad ciudadDestino;

    @OneToMany(mappedBy = "vuelo")
    private List<Silla> silla;

    @OneToMany(mappedBy = "vueloIda")
    private List<Reserva> reservasIda;

    @OneToMany(mappedBy = "vueloRegreso")
    private List<Reserva> reservasRegreso;

    public Vuelo(String codigo, String aerolinea) {
        this.codigo = codigo;
        this.aerolinea = aerolinea;
    }
}
