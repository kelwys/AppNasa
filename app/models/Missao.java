package models;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import com.avaje.ebean.Model;

import play.data.validation.Constraints.Required;

@Entity
public class Missao extends Model{
	
	@Id
	@GeneratedValue
	public Long id;
	
	@ManyToOne
	public Planeta planeta;
	@ManyToOne
	public Espaconave espaconave;
	
	public Date lancamento;
	public Boolean tripulada;
	public float orcamento;
	

	
	public static Finder<Long, Missao> find = new Finder<Long,Missao>(Missao.class);

}
