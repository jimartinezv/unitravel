package co.edu.uniquindio.unitravel.repositorios;

import co.edu.uniquindio.unitravel.dto.ReservaDto;
import co.edu.uniquindio.unitravel.entidades.Habitacion;
import co.edu.uniquindio.unitravel.entidades.Reserva;
import co.edu.uniquindio.unitravel.entidades.ReservaHabitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservaRepo extends JpaRepository<Reserva, Integer> {

    //@Query("select new co.edu.uniquindio.unitravel.dto.ReservaDto(r.cliente.nombre, r.fechaReserva, h.habitacion ) from Reserva r join r.Habitaciones h where h.habitacion.hotel.codigo=:idHotel and r.fechaFin < :fecha")
    //List<ReservaDto> obtenerDto(Integer idHotel, LocalDateTime fecha);


    @Query("select h from Habitacion h")
    Habitacion habitacionOcupada();

    @Query("select h from Habitacion h join h.reservaHabitaciones rh join rh.reserva r where h.codigo=:codigoHab and r.fechaInicio > :fecha and r.fechaFin < :fecha")
    ReservaHabitacion reservaPorFecha(Integer codigoHab, LocalDate fecha);


    @Query("select r from Reserva r join r.cliente c where c.email=:correo")
    List<Reserva> reservasByCliente(String correo);
}
