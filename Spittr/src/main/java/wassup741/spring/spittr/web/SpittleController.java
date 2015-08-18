package wassup741.spring.spittr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import wassup741.spring.spittr.data.SpittleRepositiry;

@Controller
@RequestMapping(value = "/spittles")
public class SpittleController {

	public static final int DEFAULT_COUNT = 20;
	private static final String DEFAULT_COUNT_STRING = "20";
	public static final int DEFAULT_MAX = -1;
	private static final String DEFAULT_MAX_STRING = "-1";

	private SpittleRepositiry spittleRepository;

	@Autowired
	public SpittleController(SpittleRepositiry spittleRepository) {
		this.spittleRepository = spittleRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String spittles(Model model,
			@RequestParam(value = "max", defaultValue = DEFAULT_MAX_STRING) long max,
			@RequestParam(value = "count", defaultValue = DEFAULT_COUNT_STRING) int count) {
		model.addAttribute("spittleList",
			spittleRepository.findSpittles(max, count));

		return "spittles";
	}

	@RequestMapping(value = "/{spittleId}", method = RequestMethod.GET)
	public String spittle(@PathVariable long spittleId, Model model) {
		model.addAttribute(spittleRepository.findOne(spittleId));
		return "spittle";
	}

}
