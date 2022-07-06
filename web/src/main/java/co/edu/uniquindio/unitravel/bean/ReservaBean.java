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
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@ViewScoped
public class ReservaBean implements Serializable {

    @Value("#{param['habitacion']}")
    private String codigoHabitacion;

    @Value("#{param['reserva']}")
    private String reserva;

    @Value("#{param['personas']}")
    private String personas;

    @Value("#{param['fechai']}")
    private String fechaI;

    @Value("#{param['fechaf']}")
    private String fechaF;

    @Getter @Setter
    private List<Date> rango, rangoFechas;

    @Getter @Setter
    private List<Date> fechasOcupadas;

    @Getter @Setter
    private Reserva nuevaReserva;

    @Getter @Setter
    private ReservaHabitacion reservaHabitacion;

    @Getter @Setter
    private ReservaSilla reservaSilla;

    @Getter @Setter
    private Ciudad origenIda, destinoIda;

    @Getter @Setter
    private Vuelo vueloI, vueloR;

    @Getter @Setter
    private List<Vuelo> vueloList, vueloListR, vp;

    private Hotel hotel;

    @Getter @Setter
    private String codigo;

    @Getter @Setter
    private boolean b, codigoA;

    @Getter
    private List<Ciudad> ciudadList;

    private List<ReservaHabitacion> reservaHabitacionList;

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private ServiciosGenerales serviciosGenerales;

    @Value(value = "#{seguridadBean.persona}")
    private Persona persona;


    @PostConstruct
    public void inicio(){
        if(reserva!=null && !reserva.isEmpty() ){
            try {
                nuevaReserva=clienteServicio.buscarReserva(Integer.parseInt(reserva));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else {

            b=false;
            codigoA=false;
            reservaHabitacion=new ReservaHabitacion();

            reservaHabitacionList= new ArrayList<>();

            vp=clienteServicio.listarVuelos();
            reservaSilla=new ReservaSilla();
            nuevaReserva= new Reserva();
            nuevaReserva.setReservaSillas(new ArrayList<>());
            if(personas!=null && !personas.isEmpty())
                nuevaReserva.setCantidadPersonas((byte) Integer.parseInt(personas));
            ciudadList= serviciosGenerales.listarCiudades();

            if(codigoHabitacion!=null && !codigoHabitacion.isEmpty()){
                try {

                    hotel= clienteServicio.buscarHotelByHabitacion(Integer.parseInt(codigoHabitacion));


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public String asignarSillas(){

        try {
            nuevaReserva.setPrecioTotal(1);
            nuevaReserva.setCliente((Cliente) persona);
            nuevaReserva.setEstado("En proceso");
            nuevaReserva.setCantidadPersonas((byte) Integer.parseInt(personas));
            clienteServicio.guardarReserva(nuevaReserva);
            System.out.println("se creó la reserva");
        } catch (Exception e) {
            try {
                clienteServicio.eliminarReserva(nuevaReserva.getCodigo());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        List<Silla> s=new ArrayList<>();
        System.out.println(nuevaReserva.getCantidadPersonas()+" cantidad");

            try {
                clienteServicio.asignarSillas(vueloI.getSilla(), nuevaReserva);

            } catch (Exception e) {
                e.printStackTrace();
                FacesMessage msj= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error sillas", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("msj",msj);
                try {
                    clienteServicio.eliminarReserva(nuevaReserva.getCodigo());
                } catch (Exception ex) {
                    FacesMessage msj1= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error eliminando reserva", ex.getMessage());
                    FacesContext.getCurrentInstance().addMessage("msj",msj1);
                    ex.printStackTrace();
                }
            }


            try {
                clienteServicio.asignarSillas(vueloR.getSilla(), nuevaReserva);
                FacesMessage msj= new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se han asignado las sillas");
                FacesContext.getCurrentInstance().addMessage("msj",msj);
            } catch (Exception e) {
                e.printStackTrace();
                FacesMessage msj= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error sillas "+ vueloR.getCodigo(), e.getMessage());
                FacesContext.getCurrentInstance().addMessage("msj",msj);
                try {
                    clienteServicio.eliminarReserva(nuevaReserva.getCodigo());
                } catch (Exception ex) {
                    FacesMessage msj1= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error eliminando reserva"+ nuevaReserva.getCodigo(), ex.getMessage());
                    FacesContext.getCurrentInstance().addMessage("msj",msj1);
                    ex.printStackTrace();
                }
            }
            return crearReserva();


    }

    public String crearReserva(){
        System.out.println("Creando reserva");
        try {



            Habitacion h=clienteServicio.buscarHabitacion(Integer.parseInt(codigoHabitacion));
            System.out.println("La habitacion es:" +h.getCodigo());
            reservaHabitacion.setHabitacion(h);
            reservaHabitacion.setFechaInicio(LocalDate.parse(fechaI));
            reservaHabitacion.setFechaFin(LocalDate.parse(fechaF));
            System.out.println(reservaHabitacion.toString()+"la reservaHabitacion");
            reservaHabitacion.setPrecio(h.getPrecio());
            reservaHabitacion.setReserva(nuevaReserva);
            clienteServicio.crearReservaHabitacion(reservaHabitacion);
            reservaHabitacionList.add(reservaHabitacion);
            nuevaReserva.setFechaReserva(LocalDateTime.now());
            nuevaReserva.setCliente((Cliente) persona);
            nuevaReserva.setReservaHabitacions(reservaHabitacionList);

            nuevaReserva.setEstado("NO-PAGADA");
            clienteServicio.crearReserva(nuevaReserva);
            return finalizarReserva(nuevaReserva.getCodigo().toString());
            //clienteServicio.asignarSillas(clienteServicio.buscarSillasByVuelo(vueloI.getCodigo()),nuevaReserva);
            //clienteServicio.asignarSillas(clienteServicio.buscarSillasByVuelo(vueloR.getCodigo()),nuevaReserva);
        } catch (Exception e) {
            FacesMessage msj1= new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error",  e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj",msj1);
            e.printStackTrace();
        }
        return null;

    }

    public String finalizarReserva(String codigo){
        System.out.println("/usuario/detalles_reserva?faces-redirect=true&amp;reserva="+codigo);
        return "/usuario/detalles_reserva?faces-redirect=true&amp;reserva="+codigo;

    }

    public String pagarReserva(){

        try {
            nuevaReserva.setEstado("FINALIZADO");
            clienteServicio.guardarReserva(nuevaReserva);
            return "/usuario/finalizado?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void buscarCodigo(){
        try {
            System.out.println(codigo);
            CodigoDescuento cd= clienteServicio.findCodigo(codigo);
            if(cd==null){
                System.out.println("nullo");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", " El codigo de descuento no existe!");

                PrimeFaces.current().dialog().showMessageDynamic(message);
            }else {
                codigoA=true;
                nuevaReserva.setCodigoDescuento(cd);
                FacesMessage msj1= new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso",  "Codigo de descuento aprobado");
                FacesContext.getCurrentInstance().addMessage("msj",msj1);
                System.out.println("encontro codigo");
            }
        } catch (Exception e) {
            FacesMessage msj1= new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error",  e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj",msj1);
        }
    }

    public void buscarVuelos(){

            b=true;
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
