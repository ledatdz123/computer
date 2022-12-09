package com.example.servercomputer.service;

import com.example.servercomputer.entity.Brand;

import java.util.List;
public interface BrandService {
    public List<Brand> findAll();

    public Brand findOneById(Long id);
    public Brand save(Brand brand);
    public boolean delete(Long id);
    public Brand update(Long id, Brand brand);
}
