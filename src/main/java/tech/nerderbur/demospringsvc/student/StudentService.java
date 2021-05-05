package tech.nerderbur.demospringsvc.student;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.nerderbur.demospringsvc.exception.AlreadyExistsException;
import tech.nerderbur.demospringsvc.exception.NotFoundException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class StudentService {
    // TODO: Change trace logs to info because kibana defaults to INFO
    // TODO: Implement a catch all scenario for error handling.
    // TODO: Implementation for request filtering in logs.
    private final StudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        log.trace("Entered method addNewStudent with {}", student);
        Optional<Student> student1 = studentRepository.findStudentByEmail(student.getEmail());

        if (student1.isPresent()) {
            log.error("The email {} is already taken", student.getEmail());
            throw new AlreadyExistsException("email taken");
        }

        log.info("Saving student...");
        studentRepository.save(student);
        log.debug("Successfully saved student");
        log.trace("Exit method addNewStudent");
    }

    public void deleteStudent(Long studentId) {
        log.trace("Entered method deleteStudent with {}", studentId);
        boolean exists = studentRepository.existsById(studentId);

        if (!exists) {
            throw new NotFoundException("student with id " + studentId + " doesn't exist.");
        }

        studentRepository.deleteById(studentId);
        log.trace("Exited method deleteStudent");
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        log.trace("Entered method updateStudent with params {} {} {}", studentId, name, email);
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("student with id " + studentId + " doesn't exist."));

        if (name != null &&
            name.length() > 0 &&
            !Objects.equals(student.getName(), name)) {
            log.info("Updating student name...");
            student.setName(name);
        }

        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(student.getEmail(), email)) {
            Optional<Student> optionalStudent = studentRepository.findStudentByEmail(email);

            if (optionalStudent.isPresent()) {
                log.error("Email cannot be updated because the new email {} is already taken.", email);
                throw new AlreadyExistsException("email taken");
            }
            log.info("Updating student email...");
            student.setEmail(email);
        }
        log.trace("Exited method updateStudent");
    }
}
