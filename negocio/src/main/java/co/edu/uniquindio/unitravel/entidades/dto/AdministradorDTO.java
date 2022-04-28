package co.edu.uniquindio.unitravel.entidades.dto;

import co.edu.uniquindio.unitravel.entidades.Administrador;
import co.edu.uniquindio.unitravel.entidades.AdministradorHotel;
import co.edu.uniquindio.unitravel.entidades.Vuelo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AdministradorDTO {

    private Vuelo vuelo;

    private AdministradorHotel administradorHotel;

    private Administrador administrador;
}
