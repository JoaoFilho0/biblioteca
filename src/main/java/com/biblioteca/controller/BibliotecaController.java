package com.biblioteca.controller;

import com.biblioteca.dto.EmprestimoRequestDTO;
import com.biblioteca.dto.EmprestimoResponseDTO;
import com.biblioteca.dto.ItemRequestDTO;
import com.biblioteca.dto.ItemResponseDTO;
import com.biblioteca.dto.MultaResponseDTO;
import com.biblioteca.factory.ItemFactory;
import com.biblioteca.model.abstracts.ItemBiblioteca;
import com.biblioteca.model.interfaces.Relatorio;
import com.biblioteca.service.BibliotecaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/items")
public class BibliotecaController {

    private BibliotecaService bibliotecaService;
    private Relatorio relatorio;

    public BibliotecaController(BibliotecaService bibliotecaService, Relatorio relatorio) {
        this.bibliotecaService = bibliotecaService;
        this.relatorio = relatorio;
    }

    @PostMapping
    public ResponseEntity<ItemResponseDTO> criarItem(@RequestBody ItemRequestDTO dto) {
        ItemBiblioteca itemBiblioteca = ItemFactory.criarItem(dto);
        return ResponseEntity.ok(ItemResponseDTO.fromEntity(bibliotecaService.criarItem(itemBiblioteca)));
    }

    @GetMapping()
    public ResponseEntity<List<ItemResponseDTO>> listarItems() {
        return ResponseEntity.ok(this.bibliotecaService.listarItems().stream().map(ItemResponseDTO::fromEntity).toList());
    }

    @GetMapping("/mapa")
    public ResponseEntity<Map<Long, ItemResponseDTO>> listarItemsPorId() {
        Map<Long, ItemResponseDTO> itemsPorId = bibliotecaService.listarItemsPorId()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> ItemResponseDTO.fromEntity(entry.getValue())
                ));

        return ResponseEntity.ok(itemsPorId);
    }

    @GetMapping(value = "/emprestimo/relatorio", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> gerarRelatorioEmprestimos() {
        return ResponseEntity.ok(relatorio.gerarRelatorio());
    }

    @GetMapping("/multa/{itemId}")
    public ResponseEntity<MultaResponseDTO> calcularMulta(
            @PathVariable Long itemId,
            @RequestParam int diasAtraso
    ) {
        return ResponseEntity.ok(bibliotecaService.calcularMultaItem(itemId, diasAtraso));
    }

    @PostMapping("/emprestimo/{itemId}")
    public ResponseEntity<EmprestimoResponseDTO> emprestarItem(
            @PathVariable Long itemId,
            @RequestBody EmprestimoRequestDTO dto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(EmprestimoResponseDTO.fromEntity(bibliotecaService.emprestarItem(itemId, dto)));
    }

    @PatchMapping("/devolucao/{itemId}")
    public ResponseEntity<EmprestimoResponseDTO> devolverItem(@PathVariable Long itemId) {
        return ResponseEntity.ok(EmprestimoResponseDTO.fromEntity(bibliotecaService.devolverItem(itemId)));
    }
}
