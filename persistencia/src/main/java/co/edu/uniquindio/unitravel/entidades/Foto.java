package co.edu.uniquindio.unitravel.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class Foto implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    @ManyToOne
    private Hotel hotel;

    @ManyToOne
    private Hotel habitacion;

    public Foto(String codigo) {
        this.codigo = codigo;
    }
}
