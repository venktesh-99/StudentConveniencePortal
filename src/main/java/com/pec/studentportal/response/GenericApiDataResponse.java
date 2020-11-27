package com.pec.studentportal.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericApiDataResponse<T> extends GenericApiResponse {
    T data;
}
