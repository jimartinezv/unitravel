package co.edu.uniquindio.unitravel.bean;

import co.edu.uniquindio.unitravel.entidades.Comentario;
import co.edu.uniquindio.unitravel.entidades.Hotel;
import co.edu.uniquindio.unitravel.servicios.ClienteServicio;
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
public class DetalleHotelBean implements Serializable {

    @Value("#{param['hotel']}")
    private String codigoHotel;

    @Getter @Setter
    private Hotel hotel;

    @Getter @Setter
    private Comentario comentario;

    @Getter @Setter
    private List<Comentario> comentarios;

    @Autowired
    private ClienteServicio clienteServicio;
    @PostConstruct
    public void inicio(){
        System.out.println("aqui ente"+codigoHotel);
        comentario= new Comentario();
        comentarios= new ArrayList<>();
        if(codigoHotel!=null && !codigoHotel.isEmpty()){
            try {
                System.out.println("buscando hoel");
                hotel=clienteServicio.buscarHotelPorCodigo(Integer.parseInt(codigoHotel));
                comentarios= hotel.getComentarios();
                System.out.println(hotel.getNombre());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void comentar(){
        try {
            comentario.setHotel(hotel);
            comentario.setCliente(clienteServicio.obtenerUsuario("1"));
            clienteServicio.comentarHotel(comentario);
            comentarios.add(comentario);
            comentario= new Comentario();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
