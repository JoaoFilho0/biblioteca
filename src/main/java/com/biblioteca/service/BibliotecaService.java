package com.biblioteca.service;

import com.biblioteca.dto.EmprestimoRequestDTO;
import com.biblioteca.dto.MultaResponseDTO;
import com.biblioteca.model.Emprestimo;
import com.biblioteca.model.Livro;
import com.biblioteca.model.Revista;
import com.biblioteca.model.StatusEmprestimo;
import com.biblioteca.model.abstracts.ItemBiblioteca;
import com.biblioteca.model.exceptions.EmprestimoNaoEncontradoException;
import com.biblioteca.model.exceptions.ItemJaEmprestadoException;
import com.biblioteca.model.exceptions.ItemNaoEncontradoException;
import com.biblioteca.model.exceptions.UsuarioInvalidoException;
import com.biblioteca.model.interfaces.CalcularMulta;
import com.biblioteca.model.interfaces.Relatorio;
import com.biblioteca.repository.EmprestimoRepository;
import com.biblioteca.repository.ItemBibliotecaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BibliotecaService implements Relatorio {
    private static final Collection<StatusEmprestimo> STATUS_EMPRESTIMO_ATIVO = List.of(
            StatusEmprestimo.EMPRESTADO
    );

    private ItemBibliotecaRepository itemBibliotecaRepository;
    private EmprestimoRepository emprestimoRepository;

    public BibliotecaService(ItemBibliotecaRepository itemBibliotecaRepository,
                             EmprestimoRepository emprestimoRepository) {
        this.itemBibliotecaRepository = itemBibliotecaRepository;
        this.emprestimoRepository = emprestimoRepository;
    }

    public ItemBiblioteca criarItem(ItemBiblioteca novoItem) {
        return this.itemBibliotecaRepository.save(novoItem);
    }

    public List<ItemBiblioteca> listarItems() {
        return this.itemBibliotecaRepository.findAll();
    }

    public Map<Long, ItemBiblioteca> listarItemsPorId() {
        Map<Long, ItemBiblioteca> itemsPorId = new HashMap<>();

        for (ItemBiblioteca item : itemBibliotecaRepository.findAll()) {
            itemsPorId.put(item.getId(), item);
        }

        return itemsPorId;
    }

    @Transactional
    public Emprestimo emprestarItem(Long itemId, EmprestimoRequestDTO dto) {
        validarDadosEmprestimo(dto);

        ItemBiblioteca item = itemBibliotecaRepository.findById(itemId)
                .orElseThrow(() -> new ItemNaoEncontradoException("Item com ID " + itemId + " não foi encontrado."));

        if (emprestimoRepository.existsByItemIdAndStatusIn(itemId, STATUS_EMPRESTIMO_ATIVO)) {
            throw new ItemJaEmprestadoException("Item com ID " + itemId + " já está emprestado.");
        }

        LocalDate hoje = LocalDate.now();

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setItem(item);
        emprestimo.setLeitor(dto.leitor());
        emprestimo.setDataEmprestimo(hoje);
        emprestimo.setDataPrevistaDevolucao(hoje.plusDays(dto.diasParaDevolucao()));
        emprestimo.setStatus(StatusEmprestimo.EMPRESTADO);

        return emprestimoRepository.save(emprestimo);
    }

    @Transactional
    public Emprestimo devolverItem(Long itemId) {
        if (!itemBibliotecaRepository.existsById(itemId)) {
            throw new ItemNaoEncontradoException("Item com ID " + itemId + " não foi encontrado.");
        }

        Emprestimo emprestimo = emprestimoRepository.findFirstByItemIdAndStatusIn(itemId, STATUS_EMPRESTIMO_ATIVO)
                .orElseThrow(() -> new EmprestimoNaoEncontradoException(
                        "Não existe empréstimo ativo para o item com ID " + itemId + "."
                ));

        emprestimo.setDataDevolucao(LocalDate.now());
        emprestimo.setStatus(StatusEmprestimo.DISPONIVEL);

        return emprestimoRepository.save(emprestimo);
    }

    private void validarDadosEmprestimo(EmprestimoRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Dados do empréstimo são obrigatórios.");
        }

        if (dto.leitor() == null || dto.leitor().isBlank()) {
            throw new UsuarioInvalidoException("Leitor é obrigatório.");
        }

        if (dto.diasParaDevolucao() == null || dto.diasParaDevolucao() <= 0) {
            throw new IllegalArgumentException("Dias para devolução deve ser maior que zero.");
        }
    }

    public MultaResponseDTO calcularMultaItem(Long itemId, int diasAtraso) {
        if (diasAtraso < 0) {
            throw new IllegalArgumentException("Dias de atraso não pode ser negativo.");
        }

        ItemBiblioteca item = itemBibliotecaRepository.findById(itemId)
                .orElseThrow(() -> new ItemNaoEncontradoException("Item com ID " + itemId + " não foi encontrado."));

        if (!(item instanceof CalcularMulta itemComMulta)) {
            throw new IllegalArgumentException("Item do tipo " + item.getTipo() + " não possui cálculo de multa.");
        }

        return new MultaResponseDTO(
                item.getId(),
                item.getTitulo(),
                item.getTipo(),
                diasAtraso,
                itemComMulta.calcularMulta(diasAtraso)
        );
    }

    @Override
    public String gerarRelatorio() {
        List<Emprestimo> emprestimos = emprestimoRepository.findAll()
                .stream()
                .filter(emprestimo -> emprestimo.getItem() instanceof Livro || emprestimo.getItem() instanceof Revista)
                .toList();

        if (emprestimos.isEmpty()) {
            return "Nenhum empréstimo de livro ou revista encontrado.";
        }

        StringBuilder relatorio = new StringBuilder("Relatório de empréstimos de livros e revistas:\n");

        for (Emprestimo emprestimo : emprestimos) {
            ItemBiblioteca item = emprestimo.getItem();

            relatorio.append("ID do empréstimo: ").append(emprestimo.getId())
                    .append(" | Item: ").append(item.getTitulo())
                    .append(" | Tipo: ").append(item.getTipo())
                    .append(" | Leitor: ").append(emprestimo.getLeitor())
                    .append(" | Empréstimo: ").append(emprestimo.getDataEmprestimo())
                    .append(" | Devolução prevista: ").append(emprestimo.getDataPrevistaDevolucao())
                    .append(" | Devolução realizada: ").append(emprestimo.getDataDevolucao())
                    .append(" | Status: ").append(emprestimo.getStatus())
                    .append("\n");
        }

        return relatorio.toString();
    }
}
