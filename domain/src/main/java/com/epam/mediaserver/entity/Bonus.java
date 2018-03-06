package com.epam.mediaserver.entity;

import java.util.Objects;

public class Bonus extends Model {


    private String title;
    private String description;
    private int discount;
    private String code;

    public Bonus() {
        super();
    }

    public Bonus(String title, String description, int discount, String code) {
        this.title = title;
        this.description = description;
        this.discount = discount;
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Bonus bonus = (Bonus) o;
        return discount == bonus.discount &&
               Objects.equals(title, bonus.title) &&
               Objects.equals(description, bonus.description) &&
               Objects.equals(code, bonus.code);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), title, description, discount, code);
    }

    @Override
    public String toString() {
        return "Bonus{" +
               "title='" + title + '\'' +
               ", description='" + description + '\'' +
               ", discount=" + discount +
               '}';
    }
}
