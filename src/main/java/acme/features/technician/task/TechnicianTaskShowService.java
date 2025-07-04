
package acme.features.technician.task;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.models.Dataset;
import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractGuiService;
import acme.client.services.GuiService;
import acme.entities.task.Task;
import acme.entities.task.TaskType;
import acme.realms.technicians.Technician;

@GuiService
public class TechnicianTaskShowService extends AbstractGuiService<Technician, Task> {

	@Autowired
	private TechnicianTaskRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int taskId;
		Task task;
		Technician technician;
		if (super.getRequest().getDataEntries().stream().anyMatch(e -> e.getKey().equals("id"))) {
			taskId = super.getRequest().getData("id", int.class);
			task = this.repository.findTaskById(taskId);
			technician = task == null ? null : task.getTechnician();
			status = technician != null && (super.getRequest().getPrincipal().hasRealm(technician) || task.getDraftMode().equals(false)) && task != null;
		} else
			status = false;

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Task task;
		int id;

		id = super.getRequest().getData("id", int.class);
		task = this.repository.findTaskById(id);

		super.getBuffer().addData(task);
	}

	@Override
	public void unbind(final Task task) {
		Dataset dataset;
		SelectChoices choices;

		choices = SelectChoices.from(TaskType.class, task.getTaskType());

		dataset = super.unbindObject(task, "taskType", "description", "priority", "estimatedDuration", "draftMode", "technician");
		dataset.put("types", choices);

		super.getResponse().addData(dataset);
	}

}
