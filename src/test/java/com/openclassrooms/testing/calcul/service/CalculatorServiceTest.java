package com.openclassrooms.testing.calcul.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.openclassrooms.testing.calcul.domain.Calculator;
import com.openclassrooms.testing.calcul.domain.model.CalculationModel;
import com.openclassrooms.testing.calcul.domain.model.CalculationType;

@ExtendWith(MockitoExtension.class)
public class CalculatorServiceTest {

	@Mock
	Calculator calculator;
	@Mock
	SolutionFormatter solutionFormatter;
	
	// Calculator IS CALLED BY CalculatorService
	CalculatorService classUnderTest;

	@BeforeEach
	public void setup(){
		classUnderTest = new CalculatorServiceImpl(calculator,solutionFormatter);
	}

	@Test
	public void calculate_shouldUseCalculator_forAddition() {
		//ARRANGE
		when(calculator.add(3,2)).thenReturn(5);

		//ACT
		final int result = classUnderTest.calculate(
				new CalculationModel(CalculationType.ADDITION, 3, 2)).getSolution();

		//ASSERT
		verify(calculator).add(3, 2);
		assertThat(result).isEqualTo(5);
	}

	@Test
	public void calculate_shouldUseCalculator_forAnyAddition() {
		//ARRANGE
		final Random r = new Random();
		when(calculator.add(any(Integer.class),any(Integer.class))).thenReturn(5);

		//ACT
		final int result = classUnderTest.calculate(
				new CalculationModel(CalculationType.ADDITION, r.nextInt(),r.nextInt())).getSolution();

		//ASSERT
		verify(calculator,times(1)).add(any(Integer.class),any(Integer.class));
		assertThat(result).isEqualTo(5);
	}

	@Test
	public void calculate_shouldFormatSolution_forAnAddition() {
		//ARRANGE
		final Random r = new Random();
		when(calculator.add(100000,3000)).thenReturn(103000);
		when(solutionFormatter.format(103000)).thenReturn("103 000");

		//ACT
		final String formattedResult = classUnderTest.calculate(new CalculationModel(CalculationType.ADDITION, 	100000, 3000)).getFormattedSolution();

		//ASSERT
		assertThat(formattedResult).isEqualTo("103 000");
	}

	@Test
	public void calculate_shouldUseCalculator_forSubstraction() {
		//ARRANGE
		when(calculator.sub(3,2)).thenReturn(1);

		//ACT
		final int result = classUnderTest.calculate(
				new CalculationModel(CalculationType.SUBTRACTION, 3, 2)).getSolution();

		//ASSERT
		verify(calculator).sub(3, 2);
		assertThat(result).isEqualTo(1);
	}

	@Test
	public void calculate_shouldUseCalculator_forMultiplication() {
		//ARRANGE
		when(calculator.multiply(3,2)).thenReturn(6);

		//ACT
		final int result = classUnderTest.calculate(
				new CalculationModel(CalculationType.MULTIPLICATION, 3, 2)).getSolution();

		//ASSERT
		verify(calculator).multiply(3, 2);
		assertThat(result).isEqualTo(6);
	}

	@Test
	public void calculate_shouldUseCalculator_forDivision() {
		//ARRANGE
		when(calculator.divide(6,3)).thenReturn(2);

		//ACT
		final int result = classUnderTest.calculate(
				new CalculationModel(CalculationType.DIVISION, 6, 3)).getSolution();

		//ASSERT
		verify(calculator).divide(6,3);
		assertThat(result).isEqualTo(2);
	}

	@Test
	public void calculate_shouldThrowIllegalArgumentException_forADivisionBy0() {
		//ARRANGE / GIVEN
		when(calculator.divide(1,0)).thenThrow(new IllegalArgumentException());

		//ACT / WHEN
		assertThrows(IllegalArgumentException.class, () -> classUnderTest.calculate(new CalculationModel(CalculationType.DIVISION, 1, 0)));

		//ASSERT / THEN
		verify(calculator, times(1)).divide(1,0);
	}
	
	// @Test
	// public void add_returnsTheSum_ofTwoNegativeNumbers() {
	// 	final int result = classUnderTest.calculate(
	// 			new CalculationModel(CalculationType.ADDITION, -1, 2))
	// 			.getSolution();

	// 	assertThat(result).isEqualTo(1);
	// }

	// @Test
	// public void add_returnsTheSum_ofZeroAndZero() {
	// 	final int result = classUnderTest.calculate(
	// 			new CalculationModel(CalculationType.ADDITION, 0, 0)).getSolution();
	// 	assertThat(result).isEqualTo(0);
	// }
}
