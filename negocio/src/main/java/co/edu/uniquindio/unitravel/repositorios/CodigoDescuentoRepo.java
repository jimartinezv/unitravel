package co.edu.uniquindio.unitravel.repositorios;

import co.edu.uniquindio.unitravel.entidades.CodigoDescuento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodigoDescuentoRepo extends JpaRepository<CodigoDescuento, Integer> {

    CodigoDescuento getByCodigo(String codigo);
}
