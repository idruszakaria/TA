package com.artivisi.school.onlinetest.service.impl;

import com.artivisi.school.onlinetest.domain.NotifMail;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.artivisi.school.onlinetest.dao.ApplicationConfigDao;
import com.artivisi.school.onlinetest.dao.LessonDao;
import com.artivisi.school.onlinetest.dao.MenuDao;
import com.artivisi.school.onlinetest.dao.PermissionDao;
import com.artivisi.school.onlinetest.dao.PertanyaanDao;
import com.artivisi.school.onlinetest.dao.PesertaDao;
import com.artivisi.school.onlinetest.dao.PilihanDao;
import com.artivisi.school.onlinetest.dao.RoleDao;
import com.artivisi.school.onlinetest.dao.SoalDao;
import com.artivisi.school.onlinetest.dao.TopikDao;
import com.artivisi.school.onlinetest.dao.UjianDao;
import com.artivisi.school.onlinetest.dao.UserDao;
import com.artivisi.school.onlinetest.dao.NotifMailDao;
import com.artivisi.school.onlinetest.domain.ApplicationConfig;
import com.artivisi.school.onlinetest.domain.Menu;
import com.artivisi.school.onlinetest.domain.Permission;
import com.artivisi.school.onlinetest.domain.Pertanyaan;
import com.artivisi.school.onlinetest.domain.Peserta;
import com.artivisi.school.onlinetest.domain.Pilihan;
import com.artivisi.school.onlinetest.domain.Role;
import com.artivisi.school.onlinetest.domain.Soal;
import com.artivisi.school.onlinetest.domain.Topik;
import com.artivisi.school.onlinetest.domain.Ujian;
import com.artivisi.school.onlinetest.domain.User;
import com.artivisi.school.onlinetest.domain.Lesson;
import com.artivisi.school.onlinetest.service.BelajarRestfulService;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@SuppressWarnings("unchecked")
@Service("belajarRestfulService")
@Transactional
public class BelajarRestfulServiceImpl implements BelajarRestfulService {

    @Autowired
    private ApplicationConfigDao applicationConfigDao;
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private SoalDao soalDao;
    @Autowired
    private TopikDao topikDao;
    @Autowired
    private PesertaDao pesertaDao;
    @Autowired
    private PilihanDao pilihanDao;
    @Autowired
    private PertanyaanDao pertanyaanDao;
    @Autowired
    private UjianDao ujianDao;
    @Autowired
    private LessonDao lessonDao;
    @Autowired
    private NotifMailDao notifMailDao;
    

    @Override
    public void save(ApplicationConfig ac) {
        applicationConfigDao.save(ac);
    }

    @Override
    public void delete(ApplicationConfig ac) {
        if (ac == null || ac.getId() == null) {
            return;
        }
        applicationConfigDao.delete(ac);
    }

    @Override
    public ApplicationConfig findApplicationConfigById(String id) {
        if (!StringUtils.hasText(id)) {
            return null;
        }
        return applicationConfigDao.findOne(id);
    }

    @Override
    public Page<ApplicationConfig> findAllApplicationConfigs(Pageable pageable) {
        if(pageable == null){
            pageable = new PageRequest(0, 20);
        }
        return applicationConfigDao.findAll(pageable);
    }

    @Override
    public Long countAllApplicationConfigs() {
        return applicationConfigDao.count();
    }

    @Override
    public Page<ApplicationConfig> findApplicationConfigs(String search, Pageable pageable) {
        if (!StringUtils.hasText(search)) {
            return findAllApplicationConfigs(pageable);
        }
        
        if(pageable == null){
            pageable = new PageRequest(0, 20);
        }

        return applicationConfigDao.search("%" + search + "%", pageable);
    }

    @Override
    public Long countApplicationConfigs(String search) {
        if (!StringUtils.hasText(search)) {
            return countAllApplicationConfigs();
        }
        return applicationConfigDao.count("%" + search + "%");
    }

    @Override
    public void save(Menu m) {
        menuDao.save(m);
    }

    @Override
    public void delete(Menu m) {
        menuDao.delete(m);
    }

    @Override
    public Menu findMenuById(String id) {
        if (!StringUtils.hasText(id)) {
            return null;
        }
        return menuDao.findOne(id);
    }

    @Override
    public List<Menu> findTopLevelMenu() {
        return menuDao.findTopLevelMenu();
    }
    
    @Override
    public Page<Menu> findAllMenu(Pageable pageable) {
        return menuDao.findAll(pageable);
    }
    
    @Override
    public Long countAllMenu() {
        return menuDao.count();
    }

    @Override
    public List<Menu> findMenuByParent(Menu m) {
        if(m == null || !StringUtils.hasText(m.getId())) {
            return new ArrayList<Menu>();
        }
        return menuDao.findMenuByParent(m.getId());
    }
    
    @Override
    public List<Menu> findMenuNotInRole(Role role){
        if(role == null){
            return new ArrayList<Menu>();
        }
        
        Role r = findRoleById(role.getId());
        if(r == null || r.getMenuSet().isEmpty()){
            return new ArrayList<Menu>();
        }
        
        List<String> ids = new ArrayList<String>();
        for (Menu m : r.getMenuSet()) {
            ids.add(m.getId());
        }
        
        return menuDao.findByIdNotIn(ids);
    }

    @Override
    public void save(Permission m) {
        permissionDao.save(m);
    }

    @Override
    public void delete(Permission m) {
        permissionDao.delete(m);
    }

    @Override
    public Permission findPermissionById(String id) {
        if(!StringUtils.hasText(id)){
            return null;
        }
        return permissionDao.findOne(id);
    }

    @Override
    public Page<Permission> findAllPermissions(Pageable pageable) {
        if(pageable == null){
            pageable = new PageRequest(0, 20);
        }
        return permissionDao.findAll(pageable);
    }

    @Override
    public Long countAllPermissions() {
        return permissionDao.count();
    }
    
    @Override
    public List<Permission> findPermissionsNotInRole(Role role) {
        if(role == null){
            return new ArrayList<Permission>();
        }
        
        Role r = findRoleById(role.getId());
        if(r == null || r.getPermissionSet().isEmpty()){
            return new ArrayList<Permission>();
        }
        
        List<String> ids = new ArrayList<String>();
        for (Permission p : r.getPermissionSet()) {
            ids.add(p.getId());
        }
        
        return permissionDao.findByIdNotIn(ids);
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public void delete(Role role) {
        roleDao.delete(role);
    }

    @Override
    public Role findRoleById(String id) {
        if(!StringUtils.hasText(id)){
            return null;
        }
        
        Role r = roleDao.findOne(id);
        if(r != null){
            r.getPermissionSet().size();
            r.getMenuSet().size();
        }
        
        return r;
    }

    @Override
    public Page<Role> findAllRoles(Pageable pageable) {
        return roleDao.findAll(pageable);
    }

    @Override
    public Long countAllRoles() {
        return roleDao.count();
    }

    @Override
    public void save(User m) {
        userDao.save(m);
    }

    @Override
    public void delete(User m) {
        userDao.delete(m);
    }

    @Override
    public User findUserById(String id) {
        if(!StringUtils.hasText(id)){
            return null;
        }
        return userDao.findOne(id);
    }

    @Override
    public User findUserByUsername(String username) {
        if(!StringUtils.hasText(username)){
            return null;
        }
        return userDao.findByUsername(username);
    }

    @Override
    public Page<User> findAllUsers(Pageable pageable) {
        return userDao.findAll(pageable);
    }

    @Override
    public Long countAllUsers() {
        return userDao.count();
    }


    @Override
    public void save(Peserta peserta) {
        pesertaDao.save(peserta); 
    }

    @Override
    public void delete(Peserta peserta) {
        pesertaDao.delete(peserta);
    }

    @Override
    public Peserta findPesertaById(String id) {
        if(!StringUtils.hasText(id)){
            return null;
        }
        return pesertaDao.findOne(id);
    }

    @Override
    public Page<Peserta> findAllPesertas(Pageable pageable) {
        return pesertaDao.findAll(pageable);
    }

    @Override
    public Long countAllPesertas() {
        return pesertaDao.count();
    }

    @Override
    public void save(Soal soal) {
        soalDao.save(soal);
    }

    @Override
    public void delete(Soal soal) {
        soalDao.delete(soal);
    }

    @Override
    public Soal findSoalById(String id) {
        if(!StringUtils.hasText(id)){
            return null;
        }
        return soalDao.findOne(id);
    }

    @Override
    public Page<Soal> findAllSoals(Pageable pageable) {
        return soalDao.findAll(pageable);
    }

    @Override
    public Long countAllSoals() {
        return soalDao.count();
    }

    @Override
    public void save(Pertanyaan pertanyaan) {
        pertanyaanDao.save(pertanyaan);
    }

    @Override
    public void delete(Pertanyaan pertanyaan) {
       pertanyaanDao.delete(pertanyaan);
    }

    @Override
    public Pertanyaan findPertanyaanById(String id) {
       if(!StringUtils.hasText(id)){
            return null;
        }
        return pertanyaanDao.findOne(id);
    }

    @Override
    public Page<Pertanyaan> findAllPertanyaans(Pageable pageable) {
         return pertanyaanDao.findAll(pageable);
    }

    @Override
    public Long countAllPertanyaans() {
         return pertanyaanDao.count();
    }

    @Override
    public void save(Topik topik) {
        topikDao.save(topik);
    }

    @Override
    public void delete(Topik topik) {   
        topikDao.delete(topik);
    }

    @Override
    public Topik findTopikById(String id) {
        if(!StringUtils.hasText(id)){
            return null;
        }
        return topikDao.findOne(id);
    }

    @Override
    public Page<Topik> findAllTopiks(Pageable pageable) {   
            return topikDao.findAll(pageable);
    }

    @Override
    public Long countAllTopiks() {
        return topikDao.count();
    }

    @Override
    public void save(Pilihan pilihan) {
        pilihanDao.save(pilihan);
    }

    @Override
    public void delete(Pilihan pilihan) {
        pilihanDao.delete(pilihan);
    }

    @Override
    public Pilihan findPilihanById(String id) {
        if(!StringUtils.hasText(id)){
            return null;
        }
        return pilihanDao.findOne(id);
    }

    @Override
    public Page<Pilihan> findAllPilihans(Pageable pageable) {
        return pilihanDao.findAll(pageable);
    }

    @Override
    public Long countAllPilihans() {
        return pilihanDao.count();
    }


//    @Override
//    public void save(Ujian ujian) {
//        ujianDao.save(ujian);
//    }
//
//    @Override
//    public void delete(Ujian ujian) {
//        ujianDao.delete(ujian);
//    }
//
//
//    @Override
//    public Page<Ujian> findAllUjians(Pageable Pageable) {
//        return ujianDao.findAll(Pageable);
//    }
//
//    @Override
//    public Long countAllUjians(Ujian ujian) {
//        return ujianDao.count();
//    }
//
//    @Override
//    public Ujian findUjianById(String id) {
//         if(!StringUtils.hasText(id)){
//            return null;
//        }
//        return ujianDao.findOne(id);
//    }

    @Override
    public void save(Lesson lesson) {
        lessonDao.save(lesson);
    }

    @Override
    public void delete(Lesson lesson) {
        lessonDao.delete(lesson);
    }

    @Override
    public Lesson findLessonById(String id) {
        if(!StringUtils.hasText(id)){
            return null;
        }
        return lessonDao.findOne(id);
    }

    @Override
    public Page<Lesson> findAllLessons(Pageable pageable) {
        return lessonDao.findAll(pageable);
    }

    @Override
    public Long countAllLessons() {
        return lessonDao.count();
        }

    @Override
    public void save(Ujian ujian) {
        ujianDao.save(ujian);
    }

    @Override
    public void delete(Ujian ujian) {
        ujianDao.delete(ujian);
    }

    @Override
    public Ujian findUjianById(String id) {
        if(!StringUtils.hasText(id)){
            return null;
        }
        return ujianDao.findOne(id);
    }

    @Override
    public Page<Ujian> findAllUjians(Pageable pageable) {
        return ujianDao.findAll(pageable);
    }

    @Override
    public Long countAllUjians() {
        return ujianDao.count();
    }

    @Override
    public void save(NotifMail mail) {
        notifMailDao.save(mail);
    }

    @Override
    public void delete(NotifMail mail) {
        notifMailDao.delete(mail);
    }

    @Override
    public NotifMail findNotifMailById(String id) {
        if(!StringUtils.hasText(id)){
            return null;
        }
        return notifMailDao.findOne(id);
    }

    @Override
    public Page<NotifMail> findAllNotifMail(Pageable pagination) {
        return notifMailDao.findAll(pagination);
    }

    @Override
    public Long countAllNotifMail() {
      return notifMailDao.count();
    }

    @Override
    public void sendMail(String destination, String content) {
           final String username = "praktekrpl@gmail.com";
        final String password = "rpl12345";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {

            @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("praktekrpl@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destination));
            message.setSubject("Email Notification");
            message.setText(content);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}

