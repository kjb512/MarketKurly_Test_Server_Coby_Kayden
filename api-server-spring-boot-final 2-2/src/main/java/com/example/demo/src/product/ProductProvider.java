package com.example.demo.src.product;


import com.example.demo.config.BaseException;
import com.example.demo.src.product.model.ProductInfoRes;
import com.example.demo.src.product.model.ProductMiniInfoRes;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service
public class ProductProvider {

    private final ProductDao productDao;
    private final JwtService jwtService;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ProductProvider(ProductDao productDao, JwtService jwtService) {
        this.productDao = productDao;
        this.jwtService = jwtService;
    }
    public ProductInfoRes getProduct(int productIdx) throws BaseException{
        try{
            ProductInfoRes getProductRes = productDao.getProductByIdx(productIdx);
            return getProductRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
    public List<ProductMiniInfoRes> getProductsByCategory(int categoryIdx) throws BaseException{
        try{
            List<ProductMiniInfoRes> getProductMiniInfoRes = productDao.getProductsByCategory(categoryIdx);
            for(int i=0;i<getProductMiniInfoRes.size();i++){
                getProductMiniInfoRes.get(i).setDiscountAfterPrice(getProductMiniInfoRes.get(i).getPrice()*
                        (100-getProductMiniInfoRes.get(i).getDiscount())/100);
            }
            return getProductMiniInfoRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<ProductMiniInfoRes> getProductsBySubCategory(int subCategoryIdx) throws BaseException{
        try{
            List<ProductMiniInfoRes> getProductMiniInfoRes = productDao.getProductsBySubCategory(subCategoryIdx);
            for(int i=0;i<getProductMiniInfoRes.size();i++){
                getProductMiniInfoRes.get(i).setDiscountAfterPrice(getProductMiniInfoRes.get(i).getPrice()*
                        (100-getProductMiniInfoRes.get(i).getDiscount())/100);
            }
            return getProductMiniInfoRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


}
