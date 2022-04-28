package co.edu.uniquindio.unitravel.repositorios;

import co.edu.uniquindio.unitravel.entidades.Administrador;

import co.edu.uniquindio.unitravel.entidades.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepo extends JpaRepository<Administrador, String> {

    Administrador findByEmail(String correo);

    Administrador findByEmailAndPassword(String correo, String password);

    @Query("select h from Hotel h where h.codigo=:codigo")
    Hotel buscarHotel(Integer hotel);

}
