package com.groupadso.mini_market.Services;



import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.groupadso.mini_market.Constants.MessageConstanst;
import com.groupadso.mini_market.DTO.MessageResponseDTO;
import com.groupadso.mini_market.DTO.StaffRequestDTO;
import com.groupadso.mini_market.DTO.StaffResponseDTO;
import com.groupadso.mini_market.Repository.StaffRepository;

@Service
public class StaffService {

    private JdbcTemplate jdbctemplate;

    public StaffService(JdbcTemplate jdbcTemplate){
        this.jdbctemplate = jdbcTemplate;
    }

    private final RowMapper<StaffResponseDTO> staffMapper = (rs,rowNum)  ->{
        
        StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
        staffResponseDTO.setId(rs.getLong("id"));
        staffResponseDTO.setIdCard(rs.getString("idCard"));
        staffResponseDTO.setName(rs.getString("name"));
        staffResponseDTO.setCharge(rs.getString("charge"));

        // Como LocalDate es mas moderno por que esta fue introducida en JAVA 8, esta es mas clara y segura por que no mescla la fecha con la hora
        //JDBC espera un Java,sql.Date, porque JDBC fue creado antes que existiera LocalDate
        //Al leer: java.sql.Date -> LocalDate con .toLocalDate().
        staffResponseDTO.setHireDate(rs.getDate("hireDate").toLocalDate());
        staffResponseDTO.setSalary(rs.getDouble("salary"));
        return staffResponseDTO;
    };


    public StaffResponseDTO createStaff(StaffRequestDTO staffRequestDTO ){
        
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbctemplate.update(conecction ->{

            PreparedStatement preparedStetament= conecction.prepareStatement(
                StaffRepository.INSERT_STAFF,
                Statement.RETURN_GENERATED_KEYS);

                preparedStetament.setString(1, staffRequestDTO.getIdCard());
                preparedStetament.setString(2, staffRequestDTO.getName());
                preparedStetament.setString(3, staffRequestDTO.getCharge());

                //Al guardar: LocalDate -> java.sql.Date con valueOf().
                //java.sql.Date.valueOf(LocalDate) es el puente oficial entre ambas APIs
                preparedStetament.setDate(4, java.sql.Date.valueOf(staffRequestDTO.getHireDate()));
                preparedStetament.setDouble(5, staffRequestDTO.getSalary());

                return preparedStetament;
        }, keyHolder);

        StaffResponseDTO response = new StaffResponseDTO();
        response.setId(keyHolder.getKey().longValue());
        response.setIdCard(staffRequestDTO.getIdCard());
        response.setName(staffRequestDTO.getName());
        response.setCharge(staffRequestDTO.getCharge());
        response.setHireDate(staffRequestDTO.getHireDate());
        response.setSalary(staffRequestDTO.getSalary());

        return response;
    }

    public List<StaffResponseDTO> getStaff(){
        return jdbctemplate.query(StaffRepository.GET_STAFFS, staffMapper);
    }

    public StaffResponseDTO getStaff(Long id ){
        return jdbctemplate.queryForObject(StaffRepository.GET_STAFF, staffMapper, id);
    }

    public MessageResponseDTO deleteStaff(long id){
        jdbctemplate.update(StaffRepository.DELETE_STAFF, id);

        MessageResponseDTO response = new MessageResponseDTO();
        response.setMessage(MessageConstanst.MESSAGE_RESPONSE_DELETE_USER);

        return response;
    }


    
}
