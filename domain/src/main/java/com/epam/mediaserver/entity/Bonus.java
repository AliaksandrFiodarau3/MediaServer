package com.epam.mediaserver.entity;

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
        if (!(o instanceof Bonus)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Bonus bonus = (Bonus) o;

        if (getDiscount() != bonus.getDiscount()) {
            return false;
        }
        if (getTitle() != null ? !getTitle().equals(bonus.getTitle()) : bonus.getTitle() != null) {
            return false;
        }
        if (getDescription() != null ? !getDescription().equals(bonus.getDescription())
                                     : bonus.getDescription() != null) {
            return false;
        }
        return getCode() != null ? getCode().equals(bonus.getCode()) : bonus.getCode() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + getDiscount();
        result = 31 * result + (getCode() != null ? getCode().hashCode() : 0);
        return result;
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
