package com.mainpackag.firstDemoSBProject.repository;

import com.mainpackag.firstDemoSBProject.controller.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibraryRepositoryImpl implements LibraryRepositoryCustom{
    @Autowired
    LibraryRepository repository;

    @Override
    public List<Library> findAllByAuthor(String author) {
        List<Library> ItemsByAuthor = new ArrayList<Library>();
        List<Library> Books = repository.findAll();
        for(Library item : Books){
            if(item.getAuthor().equalsIgnoreCase(author))
                ItemsByAuthor.add(item);
        }
        if(ItemsByAuthor.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        else
            return ItemsByAuthor;

   }
}
