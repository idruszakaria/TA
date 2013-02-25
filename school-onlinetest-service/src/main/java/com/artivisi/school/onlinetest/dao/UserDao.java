package com.artivisi.school.onlinetest.dao;

import com.artivisi.school.onlinetest.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDao extends PagingAndSortingRepository<User, String> {
	User findByUsername(String username);
}
