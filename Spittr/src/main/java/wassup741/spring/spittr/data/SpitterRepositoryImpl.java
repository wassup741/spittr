package wassup741.spring.spittr.data;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import wassup741.spring.spittr.Spitter;

@Component
public class SpitterRepositoryImpl implements SpitterRepository {

	public Map<String, Spitter> spitters = new HashMap<>();

	@Override
	public Spitter save(Spitter spitter) {
		spitters.put(spitter.getUsername(), spitter);
		return spitter;
	}

	@Override
	public Spitter find(String username) {
		return spitters.get(username);
	}

}
