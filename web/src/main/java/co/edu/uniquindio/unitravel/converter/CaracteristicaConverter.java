package co.edu.uniquindio.unitravel.converter;

import co.edu.uniquindio.unitravel.entidades.Caracteristica;
import co.edu.uniquindio.unitravel.entidades.Ciudad;
import co.edu.uniquindio.unitravel.servicios.ServiciosGenerales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

@Component
public class CaracteristicaConverter implements Converter<Caracteristica>, Serializable {

    @Autowired
    private ServiciosGenerales serviciosGenerales;

    @Override
    public Caracteristica getAsObject(FacesContext context, UIComponent component, String value) {
        try {

            Caracteristica c=serviciosGenerales.buscarCaracteristica(Integer.parseInt(value));
            return c;
        } catch (Exception e) {
            System.out.println("el error esta aqui");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Caracteristica value) {
        if(value!=null){
            return value.getCodigo().toString();
        }
        return "";
    }
}
