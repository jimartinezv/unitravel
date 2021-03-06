package co.edu.uniquindio.unitravel.entidades;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

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
    @Column(nullable = false)
    private String nombre;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private Map<String, String> telefonos;

    @Column(name = "estrellas", nullable = false)
    @ToString.Include
    private Integer numEstrellas;

    @OneToMany(mappedBy = "hotel") //forma de construir la llave foranea en sql
    @OnDelete(action = OnDeleteAction.CASCADE)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Habitacion> habitaciones;

    @ManyToOne
    @JoinColumn(nullable = false)
    private AdministradorHotel administradorHotel;

    @ManyToMany
    @OnDelete(action = OnDeleteAction.CASCADE)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Caracteristica> caracteristicas;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<String> fotos;

    @OneToMany (mappedBy = "hotel")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Comentario> comentarios;

    @OneToOne
    @JoinColumn(nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Direccion direccion;

    @Lob
    @Column(nullable = false)
    private String descripcion;

    public Hotel(Integer codigo,String descripcion ,String nombre, Direccion direccion, Map<String, String> telefonos, Integer numEstrellas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefonos = telefonos;
        this.numEstrellas = numEstrellas;
        this.descripcion=descripcion;
    }

    public String getImagenPrincipal(){
        if(fotos!=null){
            if (!fotos.isEmpty())
                return fotos.get(0);
        }
        return "default.png";
    }

    public int promedioComentarios(){
        int prom=0;
        if(comentarios.size()==0)
            return 0;
        for (Comentario c:
             comentarios) {
            prom+=c.getCalificacion();
        }
        return prom/comentarios.size();
    }
}
