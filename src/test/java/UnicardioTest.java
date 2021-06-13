
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UnicardioTest {

	@Test
	public void devoAcessarHomePage() {		
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8080");	
		
	}
	
	
	@Test
	public void devoLogarComoPaciente() {		
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8080");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Clickar em login
		driver.findElement(By.id("btn-login")).click();		
		//Escrever CPF
		driver.findElement(By.id("input-cpf")).sendKeys("192");
		//Escrever Senha
		driver.findElement(By.id("input-senha")).sendKeys("123456");
		//Clickar em Entrar
		driver.findElement(By.className("btn-login")).click();			
	}
}
