package com.mainpackag.firstDemoSBProject.service;

import com.mainpackag.firstDemoSBProject.controller.Library;
import com.mainpackag.firstDemoSBProject.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibraryService {
    @Autowired
    LibraryRepository repository;
    public String buildId(String isbn, int aisle){
        return isbn+aisle;
    }

    public boolean checkBookAlreadyExists(String id){
        Optional<Library> op = repository.findById(id);
        if(op.isPresent())
            return true;
        else
            return false;
    }
}
