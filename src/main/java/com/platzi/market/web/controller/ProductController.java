package com.platzi.market.web.controller;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.services.ProductService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    @ApiOperation("get all supermarket products")
    @ApiResponse(code=200, message = "OK")
    public ResponseEntity<List<Product>> getAll() {
        return  new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    @ApiOperation("Search a product with an ID")
    @ApiResponses( {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Producto not found")
    })
    public ResponseEntity<Product> getProduct(@ApiParam(value = "the id of the product", required = true, example = "7") @PathVariable("productId") int productId) {
        return  productService.getProduct(productId)
                .map( product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable("categoryId") int categoryId) {
        return  productService.getByCategory(categoryId)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity delete(@PathVariable("productId") int productId) {
        return productService.delete(productId) == true ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
