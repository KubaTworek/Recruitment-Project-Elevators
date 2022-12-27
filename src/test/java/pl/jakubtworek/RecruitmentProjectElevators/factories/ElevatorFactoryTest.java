package pl.jakubtworek.RecruitmentProjectElevators.factories;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;

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