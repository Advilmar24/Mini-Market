package com.groupadso.mini_market.Repository;

public class SuppliersRepository {

    public static final String INSERT_SUPPLIER = "INSERT INTO proveedores ( name, nit, phone, mail,address) VALUES (?,?,?,?,?)";

    public static final String GET_SUPPLIERS = "SELECT * FROM proveedores";

    public static final String GET_SUPPLIER = "SELECT * FROM proveedores WHERE idProveedor = ?";

    public static final String DELETE_SUPPLIER = "DELETE FROM proveedores WHERE idProveedor = ?";

    public static final String INSERT_ENTRADA = "INSERT INTO entradas_proveedor (idProduct, idProveedor, cantidad) VALUES (?,?,?)";
    
}
