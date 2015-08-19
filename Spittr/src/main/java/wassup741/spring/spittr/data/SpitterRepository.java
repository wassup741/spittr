package wassup741.spring.spittr.data;

import wassup741.spring.spittr.Spitter;

public interface SpitterRepository {
	public Spitter save(Spitter spitter);

	public Spitter find(String username);
}
