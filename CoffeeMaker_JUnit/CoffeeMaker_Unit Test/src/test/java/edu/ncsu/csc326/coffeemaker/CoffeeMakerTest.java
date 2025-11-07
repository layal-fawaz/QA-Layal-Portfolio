package edu.ncsu.csc326.coffeemaker;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

/**
 * Unit tests for CoffeeMaker class using JUnit 4.
 */
public class CoffeeMakerTest {

    private CoffeeMaker coffeeMaker;

    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;
    private Recipe recipe4;

    @Before
    public void setUp() throws RecipeException {
        coffeeMaker = new CoffeeMaker();

        // Recipe 1
        recipe1 = new Recipe();
        recipe1.setName("Coffee");
        recipe1.setAmtChocolate("0");
        recipe1.setAmtCoffee("3");
        recipe1.setAmtMilk("1");
        recipe1.setAmtSugar("1");
        recipe1.setPrice("50");

        // Recipe 2
        recipe2 = new Recipe();
        recipe2.setName("Mocha");
        recipe2.setAmtChocolate("20");
        recipe2.setAmtCoffee("3");
        recipe2.setAmtMilk("1");
        recipe2.setAmtSugar("1");
        recipe2.setPrice("75");

        // Recipe 3
        recipe3 = new Recipe();
        recipe3.setName("Latte");
        recipe3.setAmtChocolate("0");
        recipe3.setAmtCoffee("3");
        recipe3.setAmtMilk("3");
        recipe3.setAmtSugar("1");
        recipe3.setPrice("100");

        // Recipe 4
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
        assertFalse(coffeeMaker.addRecipe(recipe1));
        assertEquals("Coffee", coffeeMaker.getRecipes()[0].getName());
    }

    @Test
    public void testAddMultipleRecipes() {
        assertTrue(coffeeMaker.addRecipe(recipe1));
        assertTrue(coffeeMaker.addRecipe(recipe2));
        assertTrue(coffeeMaker.addRecipe(recipe3));

        assertEquals("Coffee", coffeeMaker.getRecipes()[0].getName());
        assertEquals("Mocha", coffeeMaker.getRecipes()[1].getName());
        assertEquals("Latte", coffeeMaker.getRecipes()[2].getName());
    }

    @Test
    public void testAddRecipe() {
        coffeeMaker.addRecipe(recipe1);
        coffeeMaker.addRecipe(recipe2);
        coffeeMaker.addRecipe(recipe3);
        assertTrue(coffeeMaker.addRecipe(recipe4));
    }

    @Test(expected = NullPointerException.class)
    public void testAddNullRecipe() {
        assertFalse(coffeeMaker.addRecipe(null));
    }

    @Test
    public void testDeleteRecipe() {
        coffeeMaker.addRecipe(recipe3);
        assertEquals("Latte", coffeeMaker.deleteRecipe(0));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testDeleteRecipeWithInvalidIndex() {
        coffeeMaker.addRecipe(recipe3);
        coffeeMaker.deleteRecipe(8);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testDeleteRecipeWithNegativeIndex() {
        coffeeMaker.addRecipe(recipe2);
        coffeeMaker.deleteRecipe(-3);
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

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testEditRecipeInvalidIndex() {
        coffeeMaker.addRecipe(recipe1);
        assertNull(coffeeMaker.editRecipe(5, recipe2));
    }

    @Test
    public void testAddInventory() throws InventoryException {
        coffeeMaker.addInventory("4", "7", "0", "9");
    }

    @Test(expected = InventoryException.class)
    public void testAddInventoryNonNumeric() throws InventoryException {
        coffeeMaker.addInventory("layal", "7", "9", "9");
    }

    @Test
    public void testAddInventoryZeroValues() throws InventoryException {
        coffeeMaker.addInventory("0", "0", "0", "0");
    }

    @Test(expected = InventoryException.class)
    public void testAddInventoryException() throws InventoryException {
        coffeeMaker.addInventory("4", "-1", "asdf", "3");
    }

    @Test(expected = InventoryException.class)
    public void testAddInventoryNegativeCoffee() throws InventoryException {
        coffeeMaker.addInventory("-5", "3", "3", "3");
    }

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
        coffeeMaker.addInventory("10", "10", "0", "10");
        String inventory = coffeeMaker.checkInventory();
        assertTrue(inventory.contains("Coffee: 25"));
        assertTrue(inventory.contains("Milk: 25"));
        assertTrue(inventory.contains("Sugar: 15"));
        assertTrue(inventory.contains("Chocolate: 25"));
    }

    @Test
    public void testMakeCoffeeSuccessful() {
        coffeeMaker.addRecipe(recipe1);
        assertEquals(25, coffeeMaker.makeCoffee(0, 75));
    }

    @Test
    public void testMakeCoffeeRecipeToPurchaseNotFound() {
        assertEquals(100, coffeeMaker.makeCoffee(3, 100));
    }

    @Test
    public void testMakeCoffeeInsufficientPayment() {
        coffeeMaker.addRecipe(recipe4);
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
        assertEquals(-30, result);
    }

    @Test
    public void testMakeCoffeeInsufficientInventory() {
        coffeeMaker.addRecipe(recipe2);
        assertEquals(100, coffeeMaker.makeCoffee(0, 100));
    }

    @Test
    public void testMakeCoffeeExactPayment() {
        coffeeMaker.addRecipe(recipe1);
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
        coffeeMaker.makeCoffee(0, 10);
        String inventoryAfter = coffeeMaker.checkInventory();
        assertEquals(inventoryBefore, inventoryAfter);
    }

    @Test
    public void testAddEditDeleteWorkflow() {
        coffeeMaker.addRecipe(recipe1);
        assertEquals(recipe1.getName(), coffeeMaker.editRecipe(0, recipe2));
        assertEquals(recipe2.getName(), coffeeMaker.deleteRecipe(0));
    }

    @Test
    public void testGetRecipesInitiallyEmpty() {
        Recipe[] recipes = coffeeMaker.getRecipes();
        assertNotNull(recipes);
        for (Recipe r : recipes) {
            assertNull(r);
        }
    }

    @Test
    public void testGetRecipesAfterAdding() {
        coffeeMaker.addRecipe(recipe1);
        coffeeMaker.addRecipe(recipe2);
        Recipe[] recipes = coffeeMaker.getRecipes();
        assertEquals("Coffee", recipes[0].getName());
        assertEquals("Mocha", recipes[1].getName());
    }

}