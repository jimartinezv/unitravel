package co.edu.uniquindio.unitravel.bean;

import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.servicios.ClienteServicio;
import co.edu.uniquindio.unitravel.servicios.ServiciosGenerales;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@ViewScoped
public class DetalleHotelBean implements Serializable {

    @Value("#{param['hotel']}")
    private String codigoHotel;

    @Getter @Setter
    private Hotel hotel;

    @Getter @Setter
    private Comentario comentario;

    @Getter @Setter
    private List<Comentario> comentarios;

    @Value(value = "#{seguridadBean.persona}")
    private Persona persona;

    @Getter @Setter
    private boolean busquedaHabitaciones;

    @Getter @Setter
    private List<LocalDate> rango;

    @Getter @Setter
    private byte capacidad;

    @Autowired
    private ServiciosGenerales serviciosGenerales;

    @Autowired
    private ClienteServicio clienteServicio;

    @PostConstruct
    public void inicio(){

        comentario= new Comentario();
        comentarios= new ArrayList<>();
        if(codigoHotel!=null && !codigoHotel.isEmpty()){
            try {

                hotel= clienteServicio.buscarHotelPorCodigo(Integer.parseInt(codigoHotel));

                comentarios= hotel.getComentarios();
                System.out.println(hotel.getNombre());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String irDetalleReserva(String codigoHotel) {
        System.out.println(capacidad);
        try {
            Habitacion h=clienteServicio.buscarHabitacion(Integer.parseInt(codigoHotel));
            if(capacidad>h.getCapacidad()){
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "La habitacion no cuenta capacidad seleccionada");

                PrimeFaces.current().dialog().showMessageDynamic(message);

                return null;
            }
            System.out.println(h.getNombre()+ rango.get(0)+ rango.get(rango.size()-1)+"esto fue lo que el encontro");
            if (clienteServicio.habitacionDisponible(h, rango.get(0), rango.get(rango.size()-1))) {
                return "/usuario/reserva?faces-redirect=true&amp;habitacion=" + codigoHotel+"&amp;fechai="+ rango.get(0)+"&amp;fechaf="+ rango.get(rango.size()-1)+ "&amp;personas="+capacidad ;
            }else{
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", " La habitaci??n no est?? disponible");

                PrimeFaces.current().dialog().showMessageDynamic(message);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void actualiza(byte cap){
        this.capacidad=cap;
    }

    public String buscarHabitaciones(String codigo){
        return "reserva?faces-redirect=true&amp;habitacion="+codigo;
    }

    public void comentar(){
        try {
            comentario.setHotel(hotel);
            comentario.setCliente((Cliente) persona);
            clienteServicio.comentarHotel(comentario);
            comentarios.add(comentario);
            comentario= new Comentario();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
