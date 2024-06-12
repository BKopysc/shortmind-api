package com.bkopysc.shortmind.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bkopysc.shortmind.model.ShortNoteLike;

@Repository
public interface ShortNoteLikeRepository extends JpaRepository<ShortNoteLike, Long>{
    
    @Query("select count(snl) from ShortNoteLike snl where snl.shortNote.id = :shortNoteId")
    public Long countByShortNoteId(Long shortNoteId);

    @Query("select snl from ShortNoteLike snl where snl.shortNote.id = :shortNoteId and snl.user.id = :userId")
    public Optional<ShortNoteLike> getLikeByShortNoteIdAndUserId(Long shortNoteId, Long userId);

}
