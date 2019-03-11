package com.qf58.ace.service;

import com.alibaba.fastjson.JSONObject;
import com.qf58.client.util.ExceptionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

import rabbitmq58lib.connection.ConnectionFactoryServer;
import rabbitmq58lib.constant.RabitmqConstants;
import rabbitmq58lib.producer.ExchangeProduceServer;

import javax.annotation.PostConstruct;

/**
 * Created by Administrator on 2018/6/3.
 */
@Service
public class ConsumerStartService {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerStartService.class);
    @Value("${MQ_IP}")
    private String MQ_IP;
    @Value("${MQ_PORT}")
    private String MQ_PORT;
    @Value("${MQ_USERNAME}")
    private String MQ_USERNAME;
    @Value("${MQ_PASSWD}")
    private String MQ_PASSWD;
    @Value("${MQ_TYPE}")
    private String MQ_TYPE;
    @Value("${MQ_EXCHANGE}")
    private String MQ_EXCHANGE;
    @PostConstruct
    private void initRabbitmqServer(){
        ConnectionFactoryServer.initConnectionFactory(MQ_IP,Integer.parseInt(MQ_PORT),MQ_USERNAME,MQ_PASSWD,
                RabitmqConstants.DEFAULT_VHOST);
        ExchangeProduceServer.initSingleProducerExchangeConfig(MQ_TYPE,MQ_EXCHANGE);
        try {
            ExchangeProduceServer.StartServer();
        } catch (IOException e) {
            logger.error("初始化生产者异常[{}]", ExceptionUtils.getTrace(e));
        }
        logger.info("rabbitmq consumer start.....");
    }


    public static void main(String[] args) throws IOException {
        ConnectionFactoryServer.initConnectionFactory("172.16.11.119", 5672, "root", "root", RabitmqConstants.DEFAULT_VHOST);
        ExchangeProduceServer.initSingleProducerExchangeConfig("direct", "ace.platform.exchange");
        ExchangeProduceServer.StartServer();
        JSONObject result = new JSONObject();
        result.put("approveId", "2551884696493825");
        result.put("approverId", "2536010217430528");
        ExchangeProduceServer.SendServerWithRoutingKey("ace.platform.exchange", result.toJSONString(), "ace.approve.email");
    }

}
