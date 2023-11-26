package com.jsontool.controllers;

import com.jsontool.dto.SnapshotRequestDTO;
import com.jsontool.dto.SnapshotResponseDTO;
import com.jsontool.mappers.SnapshotMapper;
import com.jsontool.model.Snapshot;
import com.jsontool.services.SnapshotService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/jsontool/snapshots")
@AllArgsConstructor
public class SnapshotController {

    private final SnapshotService service;
    private final SnapshotMapper mapper;

    @PostMapping()
    public void save(@Valid @RequestBody SnapshotRequestDTO snapshotRequestDTO) {
        service.save(snapshotRequestDTO);
    }

    @GetMapping()
    public List<SnapshotResponseDTO> getAllByUserId(Long userId) {
        return service.getAllByUserId(userId).stream()
                .map(mapper::toResponseDTO)
                .toList();

    }

    @GetMapping("/{id}")
    public SnapshotResponseDTO findById(Long id) {
        return mapper.toResponseDTO(service.findById(id));
    }

    public void update(Long id, Snapshot newSnapshot) {

    }

    @DeleteMapping()
    public void delete(Long id) {
        service.delete(id);
    }
}
