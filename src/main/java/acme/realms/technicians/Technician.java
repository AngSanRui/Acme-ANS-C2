
package acme.realms.technicians;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.Valid;

import acme.client.components.basis.AbstractRole;
import acme.client.components.mappings.Automapped;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.Optional;
import acme.client.components.validation.ValidNumber;
import acme.client.components.validation.ValidString;
import acme.constraints.ValidTechnician;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "licenseNumber")
})
@ValidTechnician
public class Technician extends AbstractRole {

	// Serialisation identifier

	private static final long	serialVersionUID	= 1L;

	// Attributes

	@Mandatory
	@ValidString(pattern = "^[A-Z]{2,3}\\d{6}$")
	@Column(unique = true)
	private String				licenseNumber;

	@Mandatory
	@ValidString(pattern = "^\\+?\\d{6,15}$")
	@Automapped
	private String				phoneNumber;

	@Mandatory
	@ValidString(max = 50)
	@Automapped
	private String				specialisation;

	@Mandatory
	@Valid
	@Automapped
	private Boolean				annualHealthTestPassed;

	@Mandatory
	@ValidNumber(min = 0, max = 120)
	@Automapped
	private Integer				experienceYears;

	@Optional
	@ValidString(max = 255)
	@Automapped
	private String				certifications;

}
