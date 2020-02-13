package br.com.magazineluiza.test.magalu.stepdefinitions;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebDriver;

import br.com.magazineluiza.test.magalu.pages.HomePage;
import br.com.magazineluiza.test.magalu.pages.ResultadoBuscaPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BuscarProdutosStepDefinitions {

	private final BaseStepDefinitions context;
	private final WebDriver driver;
	private final HomePage homePage;
	private ResultadoBuscaPage resultadoBuscaPage;

	private String[] termosBuscados = null;

	public BuscarProdutosStepDefinitions(final BaseStepDefinitions context) {
		this.context = context;
		driver = this.context.driver;
		homePage = new HomePage(driver);
	}

	@Given("o cliente acessou a loja virtual")
	public void oClienteAcessouALojaVirtual() {
		homePage.acessarPaginaInicial();
	}

	@When("buscou pelo termo {string}")
	public void buscouPeloTermo(String termo) {
		homePage.informarTermoBusca(termo);
		resultadoBuscaPage = homePage.clicarEmBuscar();
		termosBuscados = termo.split("\\s+");
	}

	@Then("o sistema deve listar todos os produtos relacionados ao termo")
	public void oSistemaDeveListarTodosOsProdutosRelacionadosAoTermo() {
		List<String> resultados = resultadoBuscaPage.itensListados();
		for (String termo : termosBuscados) {
			for (String itemResultado : resultados) {
				assertTrue(itemResultado.toLowerCase().contains(termo.toLowerCase()));
			}
		}
	}
 }
