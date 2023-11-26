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

import java.util.Set;

@Service
@AllArgsConstructor
public class SnapshotService {

    private final SnapshotRepository snapshotRepository;
    private final UserRepository userRepository;
    private final SnapshotMapper snapshotMapper;
    private final JsonValidatorContext jsonValidatorContext = new JsonValidatorContext();

    public void save(SnapshotRequestDTO snapshotRequestDTO) {
        User user = userRepository.findById(snapshotRequestDTO.getUserId())
                .orElseThrow(UserNotFoundError::new);

        jsonValidatorContext.setValidator(new StrictJsonValidator());
        if (!jsonValidatorContext.validateJson(snapshotRequestDTO.getContent())) {
            throw new JsonNotValidError();
        }

        Snapshot snapshot = snapshotMapper.toSnapshot(snapshotRequestDTO, user);
        snapshotRepository.save(snapshot);
    }

    public Set<Snapshot> getAllByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundError::new);

        return snapshotRepository.findAllByUserId(userId);
    }

    public Snapshot findById(Long id) {
        return snapshotRepository.findById(id)
                .orElseThrow(SnapshotNotFoundError::new);
    }

    public void update(Long id, Snapshot newSnapshot) {

    }

    public void delete(Long id) {
        snapshotRepository.deleteById(id);
    }

}