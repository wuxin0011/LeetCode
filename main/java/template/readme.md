
# template

leetcode采用的是**核心代码**模式
其他平台大多数是**ACM**模式

为什么交互模式不用 `new Scanner(System.in)` 这类形式 ？
```java
Scanner in=new Scanner(System.in);
int n=in.nextInt();
System.out.println(n);
```


答：`new Scanner(System.in)`底层极度消耗IO资源，容易造成 **Time out**

好像C++和C没有这个问题


C
```c
int a,b,c;
scanf("%d %d %d",&a,&b,&c);
```

C++
```c++
int a,b,c;
std::cin>>a>>b>>c;
```

