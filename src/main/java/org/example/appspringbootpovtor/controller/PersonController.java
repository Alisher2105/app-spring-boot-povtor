package org.example.appspringbootpovtor.controller;

import org.example.appspringbootpovtor.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/student")
public class PersonController {

    List<Student> students = new ArrayList<>(Arrays.asList(
            new Student(1,"Alisher","Rajabov",new Date(),"+998919138316"),
            new Student(2,"Ganisher","Rajabboev",new Date(),"+998914324812"),
            new Student(3,"Mahliyo","Jumanazarova",new Date(),"+998946556566"),
            new Student(4,"Polankas","Polankasov",new Date(),"+998910000000")
    ));

    // Studentlarni umumiy ro`yhatini olish
    @GetMapping ()
    public List<Student> getStudents(){
        return students;
    }

    // id bo`yicha student ro`yhatini olish
    @GetMapping("{id}")
    public Student getStudentsById( @PathVariable Integer id){
        for (Student student : students) {
            if (student.getId() == id ){
                return student;
            }
        }
        return new Student();
    }

    // Yangi Student qo`shish
    @PostMapping
    public String addStudent(@RequestBody Student student){
        student.setId(students.get(students.size()-1).getId() + 1);
        students.add(student);
        return "Student added";
    }

    // Studentni id bo`yicha o`chirish
    @DeleteMapping("{id}")
    public String deleteStudentById(@PathVariable Integer id){
        boolean deleted = false;
        for (Student student : students) {
            if (student.getId() == id){
                deleted = true;
                students.remove(student);
                break;
            }
        }
        return  deleted?"Student deleted":"Student not found";
    }

    // Studentni id bo`yicha taxrirlash
    @PutMapping("{id}")
    public String updateStudentById(@PathVariable Integer id,@RequestBody Student student){
        boolean edited = false;
        for (Student student1 : students) {
            if (student1.getId() == id){
                student1.setFirstName(student.getFirstName());
                student1.setLastName(student.getLastName());
                student1.setBirthDate(student.getBirthDate());
                student1.setPhoneNumber(student.getPhoneNumber());
                edited = true;
                break;
            }
        }
        return edited?"Student updated":"Student don`t updated";
    }

}





