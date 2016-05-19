package controllers;


import play.mvc.Controller;
import play.mvc.Result;
import org.json.JSONObject;

import models.Planeta;

import java.util.List;

import org.json.JSONArray;


public class API extends Controller{
	
	public Result getPlanetas(){
		JSONObject jso = new JSONObject();
		
		List <Planeta> planetas = Planeta.find.all();
		JSONArray jsa = new JSONArray();
		
		for(Planeta planeta : planetas){
			JSONObject jsoPlaneta = new JSONObject();
			jsoPlaneta.put("nome", planeta.nome);
			jsoPlaneta.put("sistema", planeta.sistema);
			jsoPlaneta.put("habitavel", planeta.habitavel);
			
			jsa.put(jsoPlaneta); // Add jsoPlaneta para vetor jsa
		}
		jso.put("planetas", jsa); //Atribui a propriedade planetas ao vetor jsa
		return ok(jso.toString()); //Retorna na String do jso
	}
	
	public Result getEspaconaves(){
		return TODO;
	}
	
	public Result getMissoes(){
		return TODO;
	}
	

}
