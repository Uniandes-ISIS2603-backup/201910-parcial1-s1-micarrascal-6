/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipes.test.logic;

import co.edu.uniandes.csw.recipes.ejb.RecipeLogic;
import co.edu.uniandes.csw.recipes.entities.RecipeEntity;
import co.edu.uniandes.csw.recipes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.recipes.persistence.RecipePersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author mi.carrascal
 */



@RunWith(Arquillian.class)
public class RecipeLogicTest {
    
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private RecipeLogic recipeLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<RecipeEntity> data = new ArrayList<RecipeEntity>();

   ;
    
    
     /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RecipeEntity.class.getPackage())
                .addPackage(RecipeLogic.class.getPackage())
                .addPackage(RecipePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test
    public void createRecipeTest() throws BusinessLogicException
    {
        RecipeEntity newEntity = factory.manufacturePojo(RecipeEntity.class);
        RecipeEntity recipe=recipeLogic.createRecipe(newEntity);
        Assert.assertNotNull(recipe);
        RecipeEntity entity=em.find(RecipeEntity.class,recipe.getId());
    }
    
    
     @Test(expected = BusinessLogicException.class)
    public void createRecipeConDescripcionInvalidaVaciaTest() throws BusinessLogicException
    {
        RecipeEntity newEntity = factory.manufacturePojo(RecipeEntity.class);
        newEntity.setDescription("");
        RecipeEntity recipe=recipeLogic.createRecipe(newEntity);
        
    }
    
     @Test(expected = BusinessLogicException.class)
    public void createRecipeConDescripcionInvalidaNullTest() throws BusinessLogicException
    {
        RecipeEntity newEntity = factory.manufacturePojo(RecipeEntity.class);
        newEntity.setDescription(null);
        RecipeEntity recipe=recipeLogic.createRecipe(newEntity);
        
    }
    
     @Test(expected = BusinessLogicException.class)
    public void createRecipeConDescripcionInvalidaLargaTest() throws BusinessLogicException
    {
        RecipeEntity newEntity = factory.manufacturePojo(RecipeEntity.class);
        newEntity.setDescription("jksjksdjklsdjklsfdjklsdjklsdajklsfdajklsfdajklsfdajklsfdajklsfdajklsfda");
        RecipeEntity recipe=recipeLogic.createRecipe(newEntity);
        
    }
    
    
    
     @Test(expected = BusinessLogicException.class)
    public void createRecipeConMismoNombreTest() throws BusinessLogicException
    {
        RecipeEntity newEntity = factory.manufacturePojo(RecipeEntity.class);
        newEntity.setName("Arroz");
        RecipeEntity recipe=recipeLogic.createRecipe(newEntity);
        RecipeEntity newEntity2 = factory.manufacturePojo(RecipeEntity.class);
        newEntity2.setName("Arroz");
        RecipeEntity recipe2=recipeLogic.createRecipe(newEntity2);
        
    }
    
    
}
