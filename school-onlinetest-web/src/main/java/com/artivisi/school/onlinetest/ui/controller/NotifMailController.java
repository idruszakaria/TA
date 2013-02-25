/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.school.onlinetest.ui.controller;

import com.artivisi.school.onlinetest.domain.NotifMail;
import com.artivisi.school.onlinetest.service.BelajarRestfulService;
import java.net.URI;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriTemplate;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;
/**
 *
 * @author Idrus
 */
@Controller
public class NotifMailController {
     @Autowired private BelajarRestfulService belajarRestfulService;
    
    
    @RequestMapping(value="/notifications/email", method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid NotifMail mail, HttpServletRequest request, HttpServletResponse response){
      belajarRestfulService.save(mail);
       String requestUrl = request.getRequestURL().toString();
        URI uri = new UriTemplate("{requestUrl}/{id}").expand(requestUrl, mail.getId());
        response.setHeader("Location", uri.toASCIIString());
    }
    
    
    @RequestMapping(value="/notifications/email/{id}", method= RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable String id, @RequestBody @Valid NotifMail x){
        NotifMail maildb = belajarRestfulService.findNotifMailById(id);
        if(maildb != null){
            belajarRestfulService.save(x);
        }
    }
    
    @RequestMapping(value="/notifications/email/{id}", method= RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id) throws Exception{
        NotifMail maildb = belajarRestfulService.findNotifMailById(id);
        if(maildb ==null){
            throw new Exception("Notif mail dengan id " + id + " tidak di temukan");
        }
            belajarRestfulService.delete(maildb);
    }
    
    @RequestMapping(value="/notifications/email/{id}", method= RequestMethod.GET)
    @ResponseBody
    public NotifMail findNotifMailById(@PathVariable String id){
        return belajarRestfulService.findNotifMailById(id);
    }
   
    
    @RequestMapping(value="/notifications/email", method= RequestMethod.GET)
    @ResponseBody
    public Page<NotifMail> findNotifMail(Pageable pagination){
        return belajarRestfulService.findAllNotifMail(pagination);
    }
    
    @RequestMapping(value="/notifications/email/send", method= RequestMethod.GET)
    public void sendMail(@RequestParam(value="destination") String destination,
            @RequestParam(value="content") String content,
            HttpServletRequest request, HttpServletResponse response){
        if(destination == null || content == null) return;
        
        belajarRestfulService.sendMail(destination, content);
    }
}
