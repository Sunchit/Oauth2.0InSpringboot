package com.example.springbootchallenge.rest.dto;

import com.example.springbootchallenge.rest.dto.Student;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StudentsDetails {

    @JsonProperty("students_list")
    List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
