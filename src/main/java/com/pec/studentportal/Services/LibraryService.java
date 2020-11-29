package com.pec.studentportal.Services;

import com.pec.studentportal.dto.LibraryRecordDTO;
import com.pec.studentportal.response.GenericApiDataResponse;

public interface LibraryService {

    GenericApiDataResponse<LibraryRecordDTO> getLibraryDataForAStudent(Integer studentId);

}
