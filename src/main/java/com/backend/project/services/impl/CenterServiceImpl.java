package com.backend.project.services.impl;

import com.backend.project.entities.Address;
import com.backend.project.entities.Center;
import com.backend.project.entities.Courses;
import com.backend.project.payload.CenterDTO;
import com.backend.project.payload.CourseDTO;
import com.backend.project.repositories.AddressRepository;
import com.backend.project.repositories.CenterRepository;
import com.backend.project.repositories.CourseRepository;
import com.backend.project.services.CenterService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.ArrayList;


@Service
public class CenterServiceImpl implements CenterService {

    @PersistenceContext
    private EntityManager entityManager;

    private final CenterRepository centerRepository;
    private final AddressRepository addressRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CenterServiceImpl(CenterRepository centerRepository, ModelMapper modelMapper,
            AddressRepository addressRepository, CourseRepository courseRepository) {
        this.centerRepository = centerRepository;
        this.modelMapper = modelMapper;
        this.addressRepository = addressRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public CenterDTO addCenter(CenterDTO centerDTO) {
        Center center = this.modelMapper.map(centerDTO, Center.class);
        center.setId(UUID.randomUUID().toString());
        Address address = this.modelMapper.map(centerDTO.getAddress(), Address.class);
        address.setId(UUID.randomUUID().toString());
        addressRepository.save(address);
        List<CourseDTO> courses = centerDTO.getCourses();
        List<Courses> courses1 = courseRepository.saveAll(courses.stream()
                .map(courseDTO -> this.modelMapper.map(courseDTO, Courses.class)).collect(Collectors.toList()));
        center.setCreatedAt(LocalDateTime.now());
        center.setAddress(address);
        center.setCourses(courses1);
        Center savedCenter = this.centerRepository.save(center);
        return this.modelMapper.map(savedCenter, CenterDTO.class);
    }

    @Override
    public List<CenterDTO> getAllCenters() {
        List<Center> centers = this.centerRepository.findAll();
        // System.out.println(centers.stream().map(center ->
        // courseRepository.findAll()).collect(Collectors.toList()));

        return centers.stream().map(center -> this.modelMapper.map(center, CenterDTO.class)).toList();
    }

    @Override
    public void deleteCenter(String id) {
        Center center = centerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Center with ID " + id + " not found."));
        centerRepository.delete(center);
    }

    @Override
    public Boolean existByCenterName(String centerName) {
        return centerRepository.existsByCenterName(centerName);
    }

    @Override
    public CenterDTO getCenterById(String id) {
        Center center = centerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Center with ID " + id + " not found."));
        return modelMapper.map(center, CenterDTO.class);
    }

    @Override
    public CenterDTO updateCenter(String id, CenterDTO centerDTO) {
        Center center = centerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Center with ID " + id + " not found"));
        center.setCenterName(centerDTO.getCenterName());
        center.setCenterCode(centerDTO.getCenterCode());
        center.setEmail(centerDTO.getEmail());
        center.setPhone(centerDTO.getPhone());
        center.setStudentCapacity(centerDTO.getStudentCapacity());
        if (centerDTO.getAddress() != null) {
            Address updatedAddress = modelMapper.map(centerDTO.getAddress(), Address.class);
            updatedAddress.setId(center.getAddress().getId());
            addressRepository.save(updatedAddress);
            center.setAddress(updatedAddress);
        }
        if (centerDTO.getCourses() != null) {
            List<Courses> updatedCourses = courseRepository.saveAll(centerDTO.getCourses().stream()
                    .map(courseDTO -> modelMapper.map(courseDTO, Courses.class))
                    .collect(Collectors.toList()));
            center.setCourses(updatedCourses);
        }
        center.setCreatedAt(center.getCreatedAt());
        Center savedCenter = centerRepository.save(center);

        return modelMapper.map(savedCenter, CenterDTO.class);
    }
}
