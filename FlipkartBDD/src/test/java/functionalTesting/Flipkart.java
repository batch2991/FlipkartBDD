package functionalTesting;

import java.util.List;
import java.util.stream.Collectors;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.ProductsListPage;
import pages.SingleProductPage;

public class Flipkart 
{
	HomePage homepage=new HomePage();
	ProductsListPage productslistpage=new ProductsListPage();
	SingleProductPage singleproductpage=new SingleProductPage();
	String valuetosearch="mobiles";
	String first[],second[];
	
	@Given("^user is on flipkart home page$")
    public void user_is_on_flipkart_home_page() throws Throwable 
	{
       homepage.setUp("firefox");
       homepage.openUrl("http://www.flipkart.com");
       homepage.closeLogin();
    }

    @When("^enter any product name and search$")
    public void enter_any_product_name_and_search() throws Throwable 
    {
       homepage.search(valuetosearch);
    }

    @Then("^title and heading should have the product name$")
    public void title_and_heading_should_have_the_product_name() throws Throwable 
    {
       String title=productslistpage.getTitle();
       String heading=productslistpage.getHeading();
       if(title.contains(valuetosearch) && heading.contains(valuetosearch))
       {
    	   System.out.println("Title and heading are same as expected");
       }
       else
       {
    	   homepage.takeScreenshot("VerifyTitleheading.png");
    	   System.out.println("Title and heading are not same as expected");
       }
       homepage.tearDown();
    }
    
    @And("^click prices low to high$")
    public void click_prices_low_to_high() throws Throwable 
    {
        productslistpage.clickLowToHigh();
    }
    
    @Then("^all the products are sorted on price$")
    public void all_the_products_are_sorted_on_price() throws Throwable 
    {
       List<Integer> actprices= productslistpage.getPrices();
       List<Integer> expprices=actprices.stream().sorted().collect(Collectors.toList());
       if(actprices.equals(expprices))
       {
    	   System.out.println("All prices are in sorting order as expected");
       }
       else
       {
    	   System.out.println("All prices are not in sorting order as expected ");
    	   System.out.println("Expected prices  :"+expprices);
    	   System.out.println("Actual prices  :"+actprices);
       }
       homepage.tearDown();
    }
    
    @And("^click on any one of the product(.+)$")
    public void click_on_any_one_of_the_product(String index) throws Throwable 
    {
        first=productslistpage.clickOneProduct(Integer.parseInt(index));
        
        
    }
    @Then("^name of the product and price in both page are same$")
    public void name_of_the_product_and_price_in_both_page_are_same() throws Throwable 
    {
       second=singleproductpage.getProductdetails();
       if(second[0].contains(first[0]) && first[1].matches(second[1]) )
       {
    	   System.out.println("Product name and price in both pages are same");
       }
       else
       {
    	   System.out.println("product name and price in both pages are not same");
       }
       homepage.tearDown();
    }

   

   
    
}
