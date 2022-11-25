package com.openclassrooms.testing.calcul.acceptance;

import org.junit.runner.RunWith;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
 
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", plugin = { "pretty","html:target/html-cucumber-report" })
public class CucumberAIT {

    @Given("Un utilisateur utilise le Calculateur")
    public void a_student_is_using_the_Calculator(){

    }

    @When("{int} et {int} sont additionnes")
    public void and_are_added(Integer leftArgument, Integer rightArgument){

    }

    @Then("on montre {int} a l'utilisateur")
    public void the_user_is_shown(Integer expectedResult){

    }
    
}
