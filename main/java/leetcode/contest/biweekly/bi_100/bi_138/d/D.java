package leetcode.contest.biweekly.bi_100.bi_138.d;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/biweekly-contest-138/problems/minimum-amount-of-damage-dealt-to-bob
 * @title: 对 Bob 造成的最少伤害
 */
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class, "minDamage", "D.txt");
    }


    static class Person {
        int h, d;

        Person(int h, int d) {
            this.h = h;
            this.d = d;
        }

    }

    public long minDamage(int power, int[] damage, int[] health) {
        int n = damage.length;
        Person[] person = new Person[n];
        long tot = 0;
        for (int i = 0; i < n; i++) {
            person[i] = new Person(ceil(health[i], power), damage[i]);
            tot += damage[i];
        }
        Arrays.sort(person, (a, b) -> (a.h + b.h) * b.d + a.h * a.d - ((a.h + b.h) * a.d + b.d * b.h));
        long origin = tot;
        long sums1 = 0;
        int i = 0;
        while (i < n) {
            sums1 += tot * person[i].h;
            tot -= person[i].d;
            i++;
        }
        return sums1;
    }


    public static int ceil(int x, int y) {
        if (x % y == 0) {
            return x / y;
        }
        return (x + y - 1) / y;
    }


}