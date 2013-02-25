package com.artivisi.school.onlinetest.ui.controller;

import com.artivisi.school.onlinetest.domain.Peserta;
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
 * @author USER
 */
@Controller
public class PesertaController {
    @Autowired private BelajarRestfulService belajarRestfulService;
    
    
    @RequestMapping(value="/master/peserta", method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid Peserta peserta, HttpServletRequest request, HttpServletResponse response){
      belajarRestfulService.save(peserta);
       String requestUrl = request.getRequestURL().toString();
        URI uri = new UriTemplate("{requestUrl}/{id}").expand(requestUrl, peserta.getId());
        response.setHeader("Location", uri.toASCIIString());
}
        @RequestMapping(value="/master/peserta{id}", method= RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable String id, @RequestBody @Valid Peserta peserta){
        Peserta pesertaDB = belajarRestfulService.findPesertaById(id);
        if(pesertaDB !=null){
            belajarRestfulService.save(peserta);
        }
        }
    @RequestMapping(value="/master/peserta{id}", method= RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id){
        Peserta pesertaDB = belajarRestfulService.findPesertaById(id);
        if(pesertaDB !=null){
            belajarRestfulService.delete(pesertaDB);
        }
    }
    @RequestMapping(value="/master/peserta{id}", method= RequestMethod.GET)
    @ResponseBody
    public Peserta findPesertaById(@PathVariable String id){
        return belajarRestfulService.findPesertaById(id);
        
    }
       @RequestMapping(value="/master/peserta", method= RequestMethod.GET)
    @ResponseBody
    public Page<Peserta> findPesertas(Pageable pagination){
        return belajarRestfulService.findAllPesertas(pagination);
    }
}
     

    

   


