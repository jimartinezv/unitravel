package co.edu.uniquindio.unitravel.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Ya que el valor del iva es un valor que puede cambiar con el tiempo o dependiendo del producto
 * este será una entidad con unico valor
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImpuestoValorAgregado implements Serializable {

    private double valorIva;

}
