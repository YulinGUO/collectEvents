# collectEvents

### 项目概览
    用户行为数据收集

#### 收集处理流程

    1. Play ! 作为web服务器，使用RESTful 规范编写接口（客户端事先埋点，然后调用接口上传数据）
    2. Play ！接口接受到的记录(json形式)经过处理后，先保存到 concurrentQueue中
    3. Play! 启动后，start一个Akka schedulable actor.他每隔一段时间，让子actor去poll queue中的数据
    4. 调用flume的封装的rpc，将数据发送到指定的端口。
    5. Flume source端接收数据，按照配置重定向数据,sink到console.
    
    
#### Flume 配置

    a1.channels = c1
    a1.sources = r1
    a1.sinks = k1
   
    a1.channels.c1.type = memory
   
    a1.sources.r1.channels = c1
    a1.sources.r1.type = avro
   
    a1.sources.r1.bind = 0.0.0.0 
    a1.sources.r1.port = 41414
   
    a1.sinks.k1.channel = c1
    a1.sinks.k1.type = logger 
    
此处flume端为简单的单点配置，source接收41414的rpc消息，然后保存到channel中，sink到console中（数据收集一般sink到HDFS中，并且可以多点收集）

#### 运行
     
     1.Play! 运行 
     2.Flume 运行

拷贝Flume的配置到example.conf，然后运行以下命令
    
     bin/flume-ng agent --conf conf --conf-file example.conf --name a1 -Dflume.root.logger=INFO,console
     
     

[@Contact me](guoyulin_nanjing@hotmail.com)
    
    


