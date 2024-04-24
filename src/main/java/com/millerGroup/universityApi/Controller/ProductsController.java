package com.millerGroup.universityApi.Controller;

import com.millerGroup.universityApi.Model.Product;
import com.millerGroup.universityApi.Repository.ProductRepository;
import com.millerGroup.universityApi.Repository.SearchProductRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProductsController {

    @Autowired
    ProductRepository repo;

    @Autowired
    SearchProductRepository searchRepo;


    @GetMapping("/allProducts")
    public List<Product> getAllPosts()
    {
        return repo.findAll();
    }
    // posts/java
    @GetMapping("/products/{text}")
    public List<Product> search(@PathVariable String text)
    {
        return searchRepo.findByText(text);
    }

    @PostMapping("/postProduct")
    public Product addPost(@RequestBody Product post)
    {
        return repo.save(post);
    }
}
