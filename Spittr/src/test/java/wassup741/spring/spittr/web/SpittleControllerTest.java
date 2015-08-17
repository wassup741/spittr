package wassup741.spring.spittr.web;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;

import wassup741.spring.spittr.Spittle;
import wassup741.spring.spittr.data.SpittleRepositiry;

public class SpittleControllerTest {

	@Test
	public void recentSpittles() throws Exception {
		int spittlesCount = 20;
		List<Spittle> expectedSpittles = createSpittles(spittlesCount);

		SpittleRepositiry repositiry = Mockito.mock(SpittleRepositiry.class);
		Mockito.when(repositiry.findSpittles(Long.MAX_VALUE, spittlesCount))
				.thenReturn(expectedSpittles);

		SpittleController controller = new SpittleController(repositiry);

		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setSingleView(
					new InternalResourceView("/WEB-INF/views/spittles.jsp"))
				.build();
		
		mockMvc.perform(MockMvcRequestBuilders.get("/spittles"))
				.andExpect(MockMvcResultMatchers.view().name("spittles"))
				.andExpect(MockMvcResultMatchers.model()
						.attributeExists("spittleList"))
				.andExpect(
					MockMvcResultMatchers.model().attribute("spittleList",
						CoreMatchers.hasItems(expectedSpittles.toArray())));
	}

	private List<Spittle> createSpittles(int count) {
		return IntStream.range(0, count)
				.mapToObj(index -> new Spittle("Spittle " + index, new Date()))
				.collect(Collectors.toList());
	}
}
