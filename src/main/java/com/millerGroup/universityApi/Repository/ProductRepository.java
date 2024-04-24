package com.millerGroup.universityApi.Repository;

import com.millerGroup.universityApi.Model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {
}
