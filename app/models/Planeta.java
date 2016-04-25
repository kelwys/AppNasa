package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import com.avaje.ebean.Model;

import play.data.validation.Constraints.Required;
import javax.persistence.OneToMany;

@Entity
public class Planeta extends Model{

	@Id	
	@GeneratedValue
	public long id;
	
	@Required
	public String nome;
	@Required
	public String sistema;
	@Required
	public boolean habitavel;
	
	@OneToMany(mappedBy="planeta")
	public List<Missao> missao = new ArrayList<>();
	
	public String toString(){
		return nome;
	}
	
	public static Finder<Long, Planeta> find = new Finder<Long,Planeta>(Planeta.class);
}
