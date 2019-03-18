package com.codefaucet.tms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.codefaucet.tms.model.TaskResult;
import com.codefaucet.tms.model.domain.Panelist;
import com.codefaucet.tms.model.dto.PanelistDTO;
import com.codefaucet.tms.model.service_interface.IPanelistService;
import com.codefaucet.tms.repository.IPanelistRepository;
import com.codefaucet.tms.service.mapper.PanelistMapper;
import com.codefaucet.tms.service.validator.PanelistValidator;

@Service
public class PanelistService implements IPanelistService {

	@Autowired
	private IPanelistRepository panelistRepository;
	
	@Autowired
	private PanelistMapper panelistMapper;
	
	@Autowired
	private PanelistValidator panelistValidator;
	
	@Override
	public TaskResult<PanelistDTO> create(PanelistDTO dto) {
		var result = new TaskResult<PanelistDTO>();
		result.setErrors(panelistValidator.validate(dto));
		if(!result.getErrors().isEmpty()) {
			return result.setFailed();
		}
		
		var panelist = new Panelist();
		panelistMapper.mapToDomain(panelist, dto);
		panelist = panelistRepository.save(panelist);
		
		return result.setSuccessful(panelistMapper.mapToDTO(panelist));
	}

	@Override
	public TaskResult<PanelistDTO> update(PanelistDTO dto) {
		var result = new TaskResult<PanelistDTO>();
		result.setErrors(panelistValidator.validate(dto));
		if(!result.getErrors().isEmpty()) {
			return result.setFailed();
		}
		
		var panelist = panelistRepository.findById(dto.getId()).get();
		panelistMapper.mapToDomain(panelist, dto);
		panelist = panelistRepository.save(panelist);
		
		return result.setSuccessful(panelistMapper.mapToDTO(panelist));
	}

	@Override
	public TaskResult<Boolean> setActive(long panelistId, boolean active) {
		var panelist = panelistRepository.findById(panelistId).get();
		if(panelist.isActive() != active) {
			panelist.setActive(active);
			panelist = panelistRepository.save(panelist);
		}
		return new TaskResult<Boolean>().setSuccessful(true);
	}

	@Override
	public PanelistDTO getNew() {
		return new PanelistDTO();
	}

	@Override
	public PanelistDTO get(long panelistId) {
		var panelist = panelistRepository.findById(panelistId).get();
		return panelistMapper.mapToDTO(panelist);
	}

	@Override
	public List<PanelistDTO> find(String queryString, boolean includeInactive, int pageNumber, int pageSize) {
		var pageable = 
			PageRequest
			.of(
				pageNumber - 1, 
				pageSize, 
				Sort.by("lastname", "firstname", "middleName")
			);
		var panelists = panelistRepository.find(queryString, includeInactive, pageable);
		return 
			panelists
			.stream()
			.map(item -> panelistMapper.mapToDTO(item))
			.collect(Collectors.toList());
	}

}
