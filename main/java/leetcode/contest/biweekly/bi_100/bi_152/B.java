package leetcode.contest.biweekly.bi_100.bi_152;

import code_generation.contest.ParseCodeInfo;
import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/biweekly-contest-152/problems/design-spreadsheet">设计电子表格</a>
 * @title: 设计电子表格
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(Spreadsheet.class, ParseCodeInfo.ConstructorClass, "B.txt");
    }


    public static class Spreadsheet {

        int[][] data;

        public Spreadsheet(int rows) {
            data = new int[rows + 10][26];
        }

        public void setCell(String cell, int value) {
            int[] x = get(cell);
            data[x[0]][x[1]] = value;
        }


        public int[] get(String cell){
            cell = cell.toUpperCase();
            int col = cell.charAt(0) - 'A';
            int row = 0;
            for (int i = 1; i < cell.length(); i++) {
                row = row * 10 + cell.charAt(i) - '0';
            }
            return new int[]{row,col};
        }
        public void resetCell(String cell) {
            int[] x = get(cell);
            data[x[0]][x[1]] = 0;
        }

        public int getValue(String s) {
            StringBuilder s1 = new StringBuilder();
            StringBuilder s2 = new StringBuilder();
            boolean flag = false;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if(c == '=') continue;
                if(c == '+') {
                    flag = true;
                    continue;
                }
                if(flag) {
                    s2.append(c);
                }else{
                    s1.append(c);
                }
            }
            int v1 = getNumber(s1.toString());
            int v2 = getNumber(s2.toString());
            if(v1 == -1 ) {
                try{
                    int[] x = get(s1.toString());
                    v1 = data[x[0]][x[1]];
                }catch (Exception e){
                    v1 = 0;
                }
            }
            if(v2 == -1 ) {

                try{
                    int[] y = get(s2.toString());
                    v2 = data[y[0]][y[1]];
                }catch (Exception e){
                    v2 = 0;
                }
            }
            return v1 + v2;
        }


        public static int getNumber(String s){
            if(!isNum(s.charAt(0) - '0')) return -1;
            int ans = 0;
            for(int i = 0;i < s.length();i++) {
                char c = s.charAt(i);
                ans = ans * 10 + c - '0';
            }
            return ans;
        }

        public static boolean isNum(int c){
            return 0 <= c && c <= 9 ;
        }


    }

}