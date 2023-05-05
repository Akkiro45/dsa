package problems.priorityqueue;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianInStreamOfIntegers {

    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    public void insert(int num) {
        if(maxHeap.isEmpty() || maxHeap.peek() >= num) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }

        if((maxHeap.size() == minHeap.size()) || (maxHeap.size() == minHeap.size() + 1)) {
            return;
        }
        if(maxHeap.size() > minHeap.size()) {
            minHeap.add(maxHeap.poll());
        } else {
            maxHeap.add(minHeap.poll());
        }
    }

    public int getMedian() {
        if(maxHeap.size() == minHeap.size()) {
            return ((maxHeap.peek() + minHeap.peek()) / 2);
        } else {
            return maxHeap.peek();
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5, 15, 1, 3};
        MedianInStreamOfIntegers obj = new MedianInStreamOfIntegers();
        for(int num : nums) {
            obj.insert(num);
            System.out.println("Median : " + obj.getMedian());
        }
    }
}
