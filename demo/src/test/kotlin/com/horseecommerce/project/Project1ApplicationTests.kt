package com.horseecommerce.project


import com.horseecommerce.project.dtos.ProductDTO
import com.horseecommerce.project.model.Product.TypeProduct

import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity


import org.assertj.core.api.Assertions.assertThat
import org.springframework.test.annotation.DirtiesContext
import java.net.URI


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class Project1ApplicationTests {

	@Autowired
	lateinit var restTemplate: TestRestTemplate

	val basicURL: String = "/api/products"

	@Test
	fun contextLoads() {
	}

	@Test
	fun shoulReturnAllProducts(){
		val response: ResponseEntity<String> = restTemplate.getForEntity(basicURL, String::class.java)
		assertThat(response.statusCode).isEqualTo(HttpStatus.OK)

	}
	@Test
	fun shouldReturnTheProductByID(){													// esse _id ta no meu banco de dados local
		val response: ResponseEntity<String> = restTemplate.getForEntity("${basicURL}/64f06199f29d1424cf95860f", String::class.java)
		assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
	}

	@Test
	fun shouldReturnNotFoundException(){
		val response: ResponseEntity<String> = restTemplate.getForEntity("${basicURL}/2222", String::class.java)
		assertThat(response.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
	}

	@Test
	fun shouldCreateNewProductAndReturnThePath(){
		val produto = ProductDTO(name = "Pedro", price = 400, TypeProduct.Fast, quantity = 1)
		val response: ResponseEntity<String> = restTemplate.postForEntity(basicURL, produto, String::class.java)
		assertThat(response.statusCode).isEqualTo(HttpStatus.CREATED)

		val newProductLocation: URI? = response.headers.location
		val getResponse: ResponseEntity<String> = restTemplate.getForEntity(newProductLocation, String::class.java)
		assertThat(getResponse.statusCode).isEqualTo(HttpStatus.OK)



	}


}
