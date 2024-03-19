# 练习题笔记

## Java版本信息

- JDK1.8
- [官方链接](https://leetcode.cn/)
- [中文接口文档](https://www.matools.com/api/java8)

## 更新日志




- 2024/3/19 破坏性调整目录结构，新增代码tempalte自动生成[点我查看](./main/java/code_generation/Problem.java)、 以及 [周赛类](./main/java/code_generation/WeekContest.java )
- 2024/2/15 调整目录结构，为`ACM`模式做预留。另外 好像大部分题目都没有写，有点懒了，算了🤣。
- 不知道啥时候首次提交了




## TODO
 - [x] 准备写个支持本地对拍的code [点解查看详情😅](./main/java/code_generation/utils/readme.md)
 - [ ] 准备实现自动解析。[点解查看详情😅](main/java/code_generation/crwal/readme.md)





## 推荐链接

- [题单](https://doocs.gitee.io/leetcode/tags.html)
- 大数据[zerotrac](https://zerotrac.github.io/leetcode_problem_rating/#/)
- 分数分类[rating](https://huxulm.github.io/lc-rating/)



由于网络问题经常访问不了，可以尝试保存在本地 [点我测试🚀](https://huxulm.github.io/lc-rating/)

打开浏览器控制将下面内容复制到控制台run

```js
const trs = document.querySelectorAll('table tbody tr')

let ans = []
Array.from(trs).forEach(tr=>{
	const tds = tr.querySelectorAll('td')
	const title = tds[2].querySelector('a').textContent
	const url = tds[2].querySelector('a').href
	const score = tds[3].querySelector('div').textContent
	// console.log(title,url,score)
	let obj = {
		title,
		score,
		url
	}
	ans.push(obj)
})
// console.log(ans)
console.table(ans)

```

