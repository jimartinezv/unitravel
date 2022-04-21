package co.edu.uniquindio.unitravel.repositorio;

import co.edu.uniquindio.unitravel.dto.ReservaDto;
import co.edu.uniquindio.unitravel.entidades.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservaRepo extends JpaRepository<Reserva, Integer> {

    @Query("select new co.edu.uniquindio.unitravel.dto.ReservaDto(r.cliente.nombre, r.fechaReserva, h.habitacion ) from Reserva r join r.Habitaciones h where h.habitacion.hotel.codigo=:idHotel and r.fechaFin < :fecha")
    List<ReservaDto> obtenerDto(Integer idHotel, LocalDateTime fecha);
}
