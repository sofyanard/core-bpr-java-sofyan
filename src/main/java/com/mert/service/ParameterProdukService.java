package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.ParameterProduk;
import com.mert.model.ParameterProductType;
import com.mert.repository.ParameterProdukRepository;

@Service
public class ParameterProdukService {
	
	@Autowired
	private ParameterProdukRepository parameterProdukRepository;
	
	public List<ParameterProduk> findAll() {
		return parameterProdukRepository.findAll();
	}
	
	public List<ParameterProduk> findByType(String productTypeId) {
		return parameterProdukRepository.findByType(productTypeId);
	}
	
	public ParameterProduk findOne(String id) {
		return parameterProdukRepository.findOne(id);
	}
	
	public void save(ParameterProduk parameterProduk) {
		parameterProdukRepository.save(parameterProduk);
	}

}
