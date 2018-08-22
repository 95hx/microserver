package cn.luv2code.sample.userprovider.kafka;

/**
 * @author hx
 * @Title: Consumer
 * @ProjectName micro-server-demo
 * @Description: TODO
 * @date 2018/8/2220:07
 */

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
class Consumer {

    @KafkaListener(topics = "testTopic")
    public void processMessage(SampleMessage message) {
        System.out.println("Received sample message [" + message + "]");
    }

}
