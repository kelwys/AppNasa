package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.avaje.ebean.Model;
import play.data.validation.Constraints.Required;

@Entity
public class Espaconave extends Model{
	
	@Id	
	@GeneratedValue
	public long id;
	
	@Required
	public String modelo;
	@Required
	public String pais;
	
	@OneToMany(mappedBy="espaconave")
	public List<Missao> missao = new ArrayList<>();
	
	public String toString(){
		return modelo;
	}
	
	public static Finder<Long, Espaconave> find = new Finder<Long,Espaconave>(Espaconave.class);

}
