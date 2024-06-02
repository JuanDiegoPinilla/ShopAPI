package co.edu.unbosque.ShopAPI.controller;

import co.edu.unbosque.ShopAPI.model.dto.CustomerDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequestMapping(path = "/customer")
public interface CustomerApi {

    // OBTENER UN CLIENTE POR ID
    @GetMapping("/getbyid/{id}")
    @Operation(summary = "Obtener un cliente por su identificador",
            description = "Recupera la información detallada de un cliente según su identificador único.")
    @ApiResponse(responseCode = "200", description = "Cliente encontrado exitosamente")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    ResponseEntity<CustomerDTO> getCustomerById(@Parameter(description = "ID del cliente a recuperar", required = true) @PathVariable Integer id);

    // OBTENER TODOS LOS CLIENTES
    @GetMapping("/getallcustomer")
    @Operation(summary = "Obtener todos los clientes",
            description = "Recupera la lista completa de todos los clientes registrados en el sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de clientes recuperada exitosamente")
    ResponseEntity<Set<CustomerDTO>> getAllCustomers();

    // CREAR UN CLIENTE
    @PostMapping("/createcustomer")
    @Operation(summary = "Crear un nuevo cliente",
            description = "Registra un nuevo cliente en el sistema utilizando los datos proporcionados.")
    @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente")
    ResponseEntity<CustomerDTO> createCustomer(@Parameter(description = "Datos del cliente a crear", required = true) @RequestBody CustomerDTO customerDTO);

    // ELIMINAR UN CLIENTE
    @DeleteMapping("/deletecustomer/{id}")
    @Operation(summary = "Eliminar un cliente",
            description = "Elimina un cliente del sistema según su identificador único.")
    @ApiResponse(responseCode = "204", description = "Cliente eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    ResponseEntity<Void> deleteCustomer(@Parameter(description = "ID del cliente a eliminar", required = true) @PathVariable Integer id);

    // EDITAR UN CLIENTE
    @PutMapping("/editcustomer/{id}")
    @Operation(summary = "Editar información de un cliente",
            description = "Actualiza la información de un cliente existente en el sistema según su identificador único.")
    @ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    ResponseEntity<CustomerDTO> editCustomer(@Parameter(description = "ID del cliente a editar", required = true) @PathVariable Integer id,
                                             @Parameter(description = "Nuevos datos del cliente", required = true) @RequestBody CustomerDTO customerDTO);
}

