package com.pec.studentportal.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericApiDataResponse<T> extends GenericApiResponse {
    private T data;

    public GenericApiDataResponse(boolean successful, String message, T data) {
        this.successful = successful;
        this.message = message;
        this.data = data;
    }
}
