# HTTP
HTTP超文本传输协议，是一个基于请求与响应，无状态的，应用层的协议.
常基于TCP/IP协议传输数据，互联网上应用最为广泛的一种网络协议,所有的WWW文件都必须遵守这个标准。
设计HTTP的初衷是为了提供一种发布和接收HTML页面的方法。

## 历史
| 版本	      | 产生时间		 | 内容                                            | 发展现状        |
|----------|--------|-----------------------------------------------|-------------|
| HTTP/0.9 | 1991年  | 不涉及数据包传输，规定客户端和服务器之间通信格式，只能GET请求	             | 没有作为正式的标准   |
| HTTP/1.0 | 1996年  | 传输内容格式不限制，增加PUT、PATCH、HEAD、 OPTIONS、DELETE命令	 | 正式作为标准      |
| HTTP/1.1 | 1997年  | 持久连接(长连接)、节约带宽、HOST域、管道机制、分块传输编码	             | 2015年前使用最广泛 |
| HTTP/2   | 2015年  | 多路复用、服务器推送、头信息压缩、二进制协议等	                      | 逐渐覆盖市场      |

# HTTPS
《图解HTTP》这本书中曾提过HTTPS是身披SSL外壳的HTTP。
HTTPS是一种通过计算机网络进行安全通信的传输协议，经由HTTP进行通信，利用SSL/TLS建立全信道，加密数据包。
HTTPS使用的主要目的是提供对网站服务器的身份认证，同时保护交换数据的隐私与完整性。

PS:TLS是传输层加密协议，前身是SSL协议，由网景公司1995年发布，有时候两者不区分。

## SSL/TLS 


		
		
		
		
