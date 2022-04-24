package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Hotel implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @ToString.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @ToString.Include
    private String nombre;

    @ElementCollection
    private Map<String, String> telefonos;

    @Column(name = "estrellas")
    @ToString.Include
    private Integer numEstrellas;

    @OneToMany(mappedBy = "hotel") //forma de construir la llave foranea en sql
    private List<Habitacion> habitaciones;

    @ManyToMany(mappedBy = "hotel")
    private List<Caracteristica> caracteristicas;

    @OneToMany(mappedBy = "hotel")
    private List<Foto> fotos;

    @OneToMany (mappedBy = "hotel")
    private List<Comentario> comentarios;

    @OneToOne
    @JoinColumn(nullable = false)
    private Direccion direccion;

    public Hotel(Integer codigo, String nombre, Direccion direccion, Map<String, String> telefonos, Integer numEstrellas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefonos = telefonos;
        this.numEstrellas = numEstrellas;
    }
}
