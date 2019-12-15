package batch.tmp;

import java.util.Optional;

public class Java8Optional {

	public static void main(String args[]) {

		Java8Optional java8Tester = new Java8Optional();
		Integer value1 = null;
		Integer value2 = Integer.valueOf(10);

		// Optional.ofNullable - allows passed parameter to be null.
		Optional<Integer> a = Optional.ofNullable(value1);

		// Optional.of - throws NullPointerException if passed parameter is null
		Optional<Integer> b = Optional.of(value2);
		System.out.println(java8Tester.sum(a, b));

		Optional<String> hogeOpt = Optional.ofNullable(java8Tester.grtHoge());
		Optional<Integer> lengthOpt = hogeOpt.map(hoge -> hoge.length());
		System.out.println(hogeOpt.get() + ":" + lengthOpt.get());
	}

	public Integer sum(Optional<Integer> a, Optional<Integer> b) {
		// Optional.isPresent - checks the value is present or not

		System.out.println("First parameter is present: " + a.isPresent());
		System.out.println("Second parameter is present: " + b.isPresent());

		// Optional.orElse - returns the value if present otherwise returns
		// the default value passed.
		Integer value1 = a.orElse(Integer.valueOf(0));

		// Optional.get - gets the value, value should be present
		Integer value2 = b.get();
		return value1 + value2;
	}

	private String grtHoge() {
		return "V";
	}

}
