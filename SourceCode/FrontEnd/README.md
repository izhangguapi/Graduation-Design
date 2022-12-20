# 前端代码

## 项目初始化
```
npm install
```

### 开发测试
```
npm run serve
```

### 构建
```
npm run build
```

### 可能出现的问题
前端Vue启动后出现报错：digital envelope routines::unsupported

是因为node.js V17版本中最近发布的OpenSSL3.0, 而OpenSSL3.0对允许算法和密钥大小增加了严格的限制，可能会对生态系统造成一些影响。故此以前的项目在升级 nodejs 版本后会报错。

解决方案就是修改package.json文件，在相关构建命令之前加入SET NODE_OPTIONS=--openssl-legacy-provider，或者将nodejs退回到V16版本