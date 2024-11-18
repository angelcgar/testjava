package gm.zona_fit.controlador;

import gm.zona_fit.modelo.Cliente;
import gm.zona_fit.servicio.IClienteServicio;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Named("indexControlador")
@Component
@Data
@ViewScoped
public class IndexControlador {

    @Autowired
    IClienteServicio clienteServicio;
    private List<Cliente> clientes;
    private static final Logger logger =
            LoggerFactory.getLogger(IndexControlador.class);

    @PostConstruct
    public void init() {
        System.out.println("hola mundo");
        cargarDatos();
    }

    public void cargarDatos() {
//        System.out.println("hola mundo");
        this.clientes = this.clienteServicio.listarCliente();
        this.clientes.forEach(cliente -> logger.info(cliente.toString()));
    }

}
