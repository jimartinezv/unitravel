package co.edu.uniquindio.unitravel.repositorios;


import co.edu.uniquindio.unitravel.entidades.AdministradorHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorHotelRepo extends JpaRepository<AdministradorHotel, String> {
    Optional<AdministradorHotel> findByEmailAndPassword(String correo, String password);


}
