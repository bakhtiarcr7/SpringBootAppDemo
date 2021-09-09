package com.mainpackag.firstDemoSBProject;

import com.mainpackag.firstDemoSBProject.controller.Library;
import com.mainpackag.firstDemoSBProject.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstDemoSbProjectApplication  {

	@Autowired
	LibraryRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(FirstDemoSbProjectApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		Library re = repo.findById("book12").get();
//		System.out.println("Author "+re.getAuthor());
//		Library re1 = new Library();
//		re1.setAisle(123);
//		re1.setAuthor("Hussain");
//		re1.setBook_name("Blah Blah Book");
//		re1.setId("ID");
//		re1.setIsbn("123isbn");
//		repo.save(re1);
//	}
}
