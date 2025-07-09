// Declaramos el paquete donde se encuentra esta clase de servicio
package com.proyecto.talento.carrito.service;

// Importamos el modelo Cliente para poder trabajar con él
import com.proyecto.talento.carrito.model.Cliente;
// Importamos el repositorio de clientes para acceder a los datos
import com.proyecto.talento.carrito.repository.ClienteRepository;
// Importamos la anotación @Autowired para inyección de dependencias
import org.springframework.beans.factory.annotation.Autowired;
// Importamos @Service para marcar esta clase como un servicio de Spring
import org.springframework.stereotype.Service;

// Importamos List para manejar listas de clientes
import java.util.List;
// Importamos Optional para manejar valores que pueden estar presentes o no
import java.util.Optional;

// @Service marca esta clase como un servicio de Spring (capa de lógica de negocio)
@Service
// Clase que contiene la lógica de negocio para los clientes
public class ClienteService {

    // @Autowired inyecta automáticamente el repositorio de clientes
    @Autowired
    // Repositorio que usamos para acceder a los datos de clientes en la base de datos
    private ClienteRepository clienteRepository;

    // Método para obtener todos los clientes registrados en la base de datos
    public List<Cliente> obtenerTodosLosClientes() {
        // Llama al método findAll() del repositorio que devuelve todos los clientes
        return clienteRepository.findAll();
    }

    // Método para buscar un cliente específico por su ID
    public Optional<Cliente> obtenerClientePorId(Long id) {
        // findById devuelve un Optional porque el cliente puede no existir
        // Optional nos ayuda a manejar casos donde no se encuentra el cliente
        return clienteRepository.findById(id);
    }

    // Método para buscar un cliente por su dirección de email
    public Optional<Cliente> buscarClientePorEmail(String email) {
        // Busca en la base de datos un cliente con el email especificado
        // Como el email es único, puede devolver máximo un cliente
        return clienteRepository.findByEmail(email);
    }

    // Método para buscar un cliente por su número de DNI
    public Optional<Cliente> buscarClientePorDni(String dni) {
        // Busca en la base de datos un cliente con el DNI especificado
        // Como el DNI es único, puede devolver máximo un cliente
        return clienteRepository.findByDni(dni);
    }

    // Método para verificar si ya existe un cliente con un email específico
    public boolean existeClientePorEmail(String email) {
        // Devuelve true si existe un cliente con ese email, false si no existe
        // Útil para validaciones antes de crear un nuevo cliente
        return clienteRepository.existsByEmail(email);
    }

    // Método para verificar si ya existe un cliente con un DNI específico
    public boolean existeClientePorDni(String dni) {
        // Devuelve true si existe un cliente con ese DNI, false si no existe
        // Útil para validaciones antes de crear un nuevo cliente
        return clienteRepository.existsByDni(dni);
    }

    // Método para crear un nuevo cliente con validaciones de unicidad
    public Cliente crearCliente(Cliente cliente) {
        // Verificar que no exista un cliente con el mismo email
        // Esto es importante porque el email debe ser único en el sistema
        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            // Si ya existe un cliente con ese email, lanzamos una excepción
            throw new RuntimeException("Ya existe un cliente con el email: " + cliente.getEmail());
        }
        
        // Verificar que no exista un cliente con el mismo DNI
        // Esto es importante porque el DNI debe ser único en el sistema
        if (clienteRepository.existsByDni(cliente.getDni())) {
            // Si ya existe un cliente con ese DNI, lanzamos una excepción
            throw new RuntimeException("Ya existe un cliente con el DNI: " + cliente.getDni());
        }
        
        // Si todas las validaciones pasan, guardamos el cliente en la base de datos
        return clienteRepository.save(cliente);
    }

    // Método para actualizar un cliente existente con validaciones de unicidad
    public Cliente actualizarCliente(Long id, Cliente clienteActualizado) {
        // Primero buscamos el cliente existente por ID
        return clienteRepository.findById(id)
                // Si encontramos el cliente, lo mapeamos (actualizamos)
                .map(cliente -> {
                    // Verificar que el email no esté siendo usado por otro cliente
                    // Solo validamos si el email cambió (es diferente al actual)
                    if (!cliente.getEmail().equals(clienteActualizado.getEmail()) && 
                        clienteRepository.existsByEmail(clienteActualizado.getEmail())) {
                        // Si otro cliente ya tiene ese email, lanzamos excepción
                        throw new RuntimeException("Ya existe un cliente con el email: " + clienteActualizado.getEmail());
                    }
                    
                    // Verificar que el DNI no esté siendo usado por otro cliente
                    // Solo validamos si el DNI cambió (es diferente al actual)
                    if (!cliente.getDni().equals(clienteActualizado.getDni()) && 
                        clienteRepository.existsByDni(clienteActualizado.getDni())) {
                        // Si otro cliente ya tiene ese DNI, lanzamos excepción
                        throw new RuntimeException("Ya existe un cliente con el DNI: " + clienteActualizado.getDni());
                    }
                    
                    // Actualizamos todos los campos del cliente con los nuevos valores
                    cliente.setNombre(clienteActualizado.getNombre());
                    cliente.setApellido(clienteActualizado.getApellido());
                    cliente.setEmail(clienteActualizado.getEmail());
                    cliente.setDni(clienteActualizado.getDni());
                    cliente.setTelefono(clienteActualizado.getTelefono());
                    cliente.setDireccion(clienteActualizado.getDireccion());
                    
                    // Guardamos el cliente actualizado en la base de datos
                    return clienteRepository.save(cliente);
                })
                // Si no encontramos el cliente, lanzamos una excepción
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
    }

    // Método para eliminar un cliente por ID de forma física
    public void eliminarCliente(Long id) {
        // Verificamos si el cliente existe antes de intentar eliminarlo
        if (!clienteRepository.existsById(id)) {
            // Si no existe, lanzamos una excepción
            throw new RuntimeException("Cliente no encontrado con ID: " + id);
        }
        // Eliminamos el cliente completamente de la base de datos
        // CUIDADO: esta acción no se puede deshacer
        clienteRepository.deleteById(id);
    }

    // Método para buscar clientes por nombre o apellido usando texto parcial
    public List<Cliente> buscarClientesPorNombreOApellido(String texto) {
        // Llama al método personalizado del repositorio que busca en ambos campos
        // La búsqueda es insensible a mayúsculas y minúsculas
        return clienteRepository.buscarPorNombreOApellido(texto);
    }

    // Método para obtener un cliente por email (lanza excepción si no existe)
    public Cliente obtenerOCrearClientePorEmail(String email) {
        // Busca el cliente por email
        return clienteRepository.findByEmail(email)
                // Si no lo encuentra, lanza una excepción
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con email: " + email));
    }
} 

