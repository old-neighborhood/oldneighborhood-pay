package com.oldneighborhood.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oldneighborhood.demo.entity.PayInfo;



public interface PayDao extends JpaRepository<PayInfo, String>{

}
