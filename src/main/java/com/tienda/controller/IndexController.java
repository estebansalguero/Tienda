package com.tienda.controller;

import com.tienda.dao.ClienteDao;
import com.tienda.domain.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@Slf4j

public class IndexController {
    
    @Autowired
    private ClienteDao clienteDao;
    
    @RequestMapping("/")
    public String inicio(Model model) {
        log.info("Ahora se usa arquitectura MVC");
        
        var mensaje = "Mensaje desde el controlador";
        model.addAttribute("Mensaje", mensaje);
        
        Cliente cliente = new Cliente("Esteban", "Salguero", "jesalguero6v@gmail.com", "83159808");
        model.addAttribute(cliente);
        
        Cliente cliente2 = new Cliente("Barbie", "Salguero", "barbie@gmail.com", "84879561");
        var clientes = Arrays.asList(cliente, cliente2);
        model.addAttribute("clientes", clientes);
        
        var clientesDB = clienteDao.findAll();
        model.addAttribute("clientesDB", clientesDB);
        
        return "index";
    }
    
}
