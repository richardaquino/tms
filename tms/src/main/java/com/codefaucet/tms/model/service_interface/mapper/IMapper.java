package com.codefaucet.tms.model.service_interface.mapper;

public interface IMapper<DOMAIN_MODEL, DTO> {
	
	void mapToDomain(DOMAIN_MODEL domain, DTO dto);
	
	DTO mapToDTO(DOMAIN_MODEL domain);
	
}
