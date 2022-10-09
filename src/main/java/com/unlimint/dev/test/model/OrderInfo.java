package com.unlimint.dev.test.model;

public class OrderInfo {

    private Integer id;
    private Integer orderId;
    private Double amount;
    private String comment;
    private String filename;
    private Integer line;
    private String result;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("{");
        sb.append("\"id\"=").append(id);
        sb.append(", \"orderId\"=").append(orderId);
        sb.append(", \"amount\"=").append(amount);
        sb.append(", \"comment\"=\"").append(comment).append('\"');
        sb.append(", \"filename\"=\"").append(filename).append('\"');
        sb.append(", \"line\"=").append(line);
        sb.append(", \"result\"=\"").append(result).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
