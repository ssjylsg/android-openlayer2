package com.netposa.npmobilesdk.geometry;

/**
 * 覆盖物样式
 */

public class Style {
    private String label;
    private String labelAlign = "cm";
    private Double labelXOffset = 0.0;
    private Double labelYOffset = 0.0;
    private String fontColor = "white";
    private String fontSize = "14px";

    public String getLabelOutlineColor() {
        return labelOutlineColor;
    }

    public void setLabelOutlineColor(String labelOutlineColor) {
        this.labelOutlineColor = labelOutlineColor;
    }

    public Double getLabelOutlineWidth() {
        return labelOutlineWidth;
    }

    public void setLabelOutlineWidth(Double labelOutlineWidth) {
        this.labelOutlineWidth = labelOutlineWidth;
    }

    public String getDisplay() {
        return display;
    }

    /**
     *
     * @param display none ""
     */
    public void setDisplay(String display) {
        this.display = display;
    }

    private String labelOutlineColor = "white";
    private Double labelOutlineWidth = null;
    private String display;
    private String fontFamily ;
    private Double rotation;

    public String toString() {
        return com.alibaba.fastjson.JSON.toJSONString(this);
    }

    public String getLabel() {
        return label;
    }

    /**
     * 设置文字 文字颜色默认red,可以通过setFontColor设置字体颜色
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;
        setFontColor("red");
    }

    public String getLabelAlign() {
        return labelAlign;
    }

    public void setLabelAlign(String labelAlign) {
        this.labelAlign = labelAlign;
    }

    /**
     *
     * @return
     */
    public Double getLabelXOffset() {
        return labelXOffset;
    }

    /**
     * 设置文字X偏移量
     * @param labelXOffset
     */
    public void setLabelXOffset(Double labelXOffset) {
        this.labelXOffset = labelXOffset;
    }

    public Double getLabelYOffset() {
        return labelYOffset;
    }

    /**
     * 设置文字Y偏移量
     * @param labelYOffset
     */
    public void setLabelYOffset(Double labelYOffset) {
        this.labelYOffset = labelYOffset;
    }

    public String getFontColor() {
        return fontColor;
    }

    /**
     * 字体颜色
     * @param fontColor
     */
    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getFontSize() {
        return fontSize;
    }

    /**
     * 字体大小
     * @param fontSize
     */
    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    /**
     * 字体
     * @param fontFamily
     */
    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public Double getRotation() {
        return rotation;
    }

    /**
     * 设置旋转角度
     * @param rotation
     */
    public void setRotation(Double rotation) {
        this.rotation = rotation;
    }

}
