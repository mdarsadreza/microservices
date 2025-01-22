package com.employee;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FindDuplicate {

	public static void main(String[] args) {
//		List<Integer> li = Arrays.asList(1,2,3,2,4,5,1);
//		List<Integer> li = Arrays.asList(1,2,3,2,4,5,1);
//		List<Integer> duplicateNumber = li.stream().filter(i -> Collections.frequency(li, i)>1).collect(Collectors.toList());
//		System.out.print(duplicateNumber);
		int arr[] = {1,0,2,3,0,4,0,0};
		int j = arr.length-1;
		for(int i=0; i< arr.length;i++) {
			if(arr[j]==0) {
				j--;
			}
			if(arr[i]==0) {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j]=temp;
				j--;
			}
			if(i>=j) {
				break;
			}
		}
		for(int i=0; i< arr.length;i++) {
		System.out.print(arr[i]);
		}

		for (int i = 0; i <= j; i++) {
			// Move `j` backward to skip zeros
			while (arr[j] == 0 && j > i) {
				j--;
			}
			// If current element is zero, swap it with `j`
			if (arr[i] == 0 && i < j) {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				j--;
			}
		}

		System.out.println();
	for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
		}
	}
}
