package com.vmikh.todo;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.testng.Assert.assertEquals;

public class WebControllerTest {
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private Model model;
    @InjectMocks
    private WebController underTest;

    @BeforeClass
    public void setUp() {
        openMocks(this);
    }
    @Test
    public void shouldFindAllTasksAndReturnIndexPage() {
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
    }
}
