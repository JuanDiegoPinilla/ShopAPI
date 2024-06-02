package co.edu.unbosque.ShopAPI.model.dto;

import co.edu.unbosque.ShopAPI.controller.CustomerApi;
import lombok.Data;

@Data
public class CustomerDTO {
    private Integer customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
