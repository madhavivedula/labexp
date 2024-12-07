package com.klu.Exp_1;

import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class StudentServices {

    private static SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
    
    @SuppressWarnings("deprecation")
	public void insertStudent(Scanner sc) {
        while (true) {
            System.out.print("Insert student details? (yes/no): ");
            if (!sc.next().equalsIgnoreCase("yes")) break;

            sc.nextLine(); 

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Gender: ");
            String gender = sc.nextLine();

            System.out.print("Enter Department: ");
            String department = sc.nextLine();

            System.out.print("Enter Program: ");
            String program = sc.nextLine();

            System.out.print("Enter Date of Birth (yyyy-MM-dd): ");
            String dob = sc.next();  
            sc.nextLine(); 

            System.out.print("Enter Contact Number: ");
            String contact = sc.nextLine();

            System.out.print("Enter Graduation Status: ");
            String status = sc.nextLine();

            System.out.print("Enter CGPA: ");
            double cgpa = sc.nextDouble();

            System.out.print("Enter No of Backlogs: ");
            int backlogs = sc.nextInt();

            Session session = factory.openSession();
            try {
                session.beginTransaction();
                Student student = new Student();
                student.setName(name);
                student.setGender(gender);
                student.setDepartment(department);
                student.setProgram(program);
                student.setDateOfBirth(java.sql.Date.valueOf(dob));
                student.setContactNumber(contact);
                student.setGraduationStatus(status);
                student.setCgpa(cgpa);
                student.setBacklogs(backlogs);
                session.save(student);
                session.getTransaction().commit();
                System.out.println("Student inserted!");
            } finally {
                session.close();
            }
        }
    }
    
    public void fetchStudentById(Scanner sc) {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Student student = session.get(Student.class, id);
            if (student != null) {
                System.out.println("Student: " + student);
            } else {
                System.out.println("Student not found.");
            }
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
    
    @SuppressWarnings("deprecation")
	public void updateStudentById(Scanner sc) {
        System.out.print("Enter Student ID to update: ");
        int id = sc.nextInt();
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Student student = session.get(Student.class, id);
            if (student != null) {
                System.out.print("Update CGPA: ");
                double cgpa = sc.nextDouble();
                student.setCgpa(cgpa);
                session.update(student);
                session.getTransaction().commit();
                System.out.println("Student updated!");
            } else {
                System.out.println("Student not found.");
            }
        } finally {
            session.close();
        }
    }
    
    @SuppressWarnings("deprecation")
	public void deleteStudentById(Scanner sc) {
        System.out.print("Enter Student ID to delete: ");
        int id = sc.nextInt();
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Student student = session.get(Student.class, id);
            if (student != null) {
                session.delete(student);
                session.getTransaction().commit();
                System.out.println("Student deleted!");
            } else {
                System.out.println("Student not found.");
            }
        } finally {
            session.close();
        }
    }
}
