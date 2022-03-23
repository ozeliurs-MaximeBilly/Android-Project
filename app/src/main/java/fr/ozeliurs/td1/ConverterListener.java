package fr.ozeliurs.td1;

public interface ConverterListener {
    public void onRateUpdated(float rate);
    public void onError(String message);
}
