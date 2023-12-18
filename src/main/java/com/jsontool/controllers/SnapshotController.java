package com.jsontool.controllers;

import com.jsontool.dto.SnapshotRequestDTO;
import com.jsontool.dto.SnapshotResponseDTO;
import com.jsontool.mappers.SnapshotMapper;
import com.jsontool.model.User;
import com.jsontool.services.SnapshotService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/jsontool/snapshots")
@AllArgsConstructor
public class SnapshotController {

    private final SnapshotService service;
    private final SnapshotMapper mapper;

    @PostMapping()
    public void save(@Valid @RequestBody SnapshotRequestDTO snapshotRequestDTO, HttpServletRequest request) {
        User user = (User) request.getAttribute("user");

        service.save(snapshotRequestDTO, user.getId());
    }

    @GetMapping()
    public List<SnapshotResponseDTO> getAllByUserId(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");

        return service.getAllByUserId(user.getId()).stream()
                .map(mapper::toResponseDTO)
                .toList();

    }

    @GetMapping("/{id}")
    public SnapshotResponseDTO findById(@PathVariable Long id, HttpServletRequest request) {
        User user = (User) request.getAttribute("user");

        return mapper.toResponseDTO(service.findById(id, user.getId()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id, HttpServletRequest request) {
        User user = (User) request.getAttribute("user");

        service.delete(id, user.getId());
    }
}
