package com.artivisi.school.onlinetest.dao;

import com.artivisi.school.onlinetest.domain.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleDao extends PagingAndSortingRepository<Role, String> {
}