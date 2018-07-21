# 运行方法

主项目下的`build.sh`只是用来打包的，无需运行

## Exam1的运行

`sh exam1.sh`

然后可以去Exam1/tmp看到文件

## Exam2的运行

先运行服务器端

`sh exam2-server.sh`

再另开一个terminal运行客户端

`sh exam2-client.sh`

然后可以去Exam2/tmp看到文件

## Exam3的运行

`sh exam3.sh`

然后可以去Exam3/tmp看到文件

ps:今天早上发现java运行有gson等包的不行，然后时间晚了，就考试了，因此这个包的问题卡了我很久，最后我把HttpClient，Dom4j改成了java原生的类，不用导包，对于Gson没办法，只能把源代码复制进去了。

