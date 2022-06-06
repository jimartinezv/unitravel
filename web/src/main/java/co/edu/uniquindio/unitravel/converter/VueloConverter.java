package co.edu.uniquindio.unitravel.converter;

import co.edu.uniquindio.unitravel.entidades.Ciudad;
import co.edu.uniquindio.unitravel.entidades.Vuelo;
import co.edu.uniquindio.unitravel.servicios.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

@Component
public class VueloConverter implements Converter<Vuelo>, Serializable {

    @Autowired
    private ClienteServicio clienteServicio;

    @Override
    public Vuelo getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            Vuelo v=clienteServicio.buscarVueloByCodigo(value);

            return v;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Vuelo value) {
        if(value!=null){
            return value.getCodigo().toString();
        }
        return "";
    }
}
