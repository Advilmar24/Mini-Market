package com.groupadso.mini_market.Service;

import com.groupadso.mini_market.DTO.MessageResponseDTO;
import com.groupadso.mini_market.DTO.SuppliersResponseDTO;
import com.groupadso.mini_market.DTO.SuppliersRequestDTO;
import com.groupadso.mini_market.Repository.SuppliersRepository;
import com.groupadso.mini_market.Constants.MessageConstanst;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.groupadso.mini_market.DTO.MessageResponseDTO;
import com.groupadso.mini_market.DTO.SuppliersResponseDTO;

@Service
public class SuppliersService {
    private JdbcTemplate jdbctemplate;

    public SuppliersService(JdbcTemplate jdbcTemplate){
        this.jdbctemplate = jdbcTemplate;
    }

    private final RowMapper<SuppliersResponseDTO> suppliersMapper = (rs,rowNum)  ->{
        
        SuppliersResponseDTO suppliersResponseDTO = new SuppliersResponseDTO();
        suppliersResponseDTO.setId(rs.getLong("idProveedor"));
        suppliersResponseDTO.setName(rs.getString("name"));
        suppliersResponseDTO.setINT(rs.getString("INT"));
        suppliersResponseDTO.setPhone(rs.getString("phone"));
        suppliersResponseDTO.setEmail(rs.getString("email"));
        // Corrijo el nombre de la columna a 'address'
        suppliersResponseDTO.setAddress(rs.getString("address"));
        return suppliersResponseDTO;
    };


    public SuppliersResponseDTO createSupplier(SuppliersRequestDTO suppliersRequestDTO ){
        
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbctemplate.update(conecction ->{
            PreparedStatement preparedStetament= conecction.prepareStatement(
                SuppliersRepository.INSERT_SUPPLIER,
                Statement.RETURN_GENERATED_KEYS);

            preparedStetament.setString(1, suppliersRequestDTO.getName());
            preparedStetament.setString(2, suppliersRequestDTO.getINT());
            preparedStetament.setString(3, suppliersRequestDTO.getPhone());
            preparedStetament.setString(4, suppliersRequestDTO.getEmail());
            preparedStetament.setString(5, suppliersRequestDTO.getAddress());

            return preparedStetament;
        }, keyHolder);

        SuppliersResponseDTO response = new SuppliersResponseDTO();
        // Prevengo posible NullPointerException
        if (keyHolder.getKey() != null) {
            response.setId(keyHolder.getKey().longValue());
        } else {
            response.setId(null);
        }
        response.setName(suppliersRequestDTO.getName());
        response.setINT(suppliersRequestDTO.getINT());
        response.setPhone(suppliersRequestDTO.getPhone());
        response.setEmail(suppliersRequestDTO.getEmail());
        response.setAddress(suppliersRequestDTO.getAddress());

        return response;
    }

    public List<SuppliersResponseDTO> getSuppliers(){
        return jdbctemplate.query(SuppliersRepository.GET_SUPPLIERS, suppliersMapper);
    }

    public SuppliersResponseDTO getSupplier(Long idProveedor ){
        return jdbctemplate.queryForObject(SuppliersRepository.GET_SUPPLIER, suppliersMapper, idProveedor);
    }

    public MessageResponseDTO deleteSupplier(long idProveedor){
        jdbctemplate.update(SuppliersRepository.DELETE_SUPPLIER, idProveedor);

        MessageResponseDTO response = new MessageResponseDTO();
        response.setMessage(MessageConstanst.MESSAGE_RESPONSE_DELETE_USER);

        return response;
    }
}
