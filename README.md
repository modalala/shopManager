# shopManager
springcloudAlibaba运营商后台管理

## 工程模块和POM参考部分

parent  | dependencies

​            | commons

​            | provider    |  api 

​                               |  service

​            | business   | fegin

​                               | service       

1. Parent总模块

   ```xml
   <!--parent-->
   	<parent>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-parent</artifactId>
           <version>2.1.7.RELEASE</version>
           <relativePath/> <!-- lookup parent from repository -->
       </parent>
   	<modules>      <!--注意要有顺序-->
           <module>dependencies</module>   <!--依赖-->
           <module>commons</module>        <!--这里commons依赖不到我就没动-->
           <!--     <module>configuration</module>--> <!--这里准备弄sentinel -->
               <module>provider</module>  <!--生产者-->
               <module>business</module>  <!--消费者-->
       </modules>
   
   	<dependencyManagement>  <!-- 直接依赖 -->
           <dependencies>
               <dependency>
                   <groupId>com.fosu.shop</groupId>
                   <artifactId>dependencies</artifactId>
                   <version>${project.version}</version>
                   <type>pom</type>
                   <scope>import</scope>
               </dependency>
           </dependencies>
       </dependencyManagement>
   ```

   

2. provide

   ```xml
   	<parent>
           <groupId>com.fosu.shop</groupId>
           <artifactId>parent</artifactId>
           <version>0.0.1-SNAPSHOT</version>
       </parent>
   
   	<artifactId>provider</artifactId>
       <packaging>pom</packaging>
   
   		<!-- dependency start-->
   			<!-- nacos-client -->
   			<!-- dubbo -->
   			<!-- spring-context-support -->
   		<!-- dependency end-->
   
   	<modules>
           <module>goods-manager-provider-api</module>
           <module>goods-manager-provider-service</module>
       </modules>
   ```

   

   1. goods-manger-provider-api

      - api   dubbo提供接口
      - domain 实体类
      - vo 需要增加的vo类

      ```xml
      	<parent>
              <groupId>com.fosu.shop</groupId>
              <artifactId>provider</artifactId>
              <version>0.0.1-SNAPSHOT</version>
          </parent>
      
      	<artifactId>goods-manager-provider-api</artifactId>
          <packaging>jar</packaging>
      
      	<!-- dependency start-->
              <!--lombok -->
              <!--persistence-api -->
              <!--pagehelper -->
              <!--jackson-databind -->
      
      		<artifactId>business-profile-feign</artifactId>
      	<!-- dependency end-->
      
      ```

      

   2. goods-manger-provider-service

      - mapper
      - service    api的实现类
      - utils         byte[ ] 转成文件工具类
      - resources
        - mapperxml
        - application.yml
      - test

      ```xml
      	<parent>
              <groupId>com.fosu.shop</groupId>
              <artifactId>provider</artifactId>
              <version>0.0.1-SNAPSHOT</version>
          </parent>
      
      	<artifactId>goods-manager-provider-service</artifactId>
          <packaging>jar</packaging>
      
      	<!-- dependency start-->
              <!--spring-boot -->
              <!--spring-cloud -->
              <!--nacos-client -->
              <!--dubbo -->
      		<!--spring-context-support -->
      		<!--HikariCP -->
      		<!--排除 tomcat-jdbc -->
      		<!--mysql-connector-java  -->
      		<!--tk.mybatis  -->
      		<!--spring-test -->
      
      		<!--goods-manager-provider-api  -->
      		<!--business-profile-feign  -->
      		<!--  -->
      		<artifactId>business-profile-feign</artifactId>
      	<!-- dependency end-->
      ```

      

   

3. business

   ```xml
   	<parent>
           <groupId>com.fosu.shop</groupId>
           <artifactId>parent</artifactId>
           <version>0.0.1-SNAPSHOT</version>
       </parent>
   	<artifactId>business</artifactId>
       <packaging>pom</packaging>
   	<!--dependency start -->
   	<!--spring-cloud  -->
   	<!--lombok  -->
   	<!--okhttp  -->
   	<!--openfeign  -->
   	
   	<!-- 这里common不知道为什么依赖不进去 所有我把common类放进去了-->
   	<!--commons -->
       <!--dependency end -->
   	<modules>
           <module>business-profile-feign</module>
           <module>business-profile-service</module>
       </modules>
   ```

   

   1. bussiness-profile-feign

      ```xml
      	<parent>
              <groupId>com.fosu.shop</groupId>
              <artifactId>business</artifactId>
              <version>0.0.1-SNAPSHOT</version>
          </parent>
      	<artifactId>business-profile-feign</artifactId>
          <packaging>jar</packaging>
      	<!--feign这里我没加 后面会加的 -->
      ```

      

   2. bussiness-profile-service

      ```xml
      	<parent>
              <groupId>com.fosu.shop</groupId>
              <artifactId>parent</artifactId>
              <version>0.0.1-SNAPSHOT</version>
          </parent>
      	<artifactId>business</artifactId>
          <packaging>pom</packaging>
      	<!--dependency start -->
      	<!--spring-cloud -->
      	<!--lombok -->
      	<!--okhttp -->
      	<!--feign-okhttp -->
      
      	<!--commons -->
          <!--dependency end -->
      ```



## 消费者模块详解

1. 数据库连接部分

   1. app主类：**mapper扫描** `@MapperScan(basePackages = "com.fosu.shop.provider.mapper")`

   2. application.yml配置 （注意：**本机数据库是mysql8**）

      ```yml
      datasource:
          type: com.zaxxer.hikari.HikariDataSource
          driver-class-name: com.mysql.cj.jdbc.Driver
          url: jdbc:mysql://127.0.0.1:3306/pinyougoudb?serverTimezone=GMT&useUnicode=true&characterEncoding=utf-8&useSSL=false
          username: root
          password: miki666
          hikari:
            minimum-idle: 5
            idle-timeout: 600000
            maximum-pool-size: 10
            auto-commit: true
            pool-name: MyHikariCP
            max-lifetime: 1800000
            connection-timeout: 30000
            connection-test-query: SELECT 1
            
      mybatis:
        type-aliases-package: com.fosu.shop.provider.domain
        mapper-locations: classpath:mapperxml/*.xml
      ```

2. 获取数据需求

   **api说明**

   | CRUD |     api名称     |   说明   |       难点       |
   | :--: | :-------------: | :------: | :--------------: |
   | 插入 |   postContent   | 广告内容 | 图片上传&JavaIO  |
   | 查询 |   getContent    | 所有广告 |                  |
   | 删除 |   delContent    | 对应广告 |                  |
   | 修改 |   fixContent    | 对应广告 |                  |
   |      |                 |          |                  |
   | 查询 |  getLikeSpecs   | 规格信息 | 多表分页模糊查询 |
   | 查询 |  getSpecsName   | 规格名字 |                  |
   | 插入 |  postLinkSpecs  | 规格信息 |   级联多表插入   |
   | 删除 |    delSpecs     | 规格信息 |                  |
   | 修改 |    fixSpecs     | 规格信息 |   级联多表修改   |
   |      |                 |          |                  |
   | 查询 | getPageItemCat  | 顶级分类 |                  |
   | 查询 | getLowerItemCat | 下级分类 |                  |
   | 插入 |   postItemCat   |   分类   |                  |
   | 删除 |   delItemCat    |   分类   |                  |
   | 修改 |   fixItemCat    |   分类   |                  |
   |      |                 |          |                  |
   | 查询 |  getLikeBrand   | 品牌信息 |                  |
   | 查询 |  getBrandName   | 品牌名字 |                  |
   | 修改 |    fixBrand     | 品牌信息 |                  |
   | 插入 |    postBrand    | 品牌信息 |                  |
   | 删除 |    delBrand     | 品牌信息 |                  |
   |      |                 |          |                  |
   |      |                 |          |                  |

   

   **较难点说明**

   1. 分页+模糊查询

      ```java
          /**
           * 分页模糊查询
           * @param pageNum 第几页
           * @param pageSize 一页有多少个
           * @param selectName 模糊查询字段
           * @return
           */
      @Override
          public PageInfo<TbBrand> getLikeBrand(Integer pageNum, Integer pageSize, String selectName) {
      		//分页开始
              PageHelper.startPage(pageNum, pageSize);
              
              //带模糊查询
              Example example = new Example(TbBrand.class);
              example.createCriteria().andLike("name","%"+selectName+"%");
              
              PageInfo<TbBrand> pageInfo = new PageInfo<>(tbBrandMapper.selectByExample(example));
              return pageInfo;
          }
      ```

   2. 分页+模糊+两表查询

      ```java
          @Override
          public PageInfo<TbSpecification> getLikeSpecs(Integer pageNum, Integer pageSize, String selectName) {
              PageHelper.startPage(pageNum,pageSize);
              //需要在mapper.xml中定制
              PageInfo<TbSpecification> pageInfo = new PageInfo<>(tbSpecificationMapper.specsQueryPage(selectName));
      
              return pageInfo;
          }
      ```

      Mapper.xml

      ```xml
       <!--分页模糊查询-->
      <!-- 主表 的返回集 注意着了collection 的 select连接了子表查询-->
        <resultMap id="QueryPageList" type="com.fosu.shop.provider.domain.TbSpecification">
          <id column="id" jdbcType="BIGINT" property="id" />
          <result column="spec_name" jdbcType="VARCHAR" property="specName" />
          <collection property="tbSpecificationOptions" javaType="java.util.List" ofType="com.fosu.shop.provider.domain.TbSpecificationOption"
                       select="queryItemInfoById"  column="id">
          </collection>
        </resultMap>
      <!-- 子表 的返回集-->
        <resultMap id="specsOption" type="com.fosu.shop.provider.domain.TbSpecificationOption">
          <!--@mbg.generated generated on Sat Sep 07 16:54:48 CST 2019.-->
          <id column="id" jdbcType="BIGINT" property="id" />
          <result column="option_name" jdbcType="VARCHAR" property="optionName" />
          <result column="spec_id" jdbcType="BIGINT" property="specId" />
          <result column="orders" jdbcType="INTEGER" property="orders" />
        </resultMap>
      
      <!--子表查询 spec_id 对应 主表id（连接） -->
        <select id="queryItemInfoById" resultMap="specsOption">
              select * from tb_specification_option tbso where tbso.spec_id = #{id}
        </select>
      
        <select id="specsQueryPage" resultMap="QueryPageList" parameterType="String">
          select * from tb_specification
          <where>
              <if test="_parameter !=null and _parameter !=''">
                  and spec_name like concat('%',#{selectName}, '%')
              </if>
          </where>
        </select>
      ```

      

   3. 批量删除

      ```java
         /**
           * 批量删除
           * @param bandId
           * @return
           */
      @Override
          public Integer delBrand(List<Integer> bandId) {
      
              AtomicReference<Integer> num = new AtomicReference(0);
              bandId.forEach((e)->{
                  int i = tbBrandMapper.deleteByPrimaryKey(e);
                  num.getAndSet(num.get()+i);
              });
              return num.get();
          }
      ```

      

   4. javaIO（dubbo消费者传递byte[]  生产者接收转成文件）

      1. 消费者类

         ```java
         @Data
         public class ContentFileName {
             //文件原始名字
             private String originalFilename;
             //文件流
             private byte[] bytes;
             //文件大小
             private long size;
         }
         //============================================
         @Reference(version = "1.0.0")
             private ContentService contentService;
         
             @PostMapping("/fileUpload")
             @ApiOperation(value = "广告添加")
             public ResponseResultMe<Integer> upload(@RequestParam("file") MultipartFile file, ContentParam contentParam) {
         
                 TbContent tbContent = new TbContent();
         
                 tbContent.setCategoryId(contentParam.getCategoryId());
                 tbContent.setSortOrder(contentParam.getSortOrder());
                 tbContent.setTitle(contentParam.getTitle());
                 tbContent.setUrl(contentParam.getUrl());
                 tbContent.setStatus(contentParam.getStatus());
         
                 //是否没有文件
                 if (Objects.isNull(file) || file.isEmpty()) {
                     // return new ResponseResultMe<>(ResponseResultMe.CodeStatus.FAIL, "文件为空，请重新上传");
                     return new ResponseResultMe<>(ResponseResultMe.CodeStatus.BREAKING, "没有文件图片传入");
                 }
         
                 ContentFileName contentFileName = new ContentFileName();
                 try {
                     //这里转为byte[]
                     byte[] bytes = file.getBytes();
         
                     contentFileName.setOriginalFilename(file.getOriginalFilename());
                     contentFileName.setBytes(file.getBytes());
                     contentFileName.setSize(file.getSize());
         			//这里转入消费者
                     contentService.postContent(tbContent, contentFileName);
                 } catch (IOException e) {
                     e.printStackTrace();
                     return new ResponseResultMe<>(ResponseResultMe.CodeStatus.FAIL, "文件存储异常");
                 }
                 return new ResponseResultMe<>(ResponseResultMe.CodeStatus.OK, "文件存储成功");
             }
         ```

         

      2. 生产者类+工具

         ```java
         @Resource
             private TbContentMapper tbContentMapper;
         
             @Override
             public Integer postContent(TbContent tbContent, ContentFileName contentFileName) {
         		//本地地址
                 String localPath = "D:/doc";
         		
                 BufferedOutputStream bos = null;
                 FileOutputStream fos = null;
                 File file = null;
                 try {
                     String filePath = localPath;
                     File dir = new File(filePath);
                     //判断文件目录是否存在
                     if (!dir.exists() || !dir.isDirectory()) {
                         dir.mkdirs();
                     }
         
                     //已经随机过的文件名字（使用工具类)
                     String pathName = localPath + "/" + FileNameUtils.getFileName(contentFileName.getOriginalFilename());
         
                     file = new File(pathName);
                     fos = new FileOutputStream(file);
                     bos = new BufferedOutputStream(fos);
                     
                     //成功从byte转成文件写入
                     bos.write(contentFileName.getBytes());
         
                     //这里要改  对应springmvc映射
                     String url = "http://localhost:9002/doc/";
                     url = url + file.getName();
         			
                     //存入数据库中
                     tbContent.setPic(url);
                     tbContentMapper.insert(tbContent);
                     return 1;
                 } catch (IOException e) {
                     e.printStackTrace();
                 } finally {
                     if (bos != null) {
                         try {
                             bos.close();
                         } catch (IOException e1) {
                             e1.printStackTrace();
                         }
                     }
                     if (fos != null) {
                         try {
                             fos.close();
                         } catch (IOException e1) {
                             e1.printStackTrace();
                         }
                     }
                 }
                 return 1;
             }
         ```

         

      

      



