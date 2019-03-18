package com.codefaucet.tms.service.mapper;

import org.springframework.stereotype.Service;

import com.codefaucet.tms.model.domain.Panelist;
import com.codefaucet.tms.model.dto.PanelistDTO;
import com.codefaucet.tms.model.service_interface.mapper.IMapper;

@Service
public class PanelistMapper implements IMapper<Panelist, PanelistDTO> {

	@Override
	public void mapToDomain(Panelist domain, PanelistDTO dto) {
		domain.setFirstName(dto.getFirstName());
		domain.setLastName(dto.getLastName());
		domain.setMiddleName(dto.getMiddleName());
		domain.setSuffix(dto.getSuffix());
		domain.setTitle(dto.getTitle());
	}

	@Override
	public PanelistDTO mapToDTO(Panelist domain) {
		return 
			new PanelistDTO(
				domain.getId(), 
				domain.isActive(), 
				domain.getTitle(), 
				domain.getLastName(), 
				domain.getFirstName(), 
				domain.getMiddleName(), 
				domain.getSuffix()
			);
	}

}
