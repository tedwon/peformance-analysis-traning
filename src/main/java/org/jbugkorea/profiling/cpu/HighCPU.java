package org.jbugkorea.profiling.cpu;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class HighCPU {

        private final Map<Integer, Integer> myMap = new HashMap<>();
//    private final Map<Integer, Integer> myMap = new ConcurrentHashMap<>();

    private void run() {
        IntStream.range(0, Integer.MAX_VALUE)
                .parallel()
                .forEach(x -> {
                    putMyMap(x, x);
                    getMyMap(x);
                    System.out.println(Thread.currentThread() + " : " + x);
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                    }
                    removeMyMap(x);
                });
    }

    private void putMyMap(Integer key, Integer value) {
        myMap.put(key, value);
    }

    private Integer getMyMap(Integer key) {
        return myMap.get(key);
    }

    private void removeMyMap(Integer key) {
        myMap.remove(key);
    }


    public static void main(String[] args) {

        HighCPU highCPU = new HighCPU();
        highCPU.run();


        // wait infinitely
        synchronized (HighCPU.class) {
            try {
                HighCPU.class.wait();
            } catch (InterruptedException e) {

            }
        }

    }
}
