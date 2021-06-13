
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
		}
		finally {
			//Fechar o drive			
			driver.quit();
		}
	}
	
	@Test
	public void devoRegistrarPaciente() {		
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
        
        //Acessar o registro paciente com ADM
        driver.get("http://localhost:8080/registros/paciente");				
		
		//Escrever Nome
		driver.findElement(By.id("input-nome")).sendKeys("Paciente Selenium");		
		//Digitar CPF
		driver.findElement(By.id("input-cpf")).sendKeys(""+ gerador.nextInt());		
		//Digitar Cor
		driver.findElement(By.id("input-cor")).sendKeys("Verde");			
		//Digitar Sexo
		driver.findElement(By.id("input-sexo")).sendKeys("Alguma coisa");	
		//Digitar DataNascimento
		driver.findElement(By.id("input-data-nascimento")).sendKeys("07091988");	
		//Digitar Senha
		driver.findElement(By.id("input-senha")).sendKeys("123456");
		//Confirmar a Senha
		driver.findElement(By.id("input-confirma-senha")).sendKeys("123456");		
		}
		finally {
			//Fechar o drive			
			driver.quit();
		}
	}
	
	@Test
	public void devoRegistrarExame() {		
		WebDriver driver = acessarAplicação();
		
		try {
		
		//Clickar em login para logar como Professor
		driver.findElement(By.id("btn-login")).click();		
		//Escrever CPF
		driver.findElement(By.id("input-cpf")).sendKeys("930");
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
        
        //Acessar o registro exame como Medico
        driver.get("http://localhost:8080/registros/exame");			
			
		//Digitar CPF
		driver.findElement(By.id("input-cpf")).sendKeys("192");
		//Selecionar CID
		WebElement selectElement = driver.findElement(By.id("input-cid"));
		Select selectObject = new Select(selectElement);
		selectObject.selectByValue("A00");			
		//Digitar Data
		driver.findElement(By.id("input-data-exame")).sendKeys("13062021");	
		//Selecionar Tipo Solicitante
		WebElement selectElement1 = driver.findElement(By.id("input-tipo-solicitante"));
		Select selectObject1 = new Select(selectElement1);
		selectObject1.selectByValue("professor");
		//Selecionar Tipo Exame
		WebElement selectElement2 = driver.findElement(By.id("input-tipo-exame"));
		Select selectObject2 = new Select(selectElement2);
		selectObject2.selectByValue("2");	
		//Digitar Recomendação
		driver.findElement(By.id("input-recomendacoes")).sendKeys("Esse teste é recomendado por Paulo Ricardo");		
		}
		finally {
			//Fechar o drive			
			driver.quit();
		}
	}
	
	@Test
	public void devoRegistrarLaudo() {		
		WebDriver driver = acessarAplicação();
		
		try {
		
		//Clickar em login para logar como Residente
		driver.findElement(By.id("btn-login")).click();		
		//Escrever CPF
		driver.findElement(By.id("input-cpf")).sendKeys("941");
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
        
        //Acessar o registro exame como Medico Residente
        driver.get("http://localhost:8080/registros/laudo");		
		
        //Digitar CRM
  		driver.findElement(By.id("input-crm")).sendKeys("768");
		//Digitar CPF
		driver.findElement(By.id("input-cpf")).sendKeys("192");				
		//Digitar Data
		driver.findElement(By.id("input-data-exame")).sendKeys("13062021");			
		//Digitar Laudo
		driver.findElement(By.id("input-text-area")).sendKeys("Esse Laudo é recomendado por Paulo Ricardo");		
		}
		finally {
			//Fechar o drive			
			driver.quit();
		}
	}	
}
