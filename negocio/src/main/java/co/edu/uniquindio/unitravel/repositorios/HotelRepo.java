package co.edu.uniquindio.unitravel.repositorios;

import co.edu.uniquindio.unitravel.entidades.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Integer> {
    @Query("select h from Hotel h where h.numEstrellas=:estrellas")
    List<Hotel> hotelPorEstrellas(Integer estrellas);

    @Query("select h.direccion.ciudad.nombre from Hotel h where h.codigo=:id")
    String obtenerNombreCiudadHotel(Integer id);

    List<Hotel> findAllByNumEstrellas(Integer estrellas);
}
