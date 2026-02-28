## 分块

### 原理

块状数组其实是类似于暴力算法，利用分块将每个区间分为各自的块,利用区间操作`[L,R]` 当 区间过大可用分块加速更新
对于一个区间可以采用暴力


### 应用

对于不可维护的区间合并信息，同时存在区间修改 可以考虑试试分块


### 模板

#### [~~模板1~~](./_1_query_interval_val.java) 区间更新
- 求区间最值 最大值最小值 这个线段树能做
- 区间求和 这个线段树能做
- 区间 `[L,R]` 求满足 `>= val` 的个数,说明一下
  -  `>val` 可以通过 查询 `val + 1` 来转换
  -  `<val` 可以通过 查询`(R - L + 1) - >=val` 来转换
  -  `==val` 可以通过 查询 `(>=val) - (>= val + 1)` 来转换
  -  `<= val` 可以通过 查询 `(R - L + 1) - (>=val + 1)` 来转换

**注意当前模板存在问题 分块比暴力还慢**

暂时提供一个查询 [``[L,R] >= val`` 的分块](./_2_query_interval_val.java)


基本模板
```java
class Block {

    int bl[],br[],sz,blockNum;

    Block(int[] a) {
        int n = a.length;
        this.initBlockInfo(n);
        // 初始化其他部分信息 ...

    }


    //初始化 块的左右边界 大小等信息
    void initBlockInfo(int n) {
        sz = (int) Math.sqrt(n);
        blockNum = (n + sz - 1) / sz;

        bl = new int[blockNum];
        br = new int[blockNum];
        Arrays.fill(bl, Integer.MAX_VALUE);
        Arrays.fill(br, Integer.MIN_VALUE);


        for (int i = 0; i < n; i++) {
            int blockIndex = i / sz;
            bl[blockIndex] = Math.min(bl[blockIndex], i);
            br[blockIndex] = Math.max(br[blockIndex], i);
        }
    }


    // 分块整体模板
    // 可以更新题目需要的信息进行修改
    // 不是完整块需要暴力 更新or查询
    // 利用块某些性质（如有序等）快速[查询|更新] todo...
    private void handler(int l, int r) {
        int blockL = l / sz;
        int blockR = r / sz;

        if (blockL == blockR) {
            
            if (bl[blockL] == l && r == br[blockR]) {
                // 整块 todo...
            } else {
                for (int i = l; i <= r; i++) {
                   // 暴力 [查询更新] todo...
                }
            }
        } else {
            for (int i = l; i <= br[blockL]; i++) {
                // 暴力 [查询更新] todo...
            }

            
            for (int i = blockL + 1; i <= blockR - 1; i++) {
                // 整块 todo...
            }

            
            
            if (br[blockR] == r) {
                // 右边整块 ...
            } else {
                
                for (int i = bl[blockR]; i <= r; i++) {
                   // 暴力[查询 | 更新] ...
                }
            }
        }
    }

}
```







## 统计区间>=x的个数



### 不带更新版本的

```java
import java.util.*;
import java.io.*;
/**
 * 分块
 * @author Administrator
 *
 */
public class code01 {
	
	public static void main(String[] args) {
		Random rd = new  Random();
		boolean ok = true;
		flag:
		for(int t = 10000;t > 0;t--) {
			int n = Math.max(10,rd.nextInt(100));
			int[] a = new int[n];
			for(int i = 0;i<n;i++) {
				a[i] = Math.max(10,rd.nextInt(100));
			}
			Block b = new Block(a);
			for(int v = 10;v>0;v--) {
				int l = rd.nextInt(n - 1);
				int r = rd.nextInt(n - 1);
				if(l>r) {
					int temp = l;
					l = r;
					r = temp;
				}
				int x = Math.max(10,rd.nextInt(100));
				int result = b.query(l,r,x);
				int except = calc(a,l,r,x);
				if(result != except) {
					ok = false;
					System.out.printf("query:%s {%s %s} {result = %s except = %s} \n",x,l,r,result,except);
					break flag;
				}
			}
		}
		System.out.println(ok ? "ok " :"error");
	}
	
	
	public static int calc(int[] a,int l,int r,int x) {
		int ans = 0;
		for(int i = l;i<=r;i++) {
			if(a[i]>=x)ans++;
		}
		return ans;
	}
	
	
	public static class Block {
		int[] a;
		int n;
		int[] sort;
		int[] bl,br,bi;
		Block(int[] a) {
			this.n = a.length;
			this.a = Arrays.copyOf(a,a.length);
			int sz = ((int)(Math.sqrt(n))) + 1;
			sz = (n + sz - 1) /sz;
			bl = new int[sz + 2];
			br = new int[sz + 2];
			bi = new int[n];
			sort = new int[n];
			for(int i = 0;i < n;i++) {
				bi[i] = i / sz;
				sort[i] = a[i];
			}
			for(int i = 0;i <= sz;i++) {
				bl[i] = Math.min(n - 1,i * sz);
				br[i] = Math.min(n - 1,(i + 1) * sz - 1);
				if(bl[i] == n - 1 || br[i] == n - 1)break;
			}
			
			for(int i = 0;i<sz;i++) {
				Arrays.sort(sort,bl[i],br[i] + 1);
			}
		}
		
		int query(int ql,int qr,int x) {
			int ans = 0;
			if(bi[ql] == bi[qr]) {
                // 这里效率差原因是没对ql,qr 是否在一个完整块中讨论
				for(int i = ql;i<=qr;i++) {
					if(a[i]>=x)ans++;
				}
			}else {
                // 这里效率差原因是没对ql,qr 是否在一个完整块中讨论
				for(int i = ql;i<=br[bi[ql]];i++) {
					if(a[i]>=x)ans++;
				}
                // 这里效率差原因是没对ql,qr 是否在一个完整块中讨论
				for(int i = bl[bi[qr]];i<=qr;i++) {
					if(a[i]>=x)ans++;
				}
				for(int i = bi[ql] + 1;i<=bi[qr] - 1;i++) {
					int l = bl[i],r = br[i];
					while(l<=r) {
						int mid = l + ((r - l)>>1);
						if(sort[mid]>=x)r = mid - 1;
						else l = mid + 1;
					}
					ans += br[i] - l + 1;
				}
			}
			return ans;
		}
		
		
	}
}

```



### 带添加的

**为了便于理解，这题提供一个普通版本的分块**

```java
public static class Block {
		int[] a;
		int n;
		int[] sort;
		int[] bl, br, bi;
		int[] adv;

		Block(int[] arr0) {
			this.n = arr0.length;
			this.a = Arrays.copyOf(arr0,arr0.length);
			int sz = ((int) (Math.sqrt(n))) + 2;
			sz = (n + sz - 1 / sz);
			bl = new int[sz + 2];
			br = new int[sz + 2];
			bi = new int[n];
			sort = new int[n];
			adv = new int[sz + 2];
			for (int i = 0; i < n; i++) {
				bi[i] = i / sz;
				adv[Math.min(i, sz - 1)] = 0;
			}
			for (int id = 0; id <= sz; id++) {
				bl[id] = Math.min(n - 1, id * sz);
				br[id] = Math.min(n - 1, (id + 1) * sz - 1);
				if (bl[id] == n - 1 || br[id] == n - 1)
					break;
			}

			for (int id = 0; id < sz; id++) {
				rest(id);
			}
		}

		void rest(int id) {
			for (int k = bl[id]; k <= br[id]; k++) {
				sort[k] = a[k];
			}
			Arrays.sort(sort, bl[id], br[id] + 1);
		}

		void add(int ql, int qr, int x) {
			if (bi[ql] == bi[qr]) {
				for (int i = ql; i <= qr; i++) {
					a[i] += x;
				}
				rest(bi[ql]);
			} else {
				for (int i = ql; i <= br[bi[ql]]; i++) {
					a[i] += x;
				}
				rest(bi[ql]);
				for (int i = bl[bi[qr]]; i <= qr; i++) {
					a[i] += x;
				}
				rest(bi[qr]);
				for (int i = bi[ql] + 1; i <= bi[qr] - 1; i++) {
					adv[i] += x;
				}
			}
		}

		int query(int ql, int qr, int x) {
			int ans = 0;
			if (bi[ql] == bi[qr]) {
                // 这里效率差原因是没对ql,qr 是否在一个完整块中讨论
				for (int i = ql; i <= qr; i++) {
					if (a[i] + adv[bi[ql]] >= x)
						ans++;
				}
			} else {
                // 这里效率差原因是没对ql,qr 是否在一个完整块中讨论
				for (int i = ql; i <= br[bi[ql]]; i++) {
					if (a[i] + adv[bi[ql]] >= x)
						ans++;
				}
                // 这里效率差原因是没对ql,qr 是否在一个完整块中讨论
				for (int i = bl[bi[qr]]; i <= qr; i++) {
					if (a[i] + adv[bi[qr]] >= x)
						ans++;
				}
				for (int id = bi[ql] + 1; id <= bi[qr] - 1; id++) {
					int l = bl[id], r = br[id];
					while (l <= r) {
						int mid = l + ((r - l) >> 1);
						if (sort[mid] + adv[id] >= x)r = mid - 1;
						else l = mid + 1;
					}
					ans += br[id] - l + 1;
				}
			}
			return ans;
		}

	}
```









```java
import java.util.*;
import java.io.*;

/**
 * 分块
 * 
 * @author Administrator
 *
 */
public class code02 {

	public static void main(String[] args) {
		Random rd = new Random();
		boolean ok = true;
		flag: for (int t = 10000, ttt = 1; t > 0; t--) {
			int n = Math.max(10, rd.nextInt(100));
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = Math.max(10, rd.nextInt(100));
			}
			Block b = new Block(a);
			for (int v = 10; v > 0; v--) {
				int l = rd.nextInt(n - 1);
				int r = rd.nextInt(n - 1);
				int op = rd.nextInt(10);
				if (l > r) {
					int temp = l;
					l = r;
					r = temp;
				}
				int x = Math.max(10, rd.nextInt(100));
				if (op <= 5) {
					b.add(l, r, x);
					add(a, l, r, x);
					// System.out.println("add");
				}
				int result = b.query(l, r, x);
				int except = calc(a, l, r, x);
				if (result != except) {
					ok = false;
					System.out.printf("query:%s {%s %s} {result = %s except = %s} \n", x, l, r, result, except);
					break flag;
				}
				// System.out.println("pass " + ttt);
				ttt++;

			}
		}
		System.out.println(ok ? "ok " : "error");
	}

	public static int calc(int[] a, int l, int r, int x) {
		int ans = 0;
		for (int i = l; i <= r; i++) {
			if (a[i] >= x)
				ans++;
		}
		return ans;
	}

	public static int add(int[] a, int l, int r, int x) {
		int ans = 0;
		for (int i = l; i <= r; i++) {
			a[i] += x;
		}
		return ans;
	}

		
	public static class Block {
		long[] a;
		int n;
		long[] sort;
		int[] bl, br, bi;
		long[] adv;

		Block(int[] arr0) {
			this.n = arr0.length;
			
			this.a = new long[n];
			for(int i = 0;i < n;i++) {
				this.a[i] = arr0[i];
			}
			int sz = ((int) (Math.sqrt(n))) + 1;
			sz = (n + sz - 1 / sz);
			bl = new int[sz + 2];
			br = new int[sz + 2];
			bi = new int[n];
			sort = new long[n];
			adv = new long[sz + 2];
			for (int i = 0; i < n; i++) {
				bi[i] = i / sz;
				adv[Math.min(i, sz - 1)] = 0;
			}
			for (int id = 0; id <= sz; id++) {
				bl[id] = Math.min(n - 1, id * sz);
				br[id] = Math.min(n - 1, (id + 1) * sz - 1);
				if (bl[id] == n - 1 || br[id] == n - 1)
					break;
			}

			for (int id = 0; id < sz; id++) {
				rest(id);
			}
		}

		void rest(int id) {
			for (int k = bl[id]; k <= br[id]; k++) {
				sort[k] = a[k];
			}
			Arrays.sort(sort, bl[id], br[id] + 1);
		}

		void add(int ql, int qr, int x) {
			if (bi[ql] == bi[qr]) {
				if(bl[bi[ql]] == ql && br[bi[qr]] == qr) {
					adv[bi[ql]] += x;
				}else {
					for (int i = ql; i <= qr; i++) {
						a[i] += x;
					}
					rest(bi[ql]);
				}
			} else {
				if(bl[bi[ql]] == ql && br[bi[qr]] < qr) {
					adv[bi[ql]] += x;
				}else {
					for (int i = ql; i <= br[bi[ql]]; i++) {
						a[i] += x;
					}
					rest(bi[ql]);
				}
				
				if(br[bi[qr]] == qr && bl[bi[qr]] > ql) {
					adv[bi[qr]] += x;
				}else {
					for (int i = bl[bi[qr]]; i <= qr; i++) {
						a[i] += x;
					}
					rest(bi[qr]);
				}
				for (int i = bi[ql] + 1; i <= bi[qr] - 1; i++) {
					adv[i] += x;
				}
			}
		}

		int query(int ql, int qr, int x) {
			int ans = 0;
			if (bi[ql] == bi[qr]) {
				if(bl[bi[ql]] == ql && br[bi[qr]] == qr) {
					ans += lowerbound(ql,qr,x);
				}else {
					for (int i = ql; i <= qr; i++) {
						if (a[i] + adv[bi[ql]] >= x)
							ans++;
					}
				}
				
			} else {
				if(bl[bi[ql]] == ql && br[bi[qr]] < qr) {
					ans += lowerbound(ql,br[bi[ql]],x);
				}else {
					for (int i = ql; i <= br[bi[ql]]; i++) {
						if (a[i] + adv[bi[ql]] >= x)
							ans++;
					}
				}
				
				if(br[bi[qr]] == qr && bl[bi[qr]] > ql) {
					ans += lowerbound(bl[bi[qr]],qr,x);
				}else {
					for (int i = bl[bi[qr]]; i <= qr; i++) {
						if (a[i] + adv[bi[qr]] >= x)
							ans++;
					}
				}
				
				for (int id = bi[ql] + 1; id <= bi[qr] - 1; id++) {
					ans += lowerbound(bl[id],br[id],x);
				}
			}
			return ans;
		}
		
		
		int lowerbound(int l,int r,int x) {
			int ans = 0;
			int id = bi[l];
			while (l <= r) {
				int mid = l + ((r - l) >> 1);
				if (sort[mid] + adv[id] >= x)r = mid - 1;
				else l = mid + 1;
			}
			ans += br[id] - l + 1;
			return ans;
		}

	}
	
}

```





##  区间最值

写着玩的😰

```java
import java.util.*;

/**
 * 分块
 * 
 * @author Administrator
 *
 */
public class code04 {

	public static void main(String[] args) {
		Random rd = new Random();
		boolean ok = true;
		flag: for (int t = 10000, ttt = 1; t > 0; t--) {
			int n = Math.max(10, rd.nextInt(20));
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = Math.max(10, rd.nextInt(100));
			}
			Block b = new Block(a);
			for (int v = 10; v > 0; v--) {
				int l = rd.nextInt(n - 1);
				int r = rd.nextInt(n - 1);
				int op = rd.nextInt(10);
				if (l > r) {
					int temp = l;
					l = r;
					r = temp;
				}
				int x = Math.max(10, rd.nextInt(100));
				if (op <= 5) {
					b.add(l, r, x);
					add(a, l, r, x);
					// System.out.println("add");
				}
				int result = (int) b.query(l, r);
				int except = calc(a, l, r);
				if (result != except) {
					ok = false;
					System.out.println(Arrays.toString(a));
					System.out.printf("query:%s {%s %s} {result = %s except = %s} \n", x, l, r, result, except);
					break flag;
				}
				// System.out.println("pass " + ttt);
				ttt++;

			}
		}
		System.out.println(ok ? "ok " : "error");
	}

	public static int calc(int[] a, int l, int r) {
		int ans = a[l];
		for (int i = l; i <= r; i++) {
			ans = Block.isMax ? Math.max(ans, a[i]): Math.min(ans, a[i]);
		}
		return ans;
	}

	public static int add(int[] a, int l, int r, int x) {
		int ans = 0;
		for (int i = l; i <= r; i++) {
			a[i] += x;
		}
		return ans;
	}

	// 分块代替线段树查询区间最值问题
	public static class Block {
		long[] a;
		int n;
		long[] sort;
		int[] bl, br, bi;
		long[] adv;
		
		public static boolean isMax = true;

		Block(int[] arr0) {
			this.n = arr0.length;

			this.a = new long[n];
			for (int i = 0; i < n; i++) {
				this.a[i] = arr0[i];
			}
			int sz = ((int) (Math.sqrt(n))) + 1;
			sz = (n + sz - 1 )/ sz;
			bl = new int[sz + 2];
			br = new int[sz + 2];
			bi = new int[n];
			sort = new long[n];
			adv = new long[sz + 2];
			for (int i = 0; i < n; i++) {
				bi[i] = i / sz;
			}
			for (int id = 0; id <= sz; id++) {
				bl[id] = Math.min(n - 1, id * sz);
				br[id] = Math.min(n - 1, (id + 1) * sz - 1);
				if (bl[id] == n - 1 || br[id] == n - 1)
					break;
			}

			for (int id = 0; id < sz; id++) {
				rest(id);
			}
		}

		void rest(int id) {
			for (int k = bl[id]; k <= br[id]; k++) {
				sort[k] = a[k];
			}
			Arrays.sort(sort, bl[id], br[id] + 1);
		}

		void add(int ql, int qr, int x) {
			if (bi[ql] == bi[qr]) {
				if (bl[bi[ql]] == ql && br[bi[qr]] == qr) {
					adv[bi[ql]] += x;
				} else {
					for (int i = ql; i <= qr; i++) {
						a[i] += x;
					}
					rest(bi[ql]);
				}
			} else {
				if (bl[bi[ql]] == ql && br[bi[qr]] < qr) {
					adv[bi[ql]] += x;
				} else {
					for (int i = ql; i <= br[bi[ql]]; i++) {
						a[i] += x;
					}
					rest(bi[ql]);
				}

				if (br[bi[qr]] == qr && bl[bi[qr]] > ql) {
					adv[bi[qr]] += x;
				} else {
					for (int i = bl[bi[qr]]; i <= qr; i++) {
						a[i] += x;
					}
					rest(bi[qr]);
				}
				for (int i = bi[ql] + 1; i <= bi[qr] - 1; i++) {
					adv[i] += x;
				}
			}
		}

		
		long op(long x,long y) {
			return isMax ? Math.max(x,y) : Math.min(x,y);
		}
		long query(int ql, int qr) {
			long ans = a[ql] + adv[bi[ql]];
			if (bi[ql] == bi[qr]) {
				if (bl[bi[ql]] == ql && br[bi[qr]] == qr) {
					return (isMax ? sort[br[bi[ql]]] : sort[bl[bi[ql]]]) + adv[bi[ql]];
				} else {
					for (int i = ql; i <= qr; i++) {
						ans = op(a[i] + adv[bi[ql]], ans);
					}
				}

			} else {
				if (bl[bi[ql]] == ql && br[bi[qr]] < qr) {
					ans = op((isMax ? sort[br[bi[ql]]] : sort[bl[bi[ql]]]) + adv[bi[ql]], ans);
				} else {
					for (int i = ql; i <= br[bi[ql]]; i++) {
						ans = op(a[i] + adv[bi[ql]], ans);
					}
				}
				if (br[bi[qr]] == qr && bl[bi[qr]] > ql) {
					ans = op((isMax ? sort[br[bi[qr]]] : sort[bl[bi[qr]]]) + adv[bi[qr]], ans);
				} else {
					for (int i = bl[bi[qr]]; i <= qr; i++) {
						ans = op(a[i] + adv[bi[i]], ans);
					}
				}
				for (int id = bi[ql] + 1; id <= bi[qr] - 1; id++) {
					ans = op((isMax ? sort[br[id]] : sort[bl[id]])  + adv[id], ans);
				}
			}
			return ans;
		}

	}
}

```





## 区间众数

```c++
#include <algorithm>
#include <bits/stdc++.h>
using namespace std;
#ifdef __IS_LEETCODE__
#include "D://template_code//leetcode.cpp"
#endif

#ifdef __IS_LOCAL__
#include "D://template_code//debug.cpp"
using namespace dbg;
#else
#define debug(...) ((void)0)
#endif


// https://www.luogu.com.cn/problem/P4168
// https://paste.ubuntu.com/p/8QVBB4W3Jm/

using ll = long long;

constexpr int inf = 1e9,N = 2e5 + 10,mod = 1e9 + 7;


void solve() {

    int n,m;
    cin >> n >> m;

    vector<int> a(n);
    for(int i = 0; i < n;i++ ){
        cin >> a[i];
    }



    vector<int> b = a;
    int sz = 1;
    sort(b.begin(),b.end());
    for(int i = 0 ;i < n;i++) {
        if(b[i] != b[sz - 1]) {
            b[sz++] = b[i];
        }
    }


    vector<int> sv = a;
    for(int i = 0;i < n;i++) {
        sv[i] = lower_bound(b.begin(),b.begin()+sz,a[i]) - b.begin();
    }


    int blen = max(1,int(sqrt(n)));
    int bcount = (n + blen - 1) / blen;

    vector<int> bi(n),bl(bcount),br(bcount);
    for(int i = 0;i < n;i++) {
        bi[i] = i / blen;
    }

    for(int i = 0;i < bcount;i++) {
        bl[i] = min(n - 1,i * blen);
        br[i] = min(n - 1,(i + 1) * blen - 1);
        if(bl[i] == n - 1 || br[i] == n - 1){
            break;
        }
    }

    vector<vector<int>> freq(bcount + 1,vector<int>(sz + 1,0)),mode(bcount + 1,vector<int>(bcount + 1,0));
    



    // init freq
    for(int id = 0;id < bcount;id++) {
        for(int i = bl[id];i<=br[id];i++) {
            freq[id][sv[i]]++;
        }
        if(id == 0) continue;
        for(int x = 0;x < sz;x++){
            freq[id][x] += freq[id - 1][x];
        }

    }



    auto get_cnt = [&](int idl,int idr,int x) -> int {
        if( idl > idr || x < 0) {
            return 0;
        }
        return freq[idr][x] - ( (idl - 1>= 0) ? freq[idl - 1][x] : 0);
    };



    // init mode
    for(int id = 0;id < bcount;id++) {
        vector<int> cnt(sz + 1);
        int mx_cnt = 0;
        for(int i = bl[id];i<=br[id];i++) {
            cnt[sv[i]]++;
            mx_cnt = max(mx_cnt,cnt[sv[i]]);
        }
        int cur = inf;
        for(int i = bl[id];i<=br[id];i++) {
            if(cnt[sv[i]] == mx_cnt) {
               cur = min(cur,sv[i]);
            }
        }
        mode[id][id] = cur;
    }

    // build mode
    for(int idl = 0;idl < bcount;idl++) {
        for(int idr = idl + 1;idr < bcount;idr++) {
            int most = mode[idl][idr - 1] ;
            int most_cnt = get_cnt(idl, idr,most);
            for(int k = bl[idr];k<=br[idr];k++) {
                int x = sv[k];
                int cnt = get_cnt(idl,idr,x);
                if(cnt > most_cnt || cnt == most_cnt && x < most) {
                    most = x;
                    most_cnt = max(cnt,most_cnt);
                }
            }
            mode[idl][idr] = most;
        }
    }




    auto query = [&](int l,int r) -> int {
        vector<int> cnt(sz + 1);
        int most = inf;
        int most_cnt = 0;
        if(bi[l] == bi[r]) {
            // debug("----ok",l,r);
            if(bl[bi[l]] == l && br[bi[r]] == r) {
                int id = bi[l];
                most =  mode[id][id];
                // debug("xxxx",l,r,most);
            }else{
                for(int i = l;i<=r;i++) {
                    cnt[sv[i]]++;
                    most_cnt = max(most_cnt,cnt[sv[i]]);
                }
                for(int i = l;i<=r;i++) {
                    if(cnt[sv[i]] == most_cnt) {
                        most = min(most,sv[i]);
                    }
                }
            }
        }else{
            most = (bi[l] + 1) <= ( bi[r] - 1) ? mode[bi[l] + 1][bi[r] - 1] : -1;
            most_cnt = get_cnt(bi[l]+ 1, bi[r] - 1,most);
            // debug(l,r,most,most_cnt);
            if(most != -1) {
                // debug(bl[bi[l] + 1],br[bi[r] - 1],most,most_cnt);
            }

            for(int i = l;i<=br[bi[l]];i++) {
                cnt[sv[i]]++;
                // debug(bi[l] + 1, bi[r] - 1,get_cnt(bi[l] + 1,bi[r] - 1,sv[i]));
                // debug(i,sv[i],cnt[sv[i]],, bi[r] - 1,sv[i]));
                int cur_cnt = cnt[sv[i]] + get_cnt(bi[l] + 1, bi[r] - 1,sv[i]);
                // debug("L",i,b[sv[i]],cur_cnt);
                if(cur_cnt > most_cnt || cur_cnt == most_cnt && sv[i] < most) {
                    most_cnt = cur_cnt;
                    most = sv[i];
                }
            }

            for(int i = bl[bi[r]];i<=r;i++) {
                cnt[sv[i]]++;
                int cur_cnt = cnt[sv[i]] + get_cnt(bi[l] + 1, bi[r] - 1,sv[i]);
                // debug("R",i,b[sv[i]],cur_cnt);
                if(cur_cnt > most_cnt || cur_cnt == most_cnt && sv[i] < most) {
                    most_cnt = cur_cnt;
                    most = sv[i];
                    // cout <<"update " << most << "\n";
                }
            }
        }
        return most;
    };


    // 暴力方法验证
    auto forces = [&](int l,int r) -> int {
        map<int,int> cnt;
        int ans = inf;
        int mx = 0;
        for(int i = l;i<=r;i++) {
            cnt[a[i]]++;
            mx = max(mx,cnt[a[i]]);
        }
        for(int i = l;i<=r;i++) {
            if(cnt[a[i]] == mx) {
                ans = min(ans,a[i]);
            }
        }
        return ans;
    };

    bool ok = 1;

    // cout << ((ok = (b[query(0,2)] == forces(0,2)))) << "\n";

    // for(int i = 0;i<n && ok;i++) {
    //     for(int j = i;j<n;j++) {
    //         if(!(b[query(i,j)] == forces(i,j))) {
    //             debug("{",i,j,"}",b[query(i,j)],forces(i,j));
    //             ok = 0;
    //             break;
    //         }
    //     }
    // }

    // debug(ok ? "ok" :"NO");
    // cout << (b[query(0,3)] == forces(0,3))  << "except " << (forces(0,3))<< '\n';

    for(int ans = 0,x,y,l,r;m > 0;m--) {

        cin >> x >> y;

        l = ((ans + x - 1) % n + 1);
        r = ((ans + y - 1) % n + 1);

        if(l > r) {
            swap(l,r);
        }
        l--,r--;
        // continue;
        ans = b[query(l,r)];

        // debug(l,r,query(l,r),ans);
        cout << ans << "\n";
    }
    
}



int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    int tt = 1;
    // cin >> tt;
    while(tt--)solve();
    return 0;
}

```



力扣 3636 [查询超过阈值频率最高元素](https://leetcode.cn/problems/threshold-majority-queries/)

`https://leetcode.cn/problems/threshold-majority-queries/submissions/684996078/`

```c++

using ll = long long;

constexpr int inf = 1e9, N = 1e4 + 10, mod = 1e9 + 7;



struct custom_hash {
  // http://xorshift.di.unimi.it/splitmix64.c
  static uint64_t splitmix64(uint64_t x) {
    x += 0x9e3779b97f4a7c15;
    x = (x ^ (x >> 30)) * 0xbf58476d1ce4e5b9;
    x = (x ^ (x >> 27)) * 0x94d049bb133111eb;
    return x ^ (x >> 31);
  }

  template <typename T> size_t operator()(T x) const {
    // static_assert(std::is_integral_v<T>, "Only supports integer types");
    static const uint64_t FIXED_RANDOM =
        chrono::steady_clock::now().time_since_epoch().count();
    return splitmix64(static_cast<uint64_t>(x) + FIXED_RANDOM);
  }

  template <typename T1, typename T2>
  size_t operator()(const std::pair<T1, T2> &p) const {
    uint64_t x = (uint64_t(p.first) << 32) | (uint64_t(p.second) & 0xFFFFFFFF);
    return (*this)(x);
  }

  template <typename T1, typename T2, typename T3>
  size_t operator()(const std::pair<T1, std::pair<T2, T3>> &p) const {
    uint64_t x = (uint64_t(p.first) << 32) ^ (uint64_t(p.second.first) << 16) ^
                 p.second.second;
    return (*this)(x);
  }

  template <typename T, size_t N>
  size_t operator()(const std::array<T, N> &arrays) const {
    uint64_t hash = 0;
    for (T x : arrays) {
      hash ^= splitmix64(static_cast<uint64_t>(x) + 0x9e3779b9 + (hash << 6) +
                         (hash >> 2));
    }
    return hash;
  }
};


int b[N],a[N],sv[N],freq[102][N],bi[N],bl[N],br[N],mode[102][102],cnt[N];

class Solution {
public:
    vector<int> subarrayMajority(vector<int>& cc, vector<vector<int>>& queries) {
        int n = cc.size();

        for(int i = 0;i<n;i++) {
            a[i] = sv[i] = b[i] = cc[i];
        }

        int sz = 1;
        sort(b,b + n);
        for (int i = 0; i < n; i++) {
            if (b[i] != b[sz - 1]) {
                b[sz++] = b[i];
            }
        }

        // 特判全部相等情况
        if(sz==1){
            vector<int> ans;
            for(auto& q : queries){
                if(q[2]>n){
                    ans.push_back(-1);
                }else{
                    ans.push_back(b[0]);
                }
            }
            return ans;
        }
        
        unordered_map<array<int,2>,array<int,2>,custom_hash> cache;
    
        for (int i = 0; i < n; i++) {
            sv[i] = lower_bound(b, b + sz, a[i]) - b;
        }
        int blen = min(100,int(sqrt(n)) + 30);
        int bcount = (n + blen - 1) / blen;
        for (int i = 0; i < n; i++) {
            bi[i] = i / blen;
        }

        for (int i = 0; i < bcount; i++) {
            bl[i] = min(n - 1, i * blen);
            br[i] = min(n - 1, (i + 1) * blen - 1);
            if (bl[i] == n - 1 || br[i] == n - 1) {
                break;
            }
        }


        for (int id = 0; id < bcount; id++) {
            for (int x = 0; x < sz; x++) {
                freq[id][x]=0;
            }
        }

        for (int id = 0; id < bcount; id++) {
            for (int i = bl[id]; i <= br[id]; i++) {
                freq[id][sv[i]]++;
            }
            if (id == 0)
                continue;
            for (int x = 0; x < sz; x++) {
                freq[id][x] += freq[id - 1][x];
            }
        }

        auto get_cnt = [&](int idl, int idr, int x) -> int {
            if (idl > idr || x < 0) {
                return 0;
            }
            return freq[idr][x] - ((idl - 1 >= 0) ? freq[idl - 1][x] : 0);
        };

        for (int id = 0; id < bcount; id++) {
            for(int i = 0;i<sz;i++) cnt[i]=0;
            int mx_cnt = 0;
            for (int i = bl[id]; i <= br[id]; i++) {
                cnt[sv[i]]++;
                mx_cnt = max(mx_cnt, cnt[sv[i]]);
            }
            int cur = inf;
            for (int i = bl[id]; i <= br[id]; i++) {
                if (cnt[sv[i]] == mx_cnt) {
                    cur = min(cur, sv[i]);
                }
            }
            
            mode[id][id] = cur;
        }

        for(int i = 0;i<sz;i++) cnt[i]=0;
        for (int idl = 0; idl < bcount; idl++) {
            for (int idr = idl + 1; idr < bcount; idr++) {
                int most = mode[idl][idr - 1];
                int most_cnt = get_cnt(idl, idr, most);
                for (int k = bl[idr]; k <= br[idr]; k++) {
                    int x = sv[k];
                    int cnt = get_cnt(idl, idr, x);
                    if (cnt > most_cnt || cnt == most_cnt && x < most) {
                        most = x;
                        most_cnt = max(cnt, most_cnt);
                    }
                }
                mode[idl][idr] = most;
            }
        }

        auto query = [&](int l, int r) -> array<int,2> {
            if (sz == 1) return {0,n};
            if(cache.count({l,r})) return cache[{l,r}];
            int most = inf;
            int most_cnt = 0;
            if (bi[l] == bi[r]) {
                if (bl[bi[l]] == l && br[bi[r]] == r) {
                    int id = bi[l];
                    most = mode[id][id];
                    most_cnt = get_cnt(id,id,most);
                    return cache[{l,r}] = {most,most_cnt};
                } 

                for (int i = l; i <= r; i++) {
                    cnt[sv[i]]++;
                    most_cnt = max(most_cnt, cnt[sv[i]]);
                }
                for (int i = l; i <= r; i++) {
                    if (cnt[sv[i]] == most_cnt) {
                        most = min(most, sv[i]);
                    }
                }

                // 清空
                for (int i = l; i <= r; i++) {
                    cnt[sv[i]] = 0;
                }
            } else {
                most = (bi[l] + 1) <= (bi[r] - 1) ? mode[bi[l] + 1][bi[r] - 1] : -1;
                most_cnt = get_cnt(bi[l] + 1, bi[r] - 1, most);
                for (int i = l; i <= br[bi[l]]; i++) {
                    cnt[sv[i]]++;
                    int cur_cnt =cnt[sv[i]] + get_cnt(bi[l] + 1, bi[r] - 1, sv[i]);
                    if (cur_cnt > most_cnt ||
                        cur_cnt == most_cnt && sv[i] < most) {
                        most_cnt = cur_cnt;
                        most = sv[i];
                    }
                }

                for (int i = bl[bi[r]]; i <= r; i++) {
                    cnt[sv[i]]++;
                    int cur_cnt = cnt[sv[i]] + get_cnt(bi[l] + 1, bi[r] - 1, sv[i]);
                    if (cur_cnt > most_cnt ||
                        cur_cnt == most_cnt && sv[i] < most) {
                        most_cnt = cur_cnt;
                        most = sv[i];
                    }
                }
                

                // 清空
                for (int i = l; i <= br[bi[l]]; i++) {
                    cnt[sv[i]] = 0;
                }

                for (int i = bl[bi[r]]; i <= r; i++) {
                    cnt[sv[i]] = 0;
                }
            }
            return cache[{l,r}] = {most, most_cnt};
        };

        vector<int> ans;
        for (auto& q : queries) {
            int l = q[0],r = q[1],mi = q[2];
            if(mi>r-l+1){
                ans.push_back(-1);
                continue;
            }
            auto [x,cnt] = query(l,r);
            if(cnt >= mi) {
                ans.push_back(b[x]);
            }else{
                ans.push_back(-1);
            }
        }
        return ans;
    }
};
```

分块模板2

类似于势能线段树,修改次数不会很多

[小橙的异或和 ](https://ac.nowcoder.com/acm/contest/128675/F)
```cpp
#include <bits/stdc++.h>
using namespace std;
#ifdef __IS_LEETCODE__
#include "D://template_code//leetcode.cpp"
#endif

#ifdef __IS_LOCAL__
#include "D://template_code//debug.cpp"
using namespace dbg;
#else
#define debug(...) ((void)0)
#endif

// init array start ===================================
template <typename T, size_t N>
void mst(T (&arr)[N], const T& value, size_t size = N) {size = std::min(size, N);for (size_t i = 0; i < size; ++i) arr[i] = value;}
template <typename T, size_t N, typename... Args>
void mst(T (&arr)[N],const typename std::remove_all_extents<T>::type& value,size_t first_dim, Args... dims) {first_dim = std::min(first_dim, N);for (size_t i = 0; i < first_dim; ++i) mst(arr[i], value, dims...);}
// init array end ===================================


#define F(i, s, e, t)  for (int(i) = (s); (t) >= 1 ? ((i) <= (e)) : ((i) >= (e)); (i) += (t))
#define all(v) (v).begin(), (v).end()
#define lower(a, x) std::lower_bound((a).begin(), (a).end(), x) - (a).begin()
#define upper(a, x) std::upper_bound((a).begin(), (a).end(), x) - (a).begin()
#define len(x) int((x).size())
#define pb push_back
#define qb pop_back
#define pf push_front
#define qf pop_front
#define rnq(a) do {std::sort((a).begin(), (a).end());(a).erase(std::unique((a).begin(), (a).end()),(a).end());} while (0)

using ll = long long;
using ull = unsigned long long;
using pll = std::pair<ll, ll>;
using pii = std::pair<int, int>;
using vi = std::vector<int>;
using vll = std::vector<ll>;
using vvi = std::vector<vi>;
constexpr ll  linf = 1e18;
constexpr int inf = 1e9,mod = 1e9 + 7;



constexpr int N = 2e5 + 1000;

int bi[N],bl[N],br[N],a[N];
int n,q,blen,bcount;

// 补充添加部分开始
int bmx[N],sums[N];
// 补充添加部分结束


void init(){
    bcount = max(1,int(sqrt(n)) + 1);
    blen = (n + bcount - 1)  / bcount;
    for(int i = 0;i < n;i++) {
        bi[i] = i / blen;
    }

    for(int b = 0;b < bcount;b++) {
        bl[b] = b * blen;
        br[b] = min(n - 1,(b + 1) * blen - 1);
        if(br[b]==n-1)break;
    }

    // cout << " block \n";
    // for(int i = 0;i < bcount ;i++) {
    //     if(i>0&&bl[i]==0)break;
    //     cout << "["<<bl[i] << "," << br[i] << "]\n";
    // }

    // 补充添加部分开始
    for(int b = 0;b < bcount;b++) {
        sums[b] = 0;
        bmx[b] = 0;
        for(int i = bl[b];i <= br[b];i++){
            sums[b] ^= a[i];
            bmx[b] = max(bmx[b],a[i]);
        }
    }

    // 补充添加部分结束
}

// 查询一个块
int queryBlock(int b) {
    return sums[b];
}


// 更新一个块
void updateBlock(int b) {
    bmx[b] = 0;
    sums[b] = 0;
    for(int i = bl[b];i<=br[b];i++) {
        sums[b] ^= a[i];
        bmx[b] = max(bmx[b],a[i]);        
    }
}



void update(int l,int r) {
    if(bi[l]==bi[r]) {
        if(bmx[bi[r]]==0)return;
        for(int i = l;i<=r;i++) {
            a[i] /= i - l + 1;
        }
        updateBlock(bi[r]);
    }else {
        for(int i = l;i<=br[bi[l]];i++){
            a[i] /= i - l + 1;
        }
        updateBlock(bi[l]);
        for(int b = bi[l] + 1;b < bi[r];b++) {
            if(bmx[b]==0)continue;
            for(int i = bl[b];i<=br[b];i++) {
                a[i] /= i - l + 1;
            }
            updateBlock(b);
        }
        if(bi[r] > bi[l]) {
            for(int i = bl[bi[r]];i<=r;i++) {
                a[i] /= i - l + 1;
            }
            updateBlock(bi[r]);
        }
    }
    
}




int query(int l,int r) {
    int ans = 0;
    if(bi[l]==bi[r]) {
        if(bmx[bi[r]]==0)return 0;
        for(int i = l;i<=r;i++) {
            ans ^= a[i];
        }
    }else {
        for(int i = l;i<=br[bi[l]];i++) {
            ans ^= a[i];
        }
        for(int b = bi[l] + 1;b < bi[r];b++) {
            ans ^= sums[b];
        }
        if(bi[r] > bi[l]) {
            for(int i = bl[bi[r]];i<=r;i++) {
                ans ^= a[i];
            }
        }
    }
    return ans;
}



void clear(int n) {
    for(int i = 0;i < n + 1;i++) {
        bi[i] = bl[i] = br[i] = sums[i] = bmx[i] = 0;
    }
}

void solve(){
    cin >> n >> q;
    clear(n);


    for(int i = 0;i < n;i++) {
        cin >> a[i];
    }
   
    init();
    // for(int i  = 0;i<n;i++) {
    //     cout << a[i] << " \n"[i == n - 1];
    // }
    for(int i = 0;i < q;i++) {
        int op,l,r;
        cin >> op;
        if(op == 1) {
            cin >> l >> r;
            l--,r--;
            // debug(l,r);
            update(l,r);
        }else{
            l = 0,r = n - 1;
            cout << query(l,r) << "\n";
        }
    }

}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    int tt = 1;
    // cin >> tt;
    while(tt--)solve();
    return 0;
}
```