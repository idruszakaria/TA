package com.artivisi.school.onlinetest.dao;

import com.artivisi.school.onlinetest.domain.Pertanyaan;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PertanyaanDao extends PagingAndSortingRepository<Pertanyaan, String> {
	
}
