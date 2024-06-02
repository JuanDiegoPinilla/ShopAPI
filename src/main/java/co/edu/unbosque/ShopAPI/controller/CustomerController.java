package co.edu.unbosque.ShopAPI.controller;

import co.edu.unbosque.ShopAPI.model.dto.CustomerDTO;
import co.edu.unbosque.ShopAPI.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController implements CustomerApi{

    @Autowired
    private CustomerService customerService;

    @Override
    public ResponseEntity<Set<CustomerDTO>> getAllCustomers() {
        Set<CustomerDTO> customers = customerService.getAllCustomers();
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(customers, HttpStatus.OK);
        }
    }

   @Override
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Integer id) {
        return customerService.getCustomer(id)
                .map(customerDTO -> new ResponseEntity<>(customerDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO createdCustomer = customerService.createCustomer(customerDTO);
        if (createdCustomer == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
        if (customerService.deleteCustomer(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<CustomerDTO> editCustomer(@PathVariable Integer id, @RequestBody CustomerDTO customerDTO) {
        CustomerDTO editedCustomer = customerService.editCustomer(id, customerDTO);
        if (editedCustomer == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(editedCustomer, HttpStatus.OK);
        }
    }
}