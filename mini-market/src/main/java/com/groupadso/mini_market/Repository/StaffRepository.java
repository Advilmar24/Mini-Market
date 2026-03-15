package com.groupadso.mini_market.Repository;

public class StaffRepository {

    public static final String INSERT_STAFF = "INSERT INTO personal (idCard, name, charge, hireDate,salary) VALUES (?,?,?,?,?)";

    public static final String GET_STAFFS = "SELECT * FROM personal";

    public static final String GET_STAFF = "SELECT * FROM personal WHERE idEmpleado = ?";

    public static final String DELETE_STAFF = "DELETE * FROM personal WHERE idEmpleado = ?";
    
}
