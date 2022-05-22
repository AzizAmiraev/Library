package com.example.librarydemo.repository;

import com.example.librarydemo.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface PhotoRepository  extends
        JpaRepository<Photo, Long> {
    @Query(value="SELECT * FROM `photo` WHERE `name` = ?", nativeQuery = true)
    Photo getPhotoByName(String name);

    @Query(value="SELECT * FROM `photo` WHERE `id` = ?", nativeQuery = true)
    Photo getPhotoById(long id);

    @Modifying
    @Query(value="DELETE FROM `photo` where `name` = ?", nativeQuery = true)
    void deletePhotoByName(String name);

}