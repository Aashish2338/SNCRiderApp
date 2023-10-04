package com.amsys.sncriderapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExactPriceData {
    @SerializedName("exactPrice")
    @Expose
    private Double exactPrice;
    @SerializedName("variantDetail")
    @Expose
    private VariantDetail variantDetail;
    @SerializedName("tupc")
    @Expose
    private String tupc;
    @SerializedName("ageing")
    @Expose
    private String ageing;
    @SerializedName("questionairs")
    @Expose
    private Questionairs questionairs;

    public Double getExactPrice() {
        return exactPrice;
    }

    public void setExactPrice(Double exactPrice) {
        this.exactPrice = exactPrice;
    }

    public VariantDetail getVariantDetail() {
        return variantDetail;
    }

    public void setVariantDetail(VariantDetail variantDetail) {
        this.variantDetail = variantDetail;
    }

    public String getTupc() {
        return tupc;
    }

    public void setTupc(String tupc) {
        this.tupc = tupc;
    }

    public String getAgeing() {
        return ageing;
    }

    public void setAgeing(String ageing) {
        this.ageing = ageing;
    }

    public Questionairs getQuestionairs() {
        return questionairs;
    }

    public void setQuestionairs(Questionairs questionairs) {
        this.questionairs = questionairs;
    }
}
