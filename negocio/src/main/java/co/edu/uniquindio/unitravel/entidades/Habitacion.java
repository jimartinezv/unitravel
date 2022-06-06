package co.edu.uniquindio.unitravel.entidades;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString(onlyExplicitlyIncluded = true)
public class Habitacion implements Serializable {
    @Id
    @ToString.Include
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;


    @ManyToOne
    @JoinColumn(nullable = true)
    private Hotel hotel;

    @Positive
    @ToString.Include
    @Column(nullable = false)
    private byte capacidad;

    @ToString.Include
    @Column(nullable = false)
    private String nombre;
    @Positive
    @ToString.Include
    @Column(nullable = false)
    private double precio;

    @ManyToMany
    private List<Caracteristica> caracteristicas;

    @ManyToMany
    private List<Cama> camas;

    @ElementCollection
    private List<String> fotos;

    @OneToMany(mappedBy = "habitacion")
    private List<ReservaHabitacion> reservaHabitaciones;



    public Habitacion(Integer codigo,String nombre, Hotel hotel, byte capacidad, double precio, List<Caracteristica> caracteristicasHabitacion, List<String> fotos) {
        this.codigo = codigo;
        this.nombre= nombre;
        this.hotel = hotel;
        this.capacidad = capacidad;
        this.precio = precio;
        this.caracteristicas = caracteristicasHabitacion;
        this.fotos = fotos;
    }

    public String getImagenPrincipal(){
        if(fotos!=null){
            if (!fotos.isEmpty())
                return fotos.get(0);
        }
        return "default.png";
    }

    public String formatearDinero(){
        Locale locale=new Locale("es", "CO");
        NumberFormat formato= NumberFormat.getNumberInstance(locale);
        return formato.format(precio);
    }
}
