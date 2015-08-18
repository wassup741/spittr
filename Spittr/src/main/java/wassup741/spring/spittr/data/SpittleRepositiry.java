package wassup741.spring.spittr.data;

import java.util.List;

import wassup741.spring.spittr.Spittle;

public interface SpittleRepositiry {
	List<Spittle> findSpittles(long max, int count);

	Spittle findOne(long spittleId);
}
