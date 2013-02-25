package com.artivisi.school.onlinetest.ui.controller;

import com.artivisi.school.onlinetest.domain.Pertanyaan;
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
 * @author nikko
 */
@Controller
public class PertanyaanController {
   @Autowired private BelajarRestfulService belajarRestfulService;
    
    
    @RequestMapping(value="/master/pertanyaan", method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid Pertanyaan pertanyaan, HttpServletRequest request, HttpServletResponse response){
      belajarRestfulService.save(pertanyaan);
       String requestUrl = request.getRequestURL().toString();
        URI uri = new UriTemplate("{requestUrl}/{id}").expand(requestUrl, pertanyaan.getId());
        response.setHeader("Location", uri.toASCIIString());
    }
     @RequestMapping(value="/master/pertanyaan{id}", method= RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable String id, @RequestBody @Valid Pertanyaan pertanyaan){
        Pertanyaan pertanyaanDB = belajarRestfulService.findPertanyaanById(id);
        if(pertanyaanDB !=null){
            belajarRestfulService.save(pertanyaan);
        }
        }
@RequestMapping(value="/master/pertanyaan{id}", method= RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id){
        Pertanyaan pertanyaanDB = belajarRestfulService.findPertanyaanById(id);
        if(pertanyaanDB !=null){
            belajarRestfulService.delete(pertanyaanDB);
        }
    }
@RequestMapping(value="/master/pertanyaan{id}", method= RequestMethod.GET)
    @ResponseBody
    public Pertanyaan findPertanyaanById(@PathVariable String id){
        return belajarRestfulService.findPertanyaanById(id);
        
    }
@RequestMapping(value="/master/pertanyaan", method= RequestMethod.GET)
    @ResponseBody
    public Page<Pertanyaan> findPertanyaans(Pageable pagination){
        return belajarRestfulService.findAllPertanyaans(pagination);
    }
}