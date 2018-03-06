package com.epam.mediaserver.entity;

import java.util.Objects;

public class BonusKeeper extends Model {

    private Bonus bonus;
    private User user;

    public BonusKeeper() {
    }

    public Bonus getBonus() {
        return bonus;
    }

    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        BonusKeeper that = (BonusKeeper) o;
        return Objects.equals(bonus, that.bonus) &&
               Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), bonus, user);
    }

    @Override
    public String toString() {
        return "BonusKeeperDao{" +
               "bonus=" + bonus +
               ", user=" + user +
               '}';
    }
}
