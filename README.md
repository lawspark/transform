# Document Transform Service

基于 Spring Boot 的文档转换服务，支持 Word 模板渲染和文档格式转换。

## 功能特性

- **模板渲染**: 支持基于 Word 模板的数据渲染
- **格式转换**: 支持多种文档格式之间的转换
- **RESTful API**: 提供简洁的 HTTP API 接口

## 技术栈

- **Java 21**
- **Spring Boot 3.5.6**
- **Apache POI 5.4.1** - 文档处理
- **poi-tl 1.12.2** - Word 模板引擎
- **JODConverter 4.4.9** - 文档格式转换
- **LibreOffice** - 底层文档处理引擎

## API 接口

### 模板渲染
```http
POST /api/v1/render
Content-Type: multipart/form-data

模板文件 + 数据模型
```

### 格式转换
```http
POST /api/v1/convert
Content-Type: multipart/form-data

文档文件
```

### 转换并渲染
```http
POST /api/v1/transform
Content-Type: multipart/form-data

模板文件 + 数据模型
```

## 环境要求

- Java 21
- Gradle 9.1.0+
- LibreOffice (用于文档转换)

## 构建运行

```bash
# 构建项目
./gradlew build

# 运行服务
./gradlew bootRun

# 构建 Docker 镜像
./gradlew bootBuildImage
```

## 开发配置

- **本地开发**: 使用 `local` profile 可跳过 REST API 控制器
- **内存配置**: 默认分配 2GB 内存 (`-Xmx2g`)
- **并行构建**: 已启用 Gradle 并行构建和缓存

## 版本

当前版本: `0.2.1-SNAPSHOT`