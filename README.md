> 参考慕课网教程，整理了代码和笔记。介绍了Spring中的IOC 和AOP。
> 原址：https://www.imooc.com/learn/196
> 我整理好的代码：https://github.com/birdskyws/springframework_practise
> 在idea中使用statics插件，统计了项目行数1500
![](https://upload-images.jianshu.io/upload_images/6234504-cd663e8263ef703f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
## 入门知识：面向接口编程  
interface声明接口，接口中的函数不用public/private，但是要匹配参数和异常。
```
// 接口
public interface OneInterface {
    String hello(String word);
}
// 实现类
public class OneInterfaceImpl implements OneInterface {
    @Override
    public String hello(String word) {
        return "word form interface \"word\":"+word;
    }
}
// 测试
public class Main {
    public static void main(String[] args) {
        OneInterface oif = new OneInterfaceImpl();
        System.out.println(oif.hello("青岛"));
    }
}
```
## IOC
Sping Context实现bean容器，容器维护bean对象，从容器中获取对象。IOC通过控制反转，实现模块解耦。con   
使用springframework需要包含spring-context，spring-bean，spring-core，spring-expressiong 四个包。
```
<!-- pom.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.birdsky.imooc</groupId>
    <artifactId>springframework_practise</artifactId>
    <version>1.0-SNAPSHOT</version>
    <!-- https://mvnrepository.com/artifact/junit/junit -->
    <properties>
        <spring.version>5.0.8.RELEASE</spring.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-expression</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.9.1</version>
        </dependency>
    </dependencies>
</project>
```
#### (1) 创建ApplicationContext
获取ioc对象的方法，用ClassPathXmlApplicationContext("classpath:"),也可以用File或在web容器中创建。
<font color='red'>此处正则表达式含义</font>
```
private ClassPathXmlApplicationContext context;
context = new ClassPathXmlApplicationContext(springXmlpath.split("[,\\s]+"));
context.start();
```
#### (2) spring 注入方式 
（1）设值 （2）构造参数。（后面高级应用，按默认名注入@Autowired）
```
## 设置注入，定义变量设置setter方法
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="injectionService" class="org.birdsky.ioc.injection.service.InjectionServiceImpl">
        <property name="injectionDAO" ref="injectionDAO"></property>
    </bean>
    <bean id="injectionDAO" class="org.birdsky.ioc.injection.dao.InjectionDAOImpl"></bean>
</beans>
```
```
## 构造注入，设置构造函数参数
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="injectionService" class="org.birdsky.ioc.injection.service.InjectionServiceImpl">
        <!--
         -- constructor injectionServiceImpl 构造函数设置参数
         -- name 构造函数传入的参数名，injectionDAO2是参数名
         -- ref对象xml文件后面的id
        -->
        <constructor-arg name="injectionDAO2" ref="injectionDAO"></constructor-arg>
    </bean>
    <bean id="injectionDAO" class="org.birdsky.ioc.injection.dao.InjectionDAOImpl"></bean>
</beans>
```
#### （3） bean的作用域
- single:单例，指定bean容器只有一份，即context中只有一个。
- prototype：每次请求创建新的实例，destroy方式不生效。？?
- request：每次http请求创建一个实例且在当前request内有效
- global session ？？
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="scope" class="org.birdsky.ioc.bean.Scope" scope="prototype"<!-- single--> ></bean>
</beans>
```
#### （4） 初始化和回收。生命周期
- xml文件中定义 init-method 和 destroy-methond,调用类中的方法
- 继承接口，InitializingBean,DisposableBean 实现afterPropertiesSet、destroy两个函数
#### （5） Aware接口
通过接口，回调的方式获取ApplicationContext对象（容器对象）
```
public class ApplicationContextAWARE implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void printSelf()
    {
        ApplicationContextAWARE application = (ApplicationContextAWARE)this.applicationContext.getBean("applcationAWARE");
        System.out.println("application code :"+application.hashCode());
    }
}
```
#### (6) 自动装配
```
public class AutowireServiceImpl implements AutowireService {
    // 定义对象和setter方法 或使用@Autowired 方法
    private AutowireDAO autowireDAO;
    public void setAutowireDAO(AutowireDAO autowireDAO) {
        this.autowireDAO = autowireDAO;
    }
}
```
xml文件中声明 default-autowire="byName",实现了：
AutowireServiceImpl中autowireDAO对象的装配。
```private AutowireDAO autowireDAO;```变量名autowireDAO和```<bean id="autuowireDAO" >```是相同Name,就是byName的由来。

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd"
        default-autowire="byName">
    <bean id="autowireService" class="org.birdsky.ioc.autowire.AutowireServiceImpl"></bean>
    <bean id="autowireDAO" class="org.birdsky.ioc.autowire.AutowireDAOImpl"></bean>
</beans>
```
另外2种："byType","constructor"对应的xml文件,此时id没有意义，也可以没有id，注入时会查找有没有该类型的对象。
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd"
    default-autowire="constructor" <!--"byType"-->>
    <bean id="autowireService" class="org.birdsky.ioc.autowire.AutowireServiceImpl"></bean>
    <bean <!--id="autowireDAO2"--> class="org.birdsky.ioc.autowire.AutowireDAOImpl"></bean>
</beans>
```
#### (7) 加载资源文件
ResoureLoader加载不同资源用不同前缀
- classpath:
- file:
- http：
- none：

```ApplicationContext applicationContext```对象继承```interface ResourceLoader```接口，getResource方法可以读取资源文件。  
```
//读取资源文件:
Resource resource = applicationContext.getResource("classpath:config.txt");
```
>接口注意事项。(继承接口，接口中的方法必须在类中必须实现)
>重写接口中声明的方法时，需要注意以下规则：
>
>1. 类在实现接口的方法时，不能抛出强制性异常，只能在接口中，或者继承接口的抽象类中抛出该强制性异常。
>
>2. 类在重写方法时要保持一致的方法名，并且应该保持相同或者相兼容的返回值类型。
>
>3. 如果实现接口的类是抽象类，那么就没必要实现该接口的方法。   

#### (8) classpath扫描  
java 1.5 和spring 3.0以后，引入注解的方式进行bean的注册。  
通过xml配置扫描路径，自动加载路径下的类。
xml文件格式，设置``xmln:context```和 ```xsi:schemalocation```
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd">
    <context:component-scan base-package="org.birdsky.ioc.annotation"></context:component-scan>
</beans>
```
类声明为spring bean使用注解@Component @Service @Reposity.. ;显示定义bean name @Component("name")
  
通过注解声明，设置生命周期@Scope(""prototype/singlton"")
  
#### (9) @Autowired   
- @Autowired注解可以用在类声明，成员变量声明上。  
对众所周知（环境变量中）的对象@Autowire注解，自动获得引用。
BeanFactory,ApplicationContext,Environment,ResourceLoader
- @Autowired 用在函数声明上，从容器中获得参数。  

@Autowired 应用在集合型成员变量，会保存容器中的全部对象。
```
    @Autowired
    private List<BeanInterface> list;
```

#### (10) @Qualifier注解，当容器中存在beanInterfaceOne,beanInterfaceTwo 都可以装载给beanInterface对象，此时用@Qualifier(id)的方式实现缩小范围。
```
    @Autowired
    @Qualifier("beanInterfaceOne")
    private BeanInterface beanInterface;
```
#### (11) 基于java 容器的注解 @Configuration
@Configuration，声明一个类是包含bean的配置类    
@Bean，声明一个被容器自动加载的类
@Scope 默认@Bean是单例 @Scope(value="prototype")
```
@Configuration
public class MoocConfiguration {

    //@Bean(name="store")
    @Bean
    public Store stringStore()
    {
        return new StringStore();
    }
}
```
```
//通过函数名，加载类
Store store = super.getBean("stringStore");
```
#### (12) 将配置文件中的变量加载到容器中
(1) 定义xml文件，写入<context:property-placeholder location="xxxx">加载资源文件xxx。
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd">
    <context:property-placeholder location="classpath:/dbconfig.properties"></context:property-placeholder>
</beans>
```
(2) 用@ImportResource("classpath:/configure.xml")，加载(1)的资源文件。@Value注解为变量赋值。
```
@Configuration
@ImportResource("classpath:/configure.xml")
public class StoreConfiguration {
    @Value("${username}")
    private String username;
    @Value("${password}")
    private String password;
    @Value("${password}")
    private String url;

    @Bean
    public MyDriveManager driveManager(){
        return new MyDriveManager(username,password,url);
    }
}
```
 
#### (13) @Resource @Inject @Named
@Resource 和@Autowired 功能一样，按id加载对象
@Inject 和@Autowired 功能一样
@Named
```
@Named("movieListener")
public class MovieListener{
    
}
```

## AOP（ Aspect oriented Programming） 
#### 一、概念  
面向切面的编程，通过预编译和运行期动态代理实现程序。（面试时，被问到过AOP是如何实现的，预编译和动态代理）  
#### 常用场景  
日志记录、性能统计、安全控制、*事务处理*、异常处理。  
- 预编译：AspectJ
- 动态代理： JDK动态代理和CGlib动态代理。 

#### 二、主要组件（概念）：
- 切面Aspect
- 通知Advice
- 切入点Pointcut
- 引入 Introduction
- 目标对象 target Object
- 织入：编译时织入、类加载时织入、执行时织入。
- 连接点：Jointpoint？？  
后面会一步步介绍每一个组件，并把他们连接起来。


#### 三、切面配置Aspect schema
###### 流程：
    (1) 定义aop:config
    (2) 在config中可以定义多个Aspect，例如logAspect获得AuthorAspect。每个Aspect中定义多个Advice和多个Pointcut。  
    (3) 配置Advice 和 pointcut 绑定。  
    (4) 定义Advice 有多种方式（前、后、环绕、final）等，绑定到pointcut上。  
    (5) pointcut采用通配符实现方法匹配。
#### （1）Aspect  
<aop:config> 一级标签。   
<aop:aspect id="" ref="">配置一个aspect，一个Aspect类包含多个Advice（Advice就是函数，注入到切入点的函数）。   

#### （2）pointcut  
```
execution(public **(..)) //执行所有public方法  
execution(* set*(..)) //执行所有set方法  
execution(* com.xyz.service.AccountService.*(..))//  AccountService所有方法
execution(* com.xyz.service..(..)) //service包下的所有方法  
execution(* com.xyz.service...(..)) //service包及其子包
within(com.xyz.serice.*)   
within(com.xyz.service..*) //within 用于匹配指定类型的方法执行 
this(com.xyz.service.AccountService) //this 匹配当前AOP代理对象类型的执行方法？.. 代表参数  
** 代表函数  
*  代表函数类型  
. 代表类名
```
#### （3）Advice
Before/AfterReturning/AfterThrowing/After/Around  
指明了advice(注入函数)和切入点的关系。Before，即在pointcut运行之前运行。  
2种绑定形式，pointcut-ref和pointcut。
```
<aop:before method="aopbefore" pointcut-ref="moocPointcut" />
<aop:around  method="initround" pointcut="execution(* mooc.birdsky.springframework.aop.schema.AspectBiz.AspectBiz.init(String,int)) and args(bizName,times)" />
```
XML范例：spring-aop-schema.xml   
业务Bean:AspectBiz,切面Bean:MoocAspect。实现MoocAspect对AspectBiz函数的注入。
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">
    <!-- 切面bean -->
    <bean id="moocAspect" class="mooc.birdsky.springframework.aop.schema.MoocAspect"></bean>
    <!-- 业务bean-->
    <bean id="aspectBiz" class="mooc.birdsky.springframework.aop.schema.AspectBiz.AspectBiz"></bean>
    <!-- 定义aop -->
    <aop:config>
        <!--定义一个切面，一个切面通常完成一项功能，如日志、安全等-->
        <aop:aspect id="moocAspectAOP" ref="moocAspect">
            <!--定义一个切入点，可以用多种通配符匹配切入点-->
            <aop:pointcut id="moocPointcut"
                          expression="execution(* mooc.birdsky.springframework.aop.schema.AspectBiz.AspectBiz.*(..))" />
            <!--advice 通知（作者称它为注入函数）,-->
            <!--advice 注入点定义 before/after-returning/after-throwing/after-->
            <aop:before method="aopbefore" pointcut-ref="moocPointcut" />
            <aop:after-returning method="aopafterReturn" pointcut-ref="moocPointcut" />
            <aop:after-throwing method="aopafterThrow" pointcut-ref="moocPointcut" />
            <aop:after method="aopafterFinal" pointcut-ref="moocPointcut"  />
            <!-- 处理pointcut中抛出异常的函数-->
            <aop:around method="aopround" pointcut-ref="moocPointcut" />
            <!--除了用pointcut-ref设置注入点，可用直接用pointcut设置注入点-->
            <aop:around  method="initround"
                          pointcut="execution(* mooc.birdsky.springframework.aop.schema.AspectBiz.AspectBiz.init(String,int)) and args(bizName,times)" />
        </aop:aspect>
    </aop:config>
</beans>
```
#### (4)Advice with Params 通知参数
xml 定义execution时不用..匹配参数，并通过args指明参数。
```
<aop:around  method="initround"
                          pointcut="execution(* mooc.birdsky.springframework.aop.schema.AspectBiz.AspectBiz.init(String,int)) 
                          and args(bizName,times)" />
```
java，业务类
```
    public void init(String bizName, int times){
        this.bizName = bizName;
        this.times = times;
        System.out.println("bizName:"+this.bizName+ " try times:"+times);
    }
```
java，切面实现advice时传入参数。
```
    public  Object initround(ProceedingJoinPoint pjp,String bizName,int times){
        Object obj = null;

        try {
            System.out.println("round before init process and bizName:"+bizName+" times:"+times);
            obj = pjp.proceed();
        }catch (Throwable e)
        {
            e.printStackTrace();
        }finally {
            System.out.println("round after init process");
            return obj;
        }
    }
```
#### （5）Introductions
Aspect 可以给业务对象添加一个父类（新添加的父类继承原父类，在继承链上插入一节）。切面配置中指定一个接口和实现类，那么业务对象就增加了接口和属性。
```
    <aop:config>
        <!--定义一个切面-->
        <aop:aspect id="moocAspectAOP" ref="moocAspect">
            <aop:declare-parents
                    types-matching="mooc.birdsky.springframework.aop.schema.AspectBiz.*(+)"
                    implement-interface="mooc.birdsky.springframework.aop.schema.Introductions.Fit"
                    default-impl="mooc.birdsky.springframework.aop.schema.Introductions.FitImpl" />
        </aop:aspect>
    </aop:config>
```
```
        Fit fit = (Fit) super.getBean("aspectBiz");
        fit.filter();
```
> 工程中有一段注入构造函数的代码，没有实现。
#### 例子，通过注入实现函数重试次数限制
```
/**
* maxRetries,设置重试次数
* around 注入实现函数重试
*/
public class ConcurrentOperationExecutor {
    private int maxRetries ;
    private int order;

    public int getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Object doConcurrentOperation(ProceedingJoinPoint pjp) throws Throwable
    {
        int numAttempts = 0;
        RuntimeException exception;
        do {
            numAttempts++;
            System.out.println("Try times : " + numAttempts);
            try {
                return pjp.proceed();
            } catch (RuntimeException ex) {
                exception = ex;
            }
        } while (numAttempts <= this.maxRetries);
        System.out.println("Try error : " + numAttempts);
        throw exception;
    }
}
```
```
<beans>
    <bean id="concurrentOperationExecutor" class="mooc.birdsky.springframework.aop.schema.concurrent.ConcurrentOperationExecutor" >
        <property name="maxRetries" value="3" />
        <property name="order" value="100" />
    </bean>
    <bean id="aspectBiz" class="mooc.birdsky.springframework.aop.schema.AspectBiz.AspectBiz"/>
    <aop:config>
        <aop:aspect id="concurrentOperationRetry" ref="concurrentOperationExecutor">
            <aop:around method="doConcurrentOperation" pointcut="execution(* mooc.birdsky.springframework.aop.schema.AspectBiz.AspectBiz.biz*())" />
        </aop:aspect>
    </aop:config>
</beans>
```
#### (6)Spring AOP API
##### 流程：
    （1）声明NameMatchMethodPointcut bean，定义Pointcut   
    （2）声明advice类和advise类   
    （3）声明ProxyFactoryBean，获取业务类，并将advice绑定和业务类，依据定义的Pointcut。   

NameMatchMethodPointcut对象，该对象是Springframework中的对象，通过bean声明加载，通过mappedNames实现按名字匹配的pointcut。

```
    <bean id="pointcutBean" class="org.springframework.aop.support.NameMatchMethodPointcut">
        <property name="mappedNames">
            <list>
                <value>sa*</value>
                <!-- 匹配函数名 -->
            </list>
        </property>
    </bean>
```
ProxyFactoryBean，通过IOC机制，装配advice和Incepter。advice绑定到全部函数上。
```
    <!-- advice类 -->
    <bean id="birdskyAfterAdvice" class="mooc.birdsky.springframework.aop.api.BirdskyAfterAdvice" />
    <bean id="birdskyBeforeAdvice" class="mooc.birdsky.springframework.aop.api.BirdskyBeforeAdvice" />

    <!-- advisor类 -->
    <bean id="defaultAdvisor" class="org.springframework.aop.support.DefaultPointcutAdvisor">
        <property name="advice" ref="birdskyBeforeAdvice" />
        <property name="pointcut" ref="pointcutBean" />
    </bean>
    
    <bean id="bizLogicImpl" class="org.springframework.aop.framework.ProxyFactoryBean" >
        <property name="target">
            <!--<ref bean="bizLogicImplTarget" />-->
            <!--采用直接定义bean，而不是通过ref的方式。-->
            <!--采用外部定义bean，这个bean可以被直接获取，这样获取的bean是没有被添加切入点的。-->
            <bean class="mooc.birdsky.springframework.aop.api.BizLogicImpl" />
        </property>
        <property name="interceptorNames">
            <list>
                <value>defaultAdvisor</value>
                <value>birdskyAfterAdvice</value>
                <!-- interceptorNames  advice 默认绑定到全部函数上 -->
            </list>
        </property>
    </bean>
```
通过API定义Advice。**复习**：xml配置时，method指定Aspect的方法，而API方式是接口实现，通过xml设置到interceptorNames上。
```
public class BirdskyBeforeAdvice implements MethodBeforeAdvice,ThrowsAdvice,AfterReturningAdvice,MethodInterceptor {

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("BirdskyBeforeAdvice:"+method.getName());
    }

    public void afterThrowing(Exception e) throws Throwable{
        System.out.println("Birdsky afterThrow1 ");
    }
    public void afterThrowing(Method method,Exception e) throws Throwable{
        System.out.println("Birdsky afterThrow2 method:" + method.getName());
    }
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object target) throws Throwable {
        System.out.println("Birdsky afterReturning method"+method.getName()+"targer :"+target.getClass().getName());
    }
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("method:"+invocation.getMethod().getName());
        System.out.println("target:"+invocation.getStaticPart().getClass().getName());
        Object obj = invocation.proceed();
        return obj;
    }
}
```
#### (7)AspectJ 通过注解实现AOP
声明AspectJ：(1) 加载Configuration配置类，添加注解@EnableAspectJAutoProxy
```
@Configuration
@EnableAspectJAutoProxy
public class Application{
}
```
（2）xml
```
<aop:aspectj-autoproxy />
```
**Aspect 代价昂贵，效率低。所以应明确切入点范围，使切面较小。**
##### 流程：
    （1）定义一个Aspect，包含pointcut定义，Advice定义。
    （2）用@Pointcut定义切入点，函数名是@Pointcut的签名。
    （3）用@Before @After等注解声明advice，匹配pointcut，可以用切入点或者签名。
示例：
```
@Component
@Aspect
public class MoocAspectj {
    @Pointcut("execution(* mooc.birdsky.springframework.aop.aspectj.biz.AspectBiz.*(..))")
    public void pointcut(){ }

    @Pointcut("within(mooc.birdsky.springframework.aop.aspectj.biz.*)")
    public void bizPointcut(){}

    @Before("execution(* mooc.birdsky.springframework.aop.aspectj.biz.AspectBiz.*(..))")
    public void before(){
        System.out.println("Aspectj exec before1");
    }

    @Before("pointcut()")
    public void before2()
    {
        System.out.println("Aspectj exec before2 ");
    }
}
```


处理参数示例：
```
    //Aspect 定义advice
    @Before("pointcut() && args(arg)")
    public void before3(String arg)
    {
        System.out.println("Aspectj exec before3 withParam:"+arg);
    }
```
```
    // 通配mooc.birdsky.springframework.aop.aspectj.biz.AspectBiz.*(..))
    // 结合advice的定义，获取输入参数
    public String say(String arg)
    {
        String ret = "说一些话："+arg;
        System.out.println(ret);
        //throw new RuntimeException("say failed");
        return ret;
    }
```
Aroud Advice示例：
```
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp ) throws Throwable{
        System.out.println("Around1");
        Object obj = pjp.proceed();
        System.out.println("Around2:"+obj);
        return obj;
    }
```
