package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // write your code here
        RBTree<Double> rbTree = new RBTree<>();
        long s = System.currentTimeMillis();
        double[] arr = {
                85,
                36,
                10,
                90,
                25,
                50,
                80,
                78,
                22
        };
        ArrayList al = new ArrayList();
        for (int i = 0; i < 1000; i++) {
            double a = Math.random();
            System.out.println(a);
            rbTree.insert(a);
        }
        //
        rbTree.inorder();
        System.out.println(System.currentTimeMillis() - s);
    }
}
