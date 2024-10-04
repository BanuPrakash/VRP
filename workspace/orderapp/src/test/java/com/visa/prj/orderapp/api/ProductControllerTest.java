package com.visa.prj.orderapp.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.visa.prj.orderapp.entity.Product;
import com.visa.prj.orderapp.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @MockBean
    private OrderService service;

    @Autowired
    private MockMvc mvc; // used for CRUD operations GET, POST,..

    // GET http://localhost:8080/api/products
    @Test
    public void testGetAllProducts() throws  Exception {
        // mock data
        List<Product> products = Arrays.asList(
                Product.builder().id(10).name("A").price(4567).build(),
                Product.builder().id(11).name("B").price(1234).build()
        );

        // mock
        when(service.getProducts()).thenReturn(products);

        // GET http://localhost:8080/api/products
        mvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].name", is("B")))
                .andExpect(jsonPath("$[0].name", is("A"))); // 200

        verify(service, times(1)).getProducts();
    }

    @Test
    public void addProductTest() throws Exception {
        // client code
        Product p = Product.builder().id(10).name("A").price(4567).quantity(100).build();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(p); // json from Product

        // mock
        when(service.addProduct(Mockito.any(Product.class))).thenReturn(Mockito.any(Product.class));

        // POST http://localhost:8080/api/products
        mvc.perform(post("/api/products")
                        .content(json)
                        .contentType("application/json"))
                .andExpect(status().isCreated());
    }

    @Test
    public void addProductExceptionTest() throws Exception {
        // client code
        Product p = Product.builder().id(10).price(-99).quantity(0).build();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(p); // json from Product

        // mock --> no need to mock
//        when(service.addProduct(Mockito.any(Product.class))).thenReturn(Mockito.any(Product.class));

        // POST http://localhost:8080/api/products
        mvc.perform(post("/api/products")
                        .content(json)
                        .contentType("application/json"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors", hasSize(3)))
                .andExpect(jsonPath("$.errors", hasItem("Name is required!!!")))
                .andExpect(jsonPath("$.errors", hasItem("Quantity 0 must be greater than 1")));

    verifyNoInteractions(service);
    }
}
