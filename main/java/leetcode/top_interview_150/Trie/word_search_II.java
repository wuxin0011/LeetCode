package leetcode.top_interview_150.Trie;

import leetcode.utils.IoUtil;

import java.util.*;

/**
 * @author: wuxin0011
 * @Description: 单词搜索II
 * @url https://leetcode.cn/problems/word-search-ii/description/?envType=study-plan-v2&envId=top-interview-150
 */
public class word_search_II {

    public static void main(String[] args) {
        IoUtil.testUtil(word_search_II.class,"findWords","./txt_file/word_search_II.txt");
    }


    private static final int MAX_LENGHT =  3 * (int)1e4+1;
    private final String[] set = new String[MAX_LENGHT];
    int m,n;
    char[][] board;
    String s;
    int[] help;

    public List<String> findWords(char[][] board, String[] words) {
        this.m = board.length;
        this.n = board[0].length;
        this.board = board;
        this.help = new int[26];
        List<String> ans = new ArrayList<>();
        ArrayList[] edges = new ArrayList[26];

        for(int i = 0;i<m;i++){
            for(int j = 0;j<n;j++){
                int idx = board[i][j]-'a';
                help[idx]++;
                if(edges[idx] == null){
                    edges[idx] = new ArrayList<>();
                }
                edges[idx].add(new int[]{i,j});
            }
        }
        int size = 0;

        for(String w : words){
            this.s = w;
            if(check()){
                set[size++] = w;
            }
        }

        for(int i = 0;i<size;i++){
            this.s = set[i];
            ArrayList<int[]> ls = edges[s.charAt(0) - 'a'];
            if(ls != null){
                for(int[] g : ls){
                    if(dfs(g[0],g[1],0)){
                        ans.add(s);
                        break;
                    }
                }
            }
        }

        return ans;
    }

    public boolean check(){
        int[] h = new int[26];
        for(int i = 0;i<s.length();i++){
            h[s.charAt(i)-'a']++;
        }
        for(int i = 0;i<h.length;i++){
            if(h[i] > help[i]) return false;
        }
        return true;
    }


    public boolean dfs(int x,int y,int k){
        if( k == s.length()) return true;
        if( x < 0 || x >= m || y < 0 || y >= n || board[x][y] == '#' ){
            return false;
        }
        if(s.charAt(k) != board[x][y] ){
            return false;
        }
        char reset = board[x][y];
        board[x][y] = '#';
        boolean ans =  dfs(x+1,y,k+1) ||  dfs(x-1,y,k+1) ||  dfs(x,y+1,k+1) ||  dfs(x,y-1,k+1);
        board[x][y] = reset; // 复原现场
        return ans;
    }
}
