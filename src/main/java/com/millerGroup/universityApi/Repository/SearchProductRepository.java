package com.millerGroup.universityApi.Repository;

import com.millerGroup.universityApi.Model.Product;

import java.util.List;

public interface SearchProductRepository {
    List<Product> findByText(String text);
}
