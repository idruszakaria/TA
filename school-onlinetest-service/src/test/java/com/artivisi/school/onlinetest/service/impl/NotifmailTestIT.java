/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.school.onlinetest.service.impl;

import com.artivisi.school.onlinetest.service.BelajarRestfulService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Idrus
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:com/artivisi/school/onlinetest/**/applicationContext.xml"})
public class NotifmailTestIT {
    @Autowired
    private BelajarRestfulService service;
    
    @Test
    public void mailSend() {
        service.sendMail("idrustrason@gmail.com", "ini tester");
    }
 
}
