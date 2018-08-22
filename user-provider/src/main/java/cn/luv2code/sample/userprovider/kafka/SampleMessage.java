package cn.luv2code.sample.userprovider.kafka;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author hx
 * @Title: SampleMessage
 * @ProjectName micro-server-demo
 * @Description: TODO
 * @date 2018/8/2220:06
 */
public class SampleMessage {

    private final Integer id;

    private final String message;

    @JsonCreator
    public SampleMessage(@JsonProperty("id") Integer id,
                         @JsonProperty("message") String message) {
        this.id = id;
        this.message = message;
    }

    public Integer getId() {
        return this.id;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return "SampleMessage{id=" + this.id + ", message='" + this.message + "'}";
    }

}
