package co.edu.uniquindio.unitravel.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Hotel implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    private String nombre;

    private String direccion;

    @ElementCollection
    private Map<String, String> telefonos;

    @Column(name = "estrellas")
    private Integer numEstrellas;

    @OneToMany(mappedBy = "hotel") //forma de construir la llave foranea en sql
    private List<Habitacion> habitaciones;

    @ManyToMany(mappedBy = "hotel")
    private List<Caracteristica> caracteristicas;

    @OneToMany(mappedBy = "hotel")
    private List<Foto> fotos;

    @OneToMany (mappedBy = "hotel")
    private List<Comentario> comentarios;

    public Hotel(Integer codigo, String nombre, String direccion, Map<String, String> telefonos, Integer numEstrellas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefonos = telefonos;
        this.numEstrellas = numEstrellas;
    }
}
