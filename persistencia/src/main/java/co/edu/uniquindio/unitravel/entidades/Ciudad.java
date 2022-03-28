package co.edu.uniquindio.unitravel.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity //@Entity nos permite informarle al proyecto que la clase es una entidad y por lo tanto debe crearse su tabla en ls bd
@NoArgsConstructor //Crea constructor vacio
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // Equals and hascode permite hacer comparación entre objetos determinando su clave única o valor comparativo
public class Ciudad implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include // Se determina como valor comparativo
    private Integer codigo;

    private String nombre;

    @OneToMany(mappedBy = "ciudad")
    private List<Cliente> Cliente;

    @OneToMany(mappedBy = "ciudadOrigen")
    private List<Vuelo> vuelosOrigen;

    @OneToMany(mappedBy = "ciudadDestino")
    private List<Vuelo> vuelosDestino;

    public Ciudad(String nombre) {
        this.nombre = nombre;
    }





}
