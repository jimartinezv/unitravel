package co.edu.uniquindio.unitravel.bean;

import co.edu.uniquindio.unitravel.entidades.Hotel;
import co.edu.uniquindio.unitravel.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
@Getter
@Setter
public class InicioBean implements Serializable {

    @Setter @Getter
    private List<Hotel> hotelList;

    @Autowired
    private ClienteServicio clienteServicio;

    @PostConstruct
    public void inicio(){
        hotelList= clienteServicio.listarHoteles();
    }

    public String irDetalleHotel(String codigoHotel){
        System.out.println("Voyy");
        return "detalle_hotel?faces-redirect=true&amp;busqueda="+codigoHotel;
    }

}
