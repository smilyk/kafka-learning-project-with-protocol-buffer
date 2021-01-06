package example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.googlecode.protobuf.format.JsonFormat;
//import example.dto.Sensor;
import com.google.protobuf.TextFormat;
//import example.dto.Sensor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import smilyk.sensor.model.SensorProto;
//import smilyk.sensor.model.SensorProto.Sensor;


import java.io.IOException;

@EnableBinding(Sink.class)
public class SensorReciever {
    ObjectMapper mapper = new ObjectMapper();

    @StreamListener(Sink.INPUT)
    public void receiveSensorData(String sensorData) throws IOException {
        sensorData = sensorData.replaceAll("[{}\"]", "");
        SensorProto.Sensor.Builder b = SensorProto.Sensor.newBuilder();
        TextFormat.getParser().merge(sensorData, b);
        SensorProto.Sensor x = b.build();
        System.out.printf("delay:%d, id:%d, data:%d\n",
                System.currentTimeMillis() - x.getTimestamp(),
                x.getId(), x.getData()
        );
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}