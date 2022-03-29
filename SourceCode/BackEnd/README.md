# 后端代码

## 可能出现的问题

1. UsersController.java文件里面引用的import com.wf.captcha.GifCaptcha报错

由于`prm.xml`文件里面的这个包在国内下载不了，导致找不到此包出现报错。

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
2. 把上层目录`Maven JAR`文件夹下的文件夹放在本地仓库目录下即可，如果您没有修改maven的配置文件，请将文件放在`${user.home}/.m2/repository`目录下（`${user.home}`为您系统的用户文件夹）。