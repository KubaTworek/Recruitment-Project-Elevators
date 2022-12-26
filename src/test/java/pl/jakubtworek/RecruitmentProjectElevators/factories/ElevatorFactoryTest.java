package pl.jakubtworek.RecruitmentProjectElevators.factories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;

class ElevatorFactoryTest {
    private ElevatorFactory elevatorFactory;

    @BeforeEach
    void setup() {
        elevatorFactory = new ElevatorFactory();
    }

    @Test
    void shouldReturnPickUpUpdateFormula_whenTwoArguments() {
        // when
        ElevatorFormula formula = elevatorFactory.create(anyInt(), anyBoolean());

        // then
        assertEquals(PickupUpdateFormula.class, formula.getClass());
    }

    @Test
    void shouldReturnAdminUpdateFormula_whenOneArguments() {
        // when
        ElevatorFormula formula = elevatorFactory.create(anyInt());

        // then
        assertEquals(AdminUpdateFormula.class, formula.getClass());
    }

    @Test
    void shouldReturnStepUpdateFormula_whenZeroArguments() {
        // when
        ElevatorFormula formula = elevatorFactory.create();

        // then
        assertEquals(StepUpdateFormula.class, formula.getClass());
    }
}