package com.employee;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CheckDuplicate {

	public static void main(String[] args) {
		Map<Integer, String> map = new HashMap<>();
		map.put(5, "I");
		map.put(1, "P");
		map.put(7,"J");
		map.put(2, "A");
		map.put(9,"C");
		
		List<Integer> li = Arrays.asList(1,1,1,2,2,7,6,85,88,8,8,8,7,4);

		Map<Integer, Integer> countEachNumber = li.stream().collect(Collectors.groupingBy(x -> x, Collectors.summingInt(x->2)));
		System.out.println(countEachNumber);
		countEachNumber = li.stream().collect(Collectors.groupingBy(x -> x, Collectors.summingInt(x->1)));
		System.out.println(countEachNumber);

		List<Integer> dupNumbers = li.stream().filter(x -> Collections.frequency(li, x) > 1).distinct().collect(Collectors.toList());
		System.out.println(dupNumbers);

		Map<Object, Object> sortedMap = map.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e,e1)->e, LinkedHashMap:: new));
		System.out.println(sortedMap);

		Map<Object, Object> sortedMapsByKey = map.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(e,e1)->e, LinkedHashMap::new));
		System.out.println("Sorted by linkedHashMap.....  "+sortedMapsByKey);
		Map<Object, Object> sortedMapByKey = map.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		System.out.println("Sorted by key.....  "+sortedMapByKey);

		Map<Boolean, List<Integer>> eventOdd = li.stream().collect(Collectors.groupingBy(x -> x % 2 == 0));
		System.out.println(eventOdd.get(true));
		System.out.println(eventOdd.get(false));

	}

}
