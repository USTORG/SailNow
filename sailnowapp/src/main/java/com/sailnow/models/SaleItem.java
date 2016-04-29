package com.sailnow.models;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sale_items", catalog = "sailnow")
@AssociationOverrides({
		@AssociationOverride(name = "pk.items",joinColumns = @JoinColumn(name = "name")),
		@AssociationOverride(name = "pk.user",joinColumns = @JoinColumn(name = "email")) })
public class SaleItem implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SaleItemId pk = new SaleItemId();
	
	
	public SaleItem() {

	}


	@EmbeddedId
	public SaleItemId getPk() {
		return pk;
	}


	/**
	 * @param pk the pk to set
	 */
	public void setPk(SaleItemId pk) {
		this.pk = pk;
	}
	
	@Transient
	public User getUser() {
		return getPk().getUser();
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		getPk().setUser(user);
	}
	
	@Transient
	public Items getItems() {
		return getPk().getItems();
	}
	/**
	 * @param item the item to set
	 */
	public void setItems(Items items) {
		getPk().setItems(items);
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		SaleItem that = (SaleItem) o;

		if (getPk() != null ? !getPk().equals(that.getPk())
				: that.getPk() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}
}
