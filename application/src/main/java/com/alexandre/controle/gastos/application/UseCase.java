package com.alexandre.controle.gastos.application;

public abstract class UseCase<IN, OUT> {

    protected abstract OUT execute(IN input);

    public <T> T execute(IN input, Presenter<OUT, T> presenter) {
        try {
            return presenter.present(execute(input));
        } catch (final Throwable throwable) {
            return presenter.present(throwable);
        }
    }
}
