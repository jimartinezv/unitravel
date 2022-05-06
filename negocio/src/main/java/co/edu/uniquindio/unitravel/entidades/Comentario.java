package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Comentario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    private String comentario;

    @Column(nullable = false)
    private Integer calificacion;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @ManyToOne
    private Hotel hotel;

    @ManyToOne
    private Cliente cliente;

    public Comentario(Integer codigo, String comentario, Integer calificacion, LocalDateTime fecha) {
        this.codigo = codigo;
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.fecha = LocalDateTime.now();
    }
}
