package co.edu.uniquindio.unitravel.servicios;


import co.edu.uniquindio.unitravel.entidades.Cliente;
import co.edu.uniquindio.unitravel.repositorios.ClienteRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicioImpl implements ClienteServicio{

    private ClienteRepo usuarioRepo;

    public ClienteServicioImpl (ClienteRepo usuarioRepo){
        this.usuarioRepo=usuarioRepo;
    }



    @Override
    public Cliente registrarUsuario(Cliente u) throws Exception {
        Cliente buscado= obtenerUsuario(u.getCedula());
        if (buscado!=null){
            throw new Exception("EL codigo del usuario ya está registrado");
        }
        Cliente buscadoEmail= buscarClienteEmail(u.getEmail());
        if(buscadoEmail!=null){
            throw new Exception("El correo del usuario ya está registrado");
        }
        System.out.println("Aqui pase y paila");
        return usuarioRepo.save(u);

    }

    public Cliente buscarClienteEmail(String email){
        return usuarioRepo.findByEmail(email).orElse(null);

    }

    @Override
    public Cliente actualizarUsuario(Cliente u) throws Exception {
        Cliente buscado= obtenerUsuario(u.getCedula());
        if(buscado==null){
            throw new Exception("El usuario no existe en la base de datos");
        }
        return usuarioRepo.save(u);
    }

    @Override
    public Cliente obtenerUsuario(String cedula) {
        return usuarioRepo.findById(cedula).orElse(null);
    }

    @Override
    public void eliminarUsuario(String cedula) throws Exception {
        Cliente cliente = obtenerUsuario(cedula);
        if(cliente==null){
            throw new Exception("El usuario no existe");
        }
        System.out.println("Usuario eliminado");
        usuarioRepo.delete(cliente);
    }

    @Override
    public List<Cliente> listarUsuarios() {
        return usuarioRepo.findAll();
    }

    @Override
    public List<Cliente> listarClientesReserva() {
        return usuarioRepo.clientesReservas();
    }

    @Override
    public Cliente validarLogin(String email, String password) throws Exception {
        Optional<Cliente> cliente= usuarioRepo.findByEmailAndPassword(email, password);
        if (cliente.isEmpty()){
            throw new Exception("Los datos de autenticación son incorrectos");
        }
        return cliente.get();
    }
}
