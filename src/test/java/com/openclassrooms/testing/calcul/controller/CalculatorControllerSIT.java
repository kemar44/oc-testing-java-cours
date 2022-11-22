package com.openclassrooms.testing.calcul.controller;

import static org.mockito.Mockito.*;
import static org.hamcrest.core.StringContains.*;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.openclassrooms.testing.calcul.domain.Calculator;
import com.openclassrooms.testing.calcul.service.CalculatorService;
import com.openclassrooms.testing.calcul.service.SolutionFormatter;


@WebMvcTest(controllers = {CalculatorController.class, CalculatorService.class})
@ExtendWith(SpringExtension.class)
public class CalculatorControllerSIT {

    @Inject
    private MockMvc mockMvc;

    @MockBean
    private SolutionFormatter solutionFormatter;

    @MockBean
    private Calculator calculator;

    @Test
    public void givenAUser_whenRequestIsMadeToAdd_thenASolutionSouldBeShown() throws Exception {
        //GIVEN
        when(calculator.add(2,3)).thenReturn(5);

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.post("/calculator")
        .param("leftArgument", "2")
        .param("rightArgument", "3")
        .param("calculationType", "ADDITION")
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
         .andExpect(MockMvcResultMatchers.content().string(containsString("id=\"solution\"")))
         .andExpect(MockMvcResultMatchers.content().string(containsString(">5</span>")));


        verify(calculator).add(2, 3);
        verify(solutionFormatter).format(5);
    }
}
