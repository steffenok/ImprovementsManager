package de.divinesx.improvementsmanager.core;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

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
	@Setter
	protected String name;

	protected Improvement() {
		this.type = this.getInfo().type();
		this.priority = this.getInfo().priority();
	}

	protected Improvement(String name) {
		super();
		this.name = name;
	}

	private ImprovementInfo getInfo() { return this.getClass().getAnnotation(ImprovementInfo.class); }

}
