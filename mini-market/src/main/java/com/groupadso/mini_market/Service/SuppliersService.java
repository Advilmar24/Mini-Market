package com.groupadso.mini_market.Service;

import com.groupadso.mini_market.DTO.MessageResponseDTO;
import com.groupadso.mini_market.DTO.SuppliersResponseDTO;
import com.groupadso.mini_market.DTO.SuppliersRequestDTO;
import com.groupadso.mini_market.Repository.SuppliersRepository;
import com.groupadso.mini_market.Constants.MessageConstants;


import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

@Service
public class SuppliersService {
    private final JdbcTemplate jdbctemplate;

    public SuppliersService(JdbcTemplate jdbcTemplate){
        this.jdbctemplate = jdbcTemplate;
    }

    private final RowMapper<SuppliersResponseDTO> suppliersMapper = (rs, rowNum) -> {
        SuppliersResponseDTO suppliersResponseDTO = new SuppliersResponseDTO();
        suppliersResponseDTO.setIdProveedor(rs.getLong("idProveedor"));
        suppliersResponseDTO.setName(rs.getString("name"));
        suppliersResponseDTO.setNit(rs.getString("nit"));
        suppliersResponseDTO.setPhone(rs.getString("phone"));
        suppliersResponseDTO.setMail(rs.getString("mail"));
        suppliersResponseDTO.setAddress(rs.getString("address"));
        return suppliersResponseDTO;
    };

    public SuppliersResponseDTO createSupplier(SuppliersRequestDTO suppliersRequestDTO) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbctemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                SuppliersRepository.INSERT_SUPPLIER,
                Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, suppliersRequestDTO.getName());
            preparedStatement.setString(2, suppliersRequestDTO.getNit());
            preparedStatement.setString(3, suppliersRequestDTO.getPhone());
            preparedStatement.setString(4, suppliersRequestDTO.getMail());
            preparedStatement.setString(5, suppliersRequestDTO.getAddress());

            return preparedStatement;
        }, keyHolder);

        SuppliersResponseDTO response = new SuppliersResponseDTO();
        if (keyHolder.getKey() != null) {
            response.setIdProveedor(keyHolder.getKey().longValue());
        } else {
            response.setIdProveedor(null);
        }
        response.setName(suppliersRequestDTO.getName());
        response.setNit(suppliersRequestDTO.getNit());
        response.setPhone(suppliersRequestDTO.getPhone());
        response.setMail(suppliersRequestDTO.getMail());
        response.setAddress(suppliersRequestDTO.getAddress());

        return response;
    }

    public List<SuppliersResponseDTO> getSuppliers() {
        return jdbctemplate.query(SuppliersRepository.GET_SUPPLIERS, suppliersMapper);
    }

    public SuppliersResponseDTO getSupplier(Long idProveedor) {
        return jdbctemplate.queryForObject(SuppliersRepository.GET_SUPPLIER, suppliersMapper, idProveedor);
    }

    public MessageResponseDTO deleteSupplier(long idProveedor) {
        jdbctemplate.update(SuppliersRepository.DELETE_SUPPLIER, idProveedor);

        MessageResponseDTO response = new MessageResponseDTO();
        response.setMessage(MessageConstants.MESSAGE_RESPONSE_DELETE_USER);

        return response;
    }
}