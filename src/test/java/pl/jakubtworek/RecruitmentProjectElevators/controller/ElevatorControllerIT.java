package pl.jakubtworek.RecruitmentProjectElevators.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.jakubtworek.RecruitmentProjectElevators.data.ElevatorsTest;
import pl.jakubtworek.RecruitmentProjectElevators.repository.ElevatorDAO;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ElevatorControllerIT {
    @MockBean
    private ElevatorDAO elevatorDAO;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setup() {
        ElevatorsTest elevatorsTest = new ElevatorsTest();
        when(elevatorDAO.findAll()).thenReturn(elevatorsTest.getElevators());
        when(elevatorDAO.findElevatorToMove()).thenReturn(elevatorsTest.getElevatorsToMove());
        when(elevatorDAO.findById(anyInt())).thenReturn(Optional.ofNullable(elevatorsTest.getElevators().get(0)));
    }

    @Test
    void shouldReturnUpdatedElevator() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .put("/elevators/update/1")
                        .param("floor", "4")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.numberOfFloor").value(4))
                .andExpect(jsonPath("$.numberOfClosestDestination").isEmpty());
    }

    @Test
    void shouldReturnElevatorToPickUp() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .put("/elevators/pickup")
                        .param("sourceFloor", "2")
                        .param("destinationFloor", "4")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.numberOfFloor").value(0))
                .andExpect(jsonPath("$.numberOfClosestDestination").value(2));
    }

    @Test
    void shouldThrowException_whenWrongFloor() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .put("/elevators/pickup")
                        .param("sourceFloor", "-5")
                        .param("destinationFloor", "4")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("There are only 10 floors"));
    }

    @Test
    void shouldReturnElevatorsToMove() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .put("/elevators/step")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].numberOfFloor").value(4))
                .andExpect(jsonPath("$[0].numberOfClosestDestination").value(5))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].numberOfFloor").value(6))
                .andExpect(jsonPath("$[1].numberOfClosestDestination").value(2));
    }

    @Test
    void shouldThrowException_whenNoElevatorsToMove() throws Exception {
        when(elevatorDAO.findElevatorToMove()).thenReturn(List.of());

        mvc.perform(MockMvcRequestBuilders
                        .put("/elevators/step")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("There are not elevators to move"));
    }

    @Test
    void shouldReturnAllElevators() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/elevators/status")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(16)));
    }

    @Test
    void shouldRestartAllElevators() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .put("/elevators/reset")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("All elevators were moved to 0 floor"));
    }
}