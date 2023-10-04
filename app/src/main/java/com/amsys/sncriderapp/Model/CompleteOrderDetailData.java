package com.amsys.sncriderapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CompleteOrderDetailData {
    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("orderDetail")
    @Expose
    private OrderDetail orderDetail;
    @SerializedName("endUserOrderPriceDetail")
    @Expose
    private EndUserOrderPriceDetail endUserOrderPriceDetail;
    @SerializedName("riderOrderPriceDetail")
    @Expose
    private RiderOrderPriceDetail riderOrderPriceDetail;
    @SerializedName("userDetail")
    @Expose
    private UserDetail userDetail;
    @SerializedName("userAddressDetail")
    @Expose
    private UserAddressDetail userAddressDetail;
    @SerializedName("questionairsByEndUser")
    @Expose
    private QuestionairsByEndUser questionairsByEndUser;
    @SerializedName("questionairsByRider")
    @Expose
    private QuestionairsByRider questionairsByRider;
    @SerializedName("productVariantDetail")
    @Expose
    private ProductVariantDetail productVariantDetail;
    @SerializedName("orderStatuses")
    @Expose
    private List<OrderStatus> orderStatuses;
    @SerializedName("pickupScheduleDetail")
    @Expose
    private PickupScheduleDetail pickupScheduleDetail;
    @SerializedName("pickupRiderDetail")
    @Expose
    private PickupRiderDetail pickupRiderDetail;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    public EndUserOrderPriceDetail getEndUserOrderPriceDetail() {
        return endUserOrderPriceDetail;
    }

    public void setEndUserOrderPriceDetail(EndUserOrderPriceDetail endUserOrderPriceDetail) {
        this.endUserOrderPriceDetail = endUserOrderPriceDetail;
    }

    public RiderOrderPriceDetail getRiderOrderPriceDetail() {
        return riderOrderPriceDetail;
    }

    public void setRiderOrderPriceDetail(RiderOrderPriceDetail riderOrderPriceDetail) {
        this.riderOrderPriceDetail = riderOrderPriceDetail;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public UserAddressDetail getUserAddressDetail() {
        return userAddressDetail;
    }

    public void setUserAddressDetail(UserAddressDetail userAddressDetail) {
        this.userAddressDetail = userAddressDetail;
    }

    public QuestionairsByEndUser getQuestionairsByEndUser() {
        return questionairsByEndUser;
    }

    public void setQuestionairsByEndUser(QuestionairsByEndUser questionairsByEndUser) {
        this.questionairsByEndUser = questionairsByEndUser;
    }

    public QuestionairsByRider getQuestionairsByRider() {
        return questionairsByRider;
    }

    public void setQuestionairsByRider(QuestionairsByRider questionairsByRider) {
        this.questionairsByRider = questionairsByRider;
    }

    public ProductVariantDetail getProductVariantDetail() {
        return productVariantDetail;
    }

    public void setProductVariantDetail(ProductVariantDetail productVariantDetail) {
        this.productVariantDetail = productVariantDetail;
    }

    public List<OrderStatus> getOrderStatuses() {
        return orderStatuses;
    }

    public void setOrderStatuses(List<OrderStatus> orderStatuses) {
        this.orderStatuses = orderStatuses;
    }

    public PickupScheduleDetail getPickupScheduleDetail() {
        return pickupScheduleDetail;
    }

    public void setPickupScheduleDetail(PickupScheduleDetail pickupScheduleDetail) {
        this.pickupScheduleDetail = pickupScheduleDetail;
    }

    public PickupRiderDetail getPickupRiderDetail() {
        return pickupRiderDetail;
    }

    public void setPickupRiderDetail(PickupRiderDetail pickupRiderDetail) {
        this.pickupRiderDetail = pickupRiderDetail;
    }
}