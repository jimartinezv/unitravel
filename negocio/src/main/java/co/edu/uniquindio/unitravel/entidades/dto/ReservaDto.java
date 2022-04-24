package co.edu.uniquindio.unitravel.dto;

import co.edu.uniquindio.unitravel.entidades.Habitacion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ReservaDto {

    private String nombreUsuario;

    private LocalDateTime fechaReserva;

    private Habitacion habitacion;
}
