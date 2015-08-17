package wassup741.spring.spittr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import wassup741.spring.spittr.data.SpittleRepositiry;

@Controller
@RequestMapping(value = "/spittles")
public class SpittleController {

	private SpittleRepositiry spittleRepositiry;

	@Autowired
	public SpittleController(SpittleRepositiry spittleRepositiry) {
		this.spittleRepositiry = spittleRepositiry;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String spittles(Model model) {
		model.addAttribute("spittleList",
			spittleRepositiry.findSpittles(Long.MAX_VALUE, 20));

		return "spittles";
	}

}
