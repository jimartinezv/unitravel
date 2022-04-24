package co.edu.uniquindio.unitravel.repositorios;

import co.edu.uniquindio.unitravel.entidades.Cliente;
import co.edu.uniquindio.unitravel.entidades.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, String> {
    Optional<Cliente> findByPasswordAndEmail(String password, String email);

    Page<Cliente> findAll(Pageable pageable);

    Optional<Cliente> findByEmailAndPassword(String email, String password);

    Optional<Cliente> findByEmail(String email);

    @Query("select r from Cliente c INNER JOIN c.reservas r where c.email=:emailCliente")
    List<Reserva> reservaCliente(String emailCliente);

    @Query("select c, r from Cliente c left join c.reservas r")
    List<Object[]> obtenerReservaClientes();

    @Query("select c from Cliente c join c.telefono t where t=:telefono")
    List<Cliente> obtenerUsuarioTelefono(String telefono);
}
