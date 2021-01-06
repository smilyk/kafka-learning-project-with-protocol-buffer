package example.dto;

public class Sensor {
    public int id;
    public long timestamp;
    public int data; // number for sake of an example

    public int getId() {
        return id;
    }
    public long getTimestamp() {
        return timestamp;
    }
    public int getData() {
        return data;
    }
    public Sensor(int id, long timestamp, int data) {
        super();
        this.id = id;
        this.timestamp = timestamp;
        this.data = data;
    }
    public Sensor() {}
    @Override
    public String toString() {
        return "Sensor [id=" + id + ", timestamp=" + timestamp + ", data=" + data + "]";
    }

}
