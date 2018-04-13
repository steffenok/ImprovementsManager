package de.divinesx.improvementsmanager.core;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import de.divinesx.improvementsmanager.core.events.ImprovementCreateEvent;
import de.divinesx.improvementsmanager.core.events.ImprovementEditEvent;
import de.divinesx.improvementsmanager.core.manager.ImprovementManager;
import de.divinesx.improvementsmanager.event.EventManager;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.var;

@MappedSuperclass
@Getter @ToString @EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class Improvement {

	public enum Type { BUG, NORMAL, WISH }

	public enum Priority {
		LOW(0), MIDDLE(1), MAX(2);

		private int id;

		Priority(int id) { this.id = id; }
		public int getId() { return this.id; }
	}

	@Transient Type type;
	@Transient Priority priority;
	@Setter @Transient Image displayImage;
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id int id;
	
	String name;
	
	@Temporal(TemporalType.TIMESTAMP)
	Calendar timestamp = Calendar.getInstance();
	
	@Getter(value = AccessLevel.PRIVATE)
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
	
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

	public Improvement setId(int id) { EventManager.INSTANCE.callEvent(new ImprovementEditEvent(this, this.id, id)); this.id = id; return this; }
	
	public Improvement setName(String name) { EventManager.INSTANCE.callEvent(new ImprovementEditEvent(this, this.name, name)); this.name = name; return this; }
	
	public Improvement setTimestamp(Calendar timestamp) { EventManager.INSTANCE.callEvent(new ImprovementEditEvent(this, this.timestamp, timestamp)); this.timestamp = timestamp; return this; }
	
	public ImageView getImageView() {
		ImageView imageView = new ImageView(this.displayImage);
		imageView.setFitWidth(25);
		imageView.setFitHeight(25);
		return imageView;
	}
	
	public Text[] getInfos() {
		var infos = new ArrayList<Text>();
		
		infos.add(TextBuilder.create().text(this.name).font(Font.font("Verdana", FontWeight.BOLD, 13)).build());
		if (ImprovementManager.INSTANCE.isShowId()) infos.add(TextBuilder.create().text(String.valueOf(this.id)).build());
		if (ImprovementManager.INSTANCE.isShowDate()) infos.add(TextBuilder.create().text(String.valueOf(this.dateFormatter.format(this.timestamp.getTime()))).build());
		if (ImprovementManager.INSTANCE.isShowPriority()) infos.add(TextBuilder.create().text(String.valueOf(this.priority.toString())).build());
		
		return infos.toArray(new Text[infos.size()]);
	}
	
}
