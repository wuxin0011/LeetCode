## 图

- [链式前向星构建图](./LinkStartBuildGraph.java)
- [二叉树转换成图](./TreeNodeGraph.java)
- [单源最短路径_Dijkstra](./Dijkstra.java) 
- [红蓝染色图](https://leetcode.cn/problems/maximize-the-number-of-target-nodes-after-connecting-trees-ii/) 
- 最小生成树
  - [Kruskal](./Kruskal.java)
  - [Prim](./Prim.java)
- [01BFS_A星](./AStar.java)
- LCA
  - [LCA-倍增法](./LCA_ST_Template.java) 
  - [LCA-Tarjan](./LCA_Tarjan_Template.java) 
- [树的重心](./GraphCenter.java)
- [树链剖分](#)

- [网络费用流](./Max_Min_Flow.java)
## 换根 DP

- [树中距离之和
  ](https://leetcode.cn/problems/sum-of-distances-in-tree/description/) [提交记录](https://leetcode.cn/submissions/detail/623836474/)


## 树链剖分



### 与线段树结合 区间修改

[P3384 【模板】重链剖分 / 树链剖分](https://www.luogu.com.cn/record/257501804)

[P2146 [NOI2015] 软件包管理器 ](https://www.luogu.com.cn/record/257531298)

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

using ll = long long;

constexpr int inf = 1e9,N = 1e5 + 100,mod = 1e9 + 7;

int head[N],to[N<<1],nxt[N<<1],we[N<<1],cnt,dfncnt,dfn[N],sz[N],son[N],pa[N],a[N],seg[N],deep[N],top[N];


// ----------------------------------------seg

ll addTag[N<<2],sums[N<<2];
int MOD,n,opcnt,root;

void up(int i) {
    sums[i] = sums[i << 1] + sums[i << 1 | 1];
    sums[i] %= MOD;
}

void build(int l,int r,int i) {
    addTag[i]=0;
    if(l==r){
        sums[i] = a[seg[l]];
    }else{
        int mid = l + ((r - l)>>1);
        build(l,mid,i<<1);
        build(mid+1,r,i<<1|1);
        up(i);
    }
}
void addLazy(int i,int sz,ll x){
    sums[i] += 1LL * x * sz;
    sums[i] %= MOD;
    addTag[i] += x;
    addTag[i] %= MOD;
}

void down(int i,int l,int r) {
    int mid = l + ((r - l)>>1);
    int ln = mid - l + 1,rn = r - mid;
    if(addTag[i]!=0){
        addLazy(i<<1,ln,addTag[i]);
        addLazy(i<<1|1,rn,addTag[i]);
        addTag[i]=0;
    }
}

void add(int ql,int qr,ll x,int l,int r,int i) {
    if(ql<=l&&r<=qr){
        addLazy(i,r - l + 1,x);
    }else{
        int mid = l + ((r - l)>>1);
        down(i,l,r);
        if(ql<=mid){
            add(ql,qr,x,l,mid,i<<1);
        }
        if(qr>mid){
            add(ql,qr,x,mid+1,r,i<<1|1);
        }
        up(i);
    }
}

ll query(int ql,int qr,int l,int r,int i) {
    if(ql<=l&&r<=qr){
        return sums[i];
    }else{
        int mid = l + ((r - l)>>1);
        ll ans = 0;
        down(i,l,r);
        if(ql<=mid){
            ans += query(ql,qr,l,mid,i<<1);
            ans %= MOD;
        }
        if(qr>mid){
            ans += query(ql,qr,mid+1,r,i<<1|1);
            ans %= MOD;
        }
        return ans;
    }
}


// ----------------------------------------seg



// build edge

void clear(int n) {
    for(int i = 0;i<=n;i++) {
        head[i]=0;
        dfn[i] = 0;
        son[i] = 0;
    }
    cnt=0;
    dfncnt=0;
}

void addEdge(int u,int v,int w) {
    ++cnt;
    nxt[cnt]=head[u],to[cnt]=v,we[cnt]=w,head[u]=cnt;
}



// dfs1

void dfs1(int u,int fa,int d) {
    sz[u] = 1;
    deep[u] = d;
    pa[u] = fa;
    for(int e = head[u];e > 0;e = nxt[e]) {
        int v = to[e];
        if(v != fa) {
            dfs1(v,u,d+1);
            sz[u] += sz[v];
            if(son[u] == 0 || sz[son[u]] < sz[v]) {
                son[u] = v;
            }
        }
    }
}

void dfs2(int u,int t) {
    dfncnt++;
    int id = dfncnt;
    dfn[u] = id;
    top[u] = t;
    seg[id] = u;
    if(son[u]==0)return;
    dfs2(son[u],t);
    for(int e = head[u];e > 0;e = nxt[e]) {
        int v = to[e];
        if(v != pa[u] && v != son[u]) {
            dfs2(v,v);
        }
    }
}

void pathaddv(int u,int v,int val){
    while(top[u] != top[v]){
        if(deep[top[u]] <= deep[top[v]]){
            add(dfn[top[v]],dfn[v],val,1,n,1);
            v = pa[top[v]];
        }else{
            add(dfn[top[u]],dfn[u],val,1,n,1);
            u = pa[top[u]];
        }
    }
    add(min(dfn[u],dfn[v]),max(dfn[u],dfn[v]),val,1,n,1);
}
ll pathsums(int u,int v) {
    ll ans = 0;
    while(top[u] != top[v]){
        if(deep[top[u]] <= deep[top[v]]){
            ans += query(dfn[top[v]],dfn[v],1,n,1);
            ans %= MOD;
            v = pa[top[v]];
        }else{
            ans += query(dfn[top[u]],dfn[u],1,n,1);
            ans %= MOD;
            u = pa[top[u]];
        }
    }
    ans += query(min(dfn[u],dfn[v]),max(dfn[u],dfn[v]),1,n,1);
    ans %= MOD;
    return ans;
}

ll subtreesums(int x){
    return query(dfn[x],dfn[x] + sz[x]-1,1,n,1) % MOD;
}

void subtreeadd(int x,ll val){
    add(dfn[x],dfn[x] + sz[x]-1,val,1,n,1);
}




void solve() { 
    cin >> n >> opcnt >> root >> MOD;

    clear(n);

    for(int i = 1;i<=n;i++){
         cin >> a[i];
    }

    for(int i = 1;i<n;i++){
        int u,v;
        cin>>u>>v;
        addEdge(u,v,0);
        addEdge(v,u,0);
    }
    
    



    // debug(root);

    dfs1(root,0,1);

    dfs2(root,0);


    build(1,n,1);

    while(opcnt>0){
        opcnt--;
        int op,x,y,z;
        cin >> op;
        if(op==1){
            cin >> x>>y >> z;
            // debug("path  add",x,y,z);
            pathaddv(x,y,z);
        }else if(op==2){
            cin>>x>>y;
            // debug("pathsums",x,y);
            cout << pathsums(x,y) <<"\n";
        }else if(op==3){
            cin >>x >> z;
            // debug("subtreeadd  add",x,z);
            subtreeadd(x,z);
        }else {
            cin >> x;
            // debug("subtreesums",x);
            cout << subtreesums(x) <<"\n";
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

### 与线段树结合 单点修改

[测试链接](https://www.luogu.com.cn/problem/P2590)

[ac记录🍵](https://www.luogu.com.cn/record/257503662)

```cpp
// https://leetcode.cn/problems/palindromic-path-queries-in-a-tree/submissions/698826298/
// https://github.com/wuxin0011/LeetCode/blob/main/main/java/template/graph/readme.md
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

using ll = long long;

constexpr int inf = 1e9,N = 1e5 + 100,mod = 1e9 + 7;

// 树链剖分模板开始

int head[N],to[N<<1],nxt[N<<1],we[N<<1],cnt,dfncnt,dfn[N],sz[N],son[N],pa[N],a[N],seg[N],deep[N],top[N];


int n,opcnt;



// seg

struct Info {
    int x;

    Info() {
        x = 0;
    }

    Info(int c) {
        x = 0;
        x |= 1 << c;
    }


    Info operator+(const Info& o) const {
        Info info {};
        // for(int i = 0;i<26;i++){
        //     int v0 = x >> i & 1;
        //     int v1 = o.x >> i & 1;
        //     if(v0==v1)continue;
        //     info.x |= 1 << i;
        // }
        info.x = o.x ^ x;
        return info;
    }


} infos[N<<2];

void up(int i) {
    infos[i] = infos[i<<1] + infos[i<<1|1];
}

void build(int l,int r,int i) {
    if(l==r){
        infos[i] = Info{a[seg[l]]};
    }else{
        int mid = l + ((r - l)>>1);
        build(l,mid,i<<1);
        build(mid+1,r,i<<1|1);
        up(i);
    }
}



void update(int ql,int qr,Info info,int l,int r,int i) {
    if(l==r){
        infos[i] =  info;
    }else{
        int mid = l + ((r - l)>>1);
        if(ql<=mid){
            update(ql,qr,info,l,mid,i<<1);
        }
        if(qr>mid){
            update(ql,qr,info,mid+1,r,i<<1|1);
        }
        up(i);
    }
}

Info query(int ql,int qr,int l,int r,int i) {
    if(ql<=l&&r<=qr){
        return infos[i];
    }else{
        int mid = l + ((r - l)>>1);
        Info ans {};
        if(ql<=mid){
            ans = ans + query(ql,qr,l,mid,i<<1);
        }
        if(qr>mid){
            ans = ans + query(ql,qr,mid+1,r,i<<1|1);
        }
        return ans;
    }
}




// seg end



// build edge

void clear(int n) {
    for(int i = 0;i<=n;i++) {
        head[i]=0;
        dfn[i] = 0;
        son[i] = 0;
    }
    for(int i = 0;i < (n<<2);i++) {
        infos[i] = {};
    }
    cnt=0;
    dfncnt=0;
}

void addEdge(int u,int v,int w) {
    ++cnt;
    nxt[cnt]=head[u],to[cnt]=v,we[cnt]=w,head[u]=cnt;
}



// dfs1

void dfs1(int u,int fa,int d) {
    sz[u] = 1;
    deep[u] = d;
    pa[u] = fa;
    for(int e = head[u];e > 0;e = nxt[e]) {
        int v = to[e];
        if(v != fa) {
            dfs1(v,u,d+1);
            sz[u] += sz[v];
            if(son[u] == 0 || sz[son[u]] < sz[v]) {
                son[u] = v;
            }
        }
    }
}

void dfs2(int u,int t) {
    dfncnt++;
    int id = dfncnt;
    dfn[u] = id;
    top[u] = t;
    seg[id] = u;
    if(son[u]==0)return;
    dfs2(son[u],t);
    for(int e = head[u];e > 0;e = nxt[e]) {
        int v = to[e];
        if(v != pa[u] && v != son[u]) {
            dfs2(v,v);
        }
    }
}



int lca(int u,int v) {
    while(top[u] != top[v]){
        if(deep[top[u]] <= deep[top[v]]){
            v = pa[top[v]];
        }else{
            u = pa[top[u]];
        }
    }
    return deep[u] < deep[v] ? u : v;
}

int dis(int u,int v) {
    return deep[u] + deep[v] - 2*deep[lca(u,v)];
}

Info pathQuery(int u,int v) {
    Info ans {};
    while(top[u] != top[v]){
        if(deep[top[u]] <= deep[top[v]]){
            ans = ans + query(dfn[top[v]],dfn[v],1,n,1);
            v = pa[top[v]];
        }else{
            ans = ans + query(dfn[top[u]],dfn[u],1,n,1);
            u = pa[top[u]];
        }
    }
    ans = ans + query(min(dfn[u],dfn[v]),max(dfn[u],dfn[v]),1,n,1);
    return ans;
}


void pathUpdate(int u,int v,Info info) {
    while(top[u] != top[v]){
        if(deep[top[u]] <= deep[top[v]]){
            update(dfn[top[v]],dfn[v],info,1,n,1);
            v = pa[top[v]];
        }else{
            update(dfn[top[u]],dfn[u],info,1,n,1);
            u = pa[top[u]];
        }
    }
    update(min(dfn[u],dfn[v]),max(dfn[u],dfn[v]),info,1,n,1);
}





// 树链剖分模板结束








// 下面为基本步骤

// 初始化操作 建图
// n = n0;
// clear(n);
// for (int i = 0; i < edges.size(); i++) {
//     int u = edges[i][0], v = edges[i][1];
//     u++, v++;
//     addEdge(u, v, 0);
//     addEdge(v, u, 0);
// }
// for (int i = 1; i <= s.size(); i++) {
//     a[i] = s[i - 1] - 'a';
// }
// dfs1(1, 0, 1);
// dfs2(1, 0);
// build(1, n, 1);

// op操作
// pathQuery(u,v)
// pathUpdate(u,v,Info{})


```





### 与线段树区间合并，区间修改

[ac记录🥗](https://www.luogu.com.cn/record/257588472)

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

using ll = long long;

constexpr int inf = 1e9,N = 1e5 + 100,mod = 1e9 + 7;

int head[N],to[N<<1],nxt[N<<1],we[N<<1],cnt,dfncnt,dfn[N],sz[N],son[N],pa[N],a[N],seg[N],deep[N],top[N];


// ----------------------------------------seg

int n,opcnt,root = 1,tag[N<<2];

struct Info{
    int color_l;
    int color_r;
    int sums;
};

Info infos[N<<2];


Info init(){
    return Info{-1,-1,0};
}

Info initInfo(int x){
    Info info = init();
    info.color_l = x;
    info.color_r = x;
    info.sums = 1;
    return info;
}

Info merge(Info info1,Info info2) {
    if(info1.color_l == -1) return info2;
    if(info2.color_l == -1) return info1;
    Info info  = init();
    info.color_l = info1.color_l;
    info.color_r = info2.color_r;
    info.sums = info1.sums + info2.sums  + (info1.color_r == info2.color_l ? -1 : 0);
    return info;
}

void up(int i) {
    infos[i] = merge(infos[i<<1],infos[i<<1|1]);
}

void build(int l,int r,int i) {
    tag[i]=0;
    if(l==r){
        infos[i] = initInfo(a[seg[l]]);
    }else{
        int mid = l + ((r - l)>>1);
        build(l,mid,i<<1);
        build(mid+1,r,i<<1|1);
        up(i);
    }
}


void updateLazy(int i,int sz,ll x){
    infos[i] = initInfo(x);
    tag[i]=x;
}

void down(int i,int l,int r) {
    int mid = l + ((r - l)>>1);
    int ln = mid - l + 1,rn = r - mid;
    if(tag[i]!=0){
        updateLazy(i<<1,ln,tag[i]);
        updateLazy(i<<1|1,rn,tag[i]);
        tag[i]=0;
    }
}

void update(int ql,int qr,int x,int l,int r,int i) {
    if(ql<=l&&r<=qr){
        updateLazy(i,r - l + 1,x);
    }else{
        int mid = l + ((r - l)>>1);
        down(i,l,r);
        if(ql<=mid){
            update(ql,qr,x,l,mid,i<<1);
        }
        if(qr>mid){
            update(ql,qr,x,mid+1,r,i<<1|1);
        }
        up(i);
    }
}

Info query(int ql,int qr,int l,int r,int i) {
    if(ql<=l&&r<=qr){
        return infos[i];
    }else{
        int mid = l + ((r - l)>>1);
        down(i,l,r);
        if(ql>mid){
            return query(ql,qr,mid+1,r,i<<1|1);
        }else if(qr <= mid) {
            return query(ql,qr,l,mid,i<<1);
        }else {
           return merge(query(ql,qr,l,mid,i<<1),query(ql,qr,mid+1,r,i<<1|1)); 
        }
    
    }
}


// ----------------------------------------seg



// build edge

void clear(int n) {
    for(int i = 0;i<=n;i++) {
        head[i]=0;
        dfn[i] = 0;
        son[i] = 0;
    }
    cnt=0;
    dfncnt=0;
}

void addEdge(int u,int v,int w) {
    ++cnt;
    nxt[cnt]=head[u],to[cnt]=v,we[cnt]=w,head[u]=cnt;
}



// dfs1

void dfs1(int u,int fa,int d) {
    sz[u] = 1;
    deep[u] = d;
    pa[u] = fa;
    for(int e = head[u];e > 0;e = nxt[e]) {
        int v = to[e];
        if(v != fa) {
            dfs1(v,u,d+1);
            sz[u] += sz[v];
            if(son[u] == 0 || sz[son[u]] < sz[v]) {
                son[u] = v;
            }
        }
    }
}

void dfs2(int u,int t) {
    dfncnt++;
    int id = dfncnt;
    dfn[u] = id;
    top[u] = t;
    seg[id] = u;
    if(son[u]==0)return;
    dfs2(son[u],t);
    for(int e = head[u];e > 0;e = nxt[e]) {
        int v = to[e];
        if(v != pa[u] && v != son[u]) {
            dfs2(v,v);
        }
    }
}

void pathupdate(int u,int v,int val){
    while(top[u] != top[v]){
        if(deep[top[u]] <= deep[top[v]]){
            update(dfn[top[v]],dfn[v],val,1,n,1);
            v = pa[top[v]];
        }else{
            update(dfn[top[u]],dfn[u],val,1,n,1);
            u = pa[top[u]];
        }
    }
    update(min(dfn[u],dfn[v]),max(dfn[u],dfn[v]),val,1,n,1);
}



int pathQuery(int u,int v) {
    int ans = 0;
    int son,father;
    while(top[u] != top[v]){
        if(deep[top[u]] <= deep[top[v]]){
            ans += query(dfn[top[v]],dfn[v],1,n,1).sums;
            son = query(dfn[top[v]],dfn[top[v]],1,n,1).color_r;
            father = query(dfn[pa[top[v]]],dfn[pa[top[v]]],1,n,1).color_r;
            v = pa[top[v]];
        }else{
            ans += query(dfn[top[u]],dfn[u],1,n,1).sums ;
            son = query(dfn[top[u]],dfn[top[u]],1,n,1).color_r;
            father = query(dfn[pa[top[u]]],dfn[pa[top[u]]],1,n,1).color_r;
            u = pa[top[u]];
        }
        if(son==father){
            ans--;
        }
    }
    ans += query(min(dfn[u],dfn[v]),max(dfn[u],dfn[v]),1,n,1).sums;
    return ans;
}



void solve() { 
    cin >> n >> opcnt;

    clear(n);

    for(int i = 1;i<=n;i++){
         cin >> a[i];
    }

    for(int i = 1;i<n;i++){
        int u,v;
        cin>>u>>v;
        addEdge(u,v,0);
        addEdge(v,u,0);
    }
    

    dfs1(root,0,1);

    dfs2(root,0);


    build(1,n,1);

    while(opcnt>0){
        opcnt--;
        char op;
        int x,y,z;
        cin >> op;
        if(op=='Q'){
            cin >> x >> y;
            cout << (pathQuery(x,y)) << "\n";
        }else{
            cin >> x >> y >> z;
            pathupdate(x,y,z);
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




### 求LCA

[测试链接](https://www.luogu.com.cn/problem/P3379)

[ac记录🍵](https://www.luogu.com.cn/record/257502652)

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

using ll = long long;

constexpr int inf = 1e9,N = 5e5 + 100,mod = 1e9 + 7;

int head[N],to[N<<1],nxt[N<<1],we[N<<1],cnt,dfncnt,dfn[N],sz[N],son[N],pa[N],deep[N],top[N];





// build edge

void clear(int n) {
    for(int i = 0;i<=n;i++) {
        head[i]=0;
        dfn[i] = 0;
        son[i] = 0;
    }
    cnt=0;
    dfncnt=0;
}

void addEdge(int u,int v,int w) {
    ++cnt;
    nxt[cnt]=head[u],to[cnt]=v,we[cnt]=w,head[u]=cnt;
}



// dfs1

void dfs1(int u,int fa,int d) {
    sz[u] = 1;
    deep[u] = d;
    pa[u] = fa;
    for(int e = head[u];e > 0;e = nxt[e]) {
        int v = to[e];
        if(v != fa) {
            dfs1(v,u,d+1);
            sz[u] += sz[v];
            if(son[u] == 0 || sz[son[u]] < sz[v]) {
                son[u] = v;
            }
        }
    }
}

void dfs2(int u,int t) {
    dfncnt++;
    int id = dfncnt;
    dfn[u] = id;
    top[u] = t;
    // seg[id] = u;
    if(son[u]==0)return;
    dfs2(son[u],t);
    for(int e = head[u];e > 0;e = nxt[e]) {
        int v = to[e];
        if(v != pa[u] && v != son[u]) {
            dfs2(v,v);
        }
    }
}

int lca(int u,int v) {
    while(top[u] != top[v]){
        if(deep[top[u]] <= deep[top[v]]){
            // add(dfn[top[v]],dfn[v],val,1,n,1);
            v = pa[top[v]];
        }else{
            // add(dfn[top[u]],dfn[u],val,1,n,1);
            u = pa[top[u]];
        }
    }
    return deep[u] < deep[v] ? u : v;
}




void solve() { 
    int n,opcnt,root;
    cin >> n >> opcnt >> root;

    clear(n);

    for(int i = 1;i<n;i++){
        int u,v;
        cin>>u>>v;
        addEdge(u,v,0);
        addEdge(v,u,0);
    }
    // debug(root);

    dfs1(root,0,1);

    dfs2(root,0);

    while(opcnt>0){
        opcnt--;
        int u,v;
        cin >> u >> v;
        cout << lca(u,v) << "\n";
        // debug(u,v);
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







## 静态点分治

静态点分支又称为重心分治，从重心遍历类似于数组上中点分治递归

- [P2634 [国家集训队] 聪聪可可](https://www.luogu.com.cn/problem/P2634) [AC链接🍵](https://www.luogu.com.cn/record/265022227)
- [P3806 【模板】点分治 ](https://www.luogu.com.cn/problem/P3806) [AC链接🍵](https://www.luogu.com.cn/record/266482941) [bitset优化🍵](https://www.luogu.com.cn/record/266482832)








## 其他 c++ 命令行编译命令

```bash
g++ -D__IS_LOCAL__=1 -D__IS_LEETCODE__=1 "%1.cpp" -o "%1" && "%1" < "%1.in" > "%1.out"
```

