package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN
    private Task getTestTask() {
        return Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .create();
    };

    @Test
    public void testShow() throws Exception {
        var task = getTestTask();
        task.setTitle(faker.lorem().word());
        task.setDescription(faker.lorem().paragraph());
        taskRepository.save(task);

        var request = get("/tasks/{id}", task.getId());
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(om.writeValueAsString(task)));
    }

    @Test
    public void testShowNegative() throws Exception {
        var result = mockMvc.perform(get("/tasks/{id}", 0))
                .andExpect(status().isNotFound())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Task with id 0 not found");
    }

    @Test
    public void testCreate() throws Exception {
        var task = getTestTask();
        task.setTitle(faker.lorem().word());
        task.setDescription(faker.lorem().paragraph());
        var size = taskRepository.findAll().size();
        var request = post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(task));
        mockMvc.perform(request)
                .andExpect(status().isCreated());
        assertThat(taskRepository.findAll()).hasSize(size + 1);
    }

    @Test
    public void testUpdate() throws Exception {
        var task = getTestTask();
        task.setTitle(faker.lorem().word());
        task.setDescription(faker.lorem().paragraph());
        taskRepository.save(task);

        var data = new HashMap<>();
        data.put("title", "Updated task!!!");

        var request = put("/tasks/{id}", task.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(data));
        var result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
        // Тело это строка, в этом случае JSON
        //var body = result.getResponse().getContentAsString();

        var updatedTask = taskRepository.findById(task.getId());
        assertThat(updatedTask).isNotEmpty();
        assertThatJson("{\"title\":\"Updated task!!!\"}").isObject().containsEntry("title", updatedTask.get().getTitle());
        //assertThat(updatedTask.get().getTitle()).isEqualTo(("Updated task!!!"));
    }

    @Test
    public void testDelete() throws Exception {
        var task = getTestTask();
        task.setTitle(faker.lorem().word());
        task.setDescription(faker.lorem().paragraph());
        taskRepository.save(task);

        var request = delete("/tasks/{id}", task.getId());
        mockMvc.perform(request).andExpect(status().isOk());

        var deletedTask = taskRepository.findById(task.getId());
        assertThat(deletedTask).isEmpty();
    }
    // END
}
