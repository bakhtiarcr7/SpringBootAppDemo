package com.mainpackag.firstDemoSBProject.controller;

import com.mainpackag.firstDemoSBProject.repository.LibraryRepository;
import com.mainpackag.firstDemoSBProject.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class LibraryController {
    @Autowired
    LibraryRepository repository;
    @Autowired
    LibraryService libService;

    @PostMapping("/addBook")
    public ResponseEntity<AddResponse> addBookImplementation(@RequestBody Library library){
        String id = libService.buildId(library.getIsbn(),library.getAisle());

        AddResponse addResponse = new AddResponse();
        if(!libService.checkBookAlreadyExists(id)){
            library.setId(id);
            repository.save(library);
            HttpHeaders headers = new HttpHeaders();
            headers.add("unique",id);
            addResponse.setMsg("Success Book is Added");
            addResponse.setId(id);
            return new ResponseEntity<AddResponse>(addResponse,headers, HttpStatus.CREATED);
        }
        else {
            addResponse.setMsg("Book Already Exists");
            addResponse.setId(id);
              return new ResponseEntity<AddResponse>(addResponse,HttpStatus.ACCEPTED);
        }
    }
    @GetMapping("/getBooks/{id}")
    public Library getBookById(@PathVariable(value = "id")String id){
        try {
            Library lib =repository.findById(id).get();
            return lib;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getBooks/author")
    public List<Library> getBooksByAuthorName(@RequestParam(value = "authorname")String author){
        return repository.findAllByAuthor(author);
    }
    @PutMapping("/updateBook/{id}")
    public Library updateBookById(@PathVariable(value = "id")String id, @RequestBody Library library){
        try {
            Library extracted = repository.findById(id).get();
            extracted.setAisle(library.getAisle());
            extracted.setAuthor(library.getAuthor());
            extracted.setBook_name(library.getBook_name());
            repository.save(extracted);
            return extracted;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable(value = "id")String id){
        if(libService.checkBookAlreadyExists(id)){
            Library library=repository.findById(id).get();
            repository.delete(library);
            return new ResponseEntity<String>("Book is Deleted!",HttpStatus.CREATED);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getBooks")
    public List<Library> getAllBooks(){
         return repository.findAll();
    }
}
