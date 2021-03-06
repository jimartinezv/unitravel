package co.edu.uniquindio.unitravel.entidades;

import lombok.*;
import org.aspectj.lang.annotation.control.CodeGenerationHint;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Ya que el valor del iva es un valor que puede cambiar con el tiempo o dependiendo del producto
 * este será una entidad con unico valor
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ImpuestoValorAgregado implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(nullable = false, unique = true)
    private double iva;

}
