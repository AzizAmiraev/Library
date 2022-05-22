package com.example.librarydemo.services;

import com.example.librarydemo.DTO.BookDTO;
import com.example.librarydemo.exceptions.CustomException;
import com.example.librarydemo.models.Book;
import com.example.librarydemo.models.Category;
import com.example.librarydemo.models.Photo;
import com.example.librarydemo.repository.BookRepository;
import com.example.librarydemo.repository.CategoryRepository;
import com.example.librarydemo.repository.EBookRepository;
import com.example.librarydemo.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    EBookRepository eBookRepository;

    @Autowired
    PhotoRepository photoRepository;


    public List<Book> BookList(){
        return (List<Book>)bookRepository.findAll();
    }

    public List<Book> InLibraryBookList(){
        return (List<Book>)bookRepository.getInLibraryBooks();
    }

    public void creatBook(BookDTO bookDTO) throws IOException{

        Category category = categoryRepository.getCategoryByName(bookDTO.getCategory());

        Photo photo = storePhoto(bookDTO.getPhotoName(), bookDTO.getPhoto());

        Book book = new Book();

        System.out.println(bookDTO.getId());

        book.setId(bookDTO.getId());
        book.setName(bookDTO.getName());
        book.setAuthor(bookDTO.getAuthor());
        book.setCategory(category);
        book.setPhotoId(photo);
        book.setDescription(bookDTO.getDescription());
        book.setReleaseYear(bookDTO.getReleaseYear());
        book.setInLibrary(true);


        System.out.println(book.getId());

        bookRepository.save(book);
    }

    public Book findBookById(long id){
        return bookRepository.findById(id).get();
    }

    @Transactional
    public void deleteBook(long id) throws CustomException{
        if(bookRepository.checkIfBookWasTaken(id)==0){
            String photoName = bookRepository.findById(id).get().getPhotoId().getName();
            bookRepository.deleteBookById(id);
            photoRepository.deletePhotoByName(photoName);
        }else{
            throw new CustomException("The book was taken");
        }


    }

    public List<Book> getDeletedBooks(){
        return bookRepository.getDeletedBooks();
    }

    public void editBook(BookDTO bookDTO) throws IOException{
        Category category = categoryRepository.getCategoryByName(bookDTO.getCategory());
        Photo photo = photoRepository.getPhotoByName(bookDTO.getPhotoName());
        Book book = bookRepository.findById(bookDTO.getId()).get();


        if(photo == null){
            photo = photoRepository.getPhotoById(book.getPhotoId().getId());
            photo.setName(bookDTO.getPhotoName());
            photo.setPhoto(bookDTO.getPhoto().getBytes());
        }


        book.setName(bookDTO.getName());
        book.setAuthor(bookDTO.getAuthor());
        book.setCategory(category);
        book.setDescription(bookDTO.getDescription());
        book.setPhotoId(photo);
        book.setReleaseYear(bookDTO.getReleaseYear());

        bookRepository.save(book);


    }

    public int getBookQuantity(){
        return bookRepository.getBookQuantity();
    }

    public  int getEBookRepository(){
        return eBookRepository.getEBookQuantity();
    }

    public Photo storePhoto(String name, MultipartFile file) throws IOException {
        // Set the form data into entity
        Photo photo = new Photo();
        photo.setName(name);
        photo.setPhoto(file.getBytes());

        Photo photodb = photoRepository.save(photo);


        // Save the records into the database
        return photodb;
    }


}
