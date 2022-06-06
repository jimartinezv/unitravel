package co.edu.uniquindio.unitravel.bean;

import co.edu.uniquindio.unitravel.entidades.Habitacion;
import co.edu.uniquindio.unitravel.entidades.Hotel;
import co.edu.uniquindio.unitravel.entidades.ReservaHabitacion;
import co.edu.uniquindio.unitravel.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@ViewScoped
public class ReservaBean implements Serializable {

    @Value("#{param['habitacion']}")
    private String codigoHabitacion;

    @Getter @Setter
    private List<Date> rango, rangoFechas;

    @Getter @Setter
    private List<Date> fechasOcupadas;

    private Hotel hotel;

    @Autowired
    private ClienteServicio clienteServicio;

    @PostConstruct
    public void inicio(){
        if(codigoHabitacion!=null && !codigoHabitacion.isEmpty()){
            try {

                hotel= clienteServicio.buscarHotelByHabitacion(Integer.parseInt(codigoHabitacion));


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void mostrarFecha(){
        System.out.println(rango.get(0));
        System.out.println(rango.get(rango.size()-1));
    }
}
