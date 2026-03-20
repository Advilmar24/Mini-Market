package com.groupadso.mini_market.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;



public class SuppliersRequestDTO {

    @NotBlank(message = "Debes escribir el NOMBRE")
    private String name;

    @NotBlank(message = "Debes escribir el NIT")
    private String nit;

    @NotBlank(message = "Debes escribir el TELEFONO")
    private String phone;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe ser un email válido")
    private String mail;

    @NotBlank(message = "Debes escribir la DIRECCIÓN")
    private String address; 

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }






}
