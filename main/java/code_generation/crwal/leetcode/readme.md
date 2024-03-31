


## 更新日志

- 支持最新场次周赛
- 添加隐私保护
- 24.3.27 上面大部分功能已经实现了，就是不知道是不是兼容所有，后面再说吧！[春季赛没弄]



---

## 预想

> 登录
1. 普通方式直接获取无法获取题目内容
> 获取题目链接

### LeetCode



力扣分下几个页面

1. [普通题目](https://leetcode.cn/problems)
2. [周赛题目](https://leetcode.cn/contest/weekly-contest-387/problems/count-submatrices-with-top-left-element-and-sum-less-than-k/)
3. [春季赛](https://leetcode.cn/contest/) 【ps：这个没找到，先用这个替代吧！】

**经过测试需要登录后的信息才能访问**

题目类型分为下面几个
- [ ] 构造题,就是系统给函数名称，自动调用，这个没实现
- [x] 输入题带`=`
- [ ]  不带`=`,但是带`:`
- [ ]  混合，带`=`,带`:`

周赛题目通过
`https://leetcode.cn/contest/weekly-contest-场次/` 解析获取题目链接



准备实现一下几个功能
- 通过题目链接解析
- 通过序号周赛场次解析
   - 这个默认应该是最近一次的
   - 不能超过当前的


对拍功能大部分已经实现了，所以目前任务是实现上面功能，毕竟总是复制

也是很麻烦的
