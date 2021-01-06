# SimpleGit详细代码设计文档

## 1. Gen_hash类

+ 数据域
+ 构造方法
+ 提供方法
  + byte[ ] **SHA1Checksum**( InputStream is )
    + 功能：将文件输入流转换成SHA1哈希值
    + 参数：文件输入流
    + 返回值：字节数组-哈希值
  + String **convertToHexString**(byte data[ ])
    + 功能：将形式为字节数组的SHA1哈希值转换为十六进制的字符串
    + 参数：字节数组-哈希值
    + 返回值：字符串-哈希值
  + String **hash**( String path  )
    + 功能：输入文件/文件夹路径，返回哈希值字符串
    + 参数：字符串-文件路径
    + 返回值：字符串-哈希值
  + String **hashString**( String s)
    + 功能：计算字符串的哈希值
    + 参数：字符串-内容
    + 返回值：字符串-哈希值

## 2. GetValue类

+ 数据域
+ 构造方法
+ 提供方法
  + String **getValue** ( String path)
    + 功能：通过文件/文件夹路径查找key对应的value
    + 参数：文件/文件夹路径
    + 返回值：字符串-文件的内容
  + String **getValue** ( File file)
    + 功能：通过文件/文件夹查找key对应的value
    + 参数：文件/文件夹
    + 返回值：字符串-文件的内容

## 3. Blob类

+ 数据域
  + (-)String **filePath**：原始文件的路径
  + (-)String **objectPath**：新的文件路径（即存放文件/文件夹哈希值的文件夹路径）
  + (-)String **key**：文件的哈希值
+ 构造函数
  + **Blob**( String filePath, String objectPath)
    + 功能：输入有原始文件路径与新文件路径就能构造一个存放key-value的blob文件
    + 参数：字符串-原始文件的路径；字符串-新的文件路径
+ 提供方法
  + String **addFile**()
    + 功能：向存储地址中添加blob对应的key-value（同样文件名的文件，即内容相同的blob文件只添加一次）
    + 参数：无
    + 返回值：字符串-Blob内容的哈希值
  + String **getFilePath**()
    + 功能：获取原始的文件路径
    + 参数：无
    + 返回值：字符串-FilePath
  + String **getObjectPath**()
    + 功能：获取新的文件路径
    + 参数：无
    + 返回值：字符串-NewPath
  + String **getKey**()
    + 功能：获取原始文件的哈希值
    + 参数：无
    + 返回值：字符串-哈希值
  + String **getValue**()
    + 功能：获取value；调用了getValue.java类
    + 参数：无
    + 返回值：字符串-文件的内容



## 4. Tree类

+ 数据域
  + (-)String **key**：文件/文件夹的哈希值
  + (-)String **filePath**：原始文件/文件夹的路径
  + (-)String **objectPath**：新的文件/文件夹路径（即存放文件/文件夹哈希值的文件夹路径）
  + (-)StringBuffer **value**：文件的内容
+ 构造函数
  + **Tree**(String P1, String P2)
    + 功能：构造一个有filePath、objectPath与key-value的实例
      + 若P1给定的是一个文件blob，则把blob从P1拷贝到P2，内容不变，文件名改成blob的哈希码
      + 若P1给定的是一个文件夹tree，则在P2中生成一个txt文件，文件内容为:
        + 原文件夹里的子文件的名字、子文件的哈希码、子文件的类型（blob or tree)
        + P2里的新文件夹名字为以上内容的哈希值
    + 参数：字符串-原始的文件/文件夹路径（P1）；字符串-新的文件/文件夹路径（P2）
+ 提供方法
  + void **gen_tree**()
    + 功能：生成文件/文件夹的tree文件,
    + 参数：无
    + 返回值：无
  + String **getFilePath**()
    + 功能：获取原始的文件/文件夹路径
    + 参数：无
    + 返回值：字符串-FilePath
  + String **getObjectPath**()
    + 功能：获取新的文件/文件夹路径
    + 参数：无
    + 返回值：字符串-NewPath
  + String **getKey**()
    + 功能：获取tree文件的哈希值
    + 参数：无
    + 返回值：字符串-哈希值
  + String **getValue**()
    + 功能：返回tree的文件内容
    + 参数：无
    + 返回值：字符串-文件的内容



## 5. Tree_content类

+ 数据域
  + (-)String **name**：文件名（含后缀）
  + (-)String **key**：文件/文件夹的哈希值
  + (-)String **type**：子文件/文件夹的类型（blob or tree）
  + String **objectPath** = "D:\\AsimpleGit\\object"：存放key-value的地址（定义成字符串常量，方便修改）
+ 构造函数
  + **Tree_content**( )
    + 功能：构造一个未赋属性的实例
    + 参数：无
  + **Tree_content**(String f )
    + 功能：构造一个Tree_content类，其中包含Tree中子文件/子文件夹的name、key和type
    + 参数：字符串-Tree中子文件/文件夹路径
+ 提供方法
  + String **output**( )
    + 功能：输出子文件/文件夹的value内容
    + 参数：无
    + 返回值：字符串-"type key name"
  + String **getName**( )
    + 功能：获取文件名
    + 参数：无
    + 返回值：字符串-文件名
  + String **getKey**( )
    + 功能：获取哈希值
    + 参数：无
    + 返回值：字符串-哈希值
  + String **getType**( )
    + 功能：获取类型
    + 参数：无
    + 返回值：字符串-文件的类型（blob or tree）
  + void **get_tree_content**( String line)
    + 功能：供回滚使用，传入tree的每一行的内容信息，得到该行所代表的type、key和name  
    + 参数：字符串-Tree文件中的一行  
    + 返回值：无



## 6. HEAD类

说明：HEAD文件中会保存过往所有的commit，最新的commit保存在最后一行。



+ 数据域
  + (-)String **gitPath**：字符串-存放commit文件的目录
  + (-)File **head_file**：文件-存放head内容的文件
  + (-)String **head**：字符串-head的内容
+ 构造函数
  + **HEAD()**
    + 功能：创建填充了各项数据域的HEAD实例
    + 参数：无
+ 提供方法
  + void **update_head** ( String commit )
    + 功能：在commit成功的情况下，如果head文件不存在则创建head文件，如果head文件存在则更新commit
    + 参数：字符串-commit文件的内容
    + 返回值：空
  + String **get_head()**
    + 功能：当head文件存在的时候，读取最新的HEAD值
    + 参数：无
    + 返回值：返回值-最新的HEAD值内容
  + String **get_last_tree_key**()
    + 功能：读取上一次的commit文件里的第一行存放的tree_key 
    + 参数：无
    + 返回值：字符串-上一次文件夹的key值



## 7. Commit类

+ 数据域
  + (-)String **filePath**：工作区目录
  + String **gitPath**：存放commit文件的目录
  + (-)String **head**：上一次生成的commit文件的文件名，也可命名为 **last_commit_key**
  + (-)String **current_tree_key**：新生成的commit文件的第一行内容
  + (-)String **last_tree_key**：上一次commit文件中的tree key
  + (-)String **current_commit_key**：新生成的commit文件的文件名
  + (-)StringBuffer **value**：本次commit文件的内容
+ 构造函数
  + **Commit**( )
    + 功能：构造一个未赋属性的实例
    + 参数：无
  + **Commit**(String  path1, String path2 )
    + 功能：提供文件路径和存放commit文件路径即可生成commit各项属性的实例
    + 参数：字符串-path1（filePath）；字符串-path2（gitPath）
+ 提供方法
  + void **gen_commit**( )
    + 功能：判断commit是否需要更新，如需要则生成新的commit文件，同时更新head文件
    + 参数：无
    + 返回值：无
  + String **getValue**( )
    + 功能：得到commit文件的内容
    + 参数：无
    + 返回值：字符串-commit文件的内容
  + String **get_all_commit**( )
    + 功能：返回历史所有commit的哈希值（供回滚时使用）
    
    + 参数：无
    
    + 返回值：字符串-HEAD文件中所有commit的内容
    
      

## 回滚任务设计

说明一：要实现回滚功能，就要完成以下操作：

​	1.在回滚之前，新建一个分支，该分支就用当前最新的一次Commit的key来命名，用于之后的往后回滚。

​	2.清空目标路径下的文件

​	3.根据一个历史版本号（也就是某次commit的文件名） 读取该文件它的第一行，就得到了那次commit所提交的整个大文件夹的tree，再根据tree里面的内容，一一把文件复制到目标路径：

 *	子文件若是blob, 就根据它的哈希码，去存放object的路径下找到它，然后复制到目标路径，用到了CopyBlob类
 *	子文件若是Tree, 就在目标路径下新建一个子文件夹；并且根据它的哈希码，去存放object的路径下找到相应Tree文件，递归即可。

​	4.更新HEAD文件，恢复到相应的历史状态。



说明二：解释一下object路径：
 * 对于第一次commit, 把所有的tree和blob存到这个路径下。

 * 如果是不同文件名，但内容完全相同的子文件，只会保存一个blob，而不是两个。

 * 对于第二次或第N次commit：

 * 如果某些旧文件改动了，则会在该object路径下新增相应的tree和blob，同时旧的tree和blob不会被删除。

 * 如果新增了全新的文件，就在object路径下新增新的tree或blob

 * 由此，便可知所有的历史版本的文件内容。

    

-------------------------------------------------------------------------------------------------

## 8. RollBack类

+ 数据域：
  + (-)String **treeKey**：commit文件中记录的tree的key值
  + (-)String **treePath**：tree文件的路径
  + (-)String **goalPath** = "D:\\AsimpleGit\\goalPath"：回滚文件存放路径
  + (-)String **commitPath** = "D:\\AsimpleGit\\commitPath"：commit文件的路径
+ 构造函数：
  + **RollBack**( String commit )
    + 功能：通过commit创建RollBack实例，如果成功则输出提示
    + 参数：commit实例
+ 提供方法：
  + boolean **gen_file**( String filePath, String goalPath )
    + 功能：读取文件路径实现回滚并还原内容
    + 参数：字符串-文件路径；字符串-存放回滚文件的路径
    + 返回值：true



## 9. DeleteFolder类

+ 数据域

+ 构造函数

  + **DeleteFolder**( String folderPath )
    + 功能：传入文件夹路径，
    + 参数：字符串-文件夹路径
  + **DeleteFolder**( File folder )
    + 功能：重载构造函数
    + 参数：文件-文件夹路径

+ 提供方法

  +  void **delete_file** ( String folderPath )
     + 功能：第一版本的删文件夹函数：传入的路径只能是文件夹路径，不能是文件。过程中会调用第二版本的删文件夹函数。
     + 参数：字符串-文件/文件夹路径
     + 返回值：无
  +  void **delete_file_completely** ( String folderPath ) 
     - 功能：第二版本的删文件夹函数，用于删除整个文件夹或文件。如果传入的是文件夹，会把最大的这个文件夹也删掉。
     - 参数：字符串-文件/文件夹路径
     - 返回值：无

  

## 10. CopyBlob类

+ 数据域
  + (-)String **filePath**：原始文件的路径
  + (-)String **goalPath**：目标文件夹的路径
  + (-)String **newName**：新的文件名
+ 构造函数
  + **CopyBlob**(String filePath, String goalPath, String newName )
    + 功能：构造一个实例，从原文件路径复制到目标路径，并重新命名
    + 参数：字符串-原始文件的路径；字符串-目标文件夹的路径；字符串-新的文件名
  + **CopyBlob**(File file, String goalPath, String newName )
    + 功能：构造一个实例，将原文件复制到目标路径，并重新命名
    + 参数：文件-原始文件；字符串-目标文件夹的路径；字符串-新的文件名
+ 提供方法
  + void **gen_file**()
    + 功能：将key-value内容转化为文件存储
    + 参数：无
    + 返回值：无





## 分支任务设计

​	默认分支是主分支，所有的commit文件和HEAD文件都放在了Global.gitPath这个路径下。

​	初始化仓库，即提交第一次commit时，会往Global.save_current_branch_file（即current_commit.txt文件）写入默认的分支名

​	若要创立新的分支。要做的操作有：

 * （1）在Global.branches路径下建立新的文件夹，文件夹名字是新的分支名
 * （2）把当前分支路径下的所有commit文件和HEAD文件原封不动地复制到新的分支文件下。
 * 这相当于是复制整个文件夹的操作，所以我写了一个工具类: CopyTree.java，用来复制整个文件夹
 * （3）仅仅是创建新分支而已，并没有切换当前分支。





## 11. Branch类

+ 数据域
  + (#)String **current_branch**：当前分支的名字
  + (#)String **current_branch_path**：当前分支的文件夹路径
  + (#)String **latest_commit**：最新的commit文件名（其哈希值）
  
+ 构造函数
  + **Branch**()
    + 功能：得到当前分支的名字，以及它的文件夹路径
    + 参数：无
  + **Branch**( String newBranchName )
    + 功能：得到当前分支的名字，以及它的文件夹路径
    + 参数：字符串-新的分支名
  
+ 提供方法
  + void **gen_branch**(String newBranchName)
    + 功能：创建新的分支文件夹，用来放该分支的commit文件，并把当前Branch文件夹的全部commit文件复制到新的Branch文件夹下面
    + 参数：字符串-新的分支名
    + 返回值 ：无

+ void **change_branch**(String branch_name)
  + 功能：用于切换分支，往current_commit.txt文件内写入新的分支名，覆盖原有的内容。
  + 参数：字符串-分支名
  + 返回值：无
+  String listBranch () 
  + 功能：返回当前的所有分支名字
  + 参数：无
  + 返回值：字符串-所有的分支名





## 12. CopyTree类

+ 数据域
+ 构造函数
  + **CopyTree**( String filePath, String goalPath )
    + 功能：生成实例，通过输入原来的和新的文件夹路径实现文件夹的复制
    + 参数：字符串-原文件夹路径；字符串-新文件夹路径
+ 提供方法
  + void **gen_file**( String filePath, String goalPath)
    + 功能：复制文件夹的全部内容
    + 参数：字符串-原文件夹路径；字符串-新文件夹路径
    + 返回值：无





## 命令行交互任务设计

 通过命令行向Command.java文件的main函数传入字符串数组，实现以下功能：

1.git initial "仓库路径"  

 * 		初始化，传入仓库路径，作为工作区目录；默认为main分支

2.git commit

 *  	在当前分支下，提交当前仓库的内容

3.git history

 *  	在当前分支下，查看提交过的commit历史

4.git rollback "某次commit的哈希值"

 *  	在当前分支下，实现回滚到某一次历史状态

5.git branch "新的分支名"

 * 		新建分支

6.git checkout "已有的分支名"

 * 		切换到另一条分支；工作区文件夹得内容也会随着改变，相当于回滚



## 13. Command类

+ main函数
  + 功能：从命令行中读取git命令
    + 输入"initial / commit / history / rollback / checkout / branch"，实现对应功能
    + 否则，输出“Sorry, this is not a git command. Try again.”

## 14. Global.java

​	所有blob、 tree、 commit文件和HEAD文件、保存当前分支的文件，其存放路径都定义在Global.java这个文件类中，且定义为静态变量（全局）。
​	注意，这些路径如果用代码更改了，都是只停留在内存中的，上次退出时停留在哪个分支是不会记得的

+ 数据域
  + (#)String **filePath** = "D:\\Users\\chenxling\\Pictures\\py图"：工作区目录
  + (#)String **objectPath** = "D:\\AsimpleGit\\object"：存放所有blob、 tree文件的目录
  + (#)String **save_current_branch_file** = "D:\\AsimpleGit\\current_branch.txt"：用于存放当前分支名
  + (#)String **branches** = "D:\\AsimpleGit\\branches"：该文件夹用于存放所有分支文件夹，包括主分支
  + (#)String **current_branch**：当前分支名
  + (#)String **gitPath**：存放commit文件的目录
+ 构造函数
  + **Global**()
    + 功能：初始化，从文件中读取当前分支；得到当前分支的名字，以及它的文件夹路径
    + 参数：无

