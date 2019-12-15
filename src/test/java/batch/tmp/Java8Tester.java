package batch.tmp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8Tester {

	public static void main(String args[]) {

		Java8Tester tester = new Java8Tester();
//		tester.methodReference();
//		tester.functional();
//		tester.stream();
		tester.collectors();
		tester.statistics();
		tester.testLocalDateTime();
	}

	private void methodReference() {
		List<String> names = new ArrayList<>();
		names.add("Mahesh");
		names.add("Suresh");
		names.add("Ramesh");
		names.add("Naresh");
		names.add("Kalpesh");

		names.forEach(System.out::println);
		names.forEach(name -> System.out.println(name));

		String s = "abc";
		IntSupplier supplier = s::length;
		System.out.println(supplier.getAsInt());

		Consumer<String> c = System.out::println;
		c.accept("abc");
	}

	private void functional() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

		// Predicate<Integer> predicate = n -> true
		// n is passed as parameter to test method of Predicate interface
		// test method will always return true no matter what value n has.

		System.out.println("Print all numbers:");

		// pass n as parameter
		eval(list, n -> true);

		// Predicate<Integer> predicate1 = n -> n%2 == 0
		// n is passed as parameter to test method of Predicate interface
		// test method will return true if n%2 comes to be zero

		System.out.println("Print even numbers:");
		eval(list, n -> n % 2 == 0);

		// Predicate<Integer> predicate2 = n -> n > 3
		// n is passed as parameter to test method of Predicate interface
		// test method will return true if n is greater than 3.

		System.out.println("Print numbers greater than 3:");
		eval(list, n -> n > 3);
	}

	public static void eval(List<Integer> list, Predicate<Integer> predicate) {

		for (Integer n : list) {

			if (predicate.test(n)) {
				System.out.println(n + " ");
			}
		}
	}

	private void stream() {
		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
		List<String> filtered = strings.stream().filter(string -> !string.isEmpty())
				.collect(Collectors.toList());
		filtered.forEach(System.out::println);

		Random random = new Random();
		random.ints().limit(10).forEach(System.out::println);

		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

		// get list of unique squares
		List<Integer> squaresList = numbers.stream().map(i -> i * i).distinct()
				.collect(Collectors.toList());
		squaresList.forEach(System.out::println);

		Random r = new Random();
		r.ints().limit(10).sorted().forEach(System.out::println);

		// get count of empty string
		long count = strings.parallelStream().filter(string -> string.isEmpty()).count();
//		long count = strings.stream().filter(string -> string.isEmpty()).count();
		System.out.println(count);

		Stream<Integer> s = numbers.stream();
		// sequentialStream
		Double avg = s.collect(Collectors.averagingInt(i -> i));
		System.out.println(avg);
	}

	private void collectors() {

		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
		List<String> filtered = strings.stream().filter(string -> !string.isEmpty())
				.collect(Collectors.toList());

		System.out.println("Filtered List: " + filtered);
		String mergedString = strings.stream().filter(string -> !string.isEmpty())
				.collect(Collectors.joining(", "));
		System.out.println("Merged String: " + mergedString);
	}

	private void statistics() {

		List<String> numbers = Arrays.asList("3", "2", "2", "3", "7", "3", "5");
		IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> Integer.valueOf(x))
				.summaryStatistics();

		System.out.println("Highest number in List : " + stats.getMax());
		System.out.println("Lowest number in List : " + stats.getMin());
		System.out.println("Sum of all numbers : " + stats.getSum());
		System.out.println("Average of all numbers : " + stats.getAverage());
	}

	public void testLocalDateTime() {
		// Get the current date and time
		LocalDateTime currentTime = LocalDateTime.now();
		System.out.println("Current DateTime: " + currentTime);

		LocalDate date1 = currentTime.toLocalDate();
		System.out.println("date1: " + date1);

		Month month = currentTime.getMonth();
		int day = currentTime.getDayOfMonth();
		int seconds = currentTime.getSecond();

		System.out.println("Month: " + month + "day: " + day + "seconds: " + seconds);

		LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
		System.out.println("date2: " + date2);

		// 12 december 2014
		LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
		System.out.println("date3: " + date3);

		// 22 hour 15 minutes
		LocalTime date4 = LocalTime.of(22, 15);
		System.out.println("date4: " + date4);

		// parse a string
		LocalTime date5 = LocalTime.parse("20:15:30");
		System.out.println("date5: " + date5);
	}
}
