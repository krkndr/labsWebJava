package kpi.labswebjava.web;

import kpi.labswebjava.AbstractIt;
import kpi.labswebjava.feature_toggle.FeatureToggleExtension;
import kpi.labswebjava.feature_toggle.FeatureToggles;
import kpi.labswebjava.feature_toggle.annotation.DisabledFeatureToggle;
import kpi.labswebjava.feature_toggle.annotation.EnabledFeatureToggle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@DisplayName("Cosmo Cats Controller IT")
@ExtendWith(FeatureToggleExtension.class)
class CosmoCatControllerIT extends AbstractIt {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisabledFeatureToggle(FeatureToggles.COSMO_CATS)
    void testDisabledGetCosmoCats() throws Exception {
        mockMvc.perform(get("/api/v1/cosmo-cats")).andExpect(status().isNotFound());
    }

    @Test
    @EnabledFeatureToggle(FeatureToggles.COSMO_CATS)
    void testGetCosmoCats() throws Exception {
        mockMvc.perform(get("/api/v1/cosmo-cats")).andExpect(status().isOk());
    }
}
