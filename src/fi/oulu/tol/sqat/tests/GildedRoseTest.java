package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest<testList> {

	ArrayList<Item> testitems;
	
	//We get immediately fucked by the private visibility of the ArrayList<Item>
	//generated in the main() part of the code. I cannot access the items in that
	//list because it is private! Best I can do, I think, is to mimic forming
	//a similar list. (There probably is some clever way to access the list even
	//when it is private, but that is beyond my basic understanding of Java.
	//(I just get errors and depression, mostly.)) So, I guess this is black
	//box testing more or less?
	
	//In an actual workplace environment, I'd imagine there to be a testing team,
	//and I could ask for pointers from the other members over lunch, or during
	//the working day on how to proceed with this problem. The solitary nature of 
	//this lab exercise, however, does not permit that, so my lacking knowledge 
	//of Java is really impairing me here. I do have some IT-field contacts, but
	//I have not reached out to them, nor is my intent to do so.
	
	//So, best I can do: we generate a list, and populate it with copied code from 
	//the main(). Now we should theoretically be able to run some tests with that 
	//list, to somehow see if quality behaves like it should, for example.
	
	//I can't seem to get the list to work on a global level, though. I need to
	//create a local version of it within each testcase. FRUSTRATING!

	//Hmm, now that I've done all I pretty much can do (returned to comment on this
	//after doing everything else in this file), I re-check the assignment and see
	//the assignment to be:
	
	//Write unit tests for UpdateEndOfDay method. Do not fix the errors in the source-code folder.

	//My test class seems to be called GildedRoseTest, should I rename it? Or does this imply
	//That I should have something actually called UpdateEndOfDay() here, which is something that
	//I do not have. Should I rename my testUpdateQualities() to that name? It has that behaviour,
	//so maybe? After hardcoding a bunch of pointers to it, I'd rather not. I mean, it should
	//do what we need it to do, right? It just has a different name.
	
	public ArrayList<Item> testList()
	{		
		//the point of this function is to just create a list with the data, that we can somehow
	    //use in the other testcase, so that I don't have to create the same list all the time
		//but this piece of shit is not being very cooperative.
		
		ArrayList<Item> testitems = new ArrayList<Item>();
		
	    testitems.add((Item) new Item("+5 Dexterity Vest", 10, 20));
	    testitems.add((Item) new Item("Aged Brie", 2, 0));
	    testitems.add((Item) new Item("Elixir of the Mongoose", 5, 7));
	    testitems.add((Item) new Item("Sulfuras, Hand of Ragnaros", 0, 80));
	    testitems.add((Item) new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
	    testitems.add((Item) new Item("Conjured Mana Cake", 3, 6));
		return testitems;
	}
	
	
	//This, more than anything, is to give me some idea on how to fetch data from the list.
	//It's a bad test, since why would these list items be always in this order? A real-life
	//list would probably not.
	@Test
	public void namesRight() 
	{
		ArrayList<Item> testables = testList();
		
		assertEquals("Item 0 name not expected","+5 Dexterity Vest",testables.get(0).getName());	    
		assertEquals("Item 1 name not expected","Aged Brie",testables.get(1).getName());
		assertEquals("Item 2 name not expected","Elixir of the Mongoose",testables.get(2).getName());
		assertEquals("Item 3 name not expected","Sulfuras, Hand of Ragnaros",testables.get(3).getName());
		assertEquals("Item 4 name not expected","Backstage passes to a TAFKAL80ETC concert",testables.get(4).getName());
		assertEquals("Item 5 name not expected","Conjured Mana Cake",testables.get(5).getName());

	}
	
	//More of a placeholder. Basically, it checks that when the list is injected with a new Item,
	//the quality gets set right.
	@Test
	public void qualitiesRight()
	{
		ArrayList<Item> testables = testList();		
		assertEquals("Item 0 quality not expected",20,testables.get(0).getQuality());
	}
	
	//And the same for sellIn
	@Test 
	public void sellInRight()
	{
		ArrayList<Item> testables = testList();
		assertEquals("Item 0 sellIn not expected",10,testables.get(0).getSellIn());
	}
		
	//here we would updateQuality() and then can see if there are changes.
	//As soon as I figure out how to actually do it properly. This one, again,
	//only does it locally, inside this testcase. This isn't what I need, though.
	//Again, a global ArrayList<Item> would be REALLY helpful.
		
	//now streamlined and takes argument for how many iterations it updates it for! Yay!
	public ArrayList<Item> testUpdateQuality(int times)
	{
		//copied the updatecode here (in this function) to update our test list.
		String testspam = "";
		ArrayList<Item> testables = testList();
		//fail("size: "+testables.size()+"."); //returned 6, so at least this works
		
		for (int j = 0; j < times; j++)
		{
		if(j > 0)
		testspam += "\n";
        for (int i = 0; i < testables.size(); i++)
        {
            if ((!"Aged Brie".equals(testables.get(i).getName())) && !"Backstage passes to a TAFKAL80ETC concert".equals(testables.get(i).getName())) 
            {
                if (testables.get(i).getQuality() > 0)
                {
                    if (!"Sulfuras, Hand of Ragnaros".equals(testables.get(i).getName()))
                    {
                    	testables.get(i).setQuality(testables.get(i).getQuality() - 1);
                    	testspam += testables.get(i).getName()+"|"+testables.get(i).getQuality();
                    }
                }
            }
            else
            {
                if (testables.get(i).getQuality() < 50)
                {
                	testables.get(i).setQuality(testables.get(i).getQuality() + 1);
                	testspam += testables.get(i).getName()+"|"+testables.get(i).getQuality();
                    if ("Backstage passes to a TAFKAL80ETC concert".equals(testables.get(i).getName()))
                    {
                        if (testables.get(i).getSellIn() < 11)
                        {
                            if (testables.get(i).getQuality() < 50)
                            {
                            	testables.get(i).setQuality(testables.get(i).getQuality() + 1);
                            	//fail("Updated "+testables.get(i).getName()+".");
                            }
                        }

                        if (testables.get(i).getSellIn() < 6)
                        {
                            if (testables.get(i).getQuality() < 50)
                            {
                            	testables.get(i).setQuality(testables.get(i).getQuality() + 1);
                            }
                        }
                    }
                }
            }

            if (!"Sulfuras, Hand of Ragnaros".equals(testables.get(i).getName()))
            {
            	testables.get(i).setSellIn(testables.get(i).getSellIn() - 1);
            	testspam += "|"+testables.get(i).getSellIn()+". ";
            }

            if (testables.get(i).getSellIn() < 0)
            {
                if (!"Aged Brie".equals(testables.get(i).getName()))
                {
                    if (!"Backstage passes to a TAFKAL80ETC concert".equals(testables.get(i).getName()))
                    {
                        if (testables.get(i).getQuality() > 0)
                        {
                            if (!"Sulfuras, Hand of Ragnaros".equals(testables.get(i).getName()))
                            {
                            	testables.get(i).setQuality(testables.get(i).getQuality() - 1);
                            }
                        }
                    }
                    else
                    {
                    	testables.get(i).setQuality(testables.get(i).getQuality() - testables.get(i).getQuality());
                    }
                }
                else
                {
                    if (testables.get(i).getQuality() < 50)
                    {
                    	testables.get(i).setQuality(testables.get(i).getQuality() + 1);
                    }
                }
            }
        }
		}//end of times loop
     //Okay, now this allows me to dig for some data from the list after update. Things seem to
     //change as expected after one update. As for how they perform after several updates, not yet
     //tested.
    //fail(testspam);
    return testables;
    }

	
	@Test
	public void BrieImproves()
	{
		ArrayList<Item> testables = testUpdateQuality(16);
		//quite clumsily, this test has been hardcoded to check for expected result after
		//16 updates. But you should get the gist of what I am checking here. Hopefully.
	    assertEquals("Brie didn't improve",30,testables.get(1).getQuality());
	}
	
	//A test to check that Sulfuras stays unchanged
	@Test 
	public void sulfurasUnchanged()
	{
     ArrayList<Item> testables = testUpdateQuality(16);	
	 assertEquals("Sulfuras quality changed - shouldn't",80,testables.get(3).getQuality());
	 assertEquals("Sulfuras sellIn changed - shouldn't",0,testables.get(3).getSellIn());
	}

	//Check for qualities < 0 and > 50. Ignore sulfuras.
	@Test
	public void badQualities()
	{
	 ArrayList<Item> testables = testUpdateQuality(16);
	 for(int i = 0; i < testables.size(); i++)
	 {
	  if (testables.get(i).getQuality() < 0)
	  fail(testables.get(i).getName()+" quality below 0, shouldn't be. Bug?");
	     
	  //Sulfuras is ID 3
	  if (testables.get(i).getQuality() > 50 && i != 3)
	  fail(testables.get(i).getName()+" quality over 50, shouldn't be. Bug?");
	  
	 }
	}
	
	//Check for special behaviour of the concert ticket
	@Test
	public void ticketExpiration()
	{
	 ArrayList<Item> testables = testUpdateQuality(16);
	 for(int i = 0; i < testables.size(); i++)
	 {	  
	  if(i == 4)
	  {
	   assertEquals("Ticket Quality unexpected.",0,testables.get(i).getQuality());
	   assertEquals("Ticket SellIn unexpected.",-1,testables.get(i).getSellIn());
	  }
	  //fail("Ticket quality:"+testables.get(i).getQuality()+" sellIn:"+testables.get(i).getSellIn()+".");
      //Quality is 0, sellIn -1
	 }
	 testables = testUpdateQuality(15);
	 for(int i = 0; i < testables.size(); i++)
	 {	  
	  if(i == 4)
	  {
	   //on final expiration day, quality 50	  
	   assertEquals("Ticket Quality unexpected.",50,testables.get(i).getQuality());
	   assertEquals("Ticket SellIn unexpected.",0,testables.get(i).getSellIn());
	  }
	 }
	}	
}
