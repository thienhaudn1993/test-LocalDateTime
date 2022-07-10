package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long idProduct;
    @Column(name = "code_product")
    private String codeProduct;
    @Column(name = "name_product")
    private String nameProduct;
    @Column(name = "initial_price")
    private Double initialPrice;
    @Column(name = "final_price")
    private Double finalPrice;
    @Column(name = "increment_price")
    private Double incrementPrice;
    @Column(name = "product_description")
    private String productDescription;
    @Column(name = "start_date")
    private String startDate;
    @Column(name = "end_date")
    private String endDate;
    @Column(name = "remaining_time")
    private String remainingTime;
    @Column(name="create_day")
    private String createDay;

    @ManyToOne(targetEntity = TypeProduct.class)
    @JoinColumn(name = "id_product_type", nullable = false)
    private TypeProduct typeProduct;

    @ManyToOne(targetEntity = ApprovalStatus.class)
    @JoinColumn(name = "id_approval_status", nullable = false)
    private ApprovalStatus approvalStatus;

    @ManyToOne(targetEntity = BiddingStatus.class)
    @JoinColumn(name = "id_bidding_status", nullable = false)
    private BiddingStatus biddingStatus;

    @OneToMany(mappedBy = "product")
    private List<ImageProduct> imageProductList;


    public Product() {
    }

    public String getCreateDay() {
        return createDay;
    }

    public void setCreateDay(String createDay) {
        this.createDay = createDay;
    }

    public String getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(String remainingTime) {
        this.remainingTime = remainingTime;
    }

    public List<ImageProduct> getImageProductList() {
        return imageProductList;
    }

    public void setImageProductList(List<ImageProduct> imageProductList) {
        this.imageProductList = imageProductList;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getCodeProduct() {
        return codeProduct;
    }

    public void setCodeProduct(String codeProduct) {
        this.codeProduct = codeProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(Double initialPrice) {
        this.initialPrice = initialPrice;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Double getIncrementPrice() {
        return incrementPrice;
    }

    public void setIncrementPrice(Double incrementPrice) {
        this.incrementPrice = incrementPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public TypeProduct getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(TypeProduct typeProduct) {
        this.typeProduct = typeProduct;
    }

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public BiddingStatus getBiddingStatus() {
        return biddingStatus;
    }

    public void setBiddingStatus(BiddingStatus biddingStatus) {
        this.biddingStatus = biddingStatus;
    }

//    ------ Phương thức thời gian ------
//    /* Get year*/
//    public int getYearCurrently(String time) {
//        String year = time.substring(0, time.indexOf('-')).trim();
//        return Integer.parseInt(year);
//    }
//
//    /* Get month */
//    public int getMonthCurrently(String time) {
//        int placeFirst = time.indexOf('-');
//        int place =  time.indexOf('-' , placeFirst + 1);
//        String month = time.substring(placeFirst + 1, place).trim();
//        return Integer.parseInt(month);
//    }
//
//    /* Get day */
//    public int getDayCurrently(String time) {
//        int placeFirst = time.indexOf('-');
//        placeFirst =  time.indexOf('-' , placeFirst + 1);
//
//        int place = time.indexOf(' ');
//        String day = time.substring(placeFirst + 1, place).trim();
//        return Integer.parseInt(day);
//    }
//
//    /* Get hour */
//    public int getHourCurrently(String time) {
//        int placeFirst = time.indexOf(' ');
//
//        int place = time.indexOf(':');
//        String hour = time.substring(placeFirst, place).trim();
//        return Integer.parseInt(hour);
//    }
//
//    /* Get minute */
//    public int getMinuteCurrently(String time) {
//        int placeFirst = time.indexOf(':');
//
//        int place = time.indexOf(':');
//        place = time.indexOf(':' , place + 1);
//        String minute = time.substring(placeFirst+1, place).trim();
//        return Integer.parseInt(minute);
//    }
//
//    /* Get second */
//    public int getSecondCurrently(String time) {
//        int placeFirst = time.indexOf(':');
//        placeFirst = time.indexOf(':' , placeFirst + 1);
//        placeFirst = time.indexOf(':' , placeFirst);
//
//        String second = time.substring(placeFirst+1).trim();
//        return Integer.parseInt(second);
//    }
//
//    /* Calculate remain time */
//    public int getRemainTime(int end , int now) {
//        int total = end - now;
//        if(total < 0) {
//            return end;
//        }
//        return end - now;
//    }
}
