package c.njk.demo;

public class AdviceModel1 {

    String advice1;
    String advice2;
    String advice3;
    String advice4;
    String heartBeat;
    String steps;
    String temperature;

    AdviceModel1(){

    }

    public String getHeartBeat() {
        return heartBeat;
    }

    public void setHeartBeat(String heartBeat) {
        this.heartBeat = heartBeat;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    AdviceModel1(String advice1, String advice2, String advice3, String advice4, String heartBeat, String steps, String temperature){
        this.advice1 = advice1;
        this.advice2 = advice2;
        this.advice3 = advice3;
        this.advice4 = advice4;
        this.heartBeat = heartBeat;
        this.steps = steps;
        this.temperature = temperature;
    }

    public String getAdvice1() {
        return advice1;
    }

    public void setAdvice1(String advice1) {
        this.advice1 = advice1;
    }

    public String getAdvice2() {
        return advice2;
    }

    public void setAdvice2(String advice2) {
        this.advice2 = advice2;
    }

    public String getAdvice3() {
        return advice3;
    }

    public void setAdvice3(String advice3) {
        this.advice3 = advice3;
    }

    public String getAdvice4() {
        return advice4;
    }

    public void setAdvice4(String advice4) {
        this.advice4 = advice4;
    }


}
