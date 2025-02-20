package com.alexandre.controle.gastos.domain.commons.exceptions;

public class DomainException extends NoStackTraceException {

    private final ErrorInfo errorInfo;

    public DomainException(final ErrorInfo errorInfo) {
        super(errorInfo.message());
        this.errorInfo = errorInfo;
    }

    public static DomainException with(final ErrorInfo errorInfo) {
        return new DomainException(errorInfo);
    }

    public static DomainException with(final String message){
        return new DomainException(new ErrorInfo(message));
    }

    public ErrorInfo errorInfo(){
        return errorInfo;
    }
}
