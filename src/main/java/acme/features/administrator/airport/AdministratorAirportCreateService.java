
package acme.features.administrator.airport;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.models.Dataset;
import acme.client.components.principals.Administrator;
import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractGuiService;
import acme.client.services.GuiService;
import acme.entities.airports.Airport;
import acme.entities.airports.OperationalScope;

@GuiService
public class AdministratorAirportCreateService extends AbstractGuiService<Administrator, Airport> {
	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorAirportRepository repository;

	// AbstractGuiService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		Integer id;

		status = super.getRequest().getPrincipal().hasRealmOfType(Administrator.class);
		if (super.getRequest().getMethod().equals("POST")) {
			id = super.getRequest().getData("id", Integer.class);
			if (id == null)
				status = false;
			else if (id != 0)
				status = false;
		}

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Airport airport;

		airport = new Airport();

		super.getBuffer().addData(airport);
	}

	@Override
	public void bind(final Airport airport) {
		super.bindObject(airport, "name", "iataCode", "operationalScope", "city", "country", "website", "email", "contactPhoneNumber");
	}

	@Override
	public void validate(final Airport airport) {
		boolean confirmation;

		confirmation = super.getRequest().getData("confirmation", boolean.class);

		super.state(confirmation, "confirmation", "acme.validation.confirmation.message");
	}

	@Override
	public void perform(final Airport airport) {
		this.repository.save(airport);
	}

	@Override
	public void unbind(final Airport airport) {
		Dataset dataset;
		SelectChoices choices;

		choices = SelectChoices.from(OperationalScope.class, airport.getOperationalScope());

		dataset = super.unbindObject(airport, "name", "iataCode", "operationalScope", "city", "country", "website", "email", "contactPhoneNumber");
		dataset.put("operationalScopes", choices);

		super.getResponse().addData(dataset);
	}

}
