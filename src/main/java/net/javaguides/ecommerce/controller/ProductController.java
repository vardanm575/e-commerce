package net.javaguides.ecommerce.controller;

import net.javaguides.ecommerce.model.ImageModel;
import net.javaguides.ecommerce.model.Product;
import net.javaguides.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PreAuthorize("hasRole('Admin')")
    @PostMapping(value = "/addNewProduct", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Product addNewProduct(@RequestPart("product") Product product,
                                 @RequestPart("imageFile")MultipartFile[] file){
      //  return productService.addNewProduct(product);
        try {
            Set<ImageModel> images = uploadImage(file);
            product.setProductImages(images);
            return productService.addNewProduct(product);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<ImageModel> imageModels = new HashSet<>();

        for (MultipartFile file: multipartFiles) {
            ImageModel imageModel = new ImageModel(file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes());
            imageModels.add(imageModel);
        }
        return imageModels;
    }

    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts(@RequestParam(defaultValue = "0") int pageNumber){
        return productService.getAllProducts(pageNumber);
    }

    @GetMapping("/getProductDetailsById/{productId}")
    public Product getProductDetailsById(@PathVariable("productId") Integer productId){
        return productService.getProductDetailsById(productId);
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/deleteProductDetails/{productId}")
    public void deleteProductDetails(@PathVariable("productId") Integer productId){
        productService.deleteProductDetails(productId);
    }

    @PreAuthorize("hasRole('User')")
    @GetMapping("/getProductDetails/{isSingleProductCheckout}/{productId}")
    public List<Product> getProductDetails(@PathVariable(name = "isSingleProductCheckout") boolean isSingleProductCheckout,
                                           @PathVariable(name = "productId") Integer productId){

        return productService.getProductDetails(isSingleProductCheckout, productId);
    }
}
