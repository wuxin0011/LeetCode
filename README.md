# 练习题笔记



## 👓 演示部分

<details>
  <summary>点我查看配置</summary>
  <img src="https://i.postimg.cc/Wz54xx2W/config.png" alt="config"/>
</details>



<details>
  <summary>点我查看自动获取每日一题</summary>
  <img src="https://i.postimg.cc/Gt7p6vXk/auto-next.gif" alt="每日一题演示"/>
</details>



<details>
  <summary>点我查看通过题目链接自动获取题目</summary>
  <img src="https://i.postimg.cc/KYQ9Mzj1/custom-problems.gif" alt="自定义题目"/>
</details>


<details>
  <summary>点我查看自定义周赛演示</summary>
  <img src="https://i.postimg.cc/52yjVKzD/week-contest.gif" alt="自定义比赛演示"/>
</details>


<details>
  <summary>点我查看获取当前比赛演示</summary>
  <img src="https://i.postimg.cc/HxK5FJB8/currrent-contest.gif" alt="当前比赛"/>

  由于当前没有任何比赛因此没有抓取
</details>




## 📦 LeetCode验证失败 ？


[了解配置详情](./main/java/code_generation/crwal/readme.md)


## 🚀 推荐链接

- [灵茶山艾府](https://leetcode.cn/u/endlesscheng/)
  - 点击讨论发布查看题单
  - 关于[bilibili](https://space.bilibili.com/206214)
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

将内容复制到本地保存为 xxx.json 文件


## 📚 完整题单推荐

- [题单](https://doocs.gitee.io/leetcode/tags.html)


## 📘 更新日志


- 2024/3/27 基本完成自动案例功能
- 2024/3/19 破坏性调整目录结构，新增代码`template`自动生成
- 2024/2/15 调整目录结构，为`ACM`模式做预留。另外 好像大部分题目都没有写，有点懒了，算了🤣。
- 2023/6/18 不知道啥时候首次提交了



## 👜Java版本信息

- JDK1.8
- [官方链接](https://leetcode.cn/)
- [中文接口文档](https://www.matools.com/api/java8)


