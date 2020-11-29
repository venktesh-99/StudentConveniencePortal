package com.pec.studentportal.Controller;

import com.pec.studentportal.Services.LibraryService;
import com.pec.studentportal.dto.LibraryRecordDTO;
import com.pec.studentportal.response.GenericApiDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @RequestMapping(method = RequestMethod.GET, value = "/fetchBooks/{id}")
    public GenericApiDataResponse<LibraryRecordDTO> getLibraryDataForAStudent(@PathVariable String id) {
        return libraryService.getLibraryDataForAStudent(Integer.parseInt(id));
    }

}
