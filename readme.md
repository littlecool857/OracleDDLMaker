# OracleDDLMaker

## 概述

`OracleDDLMaker` 是一个用于批量生成 **Oracle DDL** 建表语句的工具。它可以根据配置的 Excel 文件，自动生成符合 Oracle 数据库要求的建表语句。支持生成一个或多个 SQL 文件，并且能够根据配置生成表的主键约束。

### 版本历史
- **2024.11.26**  
  - 更新主键创建功能
  - 修复编码问题
  - 更新程序图标

---

## 配置文件：`config.properties`

在根目录下，`resources/config.properties` 文件包含了程序运行所需的配置项。请根据实际情况调整配置。

### 配置文件示例

```properties
file.excel.path=src/file/source/test.xlsx  # Excel 台账文件的路径
file.save.path=src/file/target             # 生成 SQL 文件的保存路径
```

- `file.excel.path`：指定 Excel 文件的路径。Excel 中应包含表的结构信息。
- `file.save.path`：指定生成的 SQL 文件保存的文件夹路径。

---

## Excel 文件结构

Excel 文件应包含如下列，且列顺序必须与此文档中的示例保持一致。文件中的每一行代表一个表的结构信息。

| Schema Name | Table Name | Column Name | Column Type   | Column Comment | Table Comment | Is_key |
|-------------|------------|-------------|---------------|----------------|---------------|--------|
| dwd         | test       | id          | varchar2(10)  | 通用id         | 姓名表         | 1      |
| dwd         | test       | name        | varchar2(11)  | 姓名           | 姓名表         |        |
| dwd         | test2      | id          | varchar2(10)  | 通用id         | 年龄表         | 1      |
| dwd         | test2      | age         | varchar2(11)  | 年龄           | 年龄表         |        |
| src         | test3      | asd         | number        | 阿斯顿         | 而房价         |        |
| src         | test3      | id          | number        | id主键         | 而房价         | 1      |
| src         | test3      | asfasf      | varchar2(1212)| adsfas         | 而房价         |        |

- **Schema Name**：空间名
- **Table Name**：表名
- **Column Name**：字段名称
- **Column Type**：字段类型
- **Column Comment**：字段注释
- **Table Comment**：表注释
- **Is_key**：是否为主键。如果是主键，则填写 `1`，否则留空。

> **注意**：  
> 由于程序根据 Excel 文件的行索引来获取配置信息，所以表头行可以省略，但**务必保持列的顺序与上述表格一致**。

---

## 程序使用

程序运行后会通过命令行提示，提供以下三个选项：

```bash
ERROR StatusLogger Log4j2 could not find a logging implementation. Please add log4j-core to the classpath. Using SimpleLogger to log to the console...
请输入数字：
1: 生成单个 SQL DDL 文件（所有建表语句都在一个文件里）
2: 生成多个 SQL DDL 文件（每个表的建表语句拥有独立的文件）
3: 同时生成单个和多个 SQL DDL 文件
请输入你的选择（1 || 2 || 3）：
```

根据提示选择你需要的生成方式：
- **选项 1**：生成一个包含所有建表语句的单个 SQL 文件。
- **选项 2**：为每个表生成一个独立的 SQL 文件。
- **选项 3**：同时生成一个单独的 SQL 文件和多个独立的 SQL 文件。

生成的文件将保存到 `file.save.path` 配置中指定的目录，文件后缀为 `.sql`。

---

## 示例文件结构

以下是一个典型的项目文件结构示例：

```bash
A:.
├─resources
└─src
    └─file
        ├─source
        │   └─test.xlsx       # Excel 文件：包含表结构信息
        └─target
            └─ddl_output.sql  # 生成的 SQL 文件保存位置
```

---

## 运行程序

1. **打开命令行**，定位到 `OracleDDLMaker.exe` 所在目录。
2. **运行程序**：在命令行中输入 `OracleDDLMaker.exe`，程序将读取 `resources/config.properties` 配置文件，根据其中指定的路径生成相应的 SQL 文件。

---

## 错误与调试

如果程序运行过程中出现以下错误提示：

```bash
ERROR StatusLogger Log4j2 could not find a logging implementation. Please add log4j-core to the classpath. Using SimpleLogger to log to the console...
```

这只是一个日志相关的警告，程序依然可以正常运行。你可以忽略此信息，或根据需要配置 `log4j` 以自定义日志输出。

---

## 联系我们

如遇到问题或有其他需求，欢迎联系开发者：

[weiliangchen551@qq.com] 

or 

[weiliangchen551@gmail.com]
