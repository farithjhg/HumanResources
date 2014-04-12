package com.wolfsoft.hr.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the countries database table.
 * 
 */
@Entity
@Table(name="countries")
@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="country_id")
	private String countryId;

	@Column(name="country_name")
	private String countryName;

	//bi-directional many-to-one association to Region
	@ManyToOne
	@JoinColumn(name="region_id")
	private Region region;

	//bi-directional many-to-one association to Location
	@OneToMany(mappedBy="country")
	private List<Location> locations;

	public Country() {
	}

	public String getCountryId() {
		return this.countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return this.countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public List<Location> getLocations() {
		return this.locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public Location addLocation(Location location) {
		getLocations().add(location);
		location.setCountry(this);

		return location;
	}

	public Location removeLocation(Location location) {
		getLocations().remove(location);
		location.setCountry(null);

		return location;
	}

}