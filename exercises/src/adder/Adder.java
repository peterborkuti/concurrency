package adder;

import java.util.List;
import java.util.concurrent.Callable;

public class Adder implements Callable<Long> {

	private List<Integer> nums;

	public Adder(List<Integer> nums) {
		this.nums = nums; 
	}

	@Override
	public Long call() throws Exception {
		long sum = 0;

		for (Integer num: nums) {
			sum += num;
		}

		return sum;
	}

}
