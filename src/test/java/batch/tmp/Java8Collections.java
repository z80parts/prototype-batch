package batch.tmp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8Collections {

	public static void main(String[] args) {

		Java8Collections java8 = new Java8Collections();

		List<String> list = Arrays.asList("a2", "b0", "c3");
		List<String> result = list.stream().flatMap(s -> {
			String c = s.substring(0, 1);
			int n = Integer.parseInt(s.substring(1));

			String[] array = new String[n];
			Arrays.fill(array, c);
			return Stream.of(array);
		}).collect(Collectors.toList());
		System.out.println(result);

		java8.distinct();
		java8.sorted();
		java8.peek();
	}

	private void distinct() {

		List<String> list = Arrays.asList("b", "a", "b", "c", "a");
		List<String> dist = list.stream().distinct().collect(Collectors.toList());
		System.out.println(dist);
	}

	private void sorted() {
//		List<String> list = Arrays.asList("c", "b", "d", "a");
//		List<String> sort = list.stream().sorted().collect(Collectors.toList());
//		System.out.println(sort);
		List<String> list = Arrays.asList("aa", "b", "ccc", "dd");
		List<String> sort = list.stream().sorted((s1, s2) -> s1.length() - s2.length())
				.collect(Collectors.toList());
		System.out.println(sort);
	}

	private void peek() {
		Stream<String> s = Stream.of("a", "b", "c");
		s.peek(t -> System.out.printf("peek1=%s%n", t)).map(t -> t + t)
				.peek(t -> System.out.printf("peek2=%s%n", t))
				.forEach(System.out::println);
	}

}
