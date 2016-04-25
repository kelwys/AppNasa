package controllers;

import java.util.List;

import com.avaje.ebean.annotation.Transactional;

import models.Planeta;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Planetas extends Controller{
	
private final Form<Planeta> formPlaneta = Form.form(Planeta.class);
	
	public Result lista()
	{
		List<Planeta> planetas = Planeta.find.all();
		
		return ok(views.html.planetas.planetaLista.render(planetas));
	}
	
	public Result novoPlaneta()
	{
		return ok(views.html.planetas.planetaDetalhes.render(formPlaneta,new Long(0)));
	}
	
	
	public Result detalhes(long id)
	{
		Planeta planeta = Planeta.find.byId(id);

		if (planeta == null) {
		 return notFound(String.format("Planeta %s não existe.", id));
		}
		System.out.println(planeta);
		Form<Planeta> formPreenchido = formPlaneta.fill(planeta);

		return ok(views.html.planetas.planetaDetalhes.render(formPreenchido,planeta.id));
	}
	
	
	public Result salvar(Long id){

		Form<Planeta> formEnviado = formPlaneta.bindFromRequest();
			
		Planeta planeta = formEnviado.get();
		Planeta planetaOld = Planeta.find.byId(id);
		if(planetaOld != null){
			planeta.update();
		} else {
			planeta.save();
		}
		
		flash("success", String.format("Salvo com sucesso!!!"));
		return redirect(routes.Planetas.lista());
	}
	
	public Result remover(long id){
		
		Planeta planeta = new Planeta();
		
		if (planeta == null) {
			 return notFound(String.format("Planeta %s não existe.", id));
			}
		else
		{
			
			planeta.find.ref(id).delete();
			flash("success", String.format("Planeta %s removido", planeta));
		}
		
		return redirect(routes.Planetas.lista());
		
	}
	

}
