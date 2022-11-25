package com.openclassrooms.testing.calcul.acceptance;


import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.inject.Inject;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@AutoConfigureMockMvc
@SpringBootTest
public class CalculatorSteps {

    @Inject
    MockMvc mockMvc;

    private Integer lastLeftArgument;
    private Integer lastRightArgument;
    private String calculationType;

    @Given("Un utilisateur utilise le Calculateur")
    public void a_student_is_using_the_Calculator() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/calculator"))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @When("{int} et {int} sont additionnes")
    public void and_are_added(Integer leftArgument, Integer rightArgument) throws Exception {
        lastLeftArgument = leftArgument;
        lastRightArgument = rightArgument;
        calculationType = "ADDITION";
    }

    @Then("on montre {int} a l'utilisateur")
    public void the_user_is_shown(Integer expectedResult) throws Exception {
        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/calculator")
            .param("leftArgument", lastLeftArgument.toString())
            .param("rightArgument",lastRightArgument.toString())
            .param("CalculationType",calculationType))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
            .andReturn();

        assertThat(result.getResponse(),is(">" + expectedResult + "<"));//Problem Arg AssertThat def
    }
}
