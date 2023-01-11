package net.javaguides.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInput {
    private String fullName;
    private String fullAddress;
    private String contactNumber;
    private String alternateContactNumber;
    private List<OrderProductQuantity> orderProductQuantityList;

}
