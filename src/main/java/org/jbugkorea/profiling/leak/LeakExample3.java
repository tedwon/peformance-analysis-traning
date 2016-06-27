package org.jbugkorea.profiling.leak;

import java.util.HashMap;

public class LeakExample3 {

    HashMap hm = new HashMap();
    long curValue;

    public LeakExample3() {
        curValue = 0;
    }

    public static void main(String[] args) {
        try {
            LeakExample3 m1 = new LeakExample3();
            for (int j = 0; j < 100000; j++) {
                for (int u = 0; u < 5000; u++) {
                    m1.leakMemory();
                    m1.curValue++;
                }

                System.out.println(m1.curValue);
                Thread.currentThread().sleep(1000); // Wait 2 seconds
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void setReference(GeneralClass gc) {
        hm.put(curValue, gc);
    }

    public void leakMemory() {
        GeneralClass gc = new GeneralClass();
        setReference(gc);
    }

    class GeneralClass {
        int a;
        int b;
        String s1;
        String s2;

        public GeneralClass() {
            a = 0;
            b = 4;
            s1 = "s1";
            s2 = "s2";
        }
    }
}
