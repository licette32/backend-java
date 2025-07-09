package com.proyecto.talento.carrito.controller;

import com.proyecto.talento.carrito.model.Cliente;
import com.proyecto.talento.carrito.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Obtener todos los clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerTodosLosClientes() {
        try {
            List<Cliente> clientes = clienteService.obtenerTodosLosClientes();
            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
        try {
            Optional<Cliente> cliente = clienteService.obtenerClientePorId(id);
            return cliente.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Buscar cliente por email
    @GetMapping("/email/{email}")
    public ResponseEntity<Cliente> buscarClientePorEmail(@PathVariable String email) {
        try {
            Optional<Cliente> cliente = clienteService.buscarClientePorEmail(email);
            return cliente.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Buscar cliente por DNI
    @GetMapping("/dni/{dni}")
    public ResponseEntity<Cliente> buscarClientePorDni(@PathVariable String dni) {
        try {
            Optional<Cliente> cliente = clienteService.buscarClientePorDni(dni);
            return cliente.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Verificar si existe un cliente por email
    @GetMapping("/existe/email/{email}")
    public ResponseEntity<Boolean> existeClientePorEmail(@PathVariable String email) {
        try {
            boolean existe = clienteService.existeClientePorEmail(email);
            return ResponseEntity.ok(existe);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Verificar si existe un cliente por DNI
    @GetMapping("/existe/dni/{dni}")
    public ResponseEntity<Boolean> existeClientePorDni(@PathVariable String dni) {
        try {
            boolean existe = clienteService.existeClientePorDni(dni);
            return ResponseEntity.ok(existe);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Buscar clientes por nombre o apellido
    @GetMapping("/buscar/{texto}")
    public ResponseEntity<List<Cliente>> buscarClientesPorNombreOApellido(@PathVariable String texto) {
        try {
            List<Cliente> clientes = clienteService.buscarClientesPorNombreOApellido(texto);
            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Crear un nuevo cliente
    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@Valid @RequestBody Cliente cliente) {
        try {
            Cliente nuevoCliente = clienteService.crearCliente(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Actualizar un cliente existente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, 
                                                   @Valid @RequestBody Cliente cliente) {
        try {
            Cliente clienteActualizado = clienteService.actualizarCliente(id, cliente);
            return ResponseEntity.ok(clienteActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Eliminar un cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        try {
            clienteService.eliminarCliente(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint para validar datos del cliente
    @PostMapping("/validar")
    public ResponseEntity<String> validarCliente(@Valid @RequestBody Cliente cliente) {
        try {
            // Verificar email único
            if (clienteService.existeClientePorEmail(cliente.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                                   .body("Ya existe un cliente con este email");
            }
            
            // Verificar DNI único
            if (clienteService.existeClientePorDni(cliente.getDni())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                                   .body("Ya existe un cliente con este DNI");
            }
            
            return ResponseEntity.ok("Datos válidos");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                               .body("Error al validar datos");
        }
    }
} 

