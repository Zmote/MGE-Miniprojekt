package ch.zmote.gadgeothekapp.app.service;

public interface Callback<T> {
    void onCompletion(T input);
    void onError(String message);
}
