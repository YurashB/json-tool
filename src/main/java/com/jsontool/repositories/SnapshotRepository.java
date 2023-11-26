package com.jsontool.repositories;

import com.jsontool.model.Snapshot;
import com.jsontool.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SnapshotRepository extends JpaRepository<Snapshot, Long> {

    @Query(value = "select * from snapshots s where s.user_id = :userId", nativeQuery = true)
    Set<Snapshot> findAllByUserId(Long userId);
}
