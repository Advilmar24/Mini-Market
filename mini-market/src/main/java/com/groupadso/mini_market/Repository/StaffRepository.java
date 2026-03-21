package com.groupadso.mini_market.Repository;

public class StaffRepository {

    public static final String INSERT_STAFF = "INSERT INTO personal (idCard, name, charge, hireDate,salary) VALUES (?,?,?,?,?)";

    public static final String GET_STAFFS = "SELECT * FROM personal";

    public static final String GET_STAFF = "SELECT * FROM personal WHERE idEmpleado = ?";

    public static final String UPDATE_STAFF =
    "UPDATE personal SET idCard = ?, name = ?, charge = ?, hireDate = ?, salary = ? WHERE idEmpleado = ?";

    public static final String DELETE_STAFF = "DELETE FROM personal WHERE idEmpleado = ?";
    
    // En esta consulta los lista por Cargo
    public static final String GET_STAFF_BY_CHARGE = "SELECT * FROM personal WHERE charge = ?";

    // En esta consulta por Rango de Fechas
    public static final String GET_STAFF_BY_DATE_RANGE = "SELECt * FROM personal WHERE hireDate BETWEEN ? AND ?";

    // en esta consulta por cargo y rango de fechas
    public static final String GET_STAFF_BY_CHARGE_AND_DATE_RANGE = "SELECT * FROM personal WHERE charge = ? AND hireDate BETWEEN ? AND ?";
}
