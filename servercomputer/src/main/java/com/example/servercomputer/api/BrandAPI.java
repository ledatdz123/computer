package com.example.servercomputer.api;

import com.example.servercomputer.entity.Brand;
import com.example.servercomputer.entity.Category;
import com.example.servercomputer.service.BrandService;
import com.example.servercomputer.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/api/brand")
public class BrandAPI {
    @Autowired
    private BrandService brandService;

    @GetMapping()
    public List<Brand> getList(){
        return brandService.findAll();
    }

    @GetMapping("/{id}")
    public Brand update(@PathVariable Long id) {
        return brandService.findOneById(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Brand save(@Valid @RequestBody Brand brand) {
        return brandService.save(brand);
    }

    @PutMapping("/{id}")
    public Brand update(@PathVariable Long id, @Valid @RequestBody Brand brand) {
        return brandService.update(id,brand);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return brandService.delete(id);
    }
}
