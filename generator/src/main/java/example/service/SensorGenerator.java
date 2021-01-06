package example.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.protobuf.format.JsonFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.InboundChannelAdapter;
import smilyk.sensor.model.SensorProto;


@EnableBinding(Source.class)
public class SensorGenerator {

    ObjectMapper mapper = new ObjectMapper();
    @Value("${min_id:1}")
    private int minId;
    @Value("${max_id:10}")
    private int maxId;

    @Value("${min_data:100}")
    private int minData;
    @Value("${min_data:200}")
    private int maxData;


    @InboundChannelAdapter(Source.OUTPUT)
    String sendSensorData() throws JsonProcessingException {
        String sensorData = null;
        int id = getRandomId();
        long timestamp = System.currentTimeMillis();
        int data = getRandomData();
        SensorProto.Sensor sensor = SensorProto.Sensor.newBuilder()
                .setId(id)
                .setData(data)
                .setTimestamp(timestamp)
                .build();
        sensorData = new JsonFormat().printToString(sensor);
        return sensorData;
    }

    private int getRandomData() {
        return getRandomNumber(minData, maxData);
    }

    private int getRandomId() {
        return getRandomNumber(minId, maxId);
    }

    private int getRandomNumber(int min, int max) {
        return (int) (min + Math.random() * (max - min + 1));
    }
}
