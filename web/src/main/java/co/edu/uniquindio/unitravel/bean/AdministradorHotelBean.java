package co.edu.uniquindio.unitravel.bean;

import co.edu.uniquindio.unitravel.entidades.AdministradorHotel;
import co.edu.uniquindio.unitravel.repositorios.AdministradorHotelRepo;
import co.edu.uniquindio.unitravel.servicios.AdministradorHotelServicio;
import co.edu.uniquindio.unitravel.servicios.AdministradorServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

@Component
@ViewScoped
public class AdministradorHotelBean {

    @Autowired
    private AdministradorHotelServicio administradorHotelServicio;

    @Autowired
    private AdministradorServicio administradorServicio;

    @Getter @Setter
    private AdministradorHotel administradorHotel;

    @PostConstruct
    public void inicio(){
        administradorHotel= new AdministradorHotel();
    }

    public void crearAdminHotel(){
        try {
            administradorServicio.crearAdministradorHotel(administradorHotel);
            administradorHotel=new AdministradorHotel();
            FacesMessage msj= new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso", "El administrador ha sido creado");
            FacesContext.getCurrentInstance().addMessage(null,msj);
        }catch (Exception e){
            FacesMessage msj= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null,msj);
        }

    }
}
