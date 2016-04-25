package controllers;

import java.util.List;

import models.Espaconave;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Espaconaves extends Controller {
	
private final Form<Espaconave> formEspaconave = Form.form(Espaconave.class);
	
	public Result lista()
	{
		List<Espaconave> espaconaves = Espaconave.find.all();
		
		return ok(views.html.espaconaves.espaconaveLista.render(espaconaves));
	}
	
	public Result novoEspaconave()
	{
		return ok(views.html.espaconaves.espaconaveDetalhes.render(formEspaconave, new Long(0)));
	}
	
	public Result detalhes(long id)
	{
		Espaconave espaconave = Espaconave.find.byId(id);

		if (espaconave == null) {
		 return notFound(String.format("Espaconave %s não existe.", id));
		}
		Form<Espaconave> formPreenchido = formEspaconave.fill(espaconave);

		return ok(views.html.espaconaves.espaconaveDetalhes.render(formPreenchido, espaconave.id));
	}
	
	
	public Result salvar(Long id)
	{
		Form<Espaconave> formEnviado = formEspaconave.bindFromRequest();
	
		
		
		Espaconave espaconave = formEnviado.get();
		Espaconave espaconaveOld = Espaconave.find.byId(id);
		if(espaconaveOld != null){
			espaconave.update();
		} else {
			espaconave.save();
		}
		
		
		flash("success", String.format("Espaconave %s salvo.", espaconave));
		return redirect(routes.Espaconaves.lista());
	}
	
	public Result remover(long id)
	{
		Espaconave espaconave = new Espaconave();
		
		if (espaconave == null) {
			 return notFound(String.format("Espaconave %s não existe.", id));
			}
		else
		{
			
			espaconave.find.ref(id).delete();
			flash("success", String.format("Espaconave %s removido", espaconave));
		}
		
		return redirect(routes.Espaconaves.lista());
		
	}

	

}
