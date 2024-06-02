package co.edu.unbosque.ShopAPI.service;

import co.edu.unbosque.ShopAPI.model.dto.CustomerDTO;
import co.edu.unbosque.ShopAPI.model.entity.Customer;
import co.edu.unbosque.ShopAPI.model.persistence.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;

    //Agregar un cliente a la base de datos (POST)
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        return modelMapper.map(customerRepository.
                save(modelMapper.map(customerDTO, Customer.class)),CustomerDTO.class);
    }

    public boolean deleteCustomer(Integer id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


    //Obtener todos los Clientes de la base de datos(GET)
    public Set<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> modelMapper.map(customer,CustomerDTO.class))
                .collect(Collectors.toSet());
    }
    //Obtener un cliente mediante su id (GET)
    public Optional<CustomerDTO> getCustomer(Integer id) {
        return customerRepository.findById(id)
                .map(customer -> modelMapper.map(customer, CustomerDTO.class));
    }

    //Editar un cliente (PUT)
    public CustomerDTO editCustomer (Integer id, CustomerDTO customerDTO) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            modelMapper.map(customerDTO, customer);
            return modelMapper.map(customerRepository.save(customer),CustomerDTO.class);
        } else {
            return null;
        }
    }


}
