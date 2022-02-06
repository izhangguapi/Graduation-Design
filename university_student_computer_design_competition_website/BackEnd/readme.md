# Getting Started

### 可能出现的问题
UsersController.java文件里面引用的import com.wf.captcha.GifCaptcha;报错：

由于`prm.xml`文件里面的这段代码需要下载的包，国内可能访问不到，导致找不到此包出现报错。
```xml
<!--验证码-->
<dependency>
    <groupId>com.GitHub.whvcse</groupId>
    <artifactId>easy-captcha</artifactId>
    <version>1.6.2</version>
</dependency>
```

解决方案：
1. 需要科学上网挂上代理才能进行下载。
2. 把上层目录`other`文件夹下的文件夹放在`.m2/repository/com/github`即可。
