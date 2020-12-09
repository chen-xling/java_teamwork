# java课程小组项目要求
+ **小组成员**
  + 组长：陈小灵
  + 组员：陈静、文胜熙
+ **任务概述**：
  + 命令行工具
  + 参考git实现原理，实现blob,tree,commit核心存储结构

+ **功能点**：
  + 可以提交commit，可以进行”git log”查看commit历史
  + 可以进行”git reset”回滚到指定commit
  + 可创建多分支，可在分支之间切换

+ **注**：
  + 不要求merge功能
  + 远程仓库

## 1210任务1：实现key-value存储
+ 最简单的key-value存储方式（filename→content of file）
  + Key作为文件名，文件内容作为value
+ 支持以下功能
  + 给定value，向存储中添加对应的key-value
    + 给定value：“hello world”
    + Hash(“hello world”) == 34234234
    + 创建文件 objects/34234234 --> hello world
  + 给定key，查找得到对应的value值
    + 给定34234234，要找到value的值

+ 注：
  + 封装成class对外提供接口
  + 单元测试

## 1210任务2：将一个文件夹转化成key,value
+ 给定一个文件夹目录，将其转化成若干tree和blob
  + 深度优先遍历此目录
  + 遇到子文件就转化成blob并保存
  + 遇到子文件夹就递归调用其内部的子文件/文件夹最后构造tree并保存
+ 注：
  + 使用任务1提供的接口 --- hash表
  + 单元测试
