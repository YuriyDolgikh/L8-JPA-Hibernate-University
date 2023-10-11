package biz.itehnika;

import javax.persistence.*;
import java.util.List;

public class AppUniver
{
    public static void main( String[] args ){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Univer");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            Course course1 = new Course("Group-1");
            Course course2 = new Course("Group-2");
            Course course3 = new Course("Group-3");

            for (int i = 0; i < 7; i++){
                course1.addStudent(new Student("Name" + i, 20 + i));
            }
            for (int i = 0; i < 12; i++){
                course2.addStudent(new Student("Name" + i, 20 + i));
            }
            for (int i = 0; i < 19; i++){
                course3.addStudent(new Student("Name" + i, 20 + i));
            }

            transaction.begin();
            try {
                em.persist(course1);
                em.persist(course2);
                em.persist(course3);
                transaction.commit();
            }catch (Exception ex){
                transaction.rollback();
                return;
            }
            em.clear();

            StringBuilder builder = new StringBuilder();
            TypedQuery<Course> query = em.createNamedQuery("Course.findAll", Course.class);
            List<Course> courseList = query.getResultList();
            for (Course course : courseList){
                builder.append("Course: ")
                       .append(course.getName()).append(" => ").append(course.getStudents().size()).append(" students")
                       .append(System.lineSeparator());
            }
            System.out.println(builder.toString());
        }
        finally {
            em.close();
            emf.close();
        }
    }
}
