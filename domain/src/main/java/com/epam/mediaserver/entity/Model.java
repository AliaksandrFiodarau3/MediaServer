package com.epam.mediaserver.entity;

import java.io.Serializable;
import java.util.Objects;

public class Model implements Serializable, Cloneable {

    protected Long id;

    public Model() {
    }

    public Model(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Model)) {
            return false;
        }

        Model model = (Model) o;

        return getId() == model.getId();
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Model{" +
               "id=" + id +
               '}';
    }
}
