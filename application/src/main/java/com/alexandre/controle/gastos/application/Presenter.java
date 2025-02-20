package com.alexandre.controle.gastos.application;

public interface Presenter<IN, OUT> {
    OUT present(IN data);

    OUT present(final Throwable throwable);
}
