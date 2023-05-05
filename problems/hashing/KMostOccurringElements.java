package problems.hashing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class KMostOccurringElements {
    
    // O(DlogD)
    public static List<Integer> getUsingSort(int[] nums, int n, int k) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>(n);

        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Using sort
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> obj1, Map.Entry<Integer, Integer> obj2) {
                if(obj1.getValue() == obj2.getValue()) {
                    return obj2.getKey() - obj1.getKey();
                } else {
                    return obj2.getValue() - obj1.getValue();
                }
            }
        });

        int i=0;
        for(Map.Entry<Integer, Integer> entry : list) {
            res.add(entry.getKey());
            if(++i == k) {
                break;
            }
        }

        return res;
    }

    // O(DlogD)
    public static List<Integer> getUsingHeap(int[] nums, int n, int k) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>(n);

        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Using heap
        PriorityQueue<Map.Entry<Integer, Integer>> q = new PriorityQueue<>(map.size(),
                        new Comparator<Map.Entry<Integer, Integer>>() {
                            public int compare(Map.Entry<Integer, Integer> obj1, Map.Entry<Integer, Integer> obj2) {
                                if(obj1.getValue() == obj2.getValue()) {
                                    return obj2.getKey() - obj1.getKey();
                                } else {
                                    return obj2.getValue() - obj1.getValue();
                                }
                            }
                        });
        
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            q.add(entry);
        }

        for(int i=0; i<k; i++) {
            res.add(q.poll().getKey());
        }

        return res;
    }

    // O(n)
    // Here return order will not be desc
    public static List<Integer> getUsingBucketSort(int[] nums, int n, int k) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>(n);
        List<Integer>[] bucket = new List[n+1];
        int freq;

        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Using Bucket sort
        for(int key : map.keySet()) {
            freq = map.get(key);
            if(bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }
            bucket[freq].add(key);
        }

        for(int i=n; i>=0; i--) {
            if(bucket[i] != null) {
                for(int j=0; j<Math.min(bucket[i].size(), k - res.size()); j++) {
                    res.add(bucket[i].get(j));
                }
                // res.addAll(bucket[i]);
            }
            if(res.size() == k) {
                break;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 1, 4, 4, 5, 2, 6, 1};
        int n = nums.length;
        int k = 2;
        System.out.println("Elements using sort : " + getUsingSort(nums, n, k));
        System.out.println("Elements using heap : " + getUsingHeap(nums, n, k));
        System.out.println("Elements using bucket soert : " + getUsingBucketSort(nums, n, k));

        nums = new int[]{7, 10, 11, 5, 2, 5, 5, 7, 11, 8, 9};
        n = nums.length;
        k = 4;
        System.out.println("Elements using sort : " + getUsingSort(nums, n, k));
        System.out.println("Elements using heap : " + getUsingHeap(nums, n, k));
        System.out.println("Elements using bucket soert : " + getUsingBucketSort(nums, n, k));
    }
}
