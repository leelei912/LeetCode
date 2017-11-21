package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
	public static int[] twoSum1(int[] nums, int target) {
		int len = nums.length;
		int first = -1;
		int second = -1;
		for(int i = 0; i < len; i++) {
			for(int j = i + 1; j < len; j++) {
				if((nums[i] + nums[j]) == target) {
					first = i;
					second = j;
					break;
				}
			}
			if(first != -1)
				break;
		}
        return new int[] {first, second};
    }
	
	public static int[] twoSum(int[] nums, int target) {
        
        final Map<Integer, Integer> foundSoFar = new HashMap<>(nums.length);    
        for (int i = 0; i<nums.length;i++) {
            int pair = target - nums[i];
            if (foundSoFar.containsKey(pair)) {
                int index = foundSoFar.get(pair);
                return new int[] {index, i};
            } else {
                foundSoFar.put(nums[i], i);
            }
        }
        return new int[]{};
    }
	
	public static void main(String[] args) {
		int[] a = { 3,4,3};
		int[] b = twoSum(a, 6);
		System.out.println(Arrays.toString(b));
	}
}
