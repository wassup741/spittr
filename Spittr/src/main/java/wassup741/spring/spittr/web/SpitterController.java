package wassup741.spring.spittr.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import wassup741.spring.spittr.Spitter;
import wassup741.spring.spittr.data.SpitterRepository;

@Controller
@RequestMapping("/spitter")
public class SpitterController {

	private SpitterRepository spitterRepository;

	@Autowired
	public SpitterController(SpitterRepository spitterRepository) {
		this.spitterRepository = spitterRepository;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegistrationForm(Model model) {
		model.addAttribute(new Spitter());
		return "registerForm";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String processRegistration(@Valid Spitter spitter, Errors errors) {
		if (errors.hasErrors()) {
			return "registerForm";
		}

		Spitter savedSpitter = spitterRepository.save(spitter);
		return "redirect:/spitter/" + savedSpitter.getUsername();
	}

	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public String spitter(@PathVariable String username, Model model) {
		Spitter spitter = spitterRepository.find(username);
		model.addAttribute(spitter);
		return "profile";
	}
}