package com.artivisi.school.onlinetest.service;

import java.util.List; 

import com.artivisi.school.onlinetest.domain.ApplicationConfig;
import com.artivisi.school.onlinetest.domain.Menu;
import com.artivisi.school.onlinetest.domain.Permission;
import com.artivisi.school.onlinetest.domain.Pertanyaan;
import com.artivisi.school.onlinetest.domain.Peserta;
import com.artivisi.school.onlinetest.domain.Pilihan;
import com.artivisi.school.onlinetest.domain.Role;
import com.artivisi.school.onlinetest.domain.Ujian;
import com.artivisi.school.onlinetest.domain.Soal;
import com.artivisi.school.onlinetest.domain.Topik;
import com.artivisi.school.onlinetest.domain.User;
import com.artivisi.school.onlinetest.domain.Lesson;
import com.artivisi.school.onlinetest.domain.NotifMail;
import java.util.List;
import org.hibernate.type.descriptor.java.UUIDTypeDescriptor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BelajarRestfulService extends MonitoredService {
	// konfigurasi aplikasi
	void save(ApplicationConfig ac);
	void delete(ApplicationConfig ac);
	ApplicationConfig findApplicationConfigById(String id);
    Page<ApplicationConfig> findAllApplicationConfigs(Pageable pageable);
	Long countAllApplicationConfigs();
	Long countApplicationConfigs(String search);
	Page<ApplicationConfig> findApplicationConfigs(String search, Pageable pageable);
        
    // menu
    void save(Menu m);
    void delete(Menu m);
    Menu findMenuById(String id);
    List<Menu> findTopLevelMenu();
    Page<Menu> findAllMenu(Pageable pageable);
    Long countAllMenu();
    List<Menu> findMenuByParent(Menu m);
    List<Menu> findMenuNotInRole(Role r);
    
    // permission
    void save(Permission m);
    void delete(Permission m);
    Permission findPermissionById(String id);
    Page<Permission> findAllPermissions(Pageable pageable);
    Long countAllPermissions();
    List<Permission> findPermissionsNotInRole(Role r);
    
    // role
    void save(Role role);
    void delete(Role role);
    Role findRoleById(String id);
    Page<Role> findAllRoles(Pageable pageable);
    Long countAllRoles();
    
    // user
    void save(User m);
    void delete(User m);
    User findUserById(String id);
    User findUserByUsername(String username);
    Page<User> findAllUsers(Pageable pageable);
    Long countAllUsers();
    
    // lesson
    void save(Lesson lesson);
    void delete(Lesson lesson);
    Lesson findLessonById(String id);
    Page<Lesson> findAllLessons(Pageable pageable);
    Long countAllLessons();
   
    // ujian
//    void save(Ujian ujian);
//    void delete(Ujian ujian);
//    Ujian findUjianById(String id);
//    Page<Ujian> findAllUjians(Pageable pageable);
//    Long countAllUjians(Ujian ujian);
        
    // peserta
    void save(Peserta peserta);
    void delete(Peserta peserta);
    Peserta findPesertaById(String id);
    Page<Peserta> findAllPesertas(Pageable pageable);
    Long countAllPesertas();
    
    // soal
    void save(Soal soal);
    void delete(Soal soal);
    Soal findSoalById(String id);
    Page<Soal> findAllSoals(Pageable pageable);
    Long countAllSoals();
    
    // pertanyaan
    void save(Pertanyaan pertanyaan);
    void delete(Pertanyaan pertanyaan);
    Pertanyaan findPertanyaanById(String id);
    Page<Pertanyaan> findAllPertanyaans(Pageable pageable);
    Long countAllPertanyaans();
    
    //topik
    void save (Topik topik);
    void delete(Topik topik);
    Topik findTopikById(String id);
    Page<Topik> findAllTopiks(Pageable pageable);
    Long countAllTopiks();
    
    //pilihan
    void save (Pilihan pilihan);
    void delete(Pilihan pilihan);
    Pilihan findPilihanById(String id);
    Page<Pilihan> findAllPilihans(Pageable pageable);
    Long countAllPilihans();
    
//    //ujian
    void save (Ujian ujian);
    void delete(Ujian ujian);
    Ujian findUjianById(String id);
    Page<Ujian> findAllUjians(Pageable pageable);
    Long countAllUjians();
    
      //notif_mail
    void save(NotifMail mail);
    void delete(NotifMail mail);
    NotifMail findNotifMailById(String id);
    Page<NotifMail> findAllNotifMail(Pageable pagination);
    Long countAllNotifMail();

    void sendMail(String destination, String content);
}