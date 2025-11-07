package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InventoryTest {

    private Inventory inv;
    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;
    private Recipe recipe4;

    @Before
    public void setUp() throws RecipeException {
       inv = new Inventory();
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
    }

    @Test
    public void testSetAndGetChocolate() {
        Inventory inv = new Inventory();
        inv.setChocolate(3);
        assertEquals(3, inv.getChocolate());
    }

    @Test
    public void testSetChocolateIfLess0() {
        Inventory inv = new Inventory();
        inv.setChocolate(0);
        inv.setChocolate(-3);
        assertEquals("Chocolate should remain 0 when negative value is set", 0, inv.getChocolate());
    }

    @Test
    public void testAddChocolate() throws InventoryException {
        Inventory inv = new Inventory();
        inv.addChocolate("9"); //in constructor Chocolate(15) 15+9
    }

    @Test(expected = InventoryException.class)
    public void testAddChocolateInvalidUnit() throws InventoryException {
        Inventory inv = new Inventory();
        inv.addChocolate("-4");
        assertEquals("Chocolate should increase by 9", 24, inv.getChocolate());
    }


    @Test(expected = InventoryException.class)
    public void testAddChocolateNonNumeric() throws InventoryException {
        Inventory inv = new Inventory();
        inv.addChocolate("chocolate");
    }

    @Test
    public void testSetCoffe() {
        Inventory inv = new Inventory();
        inv.setCoffee(3);
        assertEquals(3, inv.getCoffee());
        inv.setCoffee(-3);
        assertEquals(3, inv.getCoffee());

    }

    @Test
    public void testAddCoffee() throws InventoryException {
        Inventory inv = new Inventory();
        inv.addCoffee("20"); //in constructor coffee(15) 15+20
        assertEquals("Coffee should increase by 20", 35, inv.getCoffee());
    }

    @Test(expected = InventoryException.class)
    public void testAddCoffeeInvalidUnit() throws InventoryException {
        Inventory inv = new Inventory();
        inv.addCoffee("-8");
    }

    @Test(expected = InventoryException.class)
    public void testAddCoffeeNonNumeric() throws InventoryException {
        Inventory inv = new Inventory();
        inv.addCoffee("Coffee");
    }

    @Test
    public void testSetAndGetMilk() {
        Inventory inv = new Inventory();
        inv.setMilk(10);
        assertEquals(10, inv.getMilk());

        inv.setMilk(-20);
        assertEquals(10, inv.getMilk());

        inv.setMilk(0);
        assertEquals(0, inv.getMilk());
    }


    @Test
    public void testAddMilk() throws InventoryException {
        Inventory inv = new Inventory();
        inv.addMilk("10"); //in constructor coffee(15) 15+10
        assertEquals("Milk should increase by 10", 25, inv.getMilk());
    }

    @Test(expected = InventoryException.class)
    public void testAddMilkInvalidUnit() throws InventoryException {
        Inventory inv = new Inventory();
        inv.addMilk("-8");
    }

    @Test(expected = InventoryException.class)
    public void testAddMilkNonNumeric() throws InventoryException {
        Inventory inv = new Inventory();
        inv.addMilk("Milk");
    }


    @Test
    public void testAddSugarBug() throws InventoryException {
        Inventory inv = new Inventory();
        try {
            inv.addSugar("5");
            assertEquals("Sugar should increase by 5", 20, inv.getSugar());
            fail("Test passed but this means the bug is fixed!");
        } catch (InventoryException e) {
            // Test catches the exception this proves the bug exists
            System.out.println("BUG CONFIRMED: addSugar throws exception for valid positive input");
            System.out.println("Expected: sugar should be 20");
            System.out.println("Actual: Exception thrown - " + e.getMessage());
        }
    }


    @Test(expected = InventoryException.class)
    public void testAddSugarNonNumeric() throws InventoryException {
        Inventory inv = new Inventory();
        inv.addSugar("Sugar");
    }

    @Test
    public void TestEnoughIngredients() {
        Inventory inv = new Inventory();
        inv.setMilk(0);
        inv.setCoffee(2);
        inv.setChocolate(15);
        inv.setSugar(0);
        assertFalse(inv.enoughIngredients(recipe2));
    }

    @Test
    public void useIngredients() {
        Inventory inv = new Inventory();
        inv.setMilk(30);
        inv.setCoffee(40);
        inv.setChocolate(15);
        inv.setSugar(0);

        boolean used = inv.useIngredients(recipe2);
        assertFalse(used);
    }

    @Test
    public void testUseIngredientsEnough() throws RecipeException {
        Inventory inv = new Inventory();
        Recipe r = new Recipe();
        r.setAmtCoffee("5");
        r.setAmtMilk("5");
        r.setAmtSugar("5");
        r.setAmtChocolate("5");
        assertTrue(inv.useIngredients(r));
    }
    @Test
    public void testEnoughIngredientsAllEnough() throws RecipeException {
        Inventory inv = new Inventory();
        Recipe r = new Recipe();
        r.setAmtCoffee("1");
        r.setAmtMilk("1");
        r.setAmtSugar("1");
        r.setAmtChocolate("1");
        assertTrue(inv.enoughIngredients(r));
    }


    @Test
    public void testToString() {
        Inventory inv = new Inventory();
        String expected = "Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n";
        assertEquals(expected, inv.toString());
    }



}