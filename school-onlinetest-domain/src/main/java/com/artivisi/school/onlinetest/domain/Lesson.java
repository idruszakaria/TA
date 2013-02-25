package com.artivisi.school.onlinetest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @NotEmpty
    @Column(name = "majors", nullable = false, unique = true)
    private String majors;

    @NotNull
    @NotEmpty
    @Column(name = "semester", nullable = false, unique = false)
    private String semester;
    
    @NotNull
    @Column(name = "name", nullable = false, unique = false)
    private String name;

    @NotNull
    @NotEmpty
    @Column(name = "kkm", nullable = false, unique = false)
    private String kkm;

    @NotNull
    @NotEmpty
    @Column(name = "total_competency", nullable = false)
    private String total_competency;
    
    @NotNull
    @NotEmpty
    @Column(name = "description ", nullable = false)
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMajors() {
        return majors;
    }

    public void setMajors(String majors) {
        this.majors = majors;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKkm() {
        return kkm;
    }

    public void setKkm(String kkm) {
        this.kkm = kkm;
    }

    public String getTotal_competency() {
        return total_competency;
    }

    public void setTotal_competency(String total_competency) {
        this.total_competency = total_competency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }   
}
