package co.edu.uniquindio.unitravel.bean;

import co.edu.uniquindio.unitravel.entidades.Ciudad;
import co.edu.uniquindio.unitravel.entidades.Departamento;
import co.edu.uniquindio.unitravel.servicios.AdministradorServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
public class CiudadDepartamentoBean implements Serializable {

    @Autowired
    private AdministradorServicio administradorServicio;

    @Getter @Setter
    private Departamento d;
    @Getter @Setter
    private Ciudad c;

    @PostConstruct
    public void inicio(){
         d= new Departamento();
         c= new Ciudad();
    }

    public void guardarCiudad(){
        try{
            administradorServicio.crearCiudad(c);
            FacesMessage msj= new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso", "la ciudad ha sido creada");
            FacesContext.getCurrentInstance().addMessage(null,msj);
        }catch (Exception e){
            FacesMessage msj= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null,msj);
        }
    }
    public void guardarDepartamento(){
        try{
            administradorServicio.crearDepartamento(d);
            d=new Departamento();
            FacesMessage msj= new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso", "El departamento ha sido creado");
            FacesContext.getCurrentInstance().addMessage(null,msj);
        }catch (Exception e){
            FacesMessage msj= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null,msj);
        }
    }
}
