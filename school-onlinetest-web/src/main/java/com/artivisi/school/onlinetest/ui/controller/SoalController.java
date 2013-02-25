package com.artivisi.school.onlinetest.ui.controller;

import com.artivisi.school.onlinetest.domain.Soal;
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
public class SoalController {
    @Autowired private BelajarRestfulService belajarRestfulService;
    
    @RequestMapping(value="/master/soal", method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid Soal soal, HttpServletRequest request, HttpServletResponse response){
      belajarRestfulService.save(soal);
       String requestUrl = request.getRequestURL().toString();
        URI uri = new UriTemplate("{requestUrl}/{id}").expand(requestUrl, soal.getId());
        response.setHeader("Location", uri.toASCIIString());
    }
    
    @RequestMapping(value="/master/soal{id}", method= RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable String id, @RequestBody @Valid Soal soal){
        Soal soalDb = belajarRestfulService.findSoalById(id);
        if(soalDb !=null){
            belajarRestfulService.save(soal);
        }
    }
    
    @RequestMapping(value="/master/soal{id}", method= RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id){
        Soal soalDb = belajarRestfulService.findSoalById(id);
        if(soalDb !=null){
            belajarRestfulService.delete(soalDb);
        }
    }
    
    @RequestMapping(value="/master/soal{id}", method= RequestMethod.GET)
    @ResponseBody
    public Soal findSoalById(@PathVariable String id){
        return belajarRestfulService.findSoalById(id);
        
    }
    
    @RequestMapping(value="/master/soal", method= RequestMethod.GET)
    @ResponseBody
    public Page<Soal> findSoal(Pageable pagination){
        return belajarRestfulService.findAllSoals(pagination);
    }
}
