/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.school.onlinetest.dao;

import com.artivisi.school.onlinetest.domain.Topik;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author user
 */
public interface TopikDao extends PagingAndSortingRepository<Topik, String> {
    
}
