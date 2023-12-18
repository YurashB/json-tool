package com.jsontool.services;

import com.jsontool.dto.SnapshotRequestDTO;
import com.jsontool.errors.JsonNotValidError;
import com.jsontool.errors.SnapshotNotFoundError;
import com.jsontool.errors.UserNotFoundError;
import com.jsontool.mappers.SnapshotMapper;
import com.jsontool.model.Snapshot;
import com.jsontool.model.User;
import com.jsontool.repositories.SnapshotRepository;
import com.jsontool.repositories.UserRepository;
import com.jsontool.utils.validators.json.JsonValidatorContext;
import com.jsontool.utils.validators.json.StrictJsonValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class SnapshotService {

    private final SnapshotRepository snapshotRepository;
    private final UserRepository userRepository;
    private final SnapshotMapper snapshotMapper;
    private final JsonValidatorContext jsonValidatorContext = new JsonValidatorContext();

    public void save(SnapshotRequestDTO snapshotRequestDTO, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundError::new);

        jsonValidatorContext.setValidator(new StrictJsonValidator());
        if (!jsonValidatorContext.validateJson(snapshotRequestDTO.getContent())) {
            throw new JsonNotValidError();
        }

        Snapshot snapshot = snapshotMapper.toSnapshot(snapshotRequestDTO, user);
        snapshotRepository.save(snapshot);
    }

    public List<Snapshot> getAllByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundError::new);

        List<Snapshot> snapshots = snapshotRepository.findAllByUserId(user.getId()).stream()
                .toList();
        return snapshots.stream().sorted(Comparator.comparing(Snapshot::getLastModified).reversed()).toList();
    }

    public Snapshot findById(Long snapshotId, Long userId) {
        Snapshot snapshot = snapshotRepository.findById(snapshotId)
                .orElseThrow(SnapshotNotFoundError::new);

        if (!snapshot.getUser().getId().equals(userId)) {
            throw new SnapshotNotFoundError();
        }

        return snapshot;
    }

    public void delete(Long snapshotId, Long userId) {
        Snapshot snapshot = snapshotRepository.findById(snapshotId)
                .orElseThrow(SnapshotNotFoundError::new);

        if (!snapshot.getUser().getId().equals(userId)) {
            throw new SnapshotNotFoundError();
        }

        snapshotRepository.deleteById(snapshotId);
    }

}