package c.njk.demo;

public class Health {
    public String temperature;
    public String heartBeat;
    public String steps;
    public String date;
    public String key;

    public Health() {
    }

    public String getHeartBeat() {
        return heartBeat;
    }

    public String getSteps() {
        return steps;
    }

    public Health(String temperature, String heartBeat, String steps) {
        this.temperature = temperature;
        this.heartBeat = heartBeat;
        this.steps = steps;
    }

    public String getKey() {
        return key;
    }

    public String getDate() {
        return date;
    }

    public Health(String temperature, String heartBeat, String steps, String date) {
        this.temperature = temperature;
        this.heartBeat = heartBeat;
        this.steps = steps;
        this.date = date;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTemperature(){
        return temperature;
    }
}
