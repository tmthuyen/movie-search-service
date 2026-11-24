package com.thuyendev.search_service.security;

import com.thuyendev.search_service.controller.DemoApiController;
import com.thuyendev.search_service.controller.HealthController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {HealthController.class, DemoApiController.class})
@Import({SecurityConfig.class, RestAuthenticationEntryPoint.class, RestAccessDeniedHandler.class})
class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenAccessHealth_thenOk() throws Exception {
        mockMvc.perform(get("/health"))
                .andExpect(status().isOk());
    }

    @Test
    void whenAccessPublicApi_thenOk() throws Exception {
        mockMvc.perform(get("/api/v1/public/hello"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void whenAccessProtectedApiWithoutAuth_thenReturnJson401() throws Exception {
        mockMvc.perform(get("/api/v1/protected/data"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.status").value(401))
                .andExpect(jsonPath("$.error").value("Unauthorized"));
    }
}
