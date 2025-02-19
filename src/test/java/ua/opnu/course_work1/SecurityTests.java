package ua.opnu.course_work1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void accessPublicEndpointWithoutAuthentication() throws Exception {
        mockMvc.perform(get("/public"))
                .andExpect(status().isOk());
    }

    @Test
    public void denyAccessToSecuredEndpointWithoutAuthentication() throws Exception {
        mockMvc.perform(get("/members"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser  // Використовуємо значення за замовчуванням: username="user", roles={"USER"}
    public void allowAccessToSecuredEndpointWithUserRole() throws Exception {
        mockMvc.perform(get("/members"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="admin", roles={"ADMIN"})
    public void denyAccessToSecuredEndpointWithIncorrectRole() throws Exception {
        mockMvc.perform(get("/members"))
                .andExpect(status().isForbidden());
    }
}
