package cn.hecj.test;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

/**
 * 哨兵和集群模式演示
 * by hechaojie
 */
public class RedisTest {

    public static void main(String[] args) throws Exception{
        testCluster();
    }

    /**
     * 哨兵模式
     * @throws Exception
     */
    public static void testSentinel() throws Exception {
        String masterName = "mymaster";
        String password = "hechaojie";
        Set<String> sentinels = new HashSet<>();
        sentinels.add("172.16.15.130:26379");
        sentinels.add("172.16.15.131:26379");
        sentinels.add("172.16.15.133:26379");

        JedisSentinelPool pool = new JedisSentinelPool(masterName, sentinels,password); //初始化过程做了很多工作
        Jedis jedis = pool.getResource();
        jedis.set("key2", "value2");


        pool.close();
    }

    public static void testCluster() throws Exception {
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("172.16.15.130", 6379));
        nodes.add(new HostAndPort("172.16.15.131", 6379));
        nodes.add(new HostAndPort("172.16.15.133", 6379));
        nodes.add(new HostAndPort("172.16.15.136", 6379));
        nodes.add(new HostAndPort("172.16.15.137", 6379));
        nodes.add(new HostAndPort("172.16.15.138", 6379));
        // Jedis连接池配置
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大空闲连接数, 默认8个
        jedisPoolConfig.setMaxIdle(100);
        // 最大连接数, 默认8个
        jedisPoolConfig.setMaxTotal(500);
        //最小空闲连接数, 默认0
        jedisPoolConfig.setMinIdle(0);
        // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
        jedisPoolConfig.setMaxWaitMillis(2000); // 设置2秒
        //对拿到的connection进行validateObject校验
        jedisPoolConfig.setTestOnBorrow(true);
        JedisCluster cluster = new JedisCluster(nodes,2000,2000,3,"hechaojie",jedisPoolConfig);
        System.out.println(cluster.get("a"));
        cluster.close();
    }
}
