package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;

import java.util.List;

public interface AuthorService extends AuthorRepository {
    List<Author> findAll();
}
