package co.edu.uniquindio.unitravel.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class CodigoDescuento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    private String codigo;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaVencimiento;

    private double descuento;

    private String descripcion;

    private Integer cantidadDisponible;

    private Boolean estado;

    @OneToMany(mappedBy = "codigoDescuento")
    private List<Reserva> reservas;

    public CodigoDescuento(String codigo, LocalDateTime fechaCreacion, LocalDateTime fechaVencimiento, double descuento, String descripcion, Integer cantidadDisponible, Boolean estado) {
        this.codigo = codigo;
        this.fechaCreacion = fechaCreacion;
        this.fechaVencimiento = fechaVencimiento;
        this.descuento = descuento;
        this.descripcion = descripcion;
        this.cantidadDisponible = cantidadDisponible;
        this.estado = estado;
    }
}
