package com.codefaucet.tms.model.service_interface;

import java.util.List;

import com.codefaucet.tms.model.TaskResult;
import com.codefaucet.tms.model.dto.PanelistDTO;

public interface IPanelistService {

	TaskResult<PanelistDTO> create(PanelistDTO dto);

	TaskResult<PanelistDTO> update(PanelistDTO dto);

	TaskResult<Boolean> setActive(long panelistId, boolean active);

	PanelistDTO getNew();

	PanelistDTO get(long panelistId);

	List<PanelistDTO> find(String queryString, boolean includeInactive, int pageNumber, int pageSize);

}
