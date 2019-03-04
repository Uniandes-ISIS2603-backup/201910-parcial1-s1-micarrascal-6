/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipes.dtos;

import co.edu.uniandes.csw.recipes.entities.IngredientEntity;
import co.edu.uniandes.csw.recipes.entities.RecipeEntity;
import java.util.List;

/**
 *
 * @author CesarF
 */
public class RecipeDetailDTO extends RecipeDTO {
    
    
    private List<IngredientDTO> ingredients;
    
    public RecipeDetailDTO(){
    
    }
    
    public RecipeDetailDTO(RecipeEntity entity){
       
       List<IngredientEntity> lista=entity.getIngredientes();
       for(int i=0;i<lista.size(); i++)
       {
           IngredientDTO ingrediente=new IngredientDTO(lista.get(i));
           ingredients.add(ingrediente);
       }
    }
    
    
    public RecipeEntity toEntity() {
        RecipeEntity entity = new RecipeEntity();
//        entity.setIngredientes(ingredients);
         
  	
    return entity;
}


    /**
     * @return the ingredients
     */
    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    /**
     * @param ingredients the ingredients to set
     */
    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }
    
}
