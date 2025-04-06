package org.solutis.gestaodetarefas.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ErroDTO {
    private OffsetDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public ErroDTO(int status, String error, String message, String path) {
        this.timestamp = OffsetDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
