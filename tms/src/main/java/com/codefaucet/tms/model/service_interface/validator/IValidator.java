package com.codefaucet.tms.model.service_interface.validator;

import java.util.Map;

public interface IValidator<T> {

	Map<String, String> validate(T model);

}
