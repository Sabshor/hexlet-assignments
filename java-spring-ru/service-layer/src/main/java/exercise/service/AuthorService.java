package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorMapper authorMapper;
    // BEGIN
    public List<AuthorDTO> getAll() {
        var authors = authorRepository.findAll();
        return authors.stream()
                .map(authorMapper::map)
                .toList();
    }

    public AuthorDTO findById(Long id) {
        var author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id));
        return authorMapper.map(author);
    }

    public AuthorDTO create(AuthorCreateDTO dto) {
        var author = authorMapper.map(dto);
        authorRepository.save(author);
        return authorMapper.map(author);
    }

    public AuthorDTO update(AuthorUpdateDTO dto, Long id) {
        var author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id));
        authorMapper.update(dto, author);
        authorRepository.save(author);
        return authorMapper.map(author);
    }

    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
    // END
}