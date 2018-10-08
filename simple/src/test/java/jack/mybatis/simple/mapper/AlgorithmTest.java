package jack.mybatis.simple.mapper;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class AlgorithmTest {

    /**
     * Given an array nums of n integers,
     * are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
     * <p>
     * Note:
     * The solution set must not contain duplicate triplets.
     * <p>
     * Example:
     * Given array nums = [-1, 0, 1, 2, -1, -4],
     * <p>
     * A solution set is:
     * [
     * [-1, 0, 1],
     * [-1, -1, 2]
     * ]
     */
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
                        for (int q = length - 1; q >= 0; q--) {
                            List<Integer> lastSumIntegers = resultList.get(q);
                            if (lastSumIntegers.get(0) < nums[i]) {
                                break;
                            }
                            if (lastSumIntegers.get(0) == nums[i]
                                    && lastSumIntegers.get(1) == nums[j]
                                    && lastSumIntegers.get(2) == nums[k]) {
                                notExists = false;
                                break;
                            }
                        }

                        if (notExists) {
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

            while (start < end) {
                int b = nums[start];
                int c = nums[end];
                if (a + b + c == 0) {

                    int length = resultList.size();
                    boolean notExists = true;
                    for (int q = length - 1; q >= 0; q--) {
                        List<Integer> lastSumIntegers = resultList.get(q);
                        if (lastSumIntegers.get(0) < nums[i]) {
                            break;
                        }
                        if (lastSumIntegers.get(0) == a
                                && lastSumIntegers.get(1) == b
                                && lastSumIntegers.get(2) == c) {
                            notExists = false;
                            break;
                        }
                    }
                    if (notExists) {
                        resultList.add(Arrays.asList(a, b, c));
                    }
                    start++;
                    end--;
                } else if (a + b + c > 0) {
                    end--;
                } else {
                    start++;
                }
            }
        }
        return resultList;
    }

    /**
     * You are given two non-empty linked lists representing two non-negative integers.
     * The digits are stored in reverse order and each of their nodes contain a single digit.
     * Add the two numbers and return it as a linked list.
     * <p>
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     * Example:
     * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     * Output: 7 -> 0 -> 8
     * Explanation: 342 + 465 = 807.
     */
    @Test
    public void addTwoNumbersTest() {

    }

    private ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node = null;
        ListNode header = null;

        int sum = 0;
        int carry = 0;
        int val = 0;

        while (l1 != null && l2 != null) {
            sum = l1.val + l2.val + carry;
            if (sum >= 10) {
                carry = 1;
                val = sum - 10;
            } else {
                carry = 0;
                val = sum;
            }

            ListNode current = new ListNode(val);
            if (node == null) {
                header = current;
            } else {
                node.next = current;
            }
            node = current;

            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            sum = l1.val + carry;
            if (sum >= 10) {
                carry = 1;
                val = sum - 10;
            } else {
                carry = 0;
                val = sum;
            }
            ListNode current = new ListNode(val);
            node.next = current;
            node = current;
            l1 = l1.next;
        }

        while (l2 != null) {
            sum = l2.val + carry;
            if (sum >= 10) {
                carry = 1;
                val = sum - 10;
            } else {
                carry = 0;
                val = sum;
            }
            ListNode current = new ListNode(val);
            node.next = current;
            node = current;
            l2 = l2.next;
        }

        if (carry > 0) {
            ListNode current = new ListNode(carry);
            node.next = current;
        }

        return header;
    }

    @Test
    public void lengthOfLongestSubstringTest(){
        Assert.assertEquals(3, lengthOfLongestSubstring("abcabcbb"));
        Assert.assertEquals(1, lengthOfLongestSubstring("bbbbb"));
        Assert.assertEquals(3, lengthOfLongestSubstring("pwwkew"));

        Assert.assertEquals(1, lengthOfLongestSubstring(" "));
        Assert.assertEquals(3, lengthOfLongestSubstring("dvdf"));

    }

    /**
     * Given a string, find the length of the longest substring without repeating characters.
     * <p>
     * Example 1:
     * Input: "abcabcbb"
     * Output: 3
     * Explanation: The answer is "abc", with the length of 3.
     * <p>
     * Example 2:
     * Input: "bbbbb"
     * Output: 1
     * Explanation: The answer is "b", with the length of 1.
     * <p>
     * Example 3:
     * Input: "pwwkew"
     * Output: 3
     * Explanation: The answer is "wke", with the length of 3.
     * Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
     */
    private int lengthOfLongestSubstring(String s) {
        List<Character> subString = new ArrayList<>();
        int subMaxLength = 0;
        int index;
        char c;

        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            index = subString.indexOf(c);
            if (index >= 0){
                if (subString.size() > subMaxLength) {
                    subMaxLength = subString.size();
                }
                subString = subString.subList(index + 1, subString.size());
            }

            subString.add(c);
        }

        return subString.size() > subMaxLength ? subString.size() : subMaxLength;
    }

    /**
     * 算法原理:
     * 遍历字符串，使用hashset保存不重复子串所有字符，并提高查找效率
     * res 不重复子串长度， left表示记录不重复子串的左边界，right记录右边界
     * 当测试hashset包含重复字符时，把重复字符和重复之前的所有字符要从hashset中移除
     * 不包含重复字符时，直接添加至hashset，并将hashset长度与sublength比较，取最大值
     * */
    private int lengthOfLongestSubstring2(String s) {
        int res = 0, left = 0, right = 0;
        HashSet<Character> t = new HashSet<>();
        while (right < s.length()) {
            if (!t.contains(s.charAt(right))) {
                t.add(s.charAt(right++));
                res = Math.max(res, t.size());
            } else {
                t.remove(s.charAt(left++));
            }
        }
        return res;
    }

    /**
     *There are two sorted arrays nums1 and nums2 of size m and n respectively.
     * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
     * You may assume nums1 and nums2 cannot be both empty.
     *
     * Example 1:
     * nums1 = [1, 3]
     * nums2 = [2]
     * The median is 2.0
     *
     * Example 2:
     * nums1 = [1, 2]
     * nums2 = [3, 4]
     * The median is (2 + 3)/2 = 2.5
     *
     * */
    private double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return 0;
    }
}
