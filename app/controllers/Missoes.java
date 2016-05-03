package controllers;


import java.util.List;

import models.Espaconave;
import models.Missao;
import models.Planeta;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Missoes extends Controller {
	
	private final Form<Missao> formMissao = Form.form(Missao.class);
	List<Planeta> planeta = Planeta.find.all();
	public Result lista()
	{
		List<Missao> missoes = Missao.find.all();
		
		return ok(views.html.missoes.missaoLista.render(missoes));
	}
	
	public Result novoMissao()
	{
		List<Espaconave> espaconaves = Espaconave.find.all();
		List<Planeta> planetas = Planeta.find.all();
		
		
		return ok(views.html.missoes.missaoDetalhes.render(formMissao, new Long(0),espaconaves,planetas));
	}
	
	public Result detalhes(long id)
	{
		Missao missao = Missao.find.byId(id);

		if (missao == null) {
		 return notFound(String.format("Missao %s não existe.", id));
		}
		Form<Missao> formPreenchido = formMissao.fill(missao);
		List<Espaconave> espaconaves = Espaconave.find.all();
		List<Planeta> planetas = Planeta.find.all();

		return ok(views.html.missoes.missaoDetalhes.render(formPreenchido, missao.id,espaconaves,planetas));
	}
	
	
	public Result salvar(Long id)
	{
		Form<Missao> formEnviado = formMissao.bindFromRequest();
	
		
		Missao missao = formEnviado.get();
		Missao missaoOld = Missao.find.byId(id);
		if(missaoOld != null){
			missao.update();
		} else{
			missao.save();
		}
		
		
		flash("success", String.format("Missao %s salvo.", missao));
		return redirect(routes.Missoes.lista());
	}
	
	public Result remover(long id){
		
		Missao missao = new Missao();
		
		if (missao == null) {
			 return notFound(String.format("Missao %s não existe.", id));
		} else{
			
			missao.find.ref(id).delete();
			flash("success", String.format("Missao %s removido", missao));
		}
		
		return redirect(routes.Missoes.lista());
		
	}

}
