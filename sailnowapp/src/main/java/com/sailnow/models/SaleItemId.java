package com.sailnow.models;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class SaleItemId implements Serializable  {

	private User user;
	private Items items;
	
	@ManyToOne
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne
	public Items getItems() {
		return items;
	}
	/**
	 * @param item the item to set
	 */
	public void setItems(Items items) {
		this.items = items;
	}
	
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SaleItemId that = (SaleItemId) o;

        if (items != null ? !items.equals(that.items) : that.items != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (items != null ? items.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
