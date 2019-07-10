package zzu.mxd.subway.entity;

import java.math.BigDecimal;

public class ThreeDImage {
    //湿度
    private BigDecimal humidityValue;

    //光线
    private String lightValue;

    //噪声强度
    private BigDecimal noiseValue;

    //气压
    private BigDecimal pressureValue;

    //加速度
    private BigDecimal accelerValue;

    //温度
    private BigDecimal temperatureValue;

    public ThreeDImage(BigDecimal humidityValue, String lightValue, BigDecimal noiseValue, BigDecimal pressureValue, BigDecimal accelerValue, BigDecimal temperatureValue) {
        this.humidityValue = humidityValue;
        this.lightValue = lightValue;
        this.noiseValue = noiseValue;
        this.pressureValue = pressureValue;
        this.accelerValue = accelerValue;
        this.temperatureValue = temperatureValue;
    }

    public BigDecimal getHumidityValue() {
        return humidityValue;
    }

    public void setHumidityValue(BigDecimal humidityValue) {
        this.humidityValue = humidityValue;
    }

    public String getLightValue() {
        return lightValue;
    }

    public void setLightValue(String lightValue) {
        this.lightValue = lightValue;
    }

    public BigDecimal getNoiseValue() {
        return noiseValue;
    }

    public void setNoiseValue(BigDecimal noiseValue) {
        this.noiseValue = noiseValue;
    }

    public BigDecimal getPressureValue() {
        return pressureValue;
    }

    public void setPressureValue(BigDecimal pressureValue) {
        this.pressureValue = pressureValue;
    }

    public BigDecimal getAccelerValue() {
        return accelerValue;
    }

    public void setAccelerValue(BigDecimal accelerValue) {
        this.accelerValue = accelerValue;
    }

    public BigDecimal getTemperatureValue() {
        return temperatureValue;
    }

    public void setTemperatureValue(BigDecimal temperatureValue) {
        this.temperatureValue = temperatureValue;
    }
}
