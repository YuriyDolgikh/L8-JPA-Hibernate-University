package biz.itehnika;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "Courses")
@NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    @OneToMany(
            mappedBy = "course",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Student> students = new ArrayList<>();

    public Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public void addStudent(Student student){
        if (!students.contains(student)){
            students.add(student);
            student.setCourse(this);
        }
    }

    public Student getStudent(int index){
        return students.get(index);
    }

    public List<Student> getStudents(){
        return Collections.unmodifiableList(students);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}