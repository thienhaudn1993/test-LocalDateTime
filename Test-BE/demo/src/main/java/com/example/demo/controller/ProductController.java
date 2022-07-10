package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;


@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/manager/product/api")
public class ProductController {
    @Autowired
    private IProductService productService;

//    // HauLST
    @GetMapping("/list/auction")
    public ResponseEntity<List<Product>> showListProductAuction() {
        List<Product> productList = productService.getAllProductAuntion();
//        LocalDateTime localDateTime = LocalDateTime.now();
//        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String localDateTimeFormatter = localDateTime.format(myFormatObj);

        if (productList.isEmpty()) {
            return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
        } else {
//            for (Product product : productList) {
//
//                product.setRemainingTime(String.valueOf(localDateTimeFormatter));
//            }
            return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
        }
    }

    // HauLST - test LocalDateTime
//    @GetMapping("/list/auction")
//    public ResponseEntity<List<Product>> showListProductAuction() {
//        List<Product> productList = productService.getAllProductAuntion();
//        LocalDateTime localDateTime = LocalDateTime.now();
//        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//        String localDateTimeFormatter = localDateTime.format(myFormatObj);
//        System.out.println(localDateTimeFormatter);
//        Product calculateTime = new Product();
//
//        if (productList.isEmpty()) {
//            return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
//        } else {
//            for (Product product : productList) {
//                /* Lấy mỗi thời gian kết thúc và hiện tại */
//                int yearEnd = calculateTime.getYearCurrently(product.getEndDate());
//                int yearNow = calculateTime.getYearCurrently(localDateTimeFormatter);
//
//                int monthEnd = calculateTime.getMonthCurrently(product.getEndDate());
//                int monthNow = calculateTime.getMonthCurrently(localDateTimeFormatter);
//
//                int dayEnd = calculateTime.getDayCurrently(product.getEndDate());
//                int dayNow = calculateTime.getDayCurrently(localDateTimeFormatter);
//
//                int hourEnd = calculateTime.getHourCurrently(product.getEndDate());
//                int hourNow = calculateTime.getHourCurrently(localDateTimeFormatter);
//
//                int minuteEnd = calculateTime.getMinuteCurrently(product.getEndDate());
//                int minuteNow = calculateTime.getMinuteCurrently(localDateTimeFormatter);
//
//                int secondEnd = calculateTime.getSecondCurrently(product.getEndDate());
//                int secondNow = calculateTime.getSecondCurrently(localDateTimeFormatter);
//
//                /* Remaining time */
//                int year = calculateTime.getRemainTime(yearEnd , yearNow);
//                int month = calculateTime.getRemainTime(monthEnd , monthNow);
//                int day = calculateTime.getRemainTime(dayEnd , dayNow);
//                int hour = calculateTime.getRemainTime(hourEnd , hourNow);
//                int minute = calculateTime.getRemainTime(minuteEnd , minuteNow);
//                int second = calculateTime.getRemainTime(secondEnd , secondNow);
//
//                System.out.println(year + " " + month + " " + day + " " + hour + " " + minute + " " + second);
//            }
//            return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
//        }
//    }



    // HauLST
    @GetMapping("/list/finished-auction")
    public ResponseEntity<List<Product>> showListProductFinishedAuction() {
        List<Product> productList = productService.getAllProductFinishedAuntion();
        if (productList.isEmpty()) {
            return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
    }

    //HauLST
    @GetMapping("/list/auction/{typeProduct}")
    public ResponseEntity<List<Product>> showListProductAuctionAndTypeProduct(@PathVariable String typeProduct) {
        List<Product> productList = productService.gettAllProductAuntionAndTypeProduct(typeProduct);
        if (productList.isEmpty()) {
            return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
    }

    //HauLST
    @GetMapping("list/search/name={nameProduct}/type-product={typeProduct}/{min}/{max}")
    public ResponseEntity<List<Product>> searchNameAndTypeAndPrices(@PathVariable String nameProduct, @PathVariable String typeProduct, @PathVariable Double min, @PathVariable Double max) {
        List<Product> productList = productService.searchProductByNameByTypeProductByPrice(nameProduct, typeProduct, min, max);
        if (productList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    //HauLST
    @GetMapping("list/search/name={nameProduct}/type-product={typeProduct}/{min}")
    public ResponseEntity<List<Product>> searchPricesOver250(@PathVariable String nameProduct, @PathVariable String typeProduct, @PathVariable Double min) {
        List<Product> productList = productService.searchProductPricesOver250(nameProduct, typeProduct, min);
        if (productList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

}

