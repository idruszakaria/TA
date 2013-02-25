package com.artivisi.school.onlinetest.dao;

import com.artivisi.school.onlinetest.domain.Ujian;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UjianDao extends PagingAndSortingRepository<Ujian, String> {
    
}
