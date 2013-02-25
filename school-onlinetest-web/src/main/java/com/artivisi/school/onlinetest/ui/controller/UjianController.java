/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.school.onlinetest.ui.controller;


import com.artivisi.school.onlinetest.domain.Ujian;
import com.artivisi.school.onlinetest.service.BelajarRestfulService;
import java.net.URI;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriTemplate;

/**
 *
 * @author LILY
 */
@Controller
public class UjianController {
    @Autowired private BelajarRestfulService belajarRestfulService;
    
    @RequestMapping(value="/master/ujian", method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid Ujian ujian, HttpServletRequest request, HttpServletResponse response){
      belajarRestfulService.save(ujian);
       String requestUrl = request.getRequestURL().toString();
        URI uri = new UriTemplate("{requestUrl}/{id}").expand(requestUrl, ujian.getId());
        response.setHeader("Location", uri.toASCIIString());
    }
    
    @RequestMapping(value="/master/ujian{id}", method= RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable String id, @RequestBody @Valid Ujian ujian){
        Ujian ujianDb = belajarRestfulService.findUjianById(id);
        if(ujianDb !=null){
            belajarRestfulService.save(ujian);
        }
    }
    
    @RequestMapping(value="/master/ujian{id}", method= RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id){
        Ujian ujianDb = belajarRestfulService.findUjianById(id);
        if(ujianDb !=null){
            belajarRestfulService.delete(ujianDb);
        }
    }
    
    @RequestMapping(value="/master/ujian{id}", method= RequestMethod.GET)
    @ResponseBody
    public Ujian findUjianById(@PathVariable String id){
        return belajarRestfulService.findUjianById(id);
        
    }
    
    @RequestMapping(value="/master/ujian", method= RequestMethod.GET)
    @ResponseBody
    public Page<Ujian> findUjian(Pageable pagination){
        return belajarRestfulService.findAllUjians(pagination);
    }
}
