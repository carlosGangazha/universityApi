package com.millerGroup.universityApi.Repository;

import com.millerGroup.universityApi.Model.Product;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SearchProductImplement implements SearchProductRepository{

    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter converter;

    @Override
    public List<Product> findByText(String text) {

        final List<Product> products = new ArrayList<>();

        MongoDatabase database = client.getDatabase("millerGroup");
        MongoCollection<Document> collection = database.getCollection("Products");

        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                new Document("$match", new Document("product", "iphone")), // Add a $match stage to filter by "product" field
                new Document("$sort", new Document("price", 1L)),
                new Document("$limit", 5L)
        ));

        List<Product> product = new ArrayList<>();
        result.forEach(doc -> products.add(converter.read(Product.class, doc)));

        return product;
    }
}

