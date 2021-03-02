package ru.sindm.test.project.domain.utils;

public class ProductData {

    private String type;
    private Long id;
    private Boolean isCpaSearchContext;
    private Boolean isClickAndCollect;
    private String price;
    private String offerId;
    private Long productId;
    private String skuId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getCpaSearchContext() {
        return isCpaSearchContext;
    }

    public void setCpaSearchContext(Boolean cpaSearchContext) {
        isCpaSearchContext = cpaSearchContext;
    }

    public Boolean getClickAndCollect() {
        return isClickAndCollect;
    }

    public void setClickAndCollect(Boolean clickAndCollect) {
        isClickAndCollect = clickAndCollect;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }
}
