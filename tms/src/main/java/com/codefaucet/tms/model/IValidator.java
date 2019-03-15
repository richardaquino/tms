package com.codefaucet.tms.model;

import java.util.Map;

public interface IValidator<T> {

	Map<String, String> validate(T model);

}
