/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipes.dtos;

import co.edu.uniandes.csw.recipes.entities.IngredientEntity;

/**
 *
 * @author mi.carrascal
 */

public class IngredientDTO {
    
    
    private String name;
    private Long calories;
    private Long id;
    
    public IngredientDTO(IngredientEntity ingredient) {
    this.id = ingredient.getId();
    this.name = ingredient.getName();
    this.id=ingredient.getId();
    
}
    
public IngredientEntity toEntity() {
    IngredientEntity entity = new IngredientEntity();
    entity.setId(this.id);
    entity.setName(this.name);
    entity.setCalories(this.calories);
    	
    return entity;
}
}