package controller;

import com.trabalhoinicio.apitarefas.model.Tarefa;
import repositorio.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaRepository tarefaRepository;

    // Criar tarefa
    @PostMapping
    public Tarefa criar(@RequestBody Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    // Listar todas
    @GetMapping
    public List<Tarefa> listar() {
        return tarefaRepository.findAll();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public Optional<Tarefa> buscarPorId(@PathVariable Long id) {
        return tarefaRepository.findById(id);
    }

    // Atualizar
    @PutMapping("/{id}")
    public Tarefa atualizar(@PathVariable Long id, @RequestBody Tarefa novaTarefa) {
        return tarefaRepository.findById(id).map(t -> {
            t.setNome(novaTarefa.getNome());
            t.setDataEntrega(novaTarefa.getDataEntrega());
            t.setResponsavel(novaTarefa.getResponsavel());
            return tarefaRepository.save(t);
        }).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }

    // Remover
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        tarefaRepository.deleteById(id);
    }
}
