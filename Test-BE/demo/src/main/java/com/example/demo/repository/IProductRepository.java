package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    //HauLST - List sp đang đấu giá, và sắp xếp theo thời gian còn lại từ ít nhấy -> nhiều nhất
    @Query(value = "select * from Product inner join TypeProduct on Product.id_product_type = TypeProduct.id_product_type \n" +
            " inner join BiddingStatus on Product.id_bidding_status = BiddingStatus.id_bidding_status \n" +
            " inner join ImageProduct on Product.id_product = ImageProduct.id_product\n" +
            " inner join ApprovalStatus on Product.id_approval_status = ApprovalStatus.id_approval_status \n" +
            " where BiddingStatus.name_bidding_status= \"auction\" and ApprovalStatus.name_approval_status = \"posted\" and (Product.end_date > now()) \n" +
            " order by Product.end_date  asc", nativeQuery = true)
    List<Product> findAllProductAuction();

    //HauLST - List sp đấu giá đã kết thúc ( có thể thành công hoặc thất bại ), và sắp xếp theo thời gian tạo từ mới nhất -> cũ nhất
    @Query(value = " select * from Product inner join TypeProduct on Product.id_product_type = TypeProduct.id_product_type \n" +
            " inner join BiddingStatus on Product.id_bidding_status = BiddingStatus.id_bidding_status \n" +
            " inner join ImageProduct on Product.id_product = ImageProduct.id_product\n" +
            " inner join ApprovalStatus on Product.id_approval_status = ApprovalStatus.id_approval_status\n" +
            " where ApprovalStatus.name_approval_status = \"posted\"\n" +
            " and (Product.end_date <now()) \n" +
            " order by Product.end_date desc", nativeQuery = true)
    List<Product> findAllProductFinishedAuction();

    //HauLST - List sp đang đấu giá và theo từng category, và có sắp xếp theo thời gian tạo từ mới nhất -> cũ nhất
    @Query(value = "select * from Product inner join TypeProduct on Product.id_product_type = TypeProduct.id_product_type \n" +
            " inner join BiddingStatus on Product.id_bidding_status = BiddingStatus.id_bidding_status \n" +
            " inner join ImageProduct on Product.id_product = ImageProduct.id_product\n" +
            " inner join ApprovalStatus on Product.id_approval_status = ApprovalStatus.id_approval_status \n" +
            " where BiddingStatus.name_bidding_status= \"auction\" \n" +
            " and ApprovalStatus.name_approval_status = \"posted\"\n" +
            " and TypeProduct.name_product_type like %?1% \n" +
            " and (Product.end_date > now()) \n" +
            " order by Product.end_date asc", nativeQuery = true)
    List<Product> gettAllProductAuntionAndTypeProduct(String nameTypeProduct);

    //HauLST - Search sp theo nameProduct, typeProduct, prices range
    @Query(value = "select * from Product \n" +
            "inner join TypeProduct on Product.id_product_type = TypeProduct.id_product_type \n" +
            "inner join BiddingStatus on Product.id_bidding_status = BiddingStatus.id_bidding_status \n" +
            "inner join ImageProduct on Product.id_product = ImageProduct.id_product \n" +
            "inner join ApprovalStatus on Product.id_approval_status = ApprovalStatus.id_approval_status\n" +
            "where BiddingStatus.name_bidding_status= \"auction\" \n" +
            "and ApprovalStatus.name_approval_status = \"posted\"\n" +
            "and Product.name_product like %?1% \n" +
            "and TypeProduct.name_product_type like %?2% \n" +
            "and (Product.final_price between ?3 and ?4)\n" +
            "order by Product.end_date asc", nativeQuery = true)
    List<Product> searchProductByNameByTypeProductByPrice(String nameProduct, String nameTypeProduct, Double min, Double max);

    //HauLST - Search sp theo nameProduct, typeProduct, prices range>250
    @Query(value = "select * from Product \n" +
            "inner join TypeProduct on Product.id_product_type = TypeProduct.id_product_type \n" +
            "inner join BiddingStatus on Product.id_bidding_status = BiddingStatus.id_bidding_status \n" +
            "inner join ImageProduct on Product.id_product = ImageProduct.id_product \n" +
            "inner join ApprovalStatus on Product.id_approval_status = ApprovalStatus.id_approval_status\n" +
            "where BiddingStatus.name_bidding_status= \"auction\" \n" +
            "and ApprovalStatus.name_approval_status = \"posted\" and (Product.end_date > now()) \n" +
            "and Product.name_product like %?1% \n" +
            "and TypeProduct.name_product_type like %?2% \n" +
            "and (Product.final_price >?3)\n" +
            "order by Product.end_date asc", nativeQuery = true)
    List<Product> searchProductPricesOver250(String nameProduct, String nameTypeProduct, Double min);
}
