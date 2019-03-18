package com.codefaucet.tms.service.mapper;

import org.springframework.stereotype.Component;

import com.codefaucet.tms.model.domain.User;
import com.codefaucet.tms.model.dto.UserDTO;
import com.codefaucet.tms.model.service_interface.mapper.IMapper;

@Component
public class UserMapper implements IMapper<User, UserDTO> {

	@Override
	public void mapToDomain(User domain, UserDTO dto) {
		domain.setFirstName(dto.getFirstName());
		domain.setLastName(dto.getLastName());
		domain.setMiddleName(dto.getMiddleName());
		domain.setRole(dto.getRole());
		domain.setUsername(dto.getUsername());
	}

	@Override
	public UserDTO mapToDTO(User domain) {
		return new UserDTO(
			domain.getId(), 
			domain.isActive(), 
			domain.getRole(), 
			domain.getUsername(), 
			"", 
			domain.getLastName(), 
			domain.getFirstName(), 
			domain.getMiddleName()
		);
	}

}
