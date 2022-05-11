package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Los toures son compras extras que el usuario puede hacer en su reserva
 * estos solamente apareceran dependiendo la ubicación destino de la reserva
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Tour implements Serializable {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private double precio;

    private List<Foto> fotos;

    public Tour(Integer id, String descripcion, double precio) {
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fotos= new ArrayList<>();
    }
}
