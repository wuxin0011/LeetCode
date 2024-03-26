- 测试主要是Cookie中内容
    - `csrftoken`
    - `LEETCODE_SESSION`

可以参考一下配置

> headers.properties

```properties
Host=leetcode.cn
Referer=https://leetcode.cn/
Origin=https://leetcode.cn
Cookie=Cookie
Sec-Fetch-Mode=cors
Sec-Ch-Ua-Platform=Windows
Connection=keep-alive
Accept=*/*
User-Agent="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36 Edg/116.0.1938.62";
Accept-Language=zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6
Cache-Control=max-age=0
```

其他都配置好了主要是修改 `Cookie`

不会操作 ？ [点我](https://leetcode.cn/)，F12 打开控制台，网络->找到 https://leetcode.cn/ 请求的链接 复制 请求头中的
cookie，如果过期了请登录后修改！cookie 全部复制也没关系！


如果认为 cookie 配置在 ``headers.properties`` 有点麻烦，可以删除Cookie ，手动创建 `cookies.txt`或者构建时会自动创建 `cookies.txt` 文件


> cookies.txt 的优先级最高 会直接覆盖 headers.properties 中的 Cookie 属性，在 cookies.txt 中不需要 Cookie=xxxx,直接是xxx 因此如果 cookie 过期了可以直接在 cookies.txt 中修改






> example ： cookies.txt

```txt
gr_user_id=8629fa68-9b44-4f19-a0
5f-dbc83d442db9; a2873925c34ecbd2_gr_last_sent_cs1=agitated-c
urranfnd; _bl_uid=t2lsas4I8dIma15v8z2qd8O9bh5j; _gid=GA1.2.469048233.1
707529917; _ga_PDVPZYN3CW=deleted; _ga_JSBF1EVZXY=GS1.1.1710167959.2.0.17101679
59.0.0.0; csrftoken=77MX6yVgwMPRyE9PvsnzRNfTLaSrTPvYMkHwymTUTKRyt5IIzQ0hmEMQQUT
DOq7Y; tfstk=fPw9rtqltwbMByX9aS13grE0MzIhEREZJPrWnq0MGyUL8zFMnNkiDK3Luj01_NuYku4H
ndD_orLYSy0_koSZcqabkqVGtTqabxkfr-BlEllEqBBLWji6diijVdZHteoUbxkf-jHskUEarHWFJqHf
vvimfmMX1fspRc0BCm9scesK0FYOlZLIvbcQdgruwVQ-iGwEXf3Xn2vxO8poN4_-yppKe
ch99lgphKwLOUxEHV1yxqki7foTuTJZCjFbIvw1RtM7ayexNYCcbYELt7cT9N9x-R0IMRGd
FenUOlVbQPKvhcygEj3r9i9qivwoN7a90grICmsr882-iYToymA1vMd2gfiESU3lpSx2A9nK
rMjHgIlLWD3lvMd2gfiEv4jUzIRq9PC..; Hm_lvt_f0faad39bcf8471e3ab3ef70125152c3=171
1001264,1711246099,1711264335,1711325620; Hm_lvt_fa218a3ff7179639febdb15e372f411c
=1711247187,1711253763,1711265658,1711337076; Hm_lpvt_f0faad39bcf8471e3ab3ef7012515
2c3=1711384622; Hm_lpvt_fa218a3ff7179639febdb15e372f411c=1711413877; _ga_PDVPZYN3CW=GS1
.1.1711413688.241.1.1711413878.29.0.0; _ga=GA1.2.2037691452.1675754961; LEETCODE_SESSION=
eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfYXV0aF91c2VyX2lkIjoiNDc4MDY2NyIsIl9hdXRoX3VzZXJfYmFja2
VuZCI6ImF1dGhlbnRpY2F0aW9uLmF1dGhfYmFja2VuZHMuUGhvbmVBdXRoZW50aWNhdGlvbkJhY2tlbmQiLCJfYXV0aF91c2
VyX2hhc2giOiI5MDljYTgyYzFlODM4OWZmMzQ2OTAzMDYxNTdlNzU4OGFkZGVmN2VkMzM2YzZjNzgzZDhhM2VjZGUyOWQzNjc
4IiwiaWQiOjQ3ODA2NjcsImVtYWlsIjoiIiwidXNlcm5hbWUiOiJhZ2l0YXRlZC1jdXJyYW5mbmQiLCJ1c2VyX3NsdWciOiJh
Z2l0YXRlZC1jdXJyYW5mbmQiLCJhdmF0YXIiOiJodHRwczovL2Fzc2V0cy5sZWV0Y29kZS5jbi9hbGl5dW4tbGMtdXBsb2FkL3
VzZXJzL2FnaXRhdGVkLWN1cnJhbmZuZC9hdmF0YXJfMTcwOTk2MzY3NC5wbmciLCJwaG9uZV92ZXJpZmllZCI6dHJ1ZSwiX3Rpb
WVzdGFtcCI6MTcxMTI2NjcxNy44MDA1NzI5LCJleHBpcmVkX3RpbWVfIjoxNzEzODEyNDAwLCJ2ZXJzaW9uX2tleV8iOjEsImxhdG
VzdF90aW1lc3RhbXBfIjoxNzExNDEzODg1fQ.LpH1FF1nXCgR7jnrNR48Wy6wSqwOIKPJzJt2GQ9M5WM; a2873925c34ecbd2_gr
_cs1
```

**担心cookie格式？没必要！已经过滤了cookie中不必要的字符！**