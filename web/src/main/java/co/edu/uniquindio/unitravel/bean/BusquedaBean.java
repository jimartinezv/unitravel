package co.edu.uniquindio.unitravel.bean;

import co.edu.uniquindio.unitravel.entidades.AdministradorHotel;
import co.edu.uniquindio.unitravel.entidades.Hotel;
import co.edu.uniquindio.unitravel.entidades.Persona;
import co.edu.uniquindio.unitravel.servicios.AdministradorHotelServicio;
import co.edu.uniquindio.unitravel.servicios.ClienteServicio;
import jdk.dynalink.linker.LinkerServices;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class BusquedaBean implements Serializable {

    @Getter @Setter
    private String busqueda;

    @Getter @Setter
    private String tipo;

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private AdministradorHotelServicio adminHS;

    @Value("#{param['busqueda']}")
    private String busquedaParam;

    @Value("#{param['tipo']}")
    private String tipoParam;

    @Value(value = "#{seguridadBean.persona}")
    private Persona adminSesion;

    @Getter @Setter
    private List<Hotel> hotels;

    @PostConstruct
    public void inicio(){

        if (tipoParam!=null && tipoParam.equals("admin") && !adminSesion.getEmail().equals(null)){

            hotels=adminHS.buscarAdminHotel(adminSesion.getCedula());
            System.out.println("Aqui se entró"+" admin: "+ adminSesion.getCedula()+ " hoteles: " + hotels.get(0).getNombre());
        }else {
            if (busquedaParam != null && !busquedaParam.isEmpty()) {
                if (tipoParam.equals("nombre")) {
                    hotels = clienteServicio.buscarHotelesPorNombre(busquedaParam);
                } else if (tipoParam.equals("car")) {
                    hotels = clienteServicio.buscarHotelesByCaracteristicas(Integer.parseInt(busquedaParam));
                } else if (tipoParam.equals("ciudad")) {
                    hotels = clienteServicio.buscarHotelesByCiudad(Integer.parseInt(busquedaParam));
                }

            } else {
                hotels = new ArrayList<>();
            }
        }

    }

    public String buscarCar(String car) {

        return "resultado_busqueda.xhtml?tipo=car&busqueda="+car;
    }

    public String buscarByCiudad(Integer ciudad){
        return "resultado_busqueda.xhtml?tipo=ciudad&busqueda="+ciudad;
    }

    public String buscar() {

        return "resultado_busqueda?faces-redirect=true&amp;busqueda="+busqueda+"&amp;tipo=nombre";
    }
}
