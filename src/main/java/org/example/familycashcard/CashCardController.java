package org.example.familycashcard;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cashcards")
class CashCardController {
    private final CashCardRepository cashCardRepository;

    CashCardController(CashCardRepository cashCardRepository) {
        this.cashCardRepository = cashCardRepository;
    }

    @GetMapping("/{id}")
    private ResponseEntity<CashCard> findById(@PathVariable Long id) {
        Optional<CashCard> cashCard = cashCardRepository.findById(id);
        return cashCard.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    private ResponseEntity<Void> create(@RequestBody CashCard cashCard, UriComponentsBuilder ucb){
        CashCard savedCashCard = cashCardRepository.save(cashCard);
        URI locationOfNewCashCard = ucb.path("cashcards/{id}")
                .buildAndExpand(savedCashCard.id())
                .toUri();
        return ResponseEntity.created(locationOfNewCashCard).build();
    }

    @GetMapping()
    private ResponseEntity<List<CashCard>> findAll(Pageable pageable){
        Page<CashCard> page = cashCardRepository.findAll(
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSortOr(Sort.by(Sort.Direction.ASC, "amount"))
                ));
        return ResponseEntity.ok(page.getContent());
    }
}
