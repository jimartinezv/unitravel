package co.edu.uniquindio.unitravel.repositorios;

import co.edu.uniquindio.unitravel.entidades.Habitacion;
import co.edu.uniquindio.unitravel.entidades.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitacionRepo extends JpaRepository<Habitacion, String> {

    List<Habitacion> findByHotel(Hotel hotel);

    //@Query("select h from Habitacion h join h.hotel ho where ho.codigo=:codigo ")
    //List<Habitacion>
}
