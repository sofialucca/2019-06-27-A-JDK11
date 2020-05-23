package it.polito.tdp.crimes.model;

import java.time.LocalDateTime;

public class Event {
	
	private Long incident_id;
	private Integer offense_code;
	private Integer offense_code_extension;
	private String offense_type_id;
	private String offense_category_id;
	private LocalDateTime reported_date;
	private String incident_address;
	private double geo_lon;
	private double geo_lat;
	private Integer district_id;
	private Integer precinct_id;
	private String neighborhood_id;
	private Integer is_crime;
	private Integer is_traffic;
	
	public Event(Long incident_id, Integer offense_code, Integer offense_code_extension, String offense_type_id,
			String offense_category_id, LocalDateTime reported_date, String incident_address, double geo_lon,
			double geo_lat, Integer district_id, Integer precinct_id, String neighborhood_id, Integer is_crime,
			Integer is_traffic) {
		super();
		this.incident_id = incident_id;
		this.offense_code = offense_code;
		this.offense_code_extension = offense_code_extension;
		this.offense_type_id = offense_type_id;
		this.offense_category_id = offense_category_id;
		this.reported_date = reported_date;
		this.incident_address = incident_address;
		this.geo_lon = geo_lon;
		this.geo_lat = geo_lat;
		this.district_id = district_id;
		this.precinct_id = precinct_id;
		this.neighborhood_id = neighborhood_id;
		this.is_crime = is_crime;
		this.is_traffic = is_traffic;
	}
	
	public Long getIncident_id() {
		return incident_id;
	}
	public void setIncident_id(Long incident_id) {
		this.incident_id = incident_id;
	}
	public Integer getOffense_code() {
		return offense_code;
	}
	public void setOffense_code(Integer offense_code) {
		this.offense_code = offense_code;
	}
	public Integer getOffense_code_extension() {
		return offense_code_extension;
	}
	public void setOffense_code_extension(Integer offense_code_extension) {
		this.offense_code_extension = offense_code_extension;
	}
	public String getOffense_type_id() {
		return offense_type_id;
	}
	public void setOffense_type_id(String offense_type_id) {
		this.offense_type_id = offense_type_id;
	}
	public String getOffense_category_id() {
		return offense_category_id;
	}
	public void setOffense_category_id(String offense_category_id) {
		this.offense_category_id = offense_category_id;
	}
	public LocalDateTime getReported_date() {
		return reported_date;
	}
	public void setReported_date(LocalDateTime reported_date) {
		this.reported_date = reported_date;
	}
	public String getIncident_address() {
		return incident_address;
	}
	public void setIncident_address(String incident_address) {
		this.incident_address = incident_address;
	}
	public double getGeo_lon() {
		return geo_lon;
	}
	public void setGeo_lon(double geo_lon) {
		this.geo_lon = geo_lon;
	}
	public double getGeo_lat() {
		return geo_lat;
	}
	public void setGeo_lat(double geo_lat) {
		this.geo_lat = geo_lat;
	}
	public Integer getDistrict_id() {
		return district_id;
	}
	public void setDistrict_id(Integer district_id) {
		this.district_id = district_id;
	}
	public Integer getPrecinct_id() {
		return precinct_id;
	}
	public void setPrecinct_id(Integer precinct_id) {
		this.precinct_id = precinct_id;
	}
	public String getNeighborhood_id() {
		return neighborhood_id;
	}
	public void setNeighborhood_id(String neighborhood_id) {
		this.neighborhood_id = neighborhood_id;
	}
	public Integer getIs_crime() {
		return is_crime;
	}
	public void setIs_crime(Integer is_crime) {
		this.is_crime = is_crime;
	}
	public Integer getIs_traffic() {
		return is_traffic;
	}
	public void setIs_traffic(Integer is_traffic) {
		this.is_traffic = is_traffic;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((incident_id == null) ? 0 : incident_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (incident_id == null) {
			if (other.incident_id != null)
				return false;
		} else if (!incident_id.equals(other.incident_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Event [incident_id=" + incident_id + ", offense_category_id=" + offense_category_id + ", reported_date="
				+ reported_date + ", district_id=" + district_id + "]";
	}
}
