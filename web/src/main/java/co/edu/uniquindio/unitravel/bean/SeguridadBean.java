package co.edu.uniquindio.unitravel.bean;

import co.edu.uniquindio.unitravel.entidades.Administrador;
import co.edu.uniquindio.unitravel.entidades.AdministradorHotel;
import co.edu.uniquindio.unitravel.entidades.Cliente;
import co.edu.uniquindio.unitravel.entidades.Persona;
import co.edu.uniquindio.unitravel.servicios.ClienteServicio;
import co.edu.uniquindio.unitravel.servicios.ServiciosGenerales;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@Scope("session")
@Component
public class SeguridadBean implements Serializable {

    @Getter @Setter
    private Persona persona;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private Boolean autenticado=false;

    @Getter @Setter
    private int rol=0;

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private ServiciosGenerales serviciosGenerales;
    public String login(){
        try {
            persona=serviciosGenerales.login(email,password);
            autenticado=true;
            if(persona instanceof Cliente){
                rol=1;
                return "/index?faces-redirect=true";
            }else if(persona instanceof Administrador){
                rol=2;
                return "/admin/index?faces-redirect=true";
            }else if(persona instanceof AdministradorHotel){
                rol=3;
                return "/admin_hotel/index?faces-redirect=true";
            }

        } catch (Exception e) {
            FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("login-bean", fm);


        }
        return null;
    }

    public String recuperarContraseña(){
        if(email==null){
            FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "El email no puede ser nulo");
            FacesContext.getCurrentInstance().addMessage("rc", fm);
            return "/index?faces-redirect=true";
        }else {
            try {
                System.out.println("se va a recuperar de email"+email);
                clienteServicio.recuperarContrasena(email);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "/index?faces-redirect=true";
    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        rol=0;
        return "/index?faces-redirect=true";
    }
}
