package com.tstProject.demo.Student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	@Autowired
    public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	
	public List<Student> getStudents(){     
		return studentRepository.findAll();
	}


	public void insertNewStudent(Student student) {
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

		if (studentOptional.isPresent()){
			throw new IllegalStateException("Email already exists");
		}

		studentRepository.save(student);
	}


	public void deleteStudent(Long studentId) {
		if(!studentRepository.existsById(studentId)){
			throw new IllegalStateException("Student [" + studentId + "] does not exist");
		}

		studentRepository.deleteById(studentId);
		
	}

	@Transactional
	public void updateStudent(long studentId, Student student) {
		// if(!studentRepository.existsById(studentId)){
		// 	throw new IllegalStateException("Student [" + studentId + "] does not exist");
		// Student studentToupdate = new Student();
		// studentToupdate = studentRepository.getReferenceById(studentId).orElseThrow()
		// }

		Student studentToupdate = studentRepository.findById(studentId)
						   .orElseThrow(() -> new IllegalStateException("Student [" + studentId + "] does not exist"));
		studentToupdate.setName(student.getName());
		studentToupdate.setDateOfBirth(student.getDateOfBirth());
		studentToupdate.setEmail(student.getEmail());
	}
}
