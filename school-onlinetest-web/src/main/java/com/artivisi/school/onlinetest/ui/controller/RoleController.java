package com.artivisi.school.onlinetest.ui.controller;

import com.artivisi.school.onlinetest.domain.Menu;
import com.artivisi.school.onlinetest.domain.Permission;
import com.artivisi.school.onlinetest.domain.Role;
import com.artivisi.school.onlinetest.service.BelajarRestfulService;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
public class RoleController {

    @Autowired
    private BelajarRestfulService belajarRestfulService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/role/{id}")
    @ResponseBody
    public Role findById(@PathVariable String id) {
        Role x = belajarRestfulService.findRoleById(id);
        if (x == null) {
            throw new IllegalStateException();
        }
        return x;
    }
    
    @RequestMapping("/role/{id}/unselected-permission")
    @ResponseBody
    public List<Permission> findPermissionNotInRole(@PathVariable String id) {
        return belajarRestfulService.findPermissionsNotInRole(belajarRestfulService.findRoleById(id));
    }
    
    @RequestMapping("/role/{id}/unselected-menu")
    @ResponseBody
    public List<Menu> findMenuNotInRole(@PathVariable String id) {
        return belajarRestfulService.findMenuNotInRole(belajarRestfulService.findRoleById(id));
    }

    @RequestMapping(value = "/role", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid Role x, HttpServletRequest request, HttpServletResponse response) {
        belajarRestfulService.save(x);
        String requestUrl = request.getRequestURL().toString();
        URI uri = new UriTemplate("{requestUrl}/{id}").expand(requestUrl, x.getId());
        response.setHeader("Location", uri.toASCIIString());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/role/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable String id, @RequestBody @Valid Role x) {
        Role a = belajarRestfulService.findRoleById(id);
        if (a == null) {
            logger.warn("Role dengan id [{}] tidak ditemukan", id);
            throw new IllegalStateException();
        }
        x.setId(a.getId());
        belajarRestfulService.save(x);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/role/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id) {
        Role a = belajarRestfulService.findRoleById(id);
        if (a == null) {
            logger.warn("Role dengan id [{}] tidak ditemukan", id);
            throw new IllegalStateException();
        }
        belajarRestfulService.delete(a);
    }

    @RequestMapping(value = "/role", method = RequestMethod.GET)
    @ResponseBody
    public List<Role> findAll(
            Pageable pageable,
            HttpServletResponse response) {
        List<Role> hasil = belajarRestfulService.findAllRoles(pageable).getContent();
        for(Role r : hasil){
            r.setPermissionSet(null);
            r.setMenuSet(null);
        }
        return hasil;

    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({IllegalStateException.class})
    public void handle() {
        logger.debug("Resource dengan URI tersebut tidak ditemukan");
    }
}
