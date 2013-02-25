package com.artivisi.school.onlinetest.ui.controller;

import com.artivisi.school.onlinetest.domain.Soal;
import com.artivisi.school.onlinetest.domain.Topik;
import com.artivisi.school.onlinetest.service.BelajarRestfulService;
import java.net.URI;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
public class TopikController {
    @Autowired private BelajarRestfulService belajarRestfulService;
    
    @RequestMapping(value="/master/topik", method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid Topik topik, HttpServletRequest request, HttpServletResponse response){
      belajarRestfulService.save(topik);
       String requestUrl = request.getRequestURL().toString();
        URI uri = new UriTemplate("{requestUrl}/{id}").expand(requestUrl, topik.getId());
        response.setHeader("Location", uri.toASCIIString());
    }
    
    @RequestMapping(value="/master/topik{id}", method= RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable String id, @RequestBody @Valid Topik topik){
        Topik topikDb = belajarRestfulService.findTopikById(id);
        if(topikDb !=null){
            belajarRestfulService.save(topik);
        }
    }
    
    @RequestMapping(value="/master/topik{id}", method= RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id){
        Topik topikDb = belajarRestfulService.findTopikById(id);
        if(topikDb !=null){
            belajarRestfulService.delete(topikDb);
        }
    }
    
    @RequestMapping(value="/master/topik{id}", method= RequestMethod.GET)
    @ResponseBody
    public Topik findTopikById(@PathVariable String id){
        return belajarRestfulService.findTopikById(id);
        
    }
    
    @RequestMapping(value="/master/topik", method= RequestMethod.GET)
    @ResponseBody
    public Page<Soal> findTopik(Pageable pagination){
        return belajarRestfulService.findAllSoals(pagination);
    }
}
