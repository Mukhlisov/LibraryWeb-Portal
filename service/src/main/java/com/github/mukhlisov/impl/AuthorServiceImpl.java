package com.github.mukhlisov.impl;

import java.util.List;
import java.util.Optional;

import com.github.mukhlisov.dto.AuthorDto;
import com.github.mukhlisov.Author;
import com.github.mukhlisov.AuthorService;
import com.github.mukhlisov.repository.AuthorRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepo authorRepo;

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepo.findById(id);
    }

    @Override
    public void updateAuthor(AuthorDto authorDto) {
        Author author = authorRepo.findById(authorDto.getId()).get();
        author.setFullName(authorDto.getFullName());
        authorRepo.save(author);
    }

    
    @Transactional
    @Override
    public void deleteById(Long id) {
        authorRepo.deleteRelationShips(id);
        authorRepo.deleteById(id);
    }

    @Override
    public Author saveAuthor(AuthorDto authorDto) {
        Author author = new Author(authorDto.getFullName());
        return authorRepo.save(author);
    }

    @Override
    public List<Author> findAllAuthors(String phrase) {
        return authorRepo.findByPhrase(phrase);
    }

    @Override
    public Page<Author> findPaginated(int page, int page_size) {
        Pageable pageable = PageRequest.of(page - 1, page_size);
        return authorRepo.findAll(pageable);
    }

    
}
