package template.matrix;

import java.util.*;
import java.math.BigInteger;
import java.io.*;




public class MatrixBase {



	// 打印矩阵对角线
    public static void printMatrixSingle(int[][] g) {

        int m = g.length,n = g[0].length;


        for(int i = 0;i < m;i++) {
        	for(int j = 0; j< n;j++) {
        		System.out.printf("(%s,%s) ",i,j);
        	}
        	System.out.println();
        }


        // print(\\)
        
        for(int d = m - 1;d >= 0;d--) {
            int i = d;
            int j = 0;
            while( i < m && j < n) {
                System.out.printf("g[%s][%s]=%s ",i,j,g[i][j]);
            	i++;
            	j++;
            }
            System.out.println("");
        }

        for(int d = 1;d < n;d++) {
            int i = 0;
            int j = d;
            while( i < m && j < n) {
                System.out.printf("g[%s][%s]=%s ",i,j,g[i][j]);
            	i++;
            	j++;
            }
            System.out.println("");
        }


        
        // print(//)
        // for(int d = 0;d  < n ;d++) {
        //     int i = 0;
        //     int j = d;
        //     while( i < m && j >= 0) {
        //         System.out.printf("g[%s][%s]=%s ",i,j,g[i][j]);
        //     	i++;
        //     	j--;
        //     }
        //     System.out.println("");
        // }

        // for(int d = 1 ;d  < m;d++) {
        //     int i = d;
        //     int j = n - 1;
        //     while( i < m && j >= 0) {
        //         System.out.printf("g[%s][%s]=%s ",i,j,g[i][j]);
        //     	i++;
        //     	j--;
        //     }
        //     System.out.println("");
        // }
	}




}