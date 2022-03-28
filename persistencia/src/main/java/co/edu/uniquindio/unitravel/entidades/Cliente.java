package co.edu.uniquindio.unitravel.entidades;


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
public class Cliente extends Persona implements Serializable {
    @ElementCollection //Crear una entidad de muchos a muchos
    private Map<String, String> telefono;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genero genero;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Ciudad ciudad;

    @OneToMany (mappedBy = "cliente")
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "cliente")
    private List<Reserva> reservas;

    public Cliente(String cedula, String nombre, String apellidos, String email, Map<String, String> telefono, Genero genero, Ciudad ciudad) {
        super(cedula, nombre, apellidos, email);
        this.telefono = telefono;
        this.genero = genero;
        this.ciudad = ciudad;
    }
}
