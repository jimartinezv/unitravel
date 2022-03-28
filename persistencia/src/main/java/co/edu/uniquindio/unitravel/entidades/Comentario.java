package co.edu.uniquindio.unitravel.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Comentario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    private String comentario;

    private Integer calificacion;

    private LocalDateTime fecha;

    @ManyToOne
    private Hotel hotel;

    @ManyToOne
    private Cliente cliente;

    public Comentario(Integer codigo, String comentario, Integer calificacion, LocalDateTime fecha) {
        this.codigo = codigo;
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.fecha = fecha;
    }
}
