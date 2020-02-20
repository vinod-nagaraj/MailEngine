package com.geekyants.mailengine.service;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.geekyants.mailengine.constant.ApplicationConstant;
import com.geekyants.mailengine.dto.CustomerRequestDto;
import com.geekyants.mailengine.dto.ResponseDto;
import com.geekyants.mailengine.entity.Customer;
import com.geekyants.mailengine.exception.NoEntriesException;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Override
	public ResponseDto customerRegistration(CustomerRequestDto customerRequestDto) throws NoEntriesException {
		if(ObjectUtils.isEmpty(customerRequestDto)) {
			throw new NoEntriesException(ApplicationConstant.EMPTY_ENTRIES);
		}
			Customer customer = new Customer();
			customer.setStatus(ApplicationConstant.ACTIVE);
			BeanUtils.copyProperties(customerRequestDto, customer);
			ResponseDto response = new ResponseDto();
			response.setMessage(ApplicationConstant.REGISTRED_SUCCESSFULLY);
			response.setStatusCode(HttpStatus.ACCEPTED.value());
			return response;
	}

}
