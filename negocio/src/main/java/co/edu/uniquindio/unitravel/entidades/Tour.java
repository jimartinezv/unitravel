package co.edu.uniquindio.unitravel.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

/**
 * Los toures son compras extras que el usuario puede hacer en su reserva
 * estos solamente apareceran dependiendo la ubicación destino de la reserva
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tour implements Serializable {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String descripcion;

    private List<Foto> fotos;
}
