package kpi.labswebjava.service;

import kpi.labswebjava.dto.CosmoCat.CosmoCatDto;
import kpi.labswebjava.service.impl.CosmoCatServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Cosmo Cats Service Test")
@SpringBootTest(classes = {CosmoCatServiceImpl.class})
public class CosmoCatsServiceTest {

    @Autowired
    @Spy
    private CosmoCatService cosmoCatService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldGetCosmoCats() {
        List<CosmoCatDto> expectedCats = List.of(
                CosmoCatDto.builder().cat_id(UUID.randomUUID()).cat_name("Meteor Whiskers").email("meteor.whiskers@cosmo.cat").build(),
                CosmoCatDto.builder().cat_id(UUID.randomUUID()).cat_name("Comet Luna").email("comet.luna@cosmo.cat").build(),
                CosmoCatDto.builder().cat_id(UUID.randomUUID()).cat_name("Star Nebula").email("star.nebula@cosmo.cat").build()
        );
        Mockito.when(cosmoCatService.getCosmoCats()).thenReturn(expectedCats);
        assertEquals(expectedCats, cosmoCatService.getCosmoCats());
    }
}
