package com.artivisi.school.onlinetest.dao;

import com.artivisi.school.onlinetest.domain.Permission;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PermissionDao extends PagingAndSortingRepository<Permission, String> {
    public List<Permission> findByIdNotIn(List<String> ids);
}
