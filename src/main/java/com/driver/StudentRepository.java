package com.driver;
import com.driver.*;         
import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    private HashMap<String, Student> studentMap;
    private HashMap<String, Teacher> teacherMap;
    private HashMap<String, List<String>> teacherStudentMapping;

    public StudentRepository(){
        this.studentMap = new HashMap<String, Student>();
        this.teacherMap = new HashMap<String, Teacher>();
        this.teacherStudentMapping = new HashMap<String, List<String>>();
    }

    public void saveStudent(Student student){
        // your code goes here
    	studentMap.put(student.getName(), student);
    }

    public void saveTeacher(Teacher teacher){
        // your code goes here
    	teacherMap.put(teacher.getName(), teacher);
    }

    public void saveStudentTeacherPair(String student, String teacher){
        if(studentMap.containsKey(student) && teacherMap.containsKey(teacher)){
            // your code goes here
        	List<String> studentList = new ArrayList<>();
        	if(!teacherStudentMapping.containsKey(teacher)) {
        		teacherStudentMapping.put(teacher, studentList);
        		studentList.add(teacher);
        	}
        	studentList = teacherStudentMapping.get(teacher);
        	
        }
    }

    public Student findStudent(String student){
        // your code goes here
    	if(studentMap.containsKey(student)) {
    		return studentMap.get(student);
    	}
    	return null;
    }

    public Teacher findTeacher(String teacher){
        // your code goes here
    	if(teacherMap.containsKey(teacher)) {
    		return teacherMap.get(teacher);
    	}
    	return null;
		
    }

    public List<String> findStudentsFromTeacher(String teacher){
        // your code goes here
        // find student list corresponding to a teacher
    	 // Create a new ArrayList to store the list of students
        List<String> studentList = new ArrayList<>();
        
        // Check if the teacherStudentMapping contains the given teacher as a key
        if (teacherStudentMapping.containsKey(teacher)) {
            // If the teacher is found in the mapping, retrieve the list of students
            studentList = teacherStudentMapping.get(teacher);
        }
        
        // Return the list of students (empty if the teacher is not found)
        return studentList;
    }

    public List<String> findAllStudents(){
        // your code goes here
    	// Create a new ArrayList to store the list of all student names
        List<String> allStudents = new ArrayList<>(studentMap.keySet());
        
        // Return the list of all student names
        return allStudents;
    }

    public void deleteTeacher(String teacher){
        // your code goes here
    	 if(teacherStudentMapping.containsKey(teacher)) {
             List<String> studentList = new ArrayList<>(teacherStudentMapping.get(teacher));
             for(String s:studentList){
                 studentMap.remove(s);
             }
             teacherStudentMapping.remove(teacher);

         }
         if(teacherMap.containsKey(teacher)){
             teacherMap.remove(teacher);
         }
    }

    public void deleteAllTeachers(){
        // your code goes here
    	for (String teacher : new ArrayList<>(teacherMap.keySet())) {
    	    if (teacherStudentMapping.containsKey(teacher)) {
    	        // Retrieve the list of students associated with the current teacher
    	        List<String> studentList = teacherStudentMapping.get(teacher);
    	        
    	        // Remove each student from the student map
    	        for (String student : studentList) {
    	            studentMap.remove(student);
    	        }
    	        
    	        // Remove the teacher-student mapping
    	        teacherStudentMapping.remove(teacher);
    	    }	
    	    
    	    // Remove the teacher from the teacher map
    	    teacherMap.remove(teacher);
    	}
    }
}