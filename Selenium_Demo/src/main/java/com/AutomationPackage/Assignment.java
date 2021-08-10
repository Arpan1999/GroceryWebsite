package com.AutomationPackage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;
import io.github.bonigarcia.wdm.WebDriverManager;
public class Assignment {

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/project";

	 
	   static final String USER = "root";
	   static final String PASS = "root";
	public static void main(String[] args) throws InterruptedException {
		
		String lastpage_validation = "Thank you, your order has been placed successfully\n" + "You'll be redirected to Home page shortly!!";
				
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/#/practice-project");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Arpan Dey");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("arpan.dey@highradius.com");
		driver.findElement(By.xpath("//button[@id='form-submit']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@href='https://rahulshettyacademy.com/seleniumPractise/#/\']")).click();
		List<String> str = new ArrayList<String>();
		List<WebElement> lst = driver.findElements(By.xpath("//*[@class='products']/div"));
		
		for(int i =1;i<=lst.size();i++)
		{
			//System.out.println(i);
			WebElement lst1 = driver.findElement(By.xpath("//div[@class='product']["+i+"]/h4[@class='product-name']"));
			WebElement lst2 = driver.findElement(By.xpath("//div[@class='product']["+i+"]/p[@class='product-price']"));
			//System.out.println(lst1.getText().toString()+" "+ lst2.getText().toString());
			String s = lst1.getText().toString()+" "+ lst2.getText().toString();
			str.add(s);
		   
		 }
		
		Connection conn = null;
		Statement stmt = null;
		try{

		      Class.forName("com.mysql.cj.jdbc.Driver");
              conn = DriverManager.getConnection(DB_URL,USER,PASS);
		      String sql;
		      sql = "INSERT INTO vegetables(pk_id,vegetable_name,price,weight) VALUES (?,?,?,?)";
		      PreparedStatement statement = conn.prepareStatement(sql);
		      int c=0;
		for(int i =0;i<str.size();i++)
	    {
	    Pojo studObj= new Pojo();
		Pattern pattern1 = Pattern.compile("(\\w*)");
        Matcher macher1 = pattern1.matcher(str.get(i));
        if(macher1.find())
        {
        	
        
        	studObj.setvegetable_name(macher1.group(1));
        }
       Pattern pattern2 = Pattern.compile(" (\\d*)\\/?(\\d*\\sKg)");
        
       
      
        Matcher macher2 = pattern2.matcher(str.get(i));
        if(macher2.find())
        {
        	
        
        	studObj.setweight(macher2.group(1) + macher2.group(2));
        }
        Pattern pattern3 = Pattern.compile("Kg\\s(\\d*)");
        
        
        
        Matcher macher3 = pattern3.matcher(str.get(i));
        if(macher3.find())
        {
        	
        
        	studObj.setprice(macher3.group(1));
        }
        statement.setInt(1,++c);
    	statement.setString(2, studObj.getvegetable_name());
      	statement.setString(3, studObj.getprice());
      	statement.setString(4, studObj.getweight());
      	statement.addBatch();
      	statement.executeBatch();
      	
        
	    }
		
		 }catch(SQLException se){
		      
		      se.printStackTrace();
		   }catch(Exception e){
		     
		      e.printStackTrace();
		   }finally{
		      	      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se) {
		         se.printStackTrace();
		      }
		   }
		
	    ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//input[@class='search-keyword']")).sendKeys("Brocolli");
	    Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='search-button']")).click();
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div/div[2]/a[2]")).click();
		WebElement name_and_weight = driver.findElement(By.xpath("//div[@class='product']/h4[@class='product-name']"));
		WebElement price = driver.findElement(By.xpath("//div[@class='product']/p[@class='product-price']"));
		WebElement Quantity = driver.findElement(By.xpath("//div[@class='stepper-input']/input[@class = 'quantity']"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div/div[3]/button")).click();
		driver.findElement(By.xpath("//a[@class='cart-icon']")).click();
		WebElement name_and_weight1 = driver.findElement(By.xpath("//p[@class='product-name']"));
		WebElement price1 = driver.findElement(By.xpath("//p[@class='amount']"));
		String nm_we = name_and_weight.getText().toString();
		String p = price.getText().toString();
		String q = Quantity.getAttribute("value");
		System.out.println("Item Pickup Summary");
		Pattern pattern4 = Pattern.compile("(\\w*)");
        Matcher macher4 = pattern4.matcher(nm_we);
        if(macher4.find())
        {
        	System.out.println("Cart Item name:"+" "+ macher4.group(1)); 
        }
       Pattern pattern5 = Pattern.compile(" (\\d*)\\/?(\\d*\\sKg)");
       Matcher macher5 = pattern5.matcher(nm_we);
        if(macher5.find())
        {
        	System.out.println("Weight"+" "+ macher5.group(1) + macher5.group(2));
        }
        int pp = Integer.parseInt(p);
        System.out.println("Price for 1 item" + " "+ pp);
		int qq = Integer.parseInt(q);
		System.out.println("Number of items added" + " "+ qq);
		int total = (pp*qq);
		System.out.println("Total Price "+" "+total);
		String nm_we1 = name_and_weight1.getText().toString();
		String p1 = price1.getText().toString();
		System.out.println("Cart Summary");
		Pattern pattern6 = Pattern.compile("(\\w*)");
        Matcher macher6 = pattern6.matcher(nm_we1);
        if(macher6.find())
        {
        	System.out.println("Cart Item name:"+" "+ macher6.group(1)); 
        
        }
       Pattern pattern7 = Pattern.compile(" (\\d*)\\/?(\\d*\\sKg)");
       Matcher macher7 = pattern7.matcher(nm_we1);
        if(macher7.find())
        {
        System.out.println("Weight"+" "+ macher7.group(1) + macher7.group(2));
        }
        
		int pp1 = Integer.parseInt(p1);
		
		if(total == pp1) 
		{
			System.out.println("Cart Price"+" "+ pp1);
			
			System.out.println("Validated sucessfully.");
			driver.findElement(By.xpath("//*[@id=\"root\"]/div/header/div/div[3]/div[2]/div[2]/button")).click();
		}
		else
		{
			System.out.println("Price not Validated");
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/button")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/select")).sendKeys("India");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@class='chkAgree']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/button")).click();
		WebElement lastpage = driver.findElement(By.xpath("//div[@class='wrapperTwo']"));
		String lastpage_text = lastpage.getText().toString();
		if(lastpage_text.compareTo(lastpage_validation) == 0)
		{
			System.out.println("Last page message is given bellow:");
		    System.out.println(lastpage_text);
		    System.out.println("Last page message validated sucessfully.");
		}
	}

}