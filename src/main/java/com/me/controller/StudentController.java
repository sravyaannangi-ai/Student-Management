package com.me.controller;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.me.entity.Student;
import com.me.repository.StudentRepository;
@Controller
public class StudentController {
	@Autowired
	private StudentRepository repository;
	
	@PostMapping("/save")
	public String saveStudent(@RequestParam String name,@RequestParam String email,Model model)
	{
		Student student=new Student();
		student.setName(name);
		student.setEmail(email);
		
		repository.save(student);
		
		model.addAttribute("name",name);
		model.addAttribute("email",email);
		
		return "success";
	}
	@GetMapping("/view")
	public String viewStuddent(Model model) {
		List<Student> students=repository.findAll();
		model.addAttribute("students",students);
		return "view";
	}
	@GetMapping("/delete")
	public String deleteStudent(@RequestParam Long id)
	{
		repository.deleteById(id);
		return "redirect:/view";
	}
	@GetMapping("/edit")
	public String editStudent(@RequestParam Long id,Model model) {
		Student student=repository.findById(id).orElse(null);
		model.addAttribute("student",student);
		return "edit";
	}
	@PostMapping("/update")
	public String updateStuddent(@RequestParam Long id,@RequestParam String name,@RequestParam String email)
	{
		Student student=repository.findById(id).orElse(null);
		if(student!=null)
		{
			student.setName(name);
			student.setEmail(email);
			repository.save(student);
		}
		return "redirect:/view";
	}
	

}
