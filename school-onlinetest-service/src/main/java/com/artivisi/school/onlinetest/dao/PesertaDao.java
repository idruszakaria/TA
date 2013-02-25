package com.artivisi.school.onlinetest.dao;

import com.artivisi.school.onlinetest.domain.Peserta;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PesertaDao extends PagingAndSortingRepository<Peserta, String> {
	
}
