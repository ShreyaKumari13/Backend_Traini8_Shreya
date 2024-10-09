package com.backend.project.controllers;

import com.backend.project.payload.CenterDTO;
import com.backend.project.services.CenterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/centers")
public class CenterController {
    private final CenterService centerService;

    @Autowired
    public CenterController(CenterService centerService) {
        this.centerService = centerService;
    }

    @PostMapping("/")
    public ResponseEntity<CenterDTO> addCenter(@RequestBody @Valid CenterDTO centerDTO) {
        CenterDTO addedCenter = centerService.addCenter(centerDTO);
        return new ResponseEntity<>(addedCenter, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CenterDTO>> getAllCenters() {
        List<CenterDTO> allCenters = centerService.getAllCenters();
        return new ResponseEntity<>(allCenters, HttpStatus.OK);
    }

    @GetMapping("/exists/{centerName}")
    public ResponseEntity<Boolean> checkCenterExists(@PathVariable("centerName") String centerName) {
        Boolean exists = centerService.existByCenterName(centerName);
        return ResponseEntity.ok(exists);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCenter(@PathVariable("id") String id) {
        centerService.deleteCenter(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CenterDTO> getCenterById(@PathVariable("id") String id) {
        CenterDTO centerDTO = centerService.getCenterById(id);
        return new ResponseEntity<>(centerDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CenterDTO> updateCenter(@PathVariable("id") String id,
            @RequestBody @Valid CenterDTO centerDTO) {
        CenterDTO updatedCenter = centerService.updateCenter(id, centerDTO);
        return new ResponseEntity<>(updatedCenter, HttpStatus.OK);
    }

}
