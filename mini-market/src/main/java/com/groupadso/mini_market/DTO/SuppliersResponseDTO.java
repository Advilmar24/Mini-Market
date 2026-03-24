package com.groupadso.mini_market.DTO;


import lombok.Data;

@Data

public class SuppliersResponseDTO {

    private Long idProveedor;
    
    private String name;

    private String nit;

    private String phone;

    private String mail;

    private String address; 

}