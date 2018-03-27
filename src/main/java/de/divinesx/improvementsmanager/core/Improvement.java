package de.divinesx.improvementsmanager.core;

import java.util.Calendar;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import de.divinesx.improvementsmanager.core.events.ImprovementCreateEvent;
import de.divinesx.improvementsmanager.core.events.ImprovementEditEvent;
import de.divinesx.improvementsmanager.event.EventManager;
import lombok.Getter;

@MappedSuperclass
@Getter
public abstract class Improvement {

	public enum Type { BUG, NORMAL, WISH }

	public enum Priority {
		LOW(0), MIDDLE(1), MAX(2);

		private int id;

		Priority(int id) { this.id = id; }
		public int getId() { return this.id; }
	}

	@Transient
	protected Type type;
	@Transient
	protected Priority priority;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int id;
	
	protected String name;
	
	@Temporal(TemporalType.TIMESTAMP)
	protected Calendar timestamp = Calendar.getInstance();
	
	public Improvement() {
		this.type = this.getInfo().type();
		this.priority = this.getInfo().priority();
		EventManager.INSTANCE.callEvent(new ImprovementCreateEvent(this));
	}

	public Improvement(String name) {
		this();
		this.name = name;
		EventManager.INSTANCE.callEvent(new ImprovementCreateEvent(this));
	}

	private ImprovementInfo getInfo() { return this.getClass().getAnnotation(ImprovementInfo.class); }

	public void setId(int id) { EventManager.INSTANCE.callEvent(new ImprovementEditEvent(this, this.id, id)); this.id = id; }
	
	public void setName(String name) { EventManager.INSTANCE.callEvent(new ImprovementEditEvent(this, this.name, name)); this.name = name; }
	
	public void setTimestamp(Calendar timestamp) { EventManager.INSTANCE.callEvent(new ImprovementEditEvent(this, this.timestamp, timestamp)); this.timestamp = timestamp; }
	
}
