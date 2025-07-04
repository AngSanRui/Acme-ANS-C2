
package acme.features.manager.flight;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.controllers.AbstractGuiController;
import acme.client.controllers.GuiController;
import acme.entities.flights.Flight;
import acme.realms.managers.Manager;

@GuiController
public class ManagerFlightController extends AbstractGuiController<Manager, Flight> {

	@Autowired
	private ManagerFlightListService	listService;

	@Autowired
	private ManagerFlightShowService	showService;

	@Autowired
	private ManagerFlightUpdateService	updateService;

	@Autowired
	private ManagerFlightPublishService	publishService;

	@Autowired
	private ManagerFlightCreateService	createService;


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("update", this.updateService);
		super.addBasicCommand("create", this.createService);

		super.addCustomCommand("publish", "update", this.publishService);
	}

}
