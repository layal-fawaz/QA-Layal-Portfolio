/*
 * Copyright (c) 2009,  Sarah Heckman, Laurie Williams, Dright Ho
 * All Rights Reserved.
 *
 * Permission has been explicitly granted to the University of Minnesota
 * Software Engineering Center to use and distribute this source for
 * educational purposes, including delivering online education through
 * Coursera or other entities.
 *
 * No warranty is given regarding this software, including warranties as
 * to the correctness or completeness of this software, including
 * fitness for purpose.
 *
 *
 * Modifications
 * 20171114 - Ian De Silva - Updated to comply with JUnit 4 and to adhere to
 * 							 coding standards.  Added test documentation.
 */
package edu.ncsu.csc326.coffeemaker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

/**
 * Unit tests for CoffeeMaker class.
 *
 * @author Sarah Heckman
 */
public class CoffeeMakerTest {

    /**
     * The object under test.
     */
    private CoffeeMaker coffeeMaker;

    // Sample recipes to use in testing.
    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;
    private Recipe recipe4;

    /**
     * Initializes some recipes to test with and the {@link CoffeeMaker}
     * object we wish to test.
     *
     * @throws RecipeException  if there was an error parsing the ingredient
     * 		amount when setting up the recipe.
     */
    @BeforeEach
    public void setUp() throws RecipeException {
        coffeeMaker = new CoffeeMaker();

        //Set up for r1
        recipe1 = new Recipe();
        recipe1.setName("Coffee");
        recipe1.setAmtChocolate("0");
        recipe1.setAmtCoffee("3");
        recipe1.setAmtMilk("1");
        recipe1.setAmtSugar("1");
        recipe1.setPrice("50");

        //Set up for r2
        recipe2 = new Recipe();
        recipe2.setName("Mocha");
        recipe2.setAmtChocolate("20");
        recipe2.setAmtCoffee("3");
        recipe2.setAmtMilk("1");
        recipe2.setAmtSugar("1");
        recipe2.setPrice("75");

        //Set up for r3
        recipe3 = new Recipe();
        recipe3.setName("Latte");
        recipe3.setAmtChocolate("0");
        recipe3.setAmtCoffee("3");
        recipe3.setAmtMilk("3");
        recipe3.setAmtSugar("1");
        recipe3.setPrice("100");

        //Set up for r4
        recipe4 = new Recipe();
        recipe4.setName("Hot Chocolate");
        recipe4.setAmtCoffee("0");
        recipe4.setAmtMilk("1");
        recipe4.setAmtSugar("1");
        recipe4.setPrice("65");
    }

    // ------- ADD RECIPE TESTS ---------

    @Test
    public void testAddRecipeToEmptyRecipeBook() {
        assertTrue(coffeeMaker.addRecipe(recipe1));
    }

    @Test
    public void testAddRecipeCheckItExists() {
        coffeeMaker.addRecipe(recipe1);
        assertEquals("Coffee", coffeeMaker.getRecipes()[0].getName());

        // Try to add same recipe again - should return false
        assertFalse(coffeeMaker.addRecipe(recipe1));
        assertEquals("Coffee", coffeeMaker.getRecipes()[0].getName());
    }

    @Test
    public void testAddMultipleRecipes() {
        assertTrue(coffeeMaker.addRecipe(recipe1));
        assertTrue(coffeeMaker.addRecipe(recipe2));
        assertTrue(coffeeMaker.addRecipe(recipe3));

        // Check recipes are added in order
        assertEquals("Coffee", coffeeMaker.getRecipes()[0].getName());
        assertEquals("Mocha", coffeeMaker.getRecipes()[1].getName());
        assertEquals("Latte", coffeeMaker.getRecipes()[2].getName());
    }

    @Test
    public void testAddRecipeWhenRecipeBookFull() {
        coffeeMaker.addRecipe(recipe1);
        coffeeMaker.addRecipe(recipe2);
        coffeeMaker.addRecipe(recipe3);

        // Recipe book is full (max 3 recipes)
        assertFalse(coffeeMaker.addRecipe(recipe4));
    }

    @Test
    public void testAddNullRecipe() {
        assertFalse(coffeeMaker.addRecipe(null));
    }


    @Test
    public void testDeleteRecipe() {
        coffeeMaker.addRecipe(recipe3);
        assertEquals("Latte", coffeeMaker.deleteRecipe(0));
    }

    @Test
    public void testDeleteRecipeWithInvalidIndex() {
        coffeeMaker.addRecipe(recipe3);
        assertNull(coffeeMaker.deleteRecipe(8));
    }

    // ------- DELETE RECIPE TESTS ---------

    @Test
    public void testDeleteRecipeWithNegativeIndex() {
        coffeeMaker.addRecipe(recipe2);
        assertNull(coffeeMaker.deleteRecipe(-3));
    }

    @Test
    public void testDeleteRecipeEmptySlot() {
        assertNull(coffeeMaker.deleteRecipe(0));
    }

    @Test
    public void testDeleteRecipeThenAddNew() {
        coffeeMaker.addRecipe(recipe1);
        coffeeMaker.deleteRecipe(0);
        assertTrue(coffeeMaker.addRecipe(recipe2));
    }

    // ------- EDIT RECIPE TESTS ---------

    @Test
    public void testEditRecipe() {
        coffeeMaker.addRecipe(recipe1);
        Recipe newRecipe = new Recipe();
        try {
            newRecipe.setName("Espresso");
            newRecipe.setAmtChocolate("0");
            newRecipe.setAmtCoffee("5");
            newRecipe.setAmtMilk("0");
            newRecipe.setAmtSugar("0");
            newRecipe.setPrice("55");
        } catch (RecipeException e) {
            fail("Recipe setup failed");
        }
        assertEquals("Coffee", coffeeMaker.editRecipe(0, newRecipe));
    }

    @Test
    public void testEditRecipeEmptySlot() {
        assertNull(coffeeMaker.editRecipe(0, recipe1));
    }

    @Test
    public void testEditRecipeInvalidIndex() {
        coffeeMaker.addRecipe(recipe1);
        assertNull(coffeeMaker.editRecipe(5, recipe2));
    }
    // -------  ADD INVENTORY TESTS ---------

    /**
     * Given a coffee maker with the default inventory
     * When we add inventory with well-formed quantities
     * Then we do not get an exception trying to read the inventory quantities.
     *
     * @throws InventoryException  if there was an error parsing the quantity
     * 		to a positive integer.
     */
    @Test
    public void testAddInventory() throws InventoryException {
        coffeeMaker.addInventory("4", "7", "0", "9");
    }

    @Test
    public void testAddInventoryNonNumeric() {
        assertThrows(InventoryException.class, () -> {
            coffeeMaker.addInventory("layal", "7", "9", "9");
        });
    }

    @Test
    public void testAddInventoryZeroValues() throws InventoryException {
        coffeeMaker.addInventory("0", "0", "0", "0");
    }

    /**
     * Given a coffee maker with the default inventory
     * When we add inventory with malformed quantities (i.e., a negative
     * quantity and a non-numeric string)
     * Then we get an inventory exception
     *
     * @throws InventoryException  if there was an error parsing the quantity
     * 		to a positive integer.
     */
    @Test
    public void testAddInventoryException() {
        assertThrows(InventoryException.class, () -> {
            coffeeMaker.addInventory("4", "-1", "asdf", "3");
        });
    }

    @Test
    public void testAddInventoryNegativeCoffee() {
        assertThrows(InventoryException.class, () -> {
            coffeeMaker.addInventory("-5", "3", "3", "3");
        });
    }

    // -------  CHECK INVENTORY TESTS ----------

    @Test
    public void testCheckInventory() {
        String inventory = coffeeMaker.checkInventory();
        assertNotNull(inventory);
        assertTrue(inventory.contains("Coffee"));
        assertTrue(inventory.contains("Milk"));
        assertTrue(inventory.contains("Sugar"));
        assertTrue(inventory.contains("Chocolate"));
    }

    @Test
    public void testInventoryAfterAddingIngredients() throws InventoryException {
        coffeeMaker.addInventory("10", "10", "10", "10");
        String inventory = coffeeMaker.checkInventory();
        assertTrue(inventory.contains("25"));
    }

    // ------- MAKE COFFEE TESTS ----------
    /**
     * Given a coffee maker with one valid recipe
     * When we make coffee, selecting the valid recipe and paying more than
     * 		the coffee costs
     * Then we get the correct change back.
     */
    @Test
    public void testMakeCoffeeSuccessful() {
        coffeeMaker.addRecipe(recipe1); // price = 50
        assertEquals(25, coffeeMaker.makeCoffee(0, 75));
    }

    @Test
    public void testMakeCoffeeRecipeToPurchaseNotFound() {
        assertEquals(100, coffeeMaker.makeCoffee(5, 100));
    }

    @Test
    public void testMakeCoffeeInsufficientPayment() {
        coffeeMaker.addRecipe(recipe4); // price = 65
        assertEquals(50, coffeeMaker.makeCoffee(0, 50));
    }

    @Test
    public void testMakeCoffeeWithZeroPayment() {
        coffeeMaker.addRecipe(recipe1);
        assertEquals(0, coffeeMaker.makeCoffee(0, 0));
    }

    @Test
    public void testMakeCoffeeWithNegativePayment() {
        coffeeMaker.addRecipe(recipe2);
        int result = coffeeMaker.makeCoffee(0, -30);
        // Should return the payment amount (even if negative)
        assertEquals(-30, result);
    }

    @Test
    public void testMakeCoffeeInsufficientInventory() {
        coffeeMaker.addRecipe(recipe2); // Mocha needs 20 chocolate, default is 15
        assertEquals(100, coffeeMaker.makeCoffee(0, 100));
    }

    @Test
    public void testMakeCoffeeExactPayment() {
        coffeeMaker.addRecipe(recipe1); // price = 50
        assertEquals(0, coffeeMaker.makeCoffee(0, 50));
    }

    @Test
    public void testMakeCoffeeInventoryDecrements() {
        coffeeMaker.addRecipe(recipe1);
        String inventoryBefore = coffeeMaker.checkInventory();
        coffeeMaker.makeCoffee(0, 100);
        String inventoryAfter = coffeeMaker.checkInventory();
        assertNotEquals(inventoryBefore, inventoryAfter);
    }

    @Test
    public void testMakeCoffeeInventoryUnchangedOnFailure() {
        coffeeMaker.addRecipe(recipe1);
        String inventoryBefore = coffeeMaker.checkInventory();
        coffeeMaker.makeCoffee(0, 10); // Insufficient payment
        String inventoryAfter = coffeeMaker.checkInventory();
        assertEquals(inventoryBefore, inventoryAfter);
    }

    @Test
    public void testMakeCoffeeMultiplePurchases() {
        coffeeMaker.addRecipe(recipe1); // price = 50
        coffeeMaker.addRecipe(recipe4); // price = 65

        assertEquals(50, coffeeMaker.makeCoffee(0, 100));
        assertEquals(35, coffeeMaker.makeCoffee(1, 100));
    }

    // ------- GET RECIPES TESTS  --------

    @Test
    public void testGetRecipesEmpty() {
        Recipe[] recipes = coffeeMaker.getRecipes();
        assertNotNull(recipes);
        assertTrue(recipes.length >= 3);
    }

    @Test
    public void testGetRecipesAfterAdding() {
        coffeeMaker.addRecipe(recipe1);
        Recipe[] recipes = coffeeMaker.getRecipes();
        assertEquals("Coffee", recipes[0].getName());
    }

    // ------- CRITICAL BUG DETECTION TESTS  ---------

    @Test
    public void testStaticVariablesBug() {
        CoffeeMaker cm1 = new CoffeeMaker();
        CoffeeMaker cm2 = new CoffeeMaker();

        cm1.addRecipe(recipe1);

        // cm2 should not have recipes from cm1
        // This test will likely FAIL due to static variables bug
        assertNull(cm2.getRecipes()[0]);
    }

    @Test
    public void testMakeCoffeeArrayIndexBounds() {
        coffeeMaker.addRecipe(recipe1);

        // Test with negative index - should handle gracefully
        int result = coffeeMaker.makeCoffee(-1, 100);
        // If it doesn't throw exception, should return payment
        assertTrue(result >= 0);
    }

    @Test
    public void testDeleteRecipeArrayIndexBounds() {
        coffeeMaker.addRecipe(recipe1);

        // Test with very large index
        String result = coffeeMaker.deleteRecipe(100);
        assertNull(result);
    }

    @Test
    public void testEditRecipeArrayIndexBounds() {
        coffeeMaker.addRecipe(recipe1);

        // Test with negative index
        String result = coffeeMaker.editRecipe(-5, recipe2);
        assertNull(result);
    }


    @Test
    public void testCompleteWorkflow() throws InventoryException {
        // Add recipe
        assertTrue(coffeeMaker.addRecipe(recipe1));

        // Add inventory
        coffeeMaker.addInventory("10", "10", "10", "10");

        // Make coffee
        int change = coffeeMaker.makeCoffee(0, 100);
        assertEquals(50, change);

        // Check inventory changed
        String inventory = coffeeMaker.checkInventory();
        assertNotNull(inventory);
        assertTrue(inventory.contains("Coffee"));
    }

    @Test
    public void testAddEditDeleteWorkflow() {
        coffeeMaker.addRecipe(recipe1);
        assertEquals("Coffee", coffeeMaker.editRecipe(0, recipe2));
        assertEquals("Mocha", coffeeMaker.deleteRecipe(0));
    }
}