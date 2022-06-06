package co.edu.uniquindio.unitravel.bean;

import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.servicios.ClienteServicio;
import co.edu.uniquindio.unitravel.servicios.ServiciosGenerales;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@ViewScoped
public class ReservaBean implements Serializable {

    @Value("#{param['habitacion']}")
    private String codigoHabitacion;

    @Value("#{param['fechai']}")
    private String fechaI;

    @Value("#{param['fechaf']}")
    private String fechaF;

    @Getter @Setter
    private Vuelo vueloI, vueloR;

    @Getter @Setter
    private List<Date> rango, rangoFechas;

    @Getter @Setter
    private List<Date> fechasOcupadas;

    @Getter @Setter
    private Reserva nuevaReserva;

    @Getter @Setter
    private ReservaSilla reservaSilla;

    @Getter @Setter
    private Ciudad origenIda, destinoIda;

    @Getter @Setter
    private List<Vuelo> vueloList, vueloListR;


    private Hotel hotel;

    @Getter
    private List<Ciudad> ciudadList;

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private ServiciosGenerales serviciosGenerales;


    @PostConstruct
    public void inicio(){
        vueloList=new ArrayList<>();
        vueloListR=new ArrayList<>();
        reservaSilla=new ReservaSilla();
        nuevaReserva= new Reserva();
        ciudadList= serviciosGenerales.listarCiudades();
        System.out.println(fechaI+"..."+ fechaF );
        if(codigoHabitacion!=null && !codigoHabitacion.isEmpty()){
            try {

                hotel= clienteServicio.buscarHotelByHabitacion(Integer.parseInt(codigoHabitacion));


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void buscarVuelos(){

        System.out.println(origenIda.getNombre()+" ida "+ destinoIda.getNombre()+" vuelta");
        try {
            vueloList=new ArrayList<>();
            vueloListR=new ArrayList<>();
            vueloList= clienteServicio.vueloByCiudadandFecha(origenIda.getCodigo(),destinoIda.getCodigo(), LocalDate.parse(fechaI));
            vueloListR= clienteServicio.vueloByCiudadandFecha(destinoIda.getCodigo(), origenIda.getCodigo(), LocalDate.parse(fechaF));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("La cantidad de vuelos es: "+ vueloList.size());
        vueloList.forEach(v-> System.out.println(v.getCodigo()));
    }


}
