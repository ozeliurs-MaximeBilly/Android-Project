package fr.ozeliurs.td1;

public abstract class Converter {

    private float rate;

    public void init() {
        rate = 1;
    }

    public float getRate() {
        return rate;
    }

    public float convert(float in) {
        return rate*in;
    }

    public void onRateUpdate() {}

    public void setListener(ConverterListener listener) {}
}
