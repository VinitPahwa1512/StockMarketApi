package com.vinit.stockmarket.stockmarket.controller;

import java.net.URI;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vinit.stockmarket.stockmarket.exception.handler.StockNotFoundException;
import com.vinit.stockmarket.stockmarket.model.Stock;
import com.vinit.stockmarket.stockmarket.repository.StockJpaRepository;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class StockResourceController {

	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}

	@Autowired
	private StockJpaRepository stockJpaRepository;
	 
	@Operation(summary = "Get all Stock Information")
	@GetMapping("/GET/api/stocks")
	public ResponseEntity<Map<String, Object>> getAllStocks(
			// @RequestParam(required = false) String title,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size,
			@RequestParam(defaultValue = "id,desc") String[] sort) {

		try {
			List<Stock> orders = new ArrayList<Stock>();

			/*
			 * if (sort[0].contains(",")) { // will sort more than 2 fields //
			 * sortOrder="field, direction" for (String sortOrder : sort) { String[] _sort =
			 * sortOrder.split(","); orders.add(new Order(getSortDirection(_sort[1]),
			 * _sort[0])); } } else { // sort=[field, direction] orders.add(new
			 * Order(getSortDirection(sort[1]), sort[0])); }
			 */

			List<Stock> tutorials = new ArrayList<Stock>();
			Pageable pagingSort = PageRequest.of(page, size);

			Page<Stock> pageTuts;
			// if (title == null)
			pageTuts = stockJpaRepository.findAll(pagingSort);
			// else
			// pageTuts = tutorialRepository.findByTitleContaining(title, pagingSort);

			tutorials = pageTuts.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("tutorials", tutorials);
			response.put("currentPage", pageTuts.getNumber());
			response.put("totalItems", pageTuts.getTotalElements());
			response.put("totalPages", pageTuts.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "Get Stock Information By Id")
	@GetMapping("/GET/api/stocks/{id}")
	public ResponseEntity<Stock> getStockById(@PathVariable("id") Long id) {
		Optional<Stock> tutorialData = stockJpaRepository.findById(id);

		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/*
	 * @GetMapping("/GET/api/stocks/{id}") public Stock getStockById(@PathVariable
	 * long id){ return stockJpaRepository.findById((int) id).get(); //return
	 * todoService.findById(id); }
	 */

	// DELETE /users/{username}/todos/{id}
	@Operation(summary = "Delete Stock Information By Id")
	@DeleteMapping("/DELETE/api/stocks/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			stockJpaRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Edit/Update a Todo
	// PUT /users/{user_name}/todos/{todo_id}
	@Operation(summary = "Updating the Stock Information")
	@PutMapping("/PATCH/api/stocks/{id}")
	public ResponseEntity<Stock> updateStock(@PathVariable long id, @RequestBody Stock stock) {
		
		Optional<Stock> existingStock = stockJpaRepository.findById(id);
		
		
		

        if (!existingStock.isPresent()) {
            throw new StockNotFoundException("Stock [id: " + id + "] not found.");
        }
        
        if(!((existingStock.get().getName()!=null && stock.getName()!=null) && (existingStock.get().getName().equalsIgnoreCase(stock.getName()))))
        {
        	  throw new StockNotFoundException("Stock [Name: " + existingStock.get().getName() + "] not is mismatched.");
        }

		stock.setId(id);
		
		if(stock.getLastupdate()==null)
		{
			stock.setLastupdate(new Timestamp(System.currentTimeMillis()));
		}

		Stock todoUpdated = stockJpaRepository.save(stock);

		return new ResponseEntity<Stock>(stock, HttpStatus.OK);
	}

	@Operation(summary = "Creating New Stock Information")
	@PostMapping("/POST/api/stocks")
	public ResponseEntity<Void> createStock(@RequestBody Stock stock) {

		// stock.setUsername(username);

		Stock createdTodo = stockJpaRepository.save(stock);

		// Location
		// Get current resource url
		/// {id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTodo.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}
}
