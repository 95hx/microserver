//package cn.luv2code.sample.userprovider.kafka;
//
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
///**
// * @author hx
// * @Title: CommitMessage
// * @ProjectName micro-server-demo
// * @Description: TODO
// * @date 2018/8/2220:04
// */
//@Component
//public class CommitMessage {
//    @Bean
//    public ApplicationRunner runner(Producer producer) {
//        return (args) -> producer.send(new SampleMessage(Integer.valueOf(args[1]), "A simple test message"));
//    }
//}
