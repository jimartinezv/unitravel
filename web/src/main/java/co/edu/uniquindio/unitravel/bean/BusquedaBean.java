package co.edu.uniquindio.unitravel.bean;

import co.edu.uniquindio.unitravel.entidades.Hotel;
import co.edu.uniquindio.unitravel.servicios.ClienteServicio;
import jdk.dynalink.linker.LinkerServices;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class BusquedaBean implements Serializable {

    @Getter @Setter
    private String busqueda;

    @Autowired
    private ClienteServicio clienteServicio;

    @Value("#{param['busqueda']}")
    private String busquedaParam;

    @Getter @Setter
    private List<Hotel> hotels;

    @PostConstruct
    public void inicio(){
        if(busquedaParam!=null && !busquedaParam.isEmpty()){
            hotels=clienteServicio.buscarHotelesPorNombre(busquedaParam);
        }else{
            hotels=new ArrayList<>();
        }

    }

    public String buscar() {

        return "resultado_busqueda?faces-redirect=true&amp;busqueda="+busqueda;
    }
}
