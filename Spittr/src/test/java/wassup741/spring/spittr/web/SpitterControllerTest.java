package wassup741.spring.spittr.web;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import wassup741.spring.spittr.Spitter;
import wassup741.spring.spittr.data.SpitterRepository;

public class SpitterControllerTest {
	@Test
	public void shouldShowRegistration() throws Exception {
		SpitterController controller = new SpitterController(null);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		mockMvc.perform(MockMvcRequestBuilders.get("/spitter/register"))
				.andExpect(MockMvcResultMatchers.view().name("registerForm"));
	}

	@Test
	public void shouldProcessRegistration() throws Exception {
		SpitterRepository mockRepository = Mockito
				.mock(SpitterRepository.class);
		Spitter unsaved = new Spitter("jbauer", "Jack", "Bauer", "24hours");
		Spitter saved = new Spitter(24L, "jbauer", "Jack", "Bauer", "24hours");
		Mockito.when(mockRepository.save(unsaved)).thenReturn(saved);

		SpitterController controller = new SpitterController(mockRepository);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		mockMvc.perform(MockMvcRequestBuilders.post("/spitter/register")
				.param("firstName", "Jack").param("lastName", "Bauer")
				.param("username", "jbauer").param("password", "24hours"))
				.andExpect(
					MockMvcResultMatchers.redirectedUrl("/spitter/jbauer"));
		Mockito.verify(mockRepository, Mockito.atLeastOnce()).save(unsaved);
	}

	@Test
	public void shouldFailRegistration() throws Exception {
		SpitterRepository mockRepository = Mockito
				.mock(SpitterRepository.class);
		Spitter unsaved = new Spitter("jb", "Jack", "Bauer", "24hours");
		Spitter saved = new Spitter(24L, "jb", "Jack", "Bauer", "24hours");
		Mockito.when(mockRepository.save(unsaved)).thenReturn(saved);

		SpitterController controller = new SpitterController(mockRepository);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		mockMvc.perform(MockMvcRequestBuilders.post("/spitter/register")
				.param("firstName", "J").param("lastName", "B")
				.param("username", "").param("password", "2"))
				.andExpect(MockMvcResultMatchers.view().name("registerForm"));
		Mockito.verify(mockRepository, Mockito.never()).save(unsaved);
	}

	@Test
	public void spitter() throws Exception {
		String username = "fake";
		Spitter spitter = new Spitter(username, username, username, username);

		SpitterRepository repository = Mockito.mock(SpitterRepository.class);
		Mockito.when(repository.find(username)).thenReturn(spitter);

		SpitterController controller = new SpitterController(repository);

		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

		mockMvc.perform(MockMvcRequestBuilders.get("/spitter/" + username))
				.andExpect(MockMvcResultMatchers.view().name("profile"))
				.andExpect(
					MockMvcResultMatchers.model().attributeExists("spitter"))
				.andExpect(MockMvcResultMatchers.model().attribute("spitter",
					spitter));
	}
}
