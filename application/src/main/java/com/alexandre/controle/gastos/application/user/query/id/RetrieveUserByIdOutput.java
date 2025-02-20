package com.alexandre.controle.gastos.application.user.query.id;

public record RetrieveUserByIdOutput(
        Long id,
        String name,
        String email
) {
}
