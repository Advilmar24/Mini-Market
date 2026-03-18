package com.groupadso.mini_market.Repository;

public class SuppliersRepository {

    public static final String INSERT_SUPPLIER = "INSERT INTO proveedores ( name, phone, email,addres) VALUES (?,?,?,?)";

    public static final String GET_SUPPLIERS = "SELECT * FROM proveedores";

    public static final String GET_SUPPLIER = "SELECT * FROM proveedores WHERE idProveedor = ?";

    public static final String DELETE_SUPPLIER = "DELETE FROM proveedores WHERE idProveedor = ?";
    
}
