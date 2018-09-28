package jack.mybatis.simple.mapper;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlgorithmTest {
    @Test
    public void threeSum() {
        List<List<Integer>> result = threeSumImpl(new int[]{-1, 0, 1, 2, -1, -4});
        result.forEach(item -> System.out.println(item));
    }

    private List<List<Integer>> threeSumImpl(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> resultList = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {

                    if (nums[i] + nums[j] + nums[k] == 0) {
                        int length = resultList.size();
                        boolean notExists = true;
                        for (int q = length - 1; q >= 0; q--){
                            List<Integer> lastSumIntegers = resultList.get(q);
                            if (lastSumIntegers.get(0) < nums[i]){
                                break;
                            }
                            if (lastSumIntegers.get(0) == nums[i]
                                    && lastSumIntegers.get(1) == nums[j]
                                    && lastSumIntegers.get(2) == nums[k]) {
                                notExists = false;
                                break;
                            }
                        }

                        if (notExists){
                            resultList.add(Arrays.asList(nums[i], nums[j], nums[k]));
                        }

                    }
                    if (nums[i] + nums[j] + nums[k] > 0) {
                        break;
                    }
                }
            }
        }
        return resultList;
    }

    private List<List<Integer>> threeSumImpl2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> resultList = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            int a = nums[i];
            int start = i + 1;
            int end = nums.length - 1;

            while(start < end){
                int b = nums[start];
                int c = nums[end];
                if (a + b + c == 0){

                    int length = resultList.size();
                    boolean notExists = true;
                    for (int q = length - 1; q >= 0; q--){
                        List<Integer> lastSumIntegers = resultList.get(q);
                        if (lastSumIntegers.get(0) < nums[i]){
                            break;
                        }
                        if (lastSumIntegers.get(0) == a
                                && lastSumIntegers.get(1) == b
                                && lastSumIntegers.get(2) == c) {
                            notExists = false;
                            break;
                        }
                    }
                    if (notExists){
                        resultList.add(Arrays.asList(a, b, c));
                    }
                    start++;
                    end--;
                }
                else if (a + b + c > 0){
                    end--;
                }else{
                    start++;
                }
            }
        }
        return resultList;
    }
}
