package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;
import code_generation.annotation.TestCaseGroup;
import java.util.*;

import code_generation.contest.ParseCodeInfo;
/**
 * @author: qitongwei
 * @Description:
 * @url:   <a href="https://leetcode.cn/problems/walking-robot-simulation-ii">walking-robot-simulation-ii</a>
 * @title: walking-robot-simulation-ii
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class Code_0076_2069 {

    public static void main(String[] args) {
        IoUtil.testUtil(Robot.class,ParseCodeInfo.ConstructorClass,"txt_file\\Code_0076_2069.txt");
    }
    private static final int MOD = (int)1e9 + 7;

    public static class Robot {
        static Map<Character,String> d = new HashMap<>();
        static {
            d.put('U',"North");
            d.put('D',"South");
            d.put('L',"West");
            d.put('R',"East");
        }
        int w;
        int h;
        char pos;
        int x = 0;
        int y = 0;
        int m;
        Robot(int w,int h) {
            this.w = w;
            this.h = h;
            this.pos = 'R';
            this.m = (w * 2 + h * 2 - 4);
        }

        void step(int num) {
            if(num==0)return;
            if((x == 0 || x == w - 1 || y == 0 || y == h - 1) && !(x == 0 && y == 0 && num > 0) && m > 0) {
                num %= m;
            }
            if (pos == 'R') {
                int dis = w - x - 1;
                int d = Math.min(dis, num);
                num -= d;
                x += d;
                if (num > 0) {
                    pos = 'U';
                    y++;
                    num--;
                    step(num);
                }
            } else if (pos == 'L') {
                int dis = x;
                int d = Math.min(dis, num);
                num -= d;
                x -= d;
                if (num > 0) {
                    pos = 'D';
                    y--;
                    num--;
                    step(num);
                }
            } else if (pos == 'U') {
                int dis = h - y - 1;
                int d = Math.min(dis, num);
                num -= d;
                y += d;
                if (num > 0) {
                    pos = 'L';
                    x--;
                    num--;
                    step(num);
                }
            } else if (pos == 'D') {
                int dis = y;
                int d = Math.min(dis, num);
                num -= d;
                y -= d;
                if (num > 0) {
                    pos = 'R';
                    x++;
                    num--;
                    step(num);
                }
            }


            // cout << "{ "<< x <<" " << y << " }" << " pos = " << pos << "\n";

        }

        int[] getPos() {
            return new int[]{x,y};
        }

        String getDir() {
            // cout<<"pos: "<<pos<<"\n";
            return d.get(pos);
        }


    }

}