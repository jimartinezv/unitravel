package co.edu.uniquindio.unitravel.bean;

import co.edu.uniquindio.unitravel.entidades.Habitacion;
import co.edu.uniquindio.unitravel.entidades.Hotel;
import co.edu.uniquindio.unitravel.servicios.AdministradorHotelServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
public class HabitacionBean implements Serializable {

    @Autowired
    private AdministradorHotelServicio administradorHotelServicio;

    @Getter
    private Hotel hotel;

    @Getter @Setter
    private Habitacion habitacion;

    @PostConstruct
    public void inicio(){
        habitacion=new Habitacion();
    }

    public void crearHabitacion(){
        try {
            administradorHotelServicio.crearHabitacion(habitacion);
        }catch (Exception e){

        }
    }
}
