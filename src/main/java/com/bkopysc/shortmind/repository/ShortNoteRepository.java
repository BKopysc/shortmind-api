package com.bkopysc.shortmind.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bkopysc.shortmind.model.ShortNote;

@Repository
public interface ShortNoteRepository extends JpaRepository<ShortNote, Long>{
    
    @Query("SELECT s FROM ShortNote s WHERE s.id = :id")
    public Optional<ShortNote> findById(Long id);

    @Query("SELECT s FROM ShortNote s")
    public List<ShortNote> findAll();
}
