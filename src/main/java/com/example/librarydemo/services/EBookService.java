package com.example.librarydemo.services;

import com.example.librarydemo.DTO.EBooksDTO;
import com.example.librarydemo.models.Category;
import com.example.librarydemo.models.EBook;
import com.example.librarydemo.models.Photo;
import com.example.librarydemo.models.StatisticEBook;
import com.example.librarydemo.repository.CategoryRepository;
import com.example.librarydemo.repository.EBookRepository;
import com.example.librarydemo.repository.PhotoRepository;
import com.example.librarydemo.repository.StatisticEBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

//public EBook(String name, String author, String description, int releaseYear, String type, byte[] data, Category category,  Photo photoId) {


@Service
public class EBookService {

    @Autowired
    EBookRepository eBookRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    StatisticEBookRepository statisticEBookRepository;

    public EBook store(MultipartFile file, String name, String author, String description, int releaseYear, String categoryName, MultipartFile photoFile) throws IOException {
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Category category = categoryRepository.getCategoryByName(categoryName);
        Photo photo = storePhoto(photoFile.getOriginalFilename(), photoFile);
        EBook eBook = new EBook(name, author, description, releaseYear, file.getContentType(), file.getBytes(), category, photo);

        return eBookRepository.save(eBook);
    }


    public List<EBooksDTO> getEBooks(){
        return (List) eBookRepository.getEBooks();
    }

    public List<EBooksDTO> getEBookLimit(int limit){
        return eBookRepository.getEBooksLimit(limit);
    }

    public EBook getEBookById(long id){
        return eBookRepository.getEBook(id);
    }

    public void deleteEBook(long id){
        long photoId = eBookRepository.findById(id).get().getPhotoId().getId();
        eBookRepository.deleteEBook(id);
        photoRepository.deleteById(photoId);
    }

    public void makeDownloadStatistic(long bookId){
        if(statisticEBookRepository.existsInStatistic(bookId) == 1){
            StatisticEBook statisticEBook = statisticEBookRepository.findById(bookId).get();
            statisticEBook.setDownloadedQuantity(statisticEBook.getDownloadedQuantity() + 1);
            statisticEBookRepository.save(statisticEBook);
        }else{
            StatisticEBook statisticEBook = new StatisticEBook();
            statisticEBook.setDownloadedQuantity(1);
            EBook eBook = eBookRepository.findById(bookId).get();
            statisticEBook.setEBookId(eBook);
            statisticEBook.setViewedQuantity(0);
            statisticEBookRepository.save(statisticEBook);

        }
    }

    public void makeViewedStatistic(long bookId){

        if(statisticEBookRepository.existsInStatistic(bookId) == 1){
            StatisticEBook statisticEBook = statisticEBookRepository.findById(bookId).get();
            statisticEBook.setViewedQuantity(statisticEBook.getViewedQuantity() + 1);
            statisticEBookRepository.save(statisticEBook);
        }else{
            StatisticEBook statisticEBook = new StatisticEBook();
            statisticEBook.setViewedQuantity(1);
            EBook eBook = eBookRepository.findById(bookId).get();
            statisticEBook.setEBookId(eBook);
            statisticEBook.setDownloadedQuantity(0);
            statisticEBookRepository.save(statisticEBook);

        }
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
