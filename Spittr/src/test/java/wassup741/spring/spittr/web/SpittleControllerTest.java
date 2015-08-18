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
	public void somePageSpittles() throws Exception {
		int spittlesCount = 5;
		long max = 2386;
		String explicitParametersUrl = "/spittles?max=" + max + "&count="
				+ spittlesCount;

		testFetch(explicitParametersUrl, max, spittlesCount);
	}

	@Test
	public void recentSpittles() throws Exception {
		int spittlesCount = SpittleController.DEFAULT_COUNT;
		long max = SpittleController.DEFAULT_MAX;
		String defaultParametersUrl = "/spittles";

		testFetch(defaultParametersUrl, max, spittlesCount);
	}

	@Test
	public void oneSpittle() throws Exception {
		Long spittleId = 4815162342L;
		Spittle extectedSpittle = new Spittle(spittleId, "Test spittle",
				new Date(), 0d, 0d);

		SpittleRepositiry repositiry = Mockito.mock(SpittleRepositiry.class);
		Mockito.when(repositiry.findOne(spittleId)).thenReturn(extectedSpittle);

		SpittleController spittleController = new SpittleController(repositiry);

		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(spittleController)
				.build();
		mockMvc.perform(MockMvcRequestBuilders.get("/spittles/" + spittleId))
				.andExpect(MockMvcResultMatchers.view().name("spittle"))
				.andExpect(
					MockMvcResultMatchers.model().attributeExists("spittle"))
				.andExpect(MockMvcResultMatchers.model().attribute("spittle",
					extectedSpittle));
	}

	private void testFetch(String url, long max, int count) throws Exception {
		List<Spittle> expectedSpittles = createSpittles(count);

		SpittleRepositiry repositiry = Mockito.mock(SpittleRepositiry.class);
		Mockito.when(repositiry.findSpittles(max, count))
				.thenReturn(expectedSpittles);

		SpittleController controller = new SpittleController(repositiry);

		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setSingleView(
					new InternalResourceView("/WEB-INF/views/spittles.jsp"))
				.build();

		mockMvc.perform(MockMvcRequestBuilders.get(url))
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
