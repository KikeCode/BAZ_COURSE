package com.wizeline.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wizeline.model.ErrorDTO;
import com.wizeline.model.ResponseDTO;
import com.wizeline.repository.UserRepository;
import com.wizeline.utils.Utils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());

	public ResponseDTO createUser(String user, String password) {
		LOGGER.info("Inicia procesamiento en capa de negocio");
		ResponseDTO response = new ResponseDTO();
		String result = "fail";
		if (Utils.validateNullValue(user) && Utils.validateNullValue(password)) {
			result = userRepository.createUser(user, password);
			response.setCode("OK000");
			response.setStatus(result);
		} else {
			response.setCode("OK000");
			response.setStatus(result);
			response.setErrors(new ErrorDTO("ER001", "Error al crear usuario"));
		}
		return response;
	}

	public ResponseDTO login(String user, String password) {
		LOGGER.info("Inicia procesamiento en capa de negocio");
		ResponseDTO response = new ResponseDTO();
		String result = "";
		if (Utils.validateNullValue(user) && Utils.validateNullValue(password)) {
			result = userRepository.login(user, password);
		}
		if ("success".equals(result)) {
			response.setCode("OK000");
			response.setStatus(result);
		} else {
			response.setCode("ER081");
			response.setErrors(new ErrorDTO("ER001", result));
			response.setStatus("fail");

		}
		return response;
	}
}
