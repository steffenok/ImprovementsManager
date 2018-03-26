package de.divinesx.improvementsmanager.core;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
public abstract class Improvement implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public enum Type { BUG, NORMAL, WISH }
	
	public enum Priority {
		LOW( 0 ), MIDDLE( 1 ), MAX( 2 );
		
		private int id;
		
		Priority( int id ) { this.id = id; }
		public int getId() { return this.id; }
	}
	
	protected Type     type;
	protected Priority priority;
	protected String   tableName;
	
	@Id
	@Column(name = "id")
	protected   int  id;
	@Column(name = "name")
	@Setter
	protected String name;
	
	protected Improvement() {
		this.type = this.getInfo().type();
		this.priority = this.getInfo().priority();
		this.tableName = this.getInfo().tableName();
	}
	
	protected Improvement( String name) { super(); this.name = name; }
	
	private ImprovementInfo getInfo() { return this.getClass().getAnnotation( ImprovementInfo.class ); }
	
}
