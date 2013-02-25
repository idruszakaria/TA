/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.school.onlinetest.ui.controller;

import com.artivisi.school.onlinetest.domain.Pilihan;
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
 * @author nikko
 */
@Controller
public class PilihanController {
    @Autowired private BelajarRestfulService belajarRestfulService;
    
    @RequestMapping(value="/master/pilihan", method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid Pilihan pilihan, HttpServletRequest request, HttpServletResponse response){
        belajarRestfulService.save(pilihan);
        String requestUrl = request.getRequestURL().toString();
        URI uri = new UriTemplate("{requestUrl}/{id}").expand(requestUrl, pilihan.getId());
        response.setHeader("Location", uri.toASCIIString());
    }
    
    @RequestMapping(value="/master/pilihan{id}", method= RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable String id, @RequestBody @Valid Pilihan pilihan){
        Pilihan pilihanDb = belajarRestfulService.findPilihanById(id);
        if(pilihanDb !=null){
            belajarRestfulService.save(pilihan);
        }
    }
    
    @RequestMapping(value="/master/pilihan{id}", method= RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id){
        Pilihan pilihanDb = belajarRestfulService.findPilihanById(id);
        if(pilihanDb !=null){
            belajarRestfulService.delete(pilihanDb);
        }
    }
    
    @RequestMapping(value="/master/pilihan{id}", method= RequestMethod.GET)
    @ResponseBody
    public Pilihan findPilihanById(@PathVariable String id){
        return belajarRestfulService.findPilihanById(id);
        
    }
    
    @RequestMapping(value="/master/pilihan", method= RequestMethod.GET)
    @ResponseBody
    public Page<Pilihan> findPilihans(Pageable pagination){
        return belajarRestfulService.findAllPilihans(pagination);
    }
}
