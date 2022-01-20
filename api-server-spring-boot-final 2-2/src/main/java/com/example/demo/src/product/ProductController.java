package com.example.demo.src.product;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.product.model.ProductInfoRes;
import com.example.demo.src.product.model.ProductMiniInfoRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final ProductProvider productProvider;
    @Autowired
    private final ProductService productService;
    @Autowired
    private final JwtService jwtService;


    public ProductController(ProductProvider productProvider, ProductService productService, JwtService jwtService){
        this.productProvider = productProvider;
        this.productService = productService;
        this.jwtService = jwtService;
    }

    @ResponseBody
    @GetMapping("/{productIdx}")
    public BaseResponse<ProductInfoRes> getProductByIdx(@PathVariable("productIdx") int productIdx) {
        // Get Users
        try{
            ProductInfoRes getProductRes = productProvider.getProduct(productIdx);
            return new BaseResponse<>(getProductRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("/category/{categoryIdx}")
    public BaseResponse<List<ProductMiniInfoRes>> getProductByCategory(@PathVariable("categoryIdx") int categoryIdx) {
        // Get Users
        try{
            List<ProductMiniInfoRes> getProductByCategoryRes = productProvider.getProductsByCategory(categoryIdx);
            return new BaseResponse<>(getProductByCategoryRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("/subCategory/{subCategoryIdx}")
    public BaseResponse<List<ProductMiniInfoRes>> getProductBySubCategory(@PathVariable("subCategoryIdx") int subCategoryIdx ) {
        // Get Users
        try{
            List<ProductMiniInfoRes> getProductBySubCategoryRes = productProvider.getProductsBySubCategory(subCategoryIdx);
            return new BaseResponse<>(getProductBySubCategoryRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("/best-review")
    public BaseResponse<List<ProductMiniInfoRes>> getProductWithReview(@PathVariable("subCategoryIdx") int subCategoryIdx ) {
        // Get Users
        try{
            List<ProductMiniInfoRes> getProductBySubCategoryRes = productProvider.getProductsBySubCategory(subCategoryIdx);
            return new BaseResponse<>(getProductBySubCategoryRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("/dead-sale")
    public BaseResponse<List<ProductMiniInfoRes>> getProductWithDeadSale(@PathVariable("subCategoryIdx") int subCategoryIdx ) {
        // Get Users
        try{
            List<ProductMiniInfoRes> getProductBySubCategoryRes = productProvider.getProductsBySubCategory(subCategoryIdx);
            return new BaseResponse<>(getProductBySubCategoryRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("/best-new")
    public BaseResponse<List<ProductMiniInfoRes>> getProductWithBestNew(@PathVariable("subCategoryIdx") int subCategoryIdx ) {
        // Get Users
        try{
            List<ProductMiniInfoRes> getProductBySubCategoryRes = productProvider.getProductsBySubCategory(subCategoryIdx);
            return new BaseResponse<>(getProductBySubCategoryRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
    @ResponseBody
    @GetMapping("/kurly-only")
    public BaseResponse<List<ProductMiniInfoRes>> getProductKurlyOnly(@PathVariable("subCategoryIdx") int subCategoryIdx ) {
        // Get Users
        try{
            List<ProductMiniInfoRes> getProductBySubCategoryRes = productProvider.getProductsBySubCategory(subCategoryIdx);
            return new BaseResponse<>(getProductBySubCategoryRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
