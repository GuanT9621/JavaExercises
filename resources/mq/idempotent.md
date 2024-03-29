# MQ如何保证幂等性
一、什么是幂等性  
可以参考数据库乐观锁机制（可重复读），比如执行一条更新库存的 SQL 语句，在并发场景，为了性能和数据可靠性，会在更新时加上查询时的版本，并且更新这个版本信息。可能你要对一个事情进行操作，这个操作可能会执行成百上千次，但是操作结果都是相同的，这就是幂等性。  
二、消费端的幂等性保障  
在海量订单生成的业务高峰期，生产端有可能就会重复发生了消息，这时候消费端就要实现幂等性，这就意味着我们的消息永远不会被消费多次，即使我们收到了一样的消息。  

业界主流的幂等性有两种操作：  
1.唯一 ID + 指纹码 机制，利用数据库主键去重  
2.利用redis的原子性去实现  

三、唯一 ID + 指纹码 机制（雪花算法）  
大家肯定懂唯一 ID 的，就不多说了，为什么需要指纹码呢？  
这是为了应对用户在一瞬间的频繁操作，这个指纹码可能是我们的一些规则或者时间戳加别的服务给到的唯一信息码，它并不一定是我们系统生成的，基本都是由我们的业务规则拼接而来，但是一定要保证唯一性，然后就利用查询语句进行判断这个id是否存在数据库中。  
好处就是实现简单，就一个拼接，然后查询判断是否重复。  
坏处就是在高并发时，如果是单个数据库就会有写入性能瓶颈  
解决方案 ：根据 ID 进行分库分表，对 ID 进行算法路由，落到一个具体的数据库，然后当这个 ID 第二次来又会落到这个数据库，这时候就像我单库时的查重一样了。利用算法路由把单库的幂等变成多库的幂等，分摊数据流量压力，提高性能。

四、利用 redis 的原子性去实现  
相信大家都知道 redis 的原子性操作，我这里就不需要过多介绍了。  
使用 redis 的原子性去实现需要考虑两个点  
一是 是否要进行数据落库，如果落库的话，关键解决的问题是数据库和缓存如何做到原子性？  
数据库与缓存进行同步肯定要进行写操作，到底先写 redis 还是先写数据库，这是个问题，涉及到缓存更新与淘汰的问题  
二是 如果不落库，那么都存储到缓存中，如何设置定时同步的策略？  
不入库的话，可以使用双重缓存等策略，保障一个消息副本，具体同步可以使用类似 databus 这种同步工具。  