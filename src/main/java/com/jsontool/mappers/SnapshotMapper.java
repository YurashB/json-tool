package com.jsontool.mappers;

import com.jsontool.dto.SnapshotRequestDTO;
import com.jsontool.dto.SnapshotResponseDTO;
import com.jsontool.model.Snapshot;
import com.jsontool.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SnapshotMapper {
    public Snapshot toSnapshot(SnapshotRequestDTO requestDTO, User user) {
        return Snapshot.SnapshotBuilder.Snapshot()
                .withTitle(requestDTO.getTitle())
                .withContent(requestDTO.getContent())
                .withLastModified(LocalDateTime.now())
                .withUser(user)
                .build();
    }

    public SnapshotResponseDTO toResponseDTO(Snapshot snapshot) {
        return SnapshotResponseDTO.SnapshotResponseDTOBuilder.SnapshotResponseDTO()
                .withId(snapshot.getId())
                .withTitle(snapshot.getTitle())
                .withContent(snapshot.getContent())
                .withLastModified(snapshot.getLastModified())
                .build();
    }

}
