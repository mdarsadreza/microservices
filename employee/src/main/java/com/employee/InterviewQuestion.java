package com.employee;

import java.util.*;

public class InterviewQuestion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int arr[] = {1, 5, 7, -1, 5};
		int sum = 6;
		int count = 0;
		for(int i=0; i<arr.length-1; i++) {
			for(int j=i+1;j<arr.length; j++) {
				if(arr[i] + arr[j] == sum) {
					count++;
				}
			}
		}
		System.out.println(count);
		Set<Integer> set = new HashSet<>();
		count = 0;
		for(int i =0; i< arr.length; i++){
			if(set.contains(arr[i] - sum)){
				count++;
			}
			set.add(arr[i]);
		}
		System.out.println(count);
		count = 0;
		Map<Integer, Integer> map = new HashMap<>();  // Map to store frequency of elements
		for (int i = 0; i < arr.length; i++) {
			// Check if the complement (arr[i] - sum) has appeared in the map
			if (map.containsKey(arr[i] - sum)) {
				count += map.get(arr[i] - sum);  // Add the frequency of the complement to the count
			}

			// Add current element to the map or update its frequency
			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
		}

		System.out.println(count);
	}

}
