
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class UnicardioTest {
	
	Random gerador = new Random();
	
	public WebDriver acessarAplicação() {		
			
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.navigate().to("http://localhost:8080");	
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			return driver;
		}	

	@Test
	public void devoAcessarHomePage() {		
		
		
					
		acessarAplicação().quit();		
	}	
	
	@Test
	public void devoLogarComoPaciente() {		
		WebDriver driver = acessarAplicação();
		
		try {
		//Clickar em login
		driver.findElement(By.id("btn-login")).click();		
		//Escrever CPF
		driver.findElement(By.id("input-cpf")).sendKeys("192");
		//Escrever Senha
		driver.findElement(By.id("input-senha")).sendKeys("123456");
		//Clickar em Entrar
		driver.findElement(By.className("btn-login")).click();	
		}
		finally {
			//Fechar o drive			
			driver.quit();
		}
	}
	
	@Test
	public void devoLogarComoAdm() {		
		WebDriver driver = acessarAplicação();
		try {
		//Clickar em login
		driver.findElement(By.id("btn-login")).click();		
		//Escrever CPF
		driver.findElement(By.id("input-cpf")).sendKeys("123456");
		//Escrever Senha
		driver.findElement(By.id("input-senha")).sendKeys("123456");
		//Clickar em Entrar
		driver.findElement(By.className("btn-login")).click();
		}
		finally {
			//Fechar o drive			
			driver.quit();
		}
	}	
	
	@Test
	public void devoRegistrarMedico() {		
		WebDriver driver = acessarAplicação();
		
		try {
		
		//Clickar em login
		driver.findElement(By.id("btn-login")).click();		
		//Escrever CPF
		driver.findElement(By.id("input-cpf")).sendKeys("123456");
		//Escrever Senha
		driver.findElement(By.id("input-senha")).sendKeys("123456");
		//Clickar em Entrar
		driver.findElement(By.className("btn-login")).click();	
		
		
		//Abrir uma Nova Aba
		try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_T);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_T);
			} catch (AWTException ex) {
				throw new WebDriverException("Erro ao digitar CTRL + T", ex);
			}

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windowHandles.get(1));
        
        //Acessar o registro medico com ADM
        driver.get("http://localhost:8080/registros/medico");				
		
		//Escrever Nome
		driver.findElement(By.id("input-nome")).sendKeys("Medico Selenium");		
		//Digitar CPF
		driver.findElement(By.id("input-cpf")).sendKeys(""+ gerador.nextInt());		
		//Digitar CRM
		driver.findElement(By.id("input-crm")).sendKeys(""+gerador.nextInt());			
		//Selecionar Tipo Medico
		WebElement selectElement = driver.findElement(By.id("input-tipo"));
		Select selectObject = new Select(selectElement);
		selectObject.selectByValue("profissional");		
		//Digitar Senha
		driver.findElement(By.id("input-senha")).sendKeys("123456");	
		//Confirmar a Senha
		driver.findElement(By.id("input-confirma-senha")).sendKeys("123456");
		//Clickar em Registrar	
		}
		finally {
			//Fechar o drive			
			driver.quit();
		}
	}
}
