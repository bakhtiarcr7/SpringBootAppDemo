package com.mainpackag.firstDemoSBProject.repository;

import com.mainpackag.firstDemoSBProject.controller.Library;

import java.util.List;

public interface LibraryRepositoryCustom {
    public List<Library> findAllByAuthor(String author);
}
