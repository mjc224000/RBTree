package com.company;
import java.util.ArrayList;
public class Main {

    public static void main(String[] args) {
        // write your code here
        RBTree<Double> rbTree = new RBTree<>();
        long s = System.currentTimeMillis();
        double[] arr = {
                0.913229867993839,
                0.5409930258372001,
                0.869817513603347
        };
        for (int i = 0; i < 1000; i++) {
            double var = Math.random();

            rbTree.insert(var);
        }
        //
        rbTree.inorder();
        System.out.println(System.currentTimeMillis() - s);
    }
}
