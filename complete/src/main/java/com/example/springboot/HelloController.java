package com.example.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HelloController {

	Database database = new Database();

	@GetMapping("/")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		return "index";
	}

	@GetMapping("/form")
	public String form(Model model) {
		
		database.getServices();
		model.addAttribute("services", database.getServices());


		return "form";
	}
	
	@PostMapping("/thanks")
	public String thanks(@RequestParam("UserName") String name,
						 @RequestParam("service") int service,
						 @RequestParam("calendar-input") String date,
						 Model model) {
		//String fixed_date = "2023-04-26 11:00 AM";
		Appointment appointment = new Appointment();
		appointment.setName(name);
		appointment.setService(service);
		appointment.setDate(date);

		System.out.println(name + " - " + service + " - " + date );

		//check database for existing date
		
		 if(database.availableAppointmentTime(appointment)) {
			database.setAppointment(appointment);
			model.addAttribute("appointment", appointment);
			return "thanks";
		 } else {
			//Set Error Message
			System.out.println("Existing Appointment, warning user");
			model.addAttribute("message","There is already an appointment at that time, please go back and select a new time.");
			return "appointment_error";
		 }

		 //return "thanks";
		 

		 //return "thanks";

		

		
	}


}
