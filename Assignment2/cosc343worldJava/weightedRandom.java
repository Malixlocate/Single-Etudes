/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mmoore
 */
public class weightedRandom {
    
    public static void main(String[]args) {
        int n = 4;
	double[] weight = new double [n];
	weight[0] = 0.4;
	weight[1] = 0.3;
	weight[2] = 1.2;
	weight[3] = 0.1;
	double max_weight = 1.2;
	int[] counter = new int[n];
	int n_select = 1000;
	int index;
	for (int i = 0; i < n_select; i++) {
		while (true) {
			index = (int) (Math.random() * n);
			if (Math.random() < weight[index] / max_weight) break;
		}
		counter[index]++;
	}
	for (int i = 0; i < n; i++) {
		System.out.printf("counter[%d]=%d\n", i, counter[i]);
	}
}
    }
    

