package com.backend.project.services;

import com.backend.project.entities.Center;
import com.backend.project.payload.CenterDTO;

import java.util.List;

public interface CenterService {
    CenterDTO addCenter(CenterDTO centerDTO);
    List<CenterDTO> getAllCenters();
    void deleteCenter(String id);
    Boolean existByCenterName(String centerName);
    CenterDTO updateCenter(String id, CenterDTO centerDTO);
    CenterDTO getCenterById(String id);
}
