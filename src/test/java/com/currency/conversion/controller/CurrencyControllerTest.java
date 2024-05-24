package com.currency.conversion.controller;
import com.currency.conversion.request.CurrencyConversionRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CurrencyControllerTest {
    @Autowired
    private MockMvc mockMvc;



    @InjectMocks
    private CurrencyController currencyController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testConvertCurrency() throws Exception {
        // Mock input
        CurrencyConversionRequest request = new CurrencyConversionRequest();
        request.setSrcCurrency("INR");
        request.setDestCurrency("GBP");
        request.setAmount(100);


        // Perform the request and assert the response
        mockMvc.perform(MockMvcRequestBuilders.post("/convertCurrency")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currency").value("GBP"))
                .andExpect(jsonPath("$.convertedAmount").value(96.0));
    }


    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
