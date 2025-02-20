package com.alexandre.controle.gastos.application;

public abstract class UnitUseCase<IN> {

    protected abstract void execute(IN input);

}
