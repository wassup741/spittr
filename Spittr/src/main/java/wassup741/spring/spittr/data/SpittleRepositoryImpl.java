package wassup741.spring.spittr.data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import wassup741.spring.spittr.Spittle;

@Component
public class SpittleRepositoryImpl implements SpittleRepositiry {

	@Override
	public List<Spittle> findSpittles(long max, int count) {
		return IntStream.range(0, count)
				.mapToObj(index -> new Spittle("Spittle " + index, new Date()))
				.collect(Collectors.toList());
	}

}
