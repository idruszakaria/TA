package com.artivisi.school.onlinetest.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.artivisi.school.onlinetest.domain.ApplicationConfig;


public interface ApplicationConfigDao extends PagingAndSortingRepository<ApplicationConfig, String> {
    @Query("select ac from ApplicationConfig ac " +
			"where lower(ac.name) like lower(:search) " +
			"or lower(ac.label) like lower(:search) " +
			"or lower(ac.value) like lower(:search) " +
			"order by ac.name")
    Page<ApplicationConfig> search(@Param("search") String search, Pageable page);
    
    @Query("select count(ac) from ApplicationConfig ac " +
			"where lower(ac.name) like lower(:search) " +
			"or lower(ac.label) like lower(:search) " +
			"or lower(ac.value) like lower(:search)")
    Long count(@Param("search") String search);
}
