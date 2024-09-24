package exercise.service;


import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    public List<BookDTO> getAll() {
        return bookRepository.findAll().stream()
                .map(o -> bookMapper.map(o))
                .toList();
    }

    public BookDTO findById(Long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id));
        return bookMapper.map(book);
    }

    public BookDTO create(BookCreateDTO dto) {
        var author = bookMapper.map(dto);
        bookRepository.save(author);
        return bookMapper.map(author);
    }

    public BookDTO update(BookUpdateDTO dto, Long id) {
        var author = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id));
        bookMapper.update(dto, author);
        bookRepository.save(author);
        return bookMapper.map(author);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
    // END
}
