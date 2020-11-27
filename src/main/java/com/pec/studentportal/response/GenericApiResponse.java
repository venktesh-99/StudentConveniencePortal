package com.pec.studentportal.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericApiResponse {

    private boolean successful;

    private String message;

}
