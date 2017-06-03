服务入口:
-------------------
FrameworkServlet processRequest() : 重写HttpServlet的service()、doGet()...等方法 ，最终调用processRequest().该方法主要干三件事  
1.将local、requestAttibute等请求信息放入contextHold，处理完后复位  
2.真正的处理逻辑doService(request, response);被DispatcherServlet实现。  
3.publishRequestHandledEvent()发布处理完毕事件   

DispatcherServlet doService() : 真正的处理过程  
1.对include类型的请求保存属性快照，处理后复位  
2.doDispatch(request, response); 对请求进行转发  
。。。待续