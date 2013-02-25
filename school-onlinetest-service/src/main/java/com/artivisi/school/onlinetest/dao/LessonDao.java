package com.artivisi.school.onlinetest.dao;


import com.artivisi.school.onlinetest.domain.Lesson;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LessonDao extends PagingAndSortingRepository<Lesson, String> {
    
}
