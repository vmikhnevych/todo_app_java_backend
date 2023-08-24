package com.vmikh.todo;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.testng.annotations.*;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.testng.Assert.assertEquals;

public class WebControllerTest {
    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private WebController underTest;

    @BeforeTest
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void shouldFindAllTasksAndReturnIndexPage() {
        Model model = mock(Model.class);
        String result = underTest.main(model);
        verify(taskRepository).findAll();
        verify(model).addAttribute("tasks", taskRepository.findAll());
        assertEquals(result, "main");
    }

    @Test
    public void shouldCreateTaskAndAddCurrentDateToName() {
        String name = "name";
        try(MockedStatic<TodoUtil> mockedUtil = Mockito.mockStatic(TodoUtil.class)) {
            String result = underTest.createTask(name);
            mockedUtil.when(() -> TodoUtil.addCurrentDateToName(eq(name))).thenReturn(name);
            mockedUtil.verify(() -> TodoUtil.addCurrentDateToName(eq(name)));
            verify(taskRepository).save(any());
            assertEquals(result, "redirect:/todo");
        }
        reset(taskRepository);
    }

    @Test
    public void shouldDeleteTask() {
        Long id = 1L;
        String result = underTest.deleteTask(id);
        verify(taskRepository).deleteById(eq(id));
        assertEquals(result, "redirect:/todo");
    }

    @Test
    public void shouldCompleteTask() {
        when(taskRepository.findById(any())).thenReturn(Optional.of(testTask()));
        Long id = 1L;
        String result = underTest.completeTask(id);
        verify(taskRepository).findById(eq(id));
        verify(taskRepository).save(any());
        assertEquals(result, "redirect:/todo");
        reset(taskRepository);
    }

    private Task testTask() {
        Task task = new Task();
        task.setId(1L);
        task.setName("name");
        task.setCompleted(false);
        return task;
    }
}
