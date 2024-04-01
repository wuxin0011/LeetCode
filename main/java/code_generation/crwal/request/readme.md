- [request](./Request.java)
    - 手动封装的一个简单请求类，默认从当前类路径目录`request_config`中读取配置文件 可以手动指定从系统路径中读取
- [config](./Config.java)
    - 加载配置文件 如请求头的信息

对于隐私信息，也可以自己新建一个目录 ，然后修改 `` .gitignore`` 文件


如 request_config

```gitignore
request_config
```

这样也能达到保护隐私目的也能方便修改 

ps: 默认会过滤request_config这个目录