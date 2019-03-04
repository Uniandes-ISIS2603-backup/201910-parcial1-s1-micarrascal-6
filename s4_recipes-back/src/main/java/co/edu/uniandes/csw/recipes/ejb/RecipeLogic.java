/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipes.ejb;

import co.edu.uniandes.csw.recipes.entities.RecipeEntity;
import co.edu.uniandes.csw.recipes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.recipes.persistence.RecipePersistence;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author CesarF
 */
@Stateless
public class RecipeLogic {
    @Inject
    private RecipePersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    public RecipeEntity getRecipe(Long id) {
        return persistence.find(id);
    }

    /**
     * Crear una nueva receta
     *
     * @param recipeEntity La entidad de tipo libro del nuevo libro a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si el ISBN es inválido o ya existe en la
     * persistencia.
     */

   public RecipeEntity createRecipe(RecipeEntity recipeEntity) throws BusinessLogicException
   {
       if(recipeEntity.getName()==null || recipeEntity.getName().equals("") || recipeEntity.getName().length()>30)
       {
           throw new BusinessLogicException("El nombre de la receta no es valido");
           
       }
       
       if(persistence.find(recipeEntity.getId())!=null)
       {
           throw new BusinessLogicException("Ya existe una receta con ese nombre");
           
       }
       if(recipeEntity.getDescription()==null || recipeEntity.getDescription().equals("") || recipeEntity.getDescription().length()>150)
       {
           throw new BusinessLogicException("La descripción de la receta no es válida");
       }
       
       persistence.createRecipe(recipeEntity);
      
       return recipeEntity;
   }
}
