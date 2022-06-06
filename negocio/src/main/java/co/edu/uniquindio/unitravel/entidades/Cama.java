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
public class Cama implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;


    @ManyToMany(mappedBy = "camas")
    private List<Habitacion> habitacion;


    private String tipo;

    public Cama(String tipo) {
        this.tipo = tipo;
    }
}
