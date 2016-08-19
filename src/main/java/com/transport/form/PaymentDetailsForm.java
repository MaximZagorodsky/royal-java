package com.transport.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Класс содержащий все поля из блока PaymentsDetails
 * для отправки на сервер
 * Created by Maksim Zagorodskiy on 7/9/2016.
 */
@Data
public class PaymentDetailsForm {

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date moveDate;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date packingDate;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date storageDate;
    private String paymentMethod;

    private String serviceCharge;
    private String packingMaterial;

    private Integer truck;
    private Integer movers;
    private Integer shrink;
    private Integer tape;
    private Integer zipFrom;
    private Integer zipTo;

    private String rateType;
    private String heavyItemPrice;
    private Integer totalHour; //

    private String status;
    private String followUpDate;
    private String fieldForSalesmanComments;
    private String fieldForManagerComments;
    private String company;
    private String sizeOfMove;
    private String storageSize;
    private String tariff;

    private BigDecimal ratePerHour;
    private Integer ddt;
    private Double tapeValue;
    private Double shrinkValue;
    private Double discount;
    private Double packingMaterialsValue;
    private BigDecimal totalHourPrice;
    private BigDecimal totalPrice;

    @Override
    public String toString() {

        return "PaymentDetailsForm{" +
                "moveDate=" + moveDate +
                ", packingDate=" + packingDate +
                ", storageDate=" + storageDate +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", serviceCharge='" + serviceCharge + '\'' +
                ", packingMaterial='" + packingMaterial + '\'' +
                ", truck=" + truck +
                ", movers=" + movers +
                ", shrink=" + shrink +
                ", tape=" + tape +
                ", zipFrom=" + zipFrom +
                ", zipTo=" + zipTo +
                ", rateType='" + rateType + '\'' +
                ", heavyItemPrice='" + heavyItemPrice + '\'' +
                ", totalHour='" + totalHour + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                ", status='" + status + '\'' +
                ", followUpDate='" + followUpDate + '\'' +
                ", fieldForSalesmanComments='" + fieldForSalesmanComments + '\'' +
                ", fieldForManagerComments='" + fieldForManagerComments + '\'' +
                ", company='" + company + '\'' +
                ", sizeOfMove='" + sizeOfMove + '\'' +
                ", storageSize='" + storageSize + '\'' +
                ", tariff='" + tariff + '\'' +
                ", ratePerHour=" + ratePerHour +
                ", ddt=" + ddt +
                ", tapeValue=" + tapeValue +
                ", shrinkValue=" + shrinkValue +
                ", discount=" + discount +
                ", totalHourPrice=" + totalHourPrice +
                ", packingMaterialsValue=" + packingMaterialsValue +
                '}';
    }
}