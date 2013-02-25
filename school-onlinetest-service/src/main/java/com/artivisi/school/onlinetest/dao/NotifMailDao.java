/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.school.onlinetest.dao;

import com.artivisi.school.onlinetest.domain.NotifMail;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 *
 * @author Idrus
 */
public interface  NotifMailDao extends PagingAndSortingRepository<NotifMail, String>{
    
}
