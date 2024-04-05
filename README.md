# 练习题笔记



## 👓 演示部分

<details>
  <summary>点我查看配置</summary>
  <img src="https://camo.githubusercontent.com/1c1990223d48e17dacbcc99d497732671a5128a6b7ed0cc5dc54177d575f25eb/68747470733a2f2f692e706f7374696d672e63632f577a3534787832572f636f6e6669672e706e67" alt="config"/>
</details>



<details>
  <summary>点我查看自动获取每日一题</summary>
  <img src="https://camo.githubusercontent.com/918f5eb9772584a6ff3fbdfc8c92be86bdf218f55764edeadbe470412721fa78/68747470733a2f2f692e706f7374696d672e63632f477437703676586b2f6175746f2d6e6578742e676966" alt="每日一题演示"/>
</details>



<details>
  <summary>点我查看通过题目链接自动获取题目</summary>
  <img src="https://camo.githubusercontent.com/ed5680ea5f6865eb8c83559329d807ee7549076ac544b5a47c3cac17e206a094/68747470733a2f2f692e706f7374696d672e63632f4b5951394d7a6a312f637573746f6d2d70726f626c656d732e676966" alt="自定义题目"/>
</details>


<details>
  <summary>点我查看自定义周赛演示</summary>
  <img src="https://camo.githubusercontent.com/caa119d57a6fc52687bc28c6a21ceeb930c1fb4a1b91c2911ef91f6cf9218c75/68747470733a2f2f692e706f7374696d672e63632f3532796a564b7a442f7765656b2d636f6e746573742e676966" alt="自定义比赛演示"/>
</details>


<details>
  <summary>点我查看获取当前比赛演示</summary>
  <img src="https://camo.githubusercontent.com/678790a59bd39447c8ea2a47470e678e199f45c9658aecdabfbbb0c0eaf50ab4/68747470733a2f2f692e706f7374696d672e63632f48784b35464a42382f6375727272656e742d636f6e746573742e676966" alt="当前比赛"/>
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

