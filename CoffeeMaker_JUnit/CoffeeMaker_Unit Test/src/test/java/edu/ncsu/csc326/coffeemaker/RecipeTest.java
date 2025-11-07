package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeTest {

    private Inventory inv;
    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;
    private Recipe recipe4;

    @Before
    public void setUp() throws RecipeException {

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
    @Test
    public void testSetAmtChocolate()throws RecipeException {
        recipe1.setAmtChocolate("1");
        assertEquals(1,recipe1.getAmtChocolate());

        try {
            recipe1.setAmtChocolate("AmtChocolate");
            fail("Expected RecipeException for non-numeric value");
        } catch (RecipeException e) {
            assertEquals("Units of chocolate must be a positive integer", e.getMessage());
        }

        try {
            recipe1.setAmtChocolate("-20");
            fail("Expected RecipeException for negative value");
        } catch (RecipeException e) {
            assertEquals("Units of chocolate must be a positive integer", e.getMessage());
        }
    }
    @Test
    public void testSetPriceValid() throws RecipeException {
        Recipe recipe = new Recipe();
        recipe.setPrice("50");
        assertEquals(50, recipe.getPrice());
    }

    @Test(expected = RecipeException.class)
    public void testSetPriceNegative() throws RecipeException {
        Recipe recipe = new Recipe();
        recipe.setPrice("-10");
    }

    @Test(expected = RecipeException.class)
    public void testSetPriceNonNumeric() throws RecipeException {
        Recipe recipe = new Recipe();
        recipe.setPrice("abc");
    }

    @Test
    public void testSetPriceZero() throws RecipeException {
        Recipe recipe = new Recipe();
        recipe.setPrice("0");
        assertEquals(0, recipe.getPrice());
    }

    @Test
    public void testSetAmtMilk()throws RecipeException {
        recipe1.setAmtMilk("30");
        assertEquals(30,recipe1.getAmtMilk());

        try {
            recipe1.setAmtMilk("Milk");
            fail("Expected RecipeException for non-numeric value");
        } catch (RecipeException e) {
            assertEquals("Units of milk must be a positive integer", e.getMessage());
        }

        try {
            recipe1.setAmtMilk("-20");
            fail("Expected RecipeException for negative value");
        } catch (RecipeException e) {
            assertEquals("Units of milk must be a positive integer", e.getMessage());
        }
    }


    @Test
    public void testSetAmtCoffee()throws RecipeException {
        recipe1.setAmtCoffee("1");
        assertEquals(1,recipe1.getAmtCoffee());

        try {
            recipe1.setAmtCoffee("coffee");
            fail("Expected RecipeException for non-numeric value");
        } catch (RecipeException e) {
            assertEquals("Units of coffee must be a positive integer", e.getMessage());
        }

        try {
            recipe1.setAmtCoffee("-20");
            fail("Expected RecipeException for negative value");
        } catch (RecipeException e) {
            assertEquals("Units of coffee must be a positive integer", e.getMessage());
        }
    }

    @Test
    public void testSetAmtSugar()throws RecipeException {
        recipe1.setAmtSugar("10");
        assertEquals(10, recipe1.getAmtSugar());

        try {
            recipe1.setAmtSugar("sugar");
            fail("Expected RecipeException for non-numeric value");
        } catch (RecipeException e) {
            assertEquals("Units of sugar must be a positive integer", e.getMessage());
        }

        try {
            recipe1.setAmtSugar("-45");
            fail("Expected RecipeException for negative value");
        } catch (RecipeException e) {
            assertEquals("Units of sugar must be a positive integer", e.getMessage());
        }
    }

    @Test
    public void testToString() throws RecipeException {
        assertEquals("Coffee", recipe1.toString());

        Recipe r1 = new Recipe();
        r1.setName(null);
        String toStr = r1.toString();
        assertTrue("toString should handle null name", toStr == null || toStr.equals(""));
    }



    @Test
    public void testHashCodeSameName() {
        Recipe r1 = new Recipe();
        r1.setName("Coffee");
        Recipe r2 = new Recipe();
        r2.setName("Coffee");
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    public void testHashCodeDifferentName() {
        assertNotEquals(recipe1.getName().hashCode(), recipe2.getName().hashCode());
        assertNotNull(recipe1.getName());
        assertNotNull(recipe2.getName());
        assertNotEquals(recipe1.getName().hashCode(), recipe2.getName().hashCode());
    }

    @Test
    public void testHashCodeNullName() {
        Recipe recipe = new Recipe();
        recipe.setName(null);
        recipe.hashCode();
        assertTrue(true);
    }




    @Test
    public void testEqualsSameObject(){
        assertEquals("Same object should be equal to itself", recipe1, recipe1);
    }


    @Test
    public void testEqualsNull(){
        assertNotEquals("Null shouldn't be equal object", null, recipe1);
    }

    @Test
    public void testEqualsDifferentObject(){
        assertNotEquals("different object shouldn't be equal", recipe1, recipe2);
    }
    @Test
    public void testEqualsThisNameNullOtherNameNotNull() {
        Recipe r1 = new Recipe();
        r1.setName(null);

        Recipe r2 = new Recipe();
        r2.setName("latte");
        assertNotEquals("Recipe with null name should not equal recipe with non-null name", r1, r2);
    }

    @Test
    public void testEqualsSameNames(){
        Recipe r1 = new Recipe();
        r1.setName("Coffee");
        Recipe r2 = new Recipe();
        r2.setName("Coffee");
        assertEquals("Recipes with same name should be equal", r1, r2);
    }

    @Test
    public void testEqualsNameNullOtherNotNull() {
        Recipe r1 = new Recipe();
        r1.setName(null);
        assertNotEquals("Recipe with null name should not equal recipe with non-null name", r1, recipe2);
    }

    @Test
    public void testEqualsDifferentNames(){
        assertNotEquals("Recipes with different names should not be equal", recipe1, recipe3);
    }

    @Test
    public void testEqualsOneNameNull(){
        Recipe r1 = new Recipe();
        r1.setName(null);
        assertNotEquals("Recipe with null name should not equal recipe with non-null name", r1, recipe2);
    }

    @Test
    public void testEqualsBothNamesNull(){
        Recipe r1 = new Recipe();
        r1.setName(null);
        Recipe r2 = new Recipe();
        r2.setName(null);
        assertEquals("Recipes with both names null should be equal", r1, r2);
    }

    @Test
    public void testGetNameNotNull(){
        assertNotNull("getName should not return null", recipe1.getName());
        assertEquals("Coffee", recipe1.getName());
    }

    @Test
    public void testEqualsDifferentClass(){
        Object obj = new Object();
        assertNotEquals("Recipe should not equal object of different class", recipe1, obj);
    }

    @Test
    public void testSetNameNullDoesNotChange() {
        Recipe r = new Recipe();
        r.setName("Coffee");
        r.setName(null);
        assertEquals("Coffee", r.getName());
    }

    @Test
    public void testEqualsAfterSetSameName() {
        Recipe r1 = new Recipe();
        Recipe r2 = r1;
        assertTrue(r1.equals(r2));
    }

}